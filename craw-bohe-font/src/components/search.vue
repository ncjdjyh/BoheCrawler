<template>
  <div>
    <el-row>
      <el-input  v-model="inputContent" placeholder="请输入你要查询的食物"></el-input>
      <el-button type="primary" plain  @click="getFoodList">搜索</el-button>
    </el-row>
    <div align="center">
      <div v-if="hasFoods">
        <el-table
          :data="limitFoods"
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
        <div class="block">
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page="pageContent.currentPage"
            layout="prev, pager, next"
            :total="pageContent.total"
            :page-size="pageContent.pageSize">
          </el-pagination>
        </div>
      </div>
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
