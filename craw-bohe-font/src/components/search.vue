<template>
  <div>
    <el-row>
      <el-input  v-model="inputContent" placeholder="请输入内容"></el-input>
      <el-button type="primary" plain  @click="getFoodList">搜索</el-button>
    </el-row>
    <div align="center">
      <el-table
        v-if="hasFoods"
        v-loading="loading"
        :data="foods"
        style="width: 90%"
        align="center">
        <el-table-column
          prop="name"
          label="名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="heat"
          label="热量"
          width="180">
        </el-table-column>
      </el-table>
      <span v-else>
        没有找到你需要的数据,sorry!
      </span>
    </div>
  </div>
</template>

<script src="./searchContent.js"></script>

<style scoped>
  .el-input {
    margin-top: 30px;
    width: 400px;
  }
</style>
