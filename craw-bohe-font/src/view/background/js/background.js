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
      this.$store.commit('setAuthState', null)
    },
    goLoginForm() {
      this.$router.push('/login')
    }
  }
}
