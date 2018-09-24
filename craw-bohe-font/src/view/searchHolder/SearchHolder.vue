<template>
  <div>
    <el-row>
      <el-autocomplete
        class="inline-input"
        v-model="inputContent"
        :fetch-suggestions="querySearch"
        placeholder="请输入要查询的食物"
        :trigger-on-focus="false"
        @select="handleSelect">
        <template slot-scope="{ item }">
          <div class="name">{{ item.name }}</div>
        </template>
      </el-autocomplete>
      <el-button
         type="primary" plain
         @click="getFoodList">
        搜索
      </el-button>
      <el-button
          type="primary"
          @click="getFavoriteList">
        收藏夹
      </el-button>
    </el-row>
    <div align="center">
      <div v-if="hasFoods">
        <el-table
          v-loading="loadingFlag"
          @row-click="handleRowClick"
          :data="limitFoods"
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
      <div v-else class="error-text">
        <span>
          没有找到你需要的数据,sorry!
        </span>
      </div>
    </div>
  </div>
</template>

<script src="./js/searchHolder.js"></script>


<style scoped>
  .el-autocomplete {
    margin-top: 20px;
    width: 400px;
  }

  .error-text {
    margin-top: 20px;
  }
</style>
