<template>
  <div class="login-container">
    <div class="title">
      {{ musicName }}
    </div>
    <div class="login">
      <el-form :model="ruleForm" :rules="rules" ref="formEl">
        <el-form-item prop="username">
          <el-input v-model="ruleForm.username" placeholder="username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" placeholder="password" v-model="ruleForm.password" @keyup.enter="submitForm"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(formEl)">登录</el-button>
          <el-button @click="goToRegister">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
  import { defineComponent, getCurrentInstance, ref, reactive } from "vue";
  import mixin from "@/mixins/mixin";
  import { HttpManager } from "@/api/index";
  import { RouterName, MUSICNAME } from "@/enums";
  import {ElMessage, FormInstance} from "element-plus";
  import router from "@/router";
  import {useRouter} from "vue-router";

  //当前Vue组件实例
  const { proxy } = getCurrentInstance();
  const { routerManager } = mixin();

  const musicName = ref("智能制造");

  const ruleForm = reactive({
    username: "admin",
    password: "123",
  });
  const rules = reactive({
    username: [{required: true, message: "请输入用户名", trigger: "blur"}],
    password: [{required: true, message: "请输入密码", trigger: "blur"}],
  });

  const formEl = ref(null)

  async function submitForm(formEl: FormInstance | undefined ) {
    if (!formEl) return
    let canrun = true
    //1、校验表单
    await formEl.validate((valid, fields) => {
      if (valid) {
        //表单验证通过
        console.log('submit!')
      } else {
        //表单验证失败
        console.log('error submit!', fields)
        canrun = false
      }
    })

    if (canrun) {
      let userName = ruleForm.username;
      let userPasswd = ruleForm.password;
      //2、发起登陆请求
      const result = (await HttpManager.userLogin({userName, userPasswd})) as ResponseBody;
      console.log(result.data)
      console.log(result.message)
      //3、根据后端返回的数据，提示用户。
      if (result.code == "200") {
        //3.1登陆成功
        routerManager(RouterName.Info, {path: RouterName.Info})
      } else {
        //3.2登陆失败，消息提示
        ElMessage({
          showClose: true,
          message: result.message,
          type: 'warning',
        })
      }
    }




        // const result = (await HttpManager.adminLogin({name,password})) as resposeBody;
        //
        // (proxy as any).$message({
        //   message:result.message,
        //   type: result.success ? 'success' : 'error'
        // })
        //
        // if(result.success){
        //   if(true){
        //
        //
        //     routerManager(RouterName.Info,{path: RouterName.Info})
        //   }
        // }



  }
  function goToRegister() {              // ← 移到最外层，和 submitForm 同级
    router.push('/register')
    // 或者：router.push(RouterName.Register)
  }






</script>

<style scoped>
.login-container {
  position: relative;
  background: url("../assets/images/background.jpg");
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 100%;
}

.title {
  position: absolute;
  top: 50%;
  width: 100%;
  margin-top: -230px;
  text-align: center;
  font-size: 30px;
  font-weight: 600;
  color: #fff;
}

.login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 300px;
  margin: -150px 0 0 -190px;
  padding: 40px;
  border-radius: 5px;
  background: #fff;
}

.login-btn {
  width: 100%;
}
</style>
