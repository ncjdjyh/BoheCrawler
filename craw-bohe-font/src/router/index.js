import Vue from 'vue'
import Router from 'vue-router'
import FoodDetial from '../components/FoodDetail'
import SearchContent from '../components/search'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'search',
      component: SearchContent
    },
    {
      path: '/food',
      name: 'foodDetial',
      component: FoodDetial
    }
  ]
})
