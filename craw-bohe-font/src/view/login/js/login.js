import {GoLogin, GetIndex} from "../../../api/api"
import background from "../../background/js/background"

export default {
  components: {
    background
  },
  created() {
  },
  data() {
    return {
      user: {
        username:'',
        //以后可能有其他信息
      },
      formData: {
        username: '',
        password: '',
        rememberMe: true,
      }
    }
  },
  mounted() {},
  methods: {
    handleSubmit() {
      GoLogin(this.formData).then(response => {
        let data = response.data
        if (data.status === '200') {
          //登录成功的处理
          this.user.username = data.result
          this.$store.commit('setAuthState', this.user)
          this.$router.push('/')
        } else {
          //登录失败的处理
          this.$message('用户名或密码错误, 请重试')
        }
      })
    },
  },
}
