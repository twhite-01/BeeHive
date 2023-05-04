<template>
  <div>
    <div
      class="pending-reqs"
      v-for="pending in pendingList"
      v-bind:key="pending.id"
    >
      <pending-reading-details :pending-activity="pending" />
    </div>
  </div>
</template>

<script>
import hiveService from "../services/HiveService.js";
import PendingReadingDetails from "./PendingReadingDetails.vue";

export default {
  name: "pending-reading-list",
  components: { PendingReadingDetails },

  created() {
    hiveService.getPendingRequestsByUserId().then((response) => {
      this.pendingList = response.data;
    });
  },

  computed: {
    pendingList() {
      if (this.$store.state.requestList.length == 0) {
        this.$store.commit("RESET_PARENT_VIEW", this.$store.state);
        this.$store.commit("INCREMENT_PARENT_VIEW", this.$store.state);
      }
      return this.$store.state.requestList;
    },
  },
};
</script>

<style>
.pending-reqs {
  display: flex;
  flex-direction: column;
}
</style>