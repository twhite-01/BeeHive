<template>
  <div id="edit-prize-container">
    <!-- <div id="prize-photo">
      <img id="prize-pic" src="../assets/green.png" alt="prize-picture" />
    </div> -->

    <div v-if="!this.editPrize" v-show="this.hidePrize">
      <h2 id="prize-name">{{ prize.prizeName }}</h2>
      <h4 id="prize-description">description: {{ prize.description }}</h4>
      <span id="dates"
        >active dates: {{ prize.startDate }} to {{ prize.endDate }}</span
      >

      <p id="eligible-mems">{{ this.attachedMemFormatted }}</p>

      <div id="edit-or-delete">
        <button id="edit-btn" @click="updatePrize()">edit prize</button
        ><button id="edit-btn" @click="deletePrize()">delete prize</button>
      </div>
    </div>

    <new-prize-form v-if="this.editPrize" />

    <!-- <button @click="getAttachedMembers(prize.id)">See Attached Members</button> -->

    <!-- <div id="members-attached" v-for="member in attachedMembers" v-bind:key="member.id">
     {{member.firstName}} {{member.lastInitial}}
   </div> -->
  </div>
</template>

<script>
import hiveService from "../services/HiveService.js";
import NewPrizeForm from "./NewPrizeForm.vue";

export default {
  name: "edit-prize",

  props: ["prizeId"],

  components: { NewPrizeForm },

  data() {
    return {
      minutesEarned: 0,

      attachedMembers: [],

      hidePrize: true,

      attachedMemFormatted: "eligible members: ",

      editPrize: false,

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
    /* 
    hiveService
      .getMinutesTowardPrize(this.memberId, this.prizeId)
      .then((response) => {
        this.minutesEarned = response.data;
      }); */

    hiveService.getAllMembersForAGivenPrize(this.prizeId).then((response) => {
      this.attachedMembers = response.data;

      for (let i = 0; i < response.data.length; i++) {
        this.attachedMemFormatted += this.attachedMembers[i].firstName + ", ";
      }

      this.attachedMemFormatted = this.attachedMemFormatted.slice(
        0,
        this.attachedMemFormatted.length - 2
      );
    });
  },

  methods: {
    updatePrize() {
      this.editPrize = true;
    },

    deletePrize() {
      this.hidePrize = false;
    },
  },
};
</script>

<style scoped>
#edit-prize-container {
  font-family: "Livvic", sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  background: rgb(255, 254, 249);
  border: 10px black ridge;
  border-radius: 10px;
  width: 400px;

  margin: auto;
  margin-bottom: 10px;
  justify-items: left;
  padding: 5px;
}

.edit-or-delete {
  display: flex;
  justify-content: space-around;
  margin-bottom: 10px;
}

#name-progress {
  margin-left: 10px;
}

#edit-btn {
  border-radius: 20px;
  border: 2px solid #63a8da;
}

#prize-name {
  font-size: 25px;
  margin-bottom: 0px;
  border-bottom: 2px solid #63a8da;
}

#current-prize-progress {
  margin-bottom: 15px;
  padding-bottom: 35px;
}

#prize-photo {
  width: 80px;
  height: 80px;
}

#prize-pic {
  width: 100%;
}
</style>
