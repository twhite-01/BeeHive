<template>
  <div>
<!-- 
    <h2 id="reading-header">all reading activity</h2> -->
      
      <div 
      class="my-reading-display" 
      
      v-for="reading in readingList"
      v-bind:key="reading.id">

      <reading-activity-details  :activity="reading" />



      </div>

     
  </div>
</template>

<script>
//import hiveService from '../services/HiveService.js';
import ReadingActivityDetails from './ReadingActivityDetails.vue'

export default {
  name: 'reading-activity-display',
  components: {ReadingActivityDetails},

  data(){
    return {
    }
  },

    computed: {
      readingList(){
        return this.$store.state.readingActivityList;
      }
    }
  
}

  
</script>

<style>

#reading-header{
  margin-bottom: 5px;
  font-size: 1.5rem;
  
}

.my-reading-display{
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

  background-color: rgb(255, 254, 249);
  border-image-slice: 1;
}

</style>