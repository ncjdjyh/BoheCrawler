<template>
  <div id="app">
    <!--<helloworld></helloworld>-->
    <background></background>
    <search></search>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld'
import Background from "./components/Background"
import SearchBar from "./components/SearchBar"

export default {
  name: 'App',
  components: {
    helloworld: HelloWorld,
    background: Background,
    search: SearchBar,
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
