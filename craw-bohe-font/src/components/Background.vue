<template>
  <div class="block">
    <div class="description">
      <span class="demonstration">nectar的热量库</span>
    </div>
    <el-carousel height="250px">
      <el-carousel-item v-for="item in 4" :key="item">
        <!--<h3>{{ item }}</h3>-->
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
    export default {
        name: "Background"
    }
</script>

<style scoped>
  .block {
    margin-top: 10px;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 100px;
  }

  .el-carousel__item:nth-child(3) {
    background: url("../assets/food_bg_1.jpg");
  }

  .el-carousel__item:nth-child(4) {
    background: url("../assets/food_bg_1.jpg");
  }

  .el-carousel__item:nth-child(5) {
    background: url("../assets/food_bg_1.jpg");
  }

  .el-carousel__item:nth-child(6) {
    background: url("../assets/food_bg_1.jpg");
  }

  .description {
    margin-bottom: 10px;
  }

  .description .demonstration {
    text-align: center;
    color: dodgerblue;
    font-size: 20px;
    font-family: "Maiandra GD";
  }
</style>
