<template>
  <div class="form">
      <el-row>
        <el-col :span="8">
          <el-input id="name" v-model="formData.username" placeholder="请输入帐号">
            <template slot="prepend">帐号</template>
          </el-input>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-input id="password" v-model="formData.password" type="password" placeholder="请输入密码">
            <template slot="prepend">密码</template>
          </el-input>
          <el-switch class="switch"
            v-model="formData.rememberMe"
            active-text="自动登录"
            inactive-text="">
          </el-switch>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-button id="login" v-on:click="handleSubmit" style="width:100%" type="primary">登录</el-button>
        </el-col>
      </el-row>
  </div>
</template>

<script src="./js/login.js"></script>

<style scoped>
  .el-row {
    margin-bottom: 20px;
  }
  .form {
    top: 20%;
    width: 1500px;
    position: fixed;
    left: 30%;
  }
  .switch {
    margin-top: 20px;
  }
</style>
