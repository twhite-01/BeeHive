<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">login</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
        id="invalid-credentials"
      >invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="password"
        v-model="user.password"
        required
      />
      <router-link id="need-account" :to="{ name: 'register' }">need an account?</router-link>
      <button type="submit" id="sign-in-button">sign in</button>
    </form>

    </div>
</template>

<script>
import authService from "../services/AuthService";
import HiveService from '../services/HiveService';

export default {
  name: "login",
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            

            HiveService.getHiveMembers().then( (response) => {
              let memberArray = response.data;
             // this.$store.commit('SET_HIVE_MEMBERS',memberArray);
              if(memberArray.length === 0){
                this.$router.push({name: 'add-member'});
              }
            })
            


            this.$router.push("/");

          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

.form-signin{
  border: 5px black ridge;
  display: flex;
  flex-flow: column;
  width: 50%;
  margin: 0 auto;
  max-width: 400px;
  min-width: 200px;
  padding: 20px;
  border-radius: 8px;
  background: rgb(255, 254, 249)
}

#username,#password{
  width: 66%;
  margin: 0 auto;
  margin-bottom: 20px;
}

label{
  display: none;
}

h1 {
  margin: 0 auto;
  margin-bottom: 20px;
}

#need-account{
  margin: auto;
  margin-bottom: 20px;
}

#sign-in-button{
  width: 33%;
  margin: 0 auto;
  border-radius: 50px
}

#invalid-credentials{
  text-align: center;
}

</style>
