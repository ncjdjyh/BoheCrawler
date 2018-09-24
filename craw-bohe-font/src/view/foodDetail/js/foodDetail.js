import {AddFavorite} from "../../../api/api";

export default {
  created() {
    this.isLogin = this.$store.getters.getLoginState
    this.userId = this.$store.getters.getLoginUser
  },
  data() {
    return {
      currentDate: new Date(),
      food: {},
      //是否登录
      isLogin: '',
      //是否从收藏夹进入
      isFavoriteDetail: false,
      userId: ''
    };
  },
  computed: {
    imgUrl() {
      return "../static/foods/" + this.food.img
    }
  },
  methods: {
    back() {
      window.history.length > 1
        ? this.$router.go(-1)
        : this.$router.push('/')
    },
    handleAddFavorite() {
      AddFavorite(this.userId, this.food.id)
    }
  },
  mounted() {
    this.food = this.$route.params.food
    let fromFavorite = this.$route.params.isFavoriteDetail
    console.log(fromFavorite)
    if (fromFavorite != '' && fromFavorite != undefined && fromFavorite != null) {
      this.isFavoriteDetail = fromFavorite
    }
    if (this.food == undefined || this.food == "") {
      this.back()
    }
  }
}
