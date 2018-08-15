import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    food:[],
    inputContent: ''
  },
  getters: {},
  mutations: {
    setFood(state, newFood) {
      state.food = newFood
    },
    setContent(state, newContent) {
      state.inputContent = newContent
    }
  },

})
