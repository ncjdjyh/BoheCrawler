<template>
<div>
  <el-card :body-style="{ padding: '0px' }">
    <img :src="imgUrl" class="image">
    <div style="padding: 14px;">
      <span class="title">{{food.name}}</span>
      <div class="evaluation">{{food.evaluation}}</div>
      <div class="bottom clearfix">
        <time class="time">{{ currentDate }}</time>
        <span class="buttons">
          <el-button class="btFont" type="text" @click="back">返回</el-button>
          <el-button class="btFont" type="text" v-if="isLogin == true && isFavoriteDetail == false" @click="handleAddFavorite">收藏</el-button>
        </span>
      </div>
    </div>
  </el-card>
</div>
</template>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .buttons {
    float: right;
  }

  .btFont {
    color: #F56C6C;
    font-weight: bold;
    font-size: 16px;
    padding: 5px;
  }

  .title {
    font-size: 20px;
    font-weight: bold;
  }

  .evaluation {
    color: #409EFF;
  }

  .image {
    padding-top: 30px;
    margin: 0 auto;
    height: 200px;
    width: 200px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>

<script src="./js/foodDetail.js"> </script>
