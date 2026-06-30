<template>
  <el-breadcrumb class="crumbs" separator="/">
    <el-breadcrumb-item v-for="item in breadcrumbList" :key="item.name" :to="{ path: item.path, query: item.query }">
      {{ item.name }}
    </el-breadcrumb-item>
  </el-breadcrumb>

  <div class="container">
    <div class="handle-box" style="width: 60%">
      <el-button @click="deleteAll">批量删除</el-button>
      <el-input v-model="queryForm.productNum" placeholder="产品编号"></el-input>
      <el-input v-model="queryForm.productName" placeholder="产品名称"></el-input>
      <el-button type="primary" @click="getData">查询</el-button>
      <el-button type="info" @click="centerDialogVisible = true">添加</el-button>
    </div>
    <el-table height="550px" border size="small" :data="tableData" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40" align="center"></el-table-column>
      <el-table-column label="ID" prop="id" width="50" align="center"></el-table-column>
      <el-table-column label="有效标识" width="70" prop="flag">
        <template v-slot="scope">
          <p v-if="scope.row.flag == 0">
          有效
          </p>
          <p v-if="scope.row.flag == 1">
          无效
          </p>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime"></el-table-column>
      <el-table-column label="创建人ID" width="50" prop="createUserid"></el-table-column>
      <el-table-column label="修改时间" prop="updateTime"></el-table-column>
      <el-table-column label="修改人ID" width="50" prop="updateUserid"></el-table-column>
      <el-table-column label="产品编号" prop="productNum"></el-table-column>
      <el-table-column label="产品名称" prop="productName"></el-table-column>
      <el-table-column label="产品图片" prop="productImgUrl">
        <template v-slot="scope">
          <img :src="HttpManager.attachImageUrl(scope.row.productImgUrl)" style = "width: 100%" />
        </template>
      </el-table-column>
      <el-table-column label="工厂ID" width="60" prop="factoryId"></el-table-column>
      <el-table-column label="操作" width="90" align="center">
        <template v-slot="scope">
          <el-button @click="editRow(scope.row)">编辑</el-button>
          <el-button type="danger" @click="deleteRow(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        size="small"
        :current-page="page.currentPage"
        :page-size="page.pageSize"
        layout="prev, pager, next"
        :total="page.total"
        @current-change="handleCurrentChange"
    />
  </div>

  <!--添加产品-->
  <el-dialog title="添加产品" v-model="centerDialogVisible">
    <el-form id="add-product" label-width="80px" :model="productForm">

      <el-form-item label="有效标识" >
        <el-radio-group v-model="productForm.flag"  >
          <el-radio name="flag" :label="0">有效</el-radio>
          <el-radio name="flag" :label="1">无效</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item prop="productNum" label="产品编号">
        <el-input name="productNum" v-model="productForm.productNum"></el-input>
      </el-form-item>
      <el-form-item prop="productName" label="产品名称">
        <el-input name="productName" v-model="productForm.productName"></el-input>
      </el-form-item>
      <el-form-item label="图片上传">
        <input type="file" name="file" id="file" />
      </el-form-item>


    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addProduct()">确 定</el-button>
      </span>
    </template>
  </el-dialog>


  <!-- 编辑弹出框 -->
  <el-dialog title="编辑" v-model="editVisible">
    <el-form :model="editForm">
      <el-form-item label="有效标识" >
        <el-radio-group v-model="editForm.flag"  >
          <el-radio name="flag" :label="0">有效</el-radio>
          <el-radio name="flag" :label="1">无效</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item prop="productNum" label="产品编号">
        <el-input name="productNum" v-model="editForm.productNum"></el-input>
      </el-form-item>
      <el-form-item prop="productName" label="产品名称">
        <el-input name="productName" v-model="editForm.productName"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEdit">确 定</el-button>
      </span>
    </template>
  </el-dialog>




  <!-- 删除提示框 -->
  <yin-del-dialog :delVisible="delVisible" @confirm="confirm" @cancelRow="delVisible = $event"></yin-del-dialog>
</template>

<script lang="ts" setup>
import { defineComponent, getCurrentInstance, watch, ref, reactive, computed ,onMounted } from "vue";
import { useStore } from "vuex";
import { HttpManager } from "@/api";
import YinDelDialog from "@/components/dialog/YinDelDialog.vue";
import axios from "axios";
import {ElMessage} from "element-plus";


const { proxy } = getCurrentInstance();
const store = useStore();

const tableData = ref([]); // 记录数据，用于显示
const tempDate = ref([]); // 记录数据，用于搜索时能临时记录一份数据列表


const page = reactive({
  currentPage: 1,
  pageSize: 2,
  total: 100,
});

const queryForm = reactive({
  productName: "",
  productNum:"",
});

const breadcrumbList = computed(() => store.getters.breadcrumbList);

const searchWord = ref(""); // 记录输入框输入的内容
watch(searchWord, () => {
  //假搜索
});

onMounted(async () =>{
  getData()
})

//





async function handleCurrentChange(number) {
  page.currentPage = number
  getData()
}

// 获取数据
async function getData() {
  tableData.value = [];
  tempDate.value = [];
  let productName = queryForm.productName
  let productNum = queryForm.productNum
  let pageSize = page.pageSize
  let currentPage = page.currentPage
  //
  // const result = (await HttpManager.getAllProduct({productName,productNum})) as ResponseBody;
  const result = (await HttpManager.
  getProductPage({productName,productNum,pageSize,currentPage})) as ResponseBody;
  console.log(result)
  tableData.value = result.data.records
  page.total = result.data.total
}

/**
 * 添加
 */
const centerDialogVisible = ref(false);
const productForm = reactive({
  flag: "",
  productNum: "",
  productName: "",
  factoryId: "",
  file: ""
});


async function addProduct() {
  // 组装 FormData
  const addProductForm = new FormData(document.getElementById("add-product") as HTMLFormElement);
  const res = await axios.post(HttpManager.attachImageUrl(`product/add`),
      addProductForm, {
        headers: {
          'Content-Type': 'multipart/form-data'
        },
      })
  console.log(res)
  ElMessage.success('提交成功')
  //3、清空新增表单
  productForm.flag = ""
  productForm.productNum = ""
  productForm.productName = ""
  productForm.factoryId = ""
  document.getElementById("file").outerHTML = document.getElementById("file").outerHTML

  //4、关闭添加层
  centerDialogVisible.value = false
  //5、刷新表格
  getData()

}

/**
 * 删除
 */
const idx = ref(-1); // 记录当前要删除的行
const multipleSelection = ref([]); // 记录当前要删除的列表
const delVisible = ref(false); // 显示删除框

async function confirm() {
  //调用删除接口
  const result = (await HttpManager.deleteProduct(idx.value)) as ResponseBody;
  //提示信息
  ElMessage({
    showClose: true,
    message: result.message,
    type: 'warning',
  })
  //刷新表格
  getData()
  //隐藏新增层
  delVisible.value = false;
}
function deleteRow(id) {
  idx.value = id;
  delVisible.value = true;
}
function handleSelectionChange(val) {
  multipleSelection.value = val;
}
function deleteAll() {
  for (let item of multipleSelection.value) {
    deleteRow(item.id);
    confirm();
  }
  multipleSelection.value = [];
}


/**
 * 编辑
 */
const editVisible = ref(false);
const editForm = reactive({
  id: "",
  flag: "",
  productNum: "",
  productName: "",
  factoryId: ""
});

function editRow(row) {
  idx.value = row.id;
  editForm.id = row.id;
  editForm.flag = row.flag;
  editForm.productNum = row.productNum;
  editForm.productName = row.productName;
  editVisible.value = true;
}

async function saveEdit() {
  let id = editForm.id;
  let flag = editForm.flag;
  let productNum = editForm.productNum;
  let productName = editForm. productName;
  //请求修改接口
  const result = (await HttpManager.updateProduct({id, flag, productNum, productName})) as ResponseBode;
  //提示信息
  ElMessage({
    showClose: true,
    message: result.message,
    type:'warning',
  })
  //刷新表数据
  getData()
  //隐藏修改表单
  editVisible.value = false
}


</script>

<style scoped></style>
