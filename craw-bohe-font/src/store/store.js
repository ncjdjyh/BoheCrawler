import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    food:[],
    inputContent: '',
    auth: {
      isLogin: false,
      user: ''
    }
  },
  getters: {
    getLoginState: state => {
      return state.auth.isLogin
    },
    getLoginUser: state => {
      return state.auth.user
    },
    getAuthInfo: state => {
      return state.auth
    }
  },
  mutations: {
    setFood(state, newFood) {
      state.food = newFood
    },
    setContent(state, newContent) {
      state.inputContent = newContent
    },
    setLoginState(state, newStatus) {
      state.auth.isLogin = newStatus
    },
    setLoginUser(state, newUser) {
      state.auth.user = newUser
    },
    setAuthInfo(state, newAuth) {
      state.auth.user = newAuth.user
      state.auth.isLogin = newAuth.loginState
    },
    setAuthState (state, user) {
      if (user) {
        console.log(user)
        console.log('in')
        console.log(sessionStorage.getItem('username'))
        state.auth.user = user.username
        state.auth.isLogin = true
        sessionStorage.setItem('username', user.username)
        console.log(sessionStorage.getItem('username'))
      } else {
        //退出登录
        console.log("out")
        sessionStorage.setItem('username', '')
        state.auth.user = ''
        state.auth.isLogin = false
        console.log(sessionStorage.getItem('username'))
      }
    }
    }
  ,
})
