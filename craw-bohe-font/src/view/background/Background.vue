<template>
  <div class="block">
    <div class="description">
      <span class="demonstration">nectar的热量库</span>
      <div class="userInfo">
        <span v-if="isLogin">
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              {{ username }}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="logout">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
        <span v-else><el-button type="danger" size="mini" round @click="goLoginForm">登录</el-button></span>
      </div>
    </div>
    <el-carousel height="250px">
      <el-carousel-item v-for="item in 4" :key="item">
        <!--<h3>{{ item }}</h3>-->
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script src="./js/background.js"></script>

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
    background: url("../../assets/food_bg_1.jpg");
  }

  .el-carousel__item:nth-child(4) {
    background: url("../../assets/food_bg_1.jpg");
  }

  .el-carousel__item:nth-child(5) {
    background: url("../../assets/food_bg_1.jpg");
  }

  .el-carousel__item:nth-child(6) {
    background: url("../../assets/food_bg_1.jpg");
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

  .userInfo {
    margin-right: 20px;
    float: right;
  }
</style>
