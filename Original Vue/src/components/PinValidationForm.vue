<template>
  <div class="pin-form">
    <form>
      <label for="enter-pin">enter your pin: </label>
      <input id="enter-pin" type="password" v-model="memberPin" />

      <button type="submit" id="pin-btn" @click.prevent="checkPin()">
        enter
      </button>
    </form>
  </div>
</template>

<script>
import HiveService from "../services/HiveService";
export default {
  name: "pin-validation-form",

  data() {
    return {
      memberPin: "",
    };
  },

  methods: {
    checkPin() {
      let memberId = this.$route.params.id;

      HiveService.getMember(memberId).then((response) => {
        let dbMemberPin = response.data.pin;
        if (this.memberPin === dbMemberPin) {
          this.$store.commit("RESET_PARENT_VIEW", this.$store.state);
          this.$store.commit("INCREMENT_PARENT_VIEW", this.$store.state);
          HiveService.getPendingRequestsByUserId().then((response) => {
            this.$store.commit("SET_REQUEST_LIST", response.data);
          });

          this.$store.commit("SET_CURRENT_PARENT", memberId);

          this.$router.push({ name: "parent-home", params: { id: memberId } });
        } else {
          alert("Invalid Pin");
          this.$router.push({ name: "hive-members" });
        }
      });
    },
  },
};
</script>

<style>
.pin-form {
  border: 2px solid black;
  background:rgb(255, 254, 249);
  display: flex;
  flex-flow: column;
  margin: 0 auto;
  width: 200px;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
  border: 5px black ridge;
}

#enter-pin {
  width: 50%;
  margin: 10px auto;
  display: block;
}

#pin-btn {
  border-radius: 50px;
}
</style>