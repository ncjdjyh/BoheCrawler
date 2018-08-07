import {GetFoodList} from "../api/api"
import {getWebSocketConnetion, closeWebSocket} from "../api/webSocketApi"

export default {
  created() {
    //将要回调的方法给它
    getWebSocketConnetion(this.updateFoodList)
  },
  data() {
    return {
      inputContent: "",
      foods: [],
      hasFoods: true,
    }
  },
  methods: {
    getFoodList() {
      GetFoodList(this.inputContent).then(data => {
        let v = data.data
        console.log(v)
        console.log(data.data)
        if (v != null && v != '' && v != undefined) {
          this.initFoodsFlag()
          this.foods = data.data
        }
      })
    },
    updateFoodList(newV) {
      console.log(newV)
      if (newV == "error") {
        this.hasFoods = false
      } else {
        this.initFoodsFlag()
        newV = JSON.parse(newV)
        this.foods = newV
      }
    },
    initFoodsFlag() {
      if (!this.hasFoods) {
        this.hasFoods = true
      }
    }
  },
}
