<template>
<div>
  <el-card :body-style="{ padding: '0px' }">
    <img :src="imgUrl" class="image">
    <div style="padding: 14px;">
      <span class="title">{{food.name}}</span>
      <div class="evaluation">{{food.evaluation}}</div>
      <div class="bottom clearfix">
        <time class="time">{{ currentDate }}</time>
        <el-button type="text" class="button" @click="back">返回</el-button>
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

  .button {
    padding: 0;
    font-size: 18px;
    font-weight: bold;
    float: right;
    color: #F56C6C;
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

<script>
  export default {
    data() {
      return {
        currentDate: new Date(),
        food: {}
      };
    },
    computed: {
      imgUrl() {
          return "../static/foods/" + this.food.img
      }
    },
    methods: {
      back() {
        window.history.length > 1
          ? this.$router.go(-1)
          : this.$router.push('/')
      }
    },
    mounted() {
      this.food = this.$route.params.food
      if (this.food == undefined || this.food == "") {
        this.back()
      }
    }
  }
</script>
