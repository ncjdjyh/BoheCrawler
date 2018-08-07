export const getWebSocketConnetion = (fun) => {
  var websocket = null;
  //判断当前浏览器是否支持WebSocket
  if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8080/foodsWebSocket");
    //连接成功建立的回调方法
    websocket.onopen = () => {
      console.log("连接成功")
      websocket.send("客户端链接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = event => {
      fun(event.data)
    }

    //连接发生错误的回调方法
    websocket.onerror = () => {
      console.log("WebSocket连接发生错误");
    };

    //连接关闭的回调方法
    websocket.onclose = () => {
      console.log("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = () => {
      closeWebSocket();
    }

  }
  else {
    console.log("浏览器不支持websocket")
  }
}
  //关闭WebSocket连接
  export const closeWebSocket = () => function closeWebSocket() {
    websocket.close();
  }
