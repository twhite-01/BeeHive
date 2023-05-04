<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' } " v-if="$store.state.token != ''"><span @click="resetParentHome()">my hive    | </span></router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">logout</router-link>
     
    </div>
    <app-header id="app-header" />
    <router-view />
  
  </div>
  
</template>

<script>
import AppHeader from './components/AppHeader.vue'

export default {
  components: {AppHeader},

  methods: {

    resetParentHome(){
      this.$store.commit('RESET_PARENT_VIEW', this.$store.state);
      this.$store.commit('RESET_CHILD_VIEW', this.$store.state);
      this.$store.commit('RESET_CURRENT_MEMBER');
    }
  }
}
</script>


<style>

@import url('https://fonts.googleapis.com/css2?family=DynaPuff:wght@400;700&family=Press+Start+2P&display=swap');
@import url("https://fonts.googleapis.com/css2?family=Lekton&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Special+Elite&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Barriecito&family=Cutive&family=Cutive+Mono&family=Inconsolata&family=Special+Elite&family=Syne+Mono&family=Syne:wght@500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inconsolata&family=Ribeye&family=Ribeye+Marrow&family=Special+Elite&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Chelsea+Market&family=Concert+One&family=Delius+Unicase&family=Inconsolata&family=Livvic:wght@600&family=Ribeye&family=Ribeye+Marrow&family=Rubik:wght@500&family=Special+Elite&display=swap');

body{
  background-repeat: repeat-x;
  background-color: #63a8da;
  font-family: 'Lekton', sans-serif;
  font-weight: bold;
  background-image: url("./assets/bestBEElieveit.png");
}

#app-header{
  margin-bottom: 50px;

}




#nav{
  display: flex;
  justify-content: flex-end;

}
</style>