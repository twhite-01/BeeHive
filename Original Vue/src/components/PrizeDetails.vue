<template>
  <div id="prize-container" :class="this.winner ? 'winner' : 'not-winner'">
    <div id="prize-photo">
      <img id="prize-pic"  src="../assets/trophy.png" alt="prize-picture"  />
    </div>

    <!-- <div>{{this.memberArray}}</div> -->

    <div id="name-progress">
      <h2 id="prize-name">{{ prize.prizeName }}</h2>
      <h4 id="prize-description">{{ prize.description }}</h4>
      
      <h4 id="current-prize-progress" ><span v-if="!this.winner">
        my progress: {{ this.$store.state.readMinutes.minutesRead }} / </span>
        {{ prize.goalInMinutes }} minutes
      </h4>
    </div>
  </div>
</template>

<script>
import hiveService from "../services/HiveService.js";

export default {
  name: "prize-details",

  props: ["prizeId"],

  data() {
    return {
      memberId: this.$store.state.currentMember.id,

      minutesEarned: this.$store.state.readMinutes.minutesRead,

      winner: true,

      prize: {
        id: 0,
        prizeName: "",
        description: "",
        goalInMinutes: 0,
        userId: this.$store.state.user.id,
        maxWinners: 0,
        currentWinners: 0,
        startDate: "",
        endDate: "",
        isActive: true,
        memberIds: [],
      },
    };
  },

  created() {
    hiveService.getPrizeByPrizeId(this.prizeId).then((response) => {
      this.prize = response.data;
    });

    // hiveService
    //   .getMinutesTowardPrize(this.memberId, this.prizeId)
    //   .then((response) => {
    //     this.minutesEarned = response.data;
    //   });
  },

  // computed: {

  //   winner(){
  //     if(this.$store.state.readMinutes.minutesRead < this.prize.goalInMinutes){
  //       alert('Not a winner')
  //       return true;
  //     }else{
  //       alert('Is a winner')
  //       return false;
  //     }
  //   }

  // }

  mounted() {
    this.$store.state.allPrizes.forEach((prize) => {
      if (this.prizeId === prize.id) {
        if (prize.goalInMinutes > this.$store.state.readMinutes.minutesRead) {
          this.winner = false;
        } else {
          this.winner = true;
        }
      }
    });

    // if(this.$store.state.readMinutes.minutesRead < this.$store.state.allPrizes.){
    //     this.winner = false;
    //   }else{
    //     this.winner = true;
    //   }
  },
};
</script>

<style scoped>

#prize-container {
  font-family: "Livvic", sans-serif;
  display: flex; 
  flex-direction: row;
  align-items: center;
  border-radius: 10px;
  width: 400px;
  margin: auto;
  margin-bottom: 10px;
  justify-content: left;
  padding: 10px;

  border-radius: 8px;
    border: 10px black groove;

  /* border: 10px solid transparent; */
  /* border-image: linear-gradient(
    to bottom right,
    #b827fc 0%,
    #2c90fc 25%,
    #b8fd33 50%,
    #fec837 75%,
    #fd1892 100%
  ); */
  border-image-slice: 1;
}

#prize-name {
  border-bottom: 3px solid #fec837;
}

#name-progress {
  margin-left: 10px;
}

#prize-name {
  font-size: 25px;
  margin-bottom: 0px;
}

#current-prize-progress {
  margin-bottom: 15px;
  padding-bottom: 35px;
}

#prize-photo {
  width: 75px;
  height: 75px;
}

#prize-pic {
  width: 100%;
}

.winner {
  background-image: url('../assets/awarded.png');
  background-size: cover;
  background-repeat: no-repeat;
}

.not-winner{
  background: rgb(255, 254, 249);
}
</style>
