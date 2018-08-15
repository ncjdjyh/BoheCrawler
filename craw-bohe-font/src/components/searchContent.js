import {GetFoodList, SuggestSearch} from "../api/api"
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
      suggestFoods: [],
      hasFoods: true,
      loadingFlag: false,
      total: "",
      pageContent: {
        contents: [],
        currentPage: 1,
        total: 4,
        pageSize: 4
      }
    }
  },
  mounted() {
    this.checkFromStore()
  },
  computed: {
    limitFoods() {
      let page = this.pageContent
      console.log(page)
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
      this.loadingFlag = true
      let v = this.inputContent
      if (v != '' && v != null && v != undefined) {
        GetFoodList(this.inputContent).then(data => {
          let d = data.data
          if (d != null && d != '' && d != undefined) {
            this.initFoodsFlag()
            this.foods = d
            this.initPageContent(this.pageContent)
            this.loadingFlag = false
          } else {
            this.showCrawlMessage()
          }
        })
      }
    },
    checkFromStore() {
      let state = this.$store.state
      if (state.food !== []) {
        this.foods = state.food
      }
      if (state.inputContent != '') {
        this.inputContent = state.inputContent
      }
    },
    setStore() {
      this.$store.commit("setFood", this.foods)
      this.$store.commit("setContent", this.inputContent)
    },
    updateFoodList(newV) {
      this.loadingFlag = false
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
    async querySearch(queryString, cb) {
      await SuggestSearch(queryString).then(res => {
        console.log(res.data)
        this.suggestFoods = res.data
      })
      let results = this.suggestFoods.filter(this.createFilter(queryString))
      cb(this.suggestFoods);
    },
    createFilter(queryString) {
      return (food) => {
        return (food.name.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      this.inputContent = item.name
    },
    showCrawlMessage() {
      this.$notify({
        title: '爬',
        message: '没有找到你需要的食物,已帮你去爬取',
        duration: 3000
      });
    },
    handleRowClick(row) {
      console.log(row)
      this.setStore()
      this.$router.push({
        name: 'foodDetial',
        path: '/food',
        params: { food: row}})
    }
  },
}
