export default {
  name:"FoodList",
  created() {
    this.getWebSocketConnection()
  },
  props: ["foods"],
  data() {
    return {
    }
  },
  methods: {
    getWebSocketConnection() {
      var that = this
      var websocket = null;
      //判断当前浏览器是否支持WebSocket
      if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/foodsWebSocket");
        //连接成功建立的回调方法
        websocket.onopen = function () {
          alert("连接成功")
          websocket.send("客户端链接成功");
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
          console.log(event.data)
          that.$emit("updateFoodList", event.data)
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
          alert("WebSocket连接发生错误");
        };

        //连接关闭的回调方法
        websocket.onclose = function () {
          alert("WebSocket连接关闭");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
          closeWebSocket();
        }

      }
      else {

      }

      //将消息显示在网页上
      function setMessageInnerHTML(innerHTML) {

      }

      //关闭WebSocket连接
      function closeWebSocket() {
        websocket.close();
      }
    }
  }
}
