// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from './router/index'
import store from './store/store'

Vue.use(ElementUI)
Vue.config.productionTip = false


/*router.beforeEach(function (to, from, next) {
  const nextRoute = ['favorite'];
  const auth = store.state.auth;
  //跳转至上述3个页面
  if (nextRoute.indexOf(to.name) >= 0) {
    //未登录
    if (!store.state.auth.isLogin) {
      router.push({name: 'login'})
    }
  }
  //已登录的情况再去登录页，跳转至首页
  if (to.name === 'login') {
    if (auth.IsLogin) {
      router.push({name: 'home'});
    }
  }
  next();
});*/


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
