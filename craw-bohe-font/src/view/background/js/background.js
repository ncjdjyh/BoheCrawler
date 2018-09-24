import {Logout} from "../../../api/api";

export default {
  created() {
  },
  data() {
    return {

    }
  },
  computed: {
    isLogin() {
      console.log(this.$store.getters.getLoginState)
     return this.$store.getters.getLoginState
    },
    username() {
      return this.$store.getters.getLoginUser
    }
  },
  mounted() {},
  methods: {
    handleCommand(command) {
      switch (command) {
        case 'logout': {
          this.logout()
        }
    }
  },
    logout() {
      Logout()
      this.$store.commit('setAuthState', '')
    },
    goLoginForm() {
      this.$router.push('/login')
    }
  }
}
