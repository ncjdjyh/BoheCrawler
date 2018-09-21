import {GetFoodList, SuggestSearch, GetUser} from "../../../api/api"
import {getWebSocketConnetion, closeWebSocket} from "../../../api/webSocketApi"
import background from '../../background/Background'

export default {
  components: {
    background
  },
  created() {
    if (this.isLogin() == false) {
      //退出页面后重进 通过cookie重新检查用户是否登录
      GetUser().then(response => {
        let data = response.data
        if (data.status == '200') {
          this.user.username = data.result
          this.$store.commit('setAuthState', this.user)
        }
        //状态码不对
      })
    } else {
      //没有退出网页, 只是进行了刷新, 直接在session中取出
      let username = sessionStorage.getItem('username')
      console.log(username)
      this.$store.commit('setLoginUser', username)
      this.$store.commit('setLoginState', true)
    }
    //将要回调的方法给它
    getWebSocketConnetion(this.updateFoodList)
  },
  data() {
    return {
      user: {
        username:'',
      },
      inputContent: "",
      foods: [],
      suggestFoods: [],
      loadingFlag: false,
      hasFoods: true,
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
    //判断是否已经登录(未关闭浏览器页面)
    isLogin() {
      let username =  sessionStorage.getItem('username')
      console.log(username)
      if (username == undefined || username == null || username == '') {
        console.log('false')
        return false
      } else {
        return true
        console.log('true')
      }
    },
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
    getFavoriteList() {
      if (this.$store.getters.getLoginState == false) {
        this.$router.push({
          name: 'login',
          path: '/login',
        })
      } else {
        this.foods = ''
      }
    },
    checkFromStore() {
      //从食物的详细列表返回时, 原来的记录要保存起来
      let state = this.$store.state
      if (state.food !== []) {
        this.foods = state.food
      }
      if (state.inputContent != '') {
        this.inputContent = state.inputContent
      }
    },
    setFoodStore() {
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
      this.setFoodStore()
      this.$router.push({
        name: 'foodDetail',
        path: '/food',
        params: { food: row}})
    }
  },
}
