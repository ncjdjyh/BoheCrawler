import {GetFavoriteList} from "../../../api/api";

export default {
  created() {

  },
  data() {
    return {
      foods: [],
      loadingFlag: false,
    }
  },
  mounted() {
    GetFavoriteList(this.$store.getters.getLoginUser).then(response => {
      this.foods = response.data
    })
  },
  computed: {
    //使用闭包的方式为计算属性传递参数
    imgUrl() {
      return function(url) {
        return "../static/foods/" + url
      }
    }
  },
  methods: {
    handleRowClick(row) {
      console.log(row)
      this.$router.push({
        name: 'foodDetail',
        path: '/food',
        params: { food: row, isFavoriteDetail: true}})
    },
    back() {
      window.history.length > 1
        ? this.$router.go(-1)
        : this.$router.push('/')
    },
  },
}
