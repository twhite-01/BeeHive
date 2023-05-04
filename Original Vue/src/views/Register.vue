<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal" id="acct-header">create account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="confirm password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }" id="have-acct">have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit-acct-btn">
        create account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>

.form-register{
  border: 5px black ridge;
 background:rgb(255, 254, 249);
  display: flex;
  flex-flow: column;
  width: 50%;
  margin: 0 auto;
  max-width: 400px;
  min-width: 200px;
  padding: 20px;
  border-radius: 8px;
}

#username,#password, #confirmPassword{
  width: 66%;
  margin: 0 auto;
  margin-bottom: 20px;
}

#acct-header{
  margin: auto;
  margin-bottom: 15px
}

#have-acct{
  margin:auto;
  margin-bottom: 15px
}

#submit-acct-btn{
  margin:auto;
  border-radius: 50px
}

label{
  display: none;
}


</style>

