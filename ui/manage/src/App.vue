<template>
  <div id="app">

    <section>
      <h2>1. 文本插值 {{  msg }}</h2>
    </section>

    <section>
      <h2>2. 属性绑定  v-bind: </h2>
      <img v-bind:src="imgUrl" alt="图片" style="width:100px"/>
      <div :class="cls">动态class</div>
    </section>

    <section>
      <h2>3. 事件绑定 v-on:@click</h2>
      <p>计数器: {{ count }}</p>
      <button @click="add">+1</button>
      <button @click="reduce">-1</button>
    </section>

    <!-- 4. v-model 双向绑定 最常用 绑定value属性-->
    <section>
      <h2>4. v-model 双向绑定</h2>
      <input v-model="inputVal" placeholder="请输入内容" />
      <p>输入内容：{{ inputVal }}</p >
    </section>

    <!-- 5. 条件渲染 v-if / v-show -->
    <section>
      <h2>5. 条件渲染</h2>
      <button @click="isShow = !isShow">切换显示</button>
      <p v-if="isShow">v-if 控制显示隐藏（销毁DOM）</p >
      <p v-show="isShow">v-show 控制显示隐藏（display）</p >
    </section>

    <!-- 6. 列表渲染 v-for -->
    <section>
      <h2>6. 列表渲染 v-for</h2>
      <ul>
        <li v-for="item in list" :key="item.id">
            {{ item.id }} - {{ item.name }}
        </li>
      </ul>
    </section>

  </div>
</template>


<script lang="ts" setup>
import { ref } from "vue";

const msg = ref("Hello Vue");
const imgUrl = ref("https://vitejs.dev/logo.svg");
const cls = ref("red");
const count = ref(0);
const inputVal = ref("");
const isShow = ref(false);

const list = ref([
  { id: 1, name: 'Vue2' },
  { id: 2, name: "Vue3" },
  { id: 3, name: "React" },
]);

const add = () => count.value++;

function reduce() {
  count.value--;
}

</script>
<style>
.container {
  width: 800px;
  height: 80vh;
  margin: 0 30px;
  overflow-y: auto;
}

.red{
  color: red;
  font-weight: bold;
}

button{
  margin: 0 5px;
  padding: 4px 10px;
}

input{
  width: 300px;
  padding: 5px;
}
</style>


