<script setup lang="ts">
</script>
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
        <el-form-item prop="rePassword">
          <el-input type="password" placeholder="rePassword" v-model="ruleForm.rePassword"></el-input>
        </el-form-item>
        <el-form-item prop="userRealName">
          <el-input type="userRealName" placeholder="userRealName" v-model="ruleForm.userRealName"></el-input>
        </el-form-item>
        <el-form-item prop="factoryName">
          <el-input type="factoryName" placeholder="factoryName" v-model="ruleForm.factoryName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="submitForm(formEl)">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
  </template>


<script lang="ts" setup>
import {getCurrentInstance, reactive, ref} from "vue";
import {HttpManager} from "@/api/index";
import {RouterName} from "@/enums";
import {ElMessage, ElMessageBox, FormInstance} from "element-plus";
import mixin from "@/mixins/mixin";
import router from "@/router";
  //
  const { proxy } = getCurrentInstance();
  const { routerManager } = mixin();

  const musicName = ref("智能制造");
  const ruleForm = reactive({
    username: "",
    password: "",
    rePassword: "",
    factoryName: "",
    userRealName: "",
  });
  const rules = reactive({
    username: [{ required: true, message: "请输入用户名", trigger: "blur"}],
    password: [{ required: true, message: "请输入密码", trigger: "blur"}],
    userRealName: [{ required: true, message: "请输入真实姓名", trigger: "blur"}],
    rePassword: [{ required: true, message: "请输入确认密码", trigger: "blur"}],
    factoryName: [{ required: true, message: "请输入工厂名称", trigger: "blur"}]
  });
  const formEl = ref<FormInstance>()

async function submitForm(formEl:FormInstance | undefined) {
  if (!formEl) return
  let canrun = true
  // //
  // await formEl.validate((valid, fields) => {
  //   if (valid) {
  //
  //     console.log('submit!')
  //   } else {
  //
  //     console.log('error submit!', fields)
  //     canrun = false
  //   }
  // })

  if (canrun) {
    let userName = ruleForm.username;
    let userPasswd = ruleForm.password;
    let rePassword = ruleForm.rePassword;
    let userRealName = ruleForm.userRealName;
    let factoryName = ruleForm.factoryName;
    if(rePassword != userPasswd){
      ElMessage({
        showClose: true,
        message: '两次密码不一致',
        type: 'warning',
      })
      return
    }
    //2、
    // const result = (await HttpManager.userLogin({userName, userPasswd})) as ResponseBody;
    const result = (await HttpManager.userRegister({userName, userPasswd, userRealName, factoryName})) as ResponseBody;
    console.log(result.data)
    console.log(result.message)
    //3
    if (result.code == "200") {
      //3.1
      await ElMessageBox.alert('注册成功', '提示', {
        confirmButtonText: 'OK',
        callback: () => {
          routerManager(RouterName.SignIn, {path: RouterName.SignIn})
        },
      })
    } else {
      //3.2
      ElMessage({
        showClose: true,
        message: result.message,
        type: 'warning',
      })
    }
  }

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
