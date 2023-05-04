<template>
  <div>
    <!-- <h2 id="my-prizes">all prizes</h2> -->

    <div
      class="my-prize-display"
      v-for="prize in prizeList"
      v-bind:key="prize.id"
    >
      <!-- <prize-details :prizeId="prize.id" /> -->

      <edit-prize :prize-id="prize.id" />
    </div>
  </div>
</template>

<script>
import hiveService from "../services/HiveService.js";

import EditPrize from "./EditPrize.vue";

export default {
  name: "prize-list",
  components: { EditPrize },

  data() {
    return {
      prizeList: [],
    };
  },

  created() {
    hiveService.getPrizesByUser().then((response) => {
      this.prizeList = response.data;
    });
  },
};
</script>

<style>
#my-prizes {
  margin-bottom: 10px;
}

#my-prizes {
  font-size: 20px;
  font-family: "Livvic", sans-serif;
}
</style>