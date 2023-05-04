<template>
  <div class="new-prize-input">
    <div class="form-box">
      <h2>new prize form</h2>
      <form>
        <div class="user-box">
          <input type="text" name="" required="" v-model="newPrize.prizeName" />
          <label>prize name</label>
        </div>
        <div class="labelbox">
        <div class="text-box">
          <label>description</label>
          <textarea
            class="textboxform"
            rows="4"
            cols="20"
            v-model="newPrize.description"
          ></textarea>
        </div>
        </div>
        <div class="beeform">
        <label>which busy bee(s) can earn this prize?</label>
        <div
          v-for="member in memberList"
          :key="member.id"
          class="busy-bee-list"
        >
          <input
            type="checkbox"
            :id="member.id"
            :value="member.id"
            v-model="newPrize.memberIds"
          />
          <label :for="member.id"
            >{{ member.firstName }} {{ member.lastInitial }}</label
          >
        </div>
        </div>

        <div class="user-box">
          <input
            type="text"
            name=""
            required=""
            v-model.number="newPrize.goalInMinutes"
          />
          <label>minutes needed to win:</label>
        </div>
        <div class="user-box">
          <input
            type="text"
            name=""
            required=""
            v-model.number="newPrize.maxWinners"
          />
          <label>max winners possible:</label>
        </div>

        <div>
          <label>start date:</label>
          <input type="date" class="form-date" v-model="newPrize.startDate" />
        </div>
        <div>
          <label>end date:</label>
          <input type="date" class="form-date" v-model="newPrize.endDate" />
        </div>

        <a href="#" v-on:click="savePrize()">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          SUBMIT
        </a>
      </form>
    </div>

    
  </div>
</template>


<script>
import hiveService from "../services/HiveService";

export default {
  name: "new-prize-form",
  props: ["prizeToEdit"],
  data() {
    return {
      newPrize: {
        prizeId: 0,
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
      memberList: [],
    };
  },
  created() {
    hiveService.getHiveMembers().then((response) => {
      this.memberList = response.data;
    });

    if (this.prizeToEdit) {
      this.newPrize = this.prizeToEdit;
    }
  },
  methods: {
    savePrize() {
      hiveService.addPrize(this.newPrize);
      //this.$store.commit('ADD_PRIZE_TO_STORE', this.newPrize)
      this.$store.commit("RESET_PARENT_VIEW", this.$store.state);
      this.$store.commit("INCREMENT_PARENT_VIEW", this.$store.state);
    },
  },
};
</script>

<style>
/* .new-prize-input {
  text-align: center;
}

.prize-form {
  margin: 20px;
  border: 4px black groove;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  width: 300px;
  padding: 5px;
}

.prize-form button {
  margin: 10px;
  font-size: 1rem;
} */
</style>