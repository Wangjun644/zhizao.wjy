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
  open.value = result.data.open
  fault.value = result.data.fault
  run.value = result.data.run
  general.value = result.data.general



  //
  let orderPieDOM = document.getElementById('orderPie');
  //
  let orderPieChart = echarts.init(orderPieDOM);
  //
  orderPieChart.setOption(orderPie)

  //
  let orderBarDOM = document.getElementById('orderBar');
  //
  let orderBarChart = echarts.init(orderBarDOM);
  //
  orderBarChart.setOption(orderBar)
})

const orderPie = reactive({
  tooltip: {
    trigger: 'item'
  },
  legend: {
    top: '5%',
    left: 'center'
  },
  series: [{
      name: 'Access From',
      type: "pie",
      radius: ['40%', '70%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 10,
        borderColor: '#fff',
        borderWidth: 2
      },
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: 40,
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: [
        {
          value: 5,
          name: "待接单",
        },
        {
          value: 2,
          name: "生产中",
        },
        {
          value: 3,
          name: "已结单",
        },
        {
          value: 10,
          name: "已排产",
        },
        {
          value: 1,
          name: "已完成",
        },
      ]
    }
  ]
})

const orderBar = reactive({

  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: [{
    type: "category",
    data: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
    axisTick: {
      alignWithLabel: true
    }
  }],
  yAxis: [{
    type: "value",
  }],
  series: [
    {
      data: [0, 10, 40, 20, 80, 70, 60, 70, 10, 50, 0, 20],
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
