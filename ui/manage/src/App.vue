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

  </div>
</template>


<script lang="ts" setup>
import { ref } from "vue";

const msg = ref("Hello Vue");
const imgUrl = ref("https://vitejs.dev/logo.svg");
const cls = ref("red");
const count = ref(0);

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


