<template>
  <div id="prize-box">
      <!-- <h2 id="my-prizes">my prizes!</h2> -->

      <div 
      class="my-prize-display" 
      v-for="prize in prizeList"
      v-bind:key="prize.id">

      <prize-details :prizeId="prize.id" />



      </div>

      
  </div>
</template>

<script>

import hiveService from "../services/HiveService.js";
import PrizeDetails from './PrizeDetails.vue';

export default {
  name: 'prize-list',
  components: { PrizeDetails },

  data(){
    return {
      prizeList: [],
    }
  },


  created(){
     hiveService.getPrizesByMember(this.$route.params.id)
     .then((response) => {
       this.prizeList = response.data;
     })

    

  }

}
</script>

<style>

#my-prizes{
  margin-bottom: 10px;
}

.my-prize-display{
  margin: 10px;
 
}

#prize-box{
 display: flex;
  flex-direction: row;
  justify-content: center;
}





</style>
