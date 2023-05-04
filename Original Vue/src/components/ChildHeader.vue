<template>
  <div id="member-header-container">

    <h2 id="mem-name">{{ this.$store.state.currentMember.firstName }} {{ this.$store.state.currentMember.lastInitial }} </h2>
    <!-- <div id="total-links"><span><a href="#" @click="jumpToParentView(20)">total books read</a>  |  <a href="#">total minutes read</a></span></div> -->
    <button @click="jumpToChildView(1)" id="child-home-button">home</button>
    <div id="user-stats">
      <!-- <div>
        <h3>Total Books Read: {{this.$store.state.memberBookList.length}} </h3>
        <button  @click="jumpToParentView(20)"> See All Books </button>
          </div> -->

          <!-- <div>
      <a class="not-bee-btn-slide" href="#" @click="jumpToChildView(10)">
        <span class="circle"> </span>
        <span class="title">total books read: {{this.$store.state.memberBookList.length}}</span>
        <span class="title title-hover">see all books</span>
      </a>
    </div>

    <div>
      <a class="not-bee-btn-slide" href="#" @click="jumpToChildView(30)">
        <span class="circle"> </span>
        <span class="title">total minutes read: {{this.$store.state.readMinutes.minutesRead}}</span>
        <span class="title title-hover">see all activity</span>
      </a>
    </div> -->

    <span id="total-books-minutes">
        <a id="total-books" href="#" @click="jumpToChildView(10)">
          total books read:
          {{ this.$store.state.memberBookList.length }}  </a>
        <span>|</span>

        <a id="total-mins" href="#" @click="jumpToChildView(30)">
          total minutes read:
          {{ this.$store.state.readMinutes.minutesRead }}</a
        >
      </span>




         <!-- <div>
        <h3>Total Minutes Read: {{this.$store.state.readMinutes.minutesRead}}</h3>
        <button @click="jumpToParentView(30)">See All Activity</button>
          </div> -->

      
    </div>
  </div>
</template>

<script>

import HiveService from '../services/HiveService.js';
export default {
  name: "member-header",
  data() {
    return {
      totalMinutes: this.$store.state.readMinutes
      // currentMember: {
      //   id: 0,
      //   firstName: "",
      //   lastInitial: "",
      //   avatarId: 0,
      //   pinRequired: false,
      //   isChild: false,
      //   pin: "",
      // },
    };
  },


  methods: {
    jumpToChildView(viewNum){
      this.$store.commit('JUMP_TO_CHILD_VIEW', viewNum)
    }
  },

  getTotalMinutesRead()
  {
    
    HiveService.getTotalMinutesByMember(this.$store.state.currentMember.id)
    .then((response) =>
    {
     this.totalMinutes = response.data;
    } );
  }
};
</script>

<style>

#member-header-container{
  display: flex; 
  flex-direction: column;
  justify-content: center;
  text-align: center;
  
}

#mem-name{
  
font-family: 'Livvic', sans-serif;
  margin-bottom: 5px;
  font-size: 40px;
  color: black
}

#total-links{
  margin-bottom: 20px;
  text-align: center
}

#user-stats
{
  display:flex;
 justify-content:center;
}

#child-home-button{
  width: 80px;
  border: 2px solid black;
  border-radius: 20px;
  margin: 0 auto;
  font-size: 1.1rem;
}
#child-home-button:hover{
  background-color: #fdf189;
}


</style>