<template>
  <div>
    <el-row>
      <el-input  v-model="inputContent" placeholder="请输入内容"></el-input>
      <el-button type="primary" plain  @click="getFoodList">搜索</el-button>
    </el-row>
    <div align="center">
      <el-table
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
    </div>
  </div>
</template>

<script>

  import {getFoodList} from "../api/api"

    export default {
      name: 'SearchBar',
      created() {
        this.getWebSocketConnection()
      },
      data() {
        return {
          inputContent: '',
          foods: [],
          food: {
            name:'',
            description: ''
          }
        }
      },
      methods: {
        updateFoodList(data)  {
          //this.foods = data
          console.log(data)
          if(data == 'hello') {
            this.foods = []
          } else {
            this.foods = JSON.parse(data)
          }
        },
        getWebSocketConnection() {
          var websocket = null;
          //判断当前浏览器是否支持WebSocket
          if ('WebSocket' in window) {
            websocket = new WebSocket("ws://localhost:8080/foodsWebSocket");
            //连接成功建立的回调方法
            websocket.onopen = function () {
              //alert("连接成功")
              websocket.send("客户端链接成功");
            }

            //接收到消息的回调方法
            websocket.onmessage = event => {
              console.log(event.data)
              this.updateFoodList(event.data)
            }

            //连接发生错误的回调方法
            websocket.onerror = function () {
              //alert("WebSocket连接发生错误");
            };

            //连接关闭的回调方法
            websocket.onclose = function () {
              //alert("WebSocket连接关闭");
            }

            //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
            window.onbeforeunload = function () {
              closeWebSocket();
            }

          }
          else {
            alert('当前浏览器 Not support websocket')
          }

          //将消息显示在网页上
          function setMessageInnerHTML(innerHTML) {
            var bitcoin = eval("("+innerHTML+")");
            document.getElementById('price').innerHTML = bitcoin.price;
            document.getElementById('total').innerHTML = bitcoin.total;
          }

          //关闭WebSocket连接
          function closeWebSocket() {
            websocket.close();
          }
        },
        getFoodList() {
          if (this.inputContent != '') {
            getFoodList(this.inputContent).then(res => {
              this.foods = res.data
              console.log(this.foods)
            }).catch(err => {
              console.log(err)
            })
          }
          },
        }
    }
</script>

<style scoped>
  .el-input {
    margin-top: 30px;
    width: 400px;
  }
</style>
