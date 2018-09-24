import Vue from 'vue'
import Router from 'vue-router'
import FoodDetail from '../view/foodDetail/FoodDetail'
import Home from '../view/home/Home'
import LoginForm from '../view/login/Login'
import SearchHolder from '../view/searchHolder/SearchHolder'
import Favorite from '../view/favorite/Favorite'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/', redirect: '/home/search'
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      children:
        [
          {
            path: '/home/search',
            name: 'search',
            component: SearchHolder
          },
          {
            path: '/home/favorite',
            name: 'favorite',
            component: Favorite
          }
        ]
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
