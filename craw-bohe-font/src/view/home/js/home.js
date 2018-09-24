import {GetFoodList, SuggestSearch, GetUser, GetFavoriteList} from "../../../api/api"
import {getWebSocketConnetion, closeWebSocket} from "../../../api/webSocketApi"
import background from '../../background/Background'

export default {
  components: {
    background
  },
  data(){
    return {
      user: {
        username:'',
      },
    }
  },
  methods: {
    //判断是否已经登录(未关闭浏览器页面)
    isLogin() {
      let username =  sessionStorage.getItem('username')
      if (username == undefined || username == null || username == '') {
        return false
      } else {
        return true
      }
    },
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
      if (username != 'null' && username != '' && username != undefined) {
        console.log("从session取")
        this.$store.commit('setLoginUser', username)
        this.$store.commit('setLoginState', true)
        this.user.username = username
      }
    }
    //将要回调的方法给它
    getWebSocketConnetion(this.updateFoodList)
  }
}
