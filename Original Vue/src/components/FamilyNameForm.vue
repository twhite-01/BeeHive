<template>
  <div>
      <h2>Welcome to Bookhive!!</h2>
      <h3>To start your un-bee-lievable hive, we need a few things from you.</h3>
      <form  v-on:submit="saveFamilyName">
      <label for="family-name-input">
      <input id="family-name-input" type="text" placeholder="Your Hive Name" v-model="hiveName.familyName"></label>
      <!--<button @click="saveFamilyName">Save</button>-->
      </form>
      <h3>Now you get to bee the first hive member: </h3>
  </div>
</template>
<script>
import HiveService from '../services/HiveService'
export default {
    name: "family-name-form",
    data(){
      return {
        hiveName: {
          id: this.$store.state.user.id,
          familyName: '',
        }, 
        showMe: false,
      }
    },
    methods: {
      saveFamilyName(){
        HiveService.addHiveName(this.hiveName.id, this.hiveName).then((response)=>{
          this.showMe= true
        if(response.status != 500){
          //do nothing
        }
        }
        )
      }
    }
}
</script>
<style>

.hidden{
  display: none;
}
</style>