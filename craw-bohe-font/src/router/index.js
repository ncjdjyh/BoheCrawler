import Vue from 'vue'
import Router from 'vue-router'
import FoodDetail from '../view/foodDetail/FoodDetail'
import Home from '../view/home/Home'
import LoginForm from '../view/login/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/food',
      name: 'foodDetail',
      component: FoodDetail
    },
    {
      path: '/login',
      name: 'login',
      component: LoginForm
    },
  ]
})
