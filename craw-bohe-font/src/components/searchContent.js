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
      total: "",
      pageContent: {
        contents: [],
        currentPage: 1,
        total: 5,
        pageSize: 5
      }
    }
  },
  computed: {
    limitFoods() {
      let page = this.pageContent
      return this.foods.slice((page.currentPage - 1) * page.pageSize, page.currentPage * page.pageSize)
    },
    //使用闭包的方式为计算属性传递参数
    imgUrl() {
      return function(url) {
        return "../static/foods/" + url
      }
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
          this.initPageContent(this.pageContent)
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
        this.initPageContent(this.pageContent)
      }
    },
    initFoodsFlag() {
      if (!this.hasFoods) {
        this.hasFoods = true
      }
    },
    handleCurrentChange(val) {
      this.pageContent.currentPage = val
    },
    initPageContent(page) {
      page.total = this.foods.length
    },
  },
}
