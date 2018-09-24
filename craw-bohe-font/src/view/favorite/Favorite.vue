<template>
  <div align="center">
      <el-table
        v-loading="loadingFlag"
        @row-click="handleRowClick"
        :data="foods"
        align="center">
        <el-table-column prop="name" label="名称" width="180"></el-table-column>
        <el-table-column prop="heat" label="热量" width="180"></el-table-column>
        <el-table-column prop="category" label="类别" width="180"></el-table-column>
        <el-table-column
          prop="img"
          label="图片"
          width="180">
          <template slot-scope="scope">
            <img :src= "imgUrl(scope.row.img)"
                 width="100"
                 height="100"/>
          </template>
        </el-table-column>
      </el-table>
  </div>
</template>

<script src="./js/favorite.js"></script>

<style scoped></style>
