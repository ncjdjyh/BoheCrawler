<template>
  <div id="app">
    <background></background>
    <router-view></router-view>
  </div>
</template>

<script>
import Background from "./components/Background"
import SearchContent from "./components/search"

export default {
  name: 'App',
  components: {
    background: Background,
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
