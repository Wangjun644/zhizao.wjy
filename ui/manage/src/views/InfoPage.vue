<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <el-card shadow="hover" :body-style="{ padding: '0px' }">
        <div class="card-content">
          <div class="card-left">
            开机率
          </div>
          <div class="card-right">
            <div class="card-num">{{ open }}%</div>

          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card shadow="hover" :body-style="{ padding: '0px' }">
        <div class="card-content">
          <div class="card-left">
            故障率
          </div>
          <div class="card-right">
            <div class="card-num">{{ fault }}%</div>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card shadow="hover" :body-style="{ padding: '0px' }">
        <div class="card-content">
          <div class="card-left">
            运行率
          </div>
          <div class="card-right">
            <div class="card-num">{{ run }}%</div>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card shadow="hover" :body-style="{ padding: '0px' }">
        <div class="card-content">
          <div class="card-left">
            综合率
          </div>
          <div class="card-right">
            <div class="card-num">{{ general }}%</div>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
  <el-row :gutter="20">
    <el-col :span="12">
      <h3>工厂订单信息</h3>
      <el-card class="cav-info" shadow="hover" :body-style="{ padding: '0px' }" id="orderPie"></el-card>
    </el-col>
    <el-col :span="12">
      <h3>工厂订单信息</h3>
      <el-card class="cav-info" shadow="hover" :body-style="{ padding: '0px' }" id="orderBar"></el-card>
    </el-col>
  </el-row>

</template>
<script lang="ts" setup>
// import { ref, reactive, getCurrentInstance } from "vue";
import {ref, reactive, onMounted} from "vue";
import * as echarts from "echarts";
import { Mic, Document, User, Headset } from "@element-plus/icons-vue";
import { HttpManager } from "@/api/index";

// const { proxy } = getCurrentInstance();
const open = ref(0);
const fault = ref(0);
const run = ref(0);
const general = ref(0);

onMounted(async () =>{
  //
  const result = (await HttpManager.infoIndex()) as ResponseBody;
  console.log(result)
})

const orderPie = reactive({
  series: [
    {
      type: "pie",
      data: [
        {
          value: 0,
          name: "男",
        },
        {
          value: 0,
          name: "女",
        },
      ],
    },
  ],
});
const orderBar = reactive({
  xAxis: {
    type: "category",
    data: ["华语", "粤语", "欧美", "日韩", "BGM", "轻音乐", "乐器"],
  },
  yAxis: {
    type: "value",
  },
  series: [
    {
      data: [0, 0, 0, 0, 0, 0, 0],
      type: "bar",
      barWidth: "20%",
    },
  ],
});


//1.获取所有用户信息插入echarts
//2、获取所有歌单
//3 获取所有歌手
//4 获取所有歌曲

</script>

<style scoped>
.card-content {
  display: flex;
  align-items: center;
  justify-content: space-around;
  height: 100px;
  padding-left: 20%;
  text-align: center;
}

.card-left {
  display: flex;
  font-size: 2rem;
}

.card-right {
  flex: 1;
  font-size: 14px;
}

.card-num {
  font-size: 30px;
  font-weight: bold;
}

h3 {
  margin: 10px 0;
  text-align: center;
}
.cav-info {
  border-radius: 6px;
  overflow: hidden;
  height: 250px;
  background-color: white;
}
</style>
