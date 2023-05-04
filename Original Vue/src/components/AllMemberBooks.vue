<template>
  <div id="all-books-container">
    <div id="all-books-display" v-for="book in allBooks" v-bind:key="book.id">
      <book-display v-bind:bookId="book.id" />
    </div>
  </div>
</template>

<script>
import hiveService from "../services/HiveService.js";
import BookDisplay from "./BookDisplay.vue";

export default {
  name: "all-member-books",
  components: { BookDisplay },
  data() {
    return {
      allBooks: [],
    };
  },

  created() {
     if(this.$route.params.childId){
       hiveService.getAllBooksForMember(this.$route.params.childId).then((response) => {
         this.allBooks = response.data;
       });
     }else{

    hiveService.getAllBooksForMember(this.$route.params.id).then((response) => {
      this.allBooks = response.data;
    }); 
     }
  },

};
</script>

<style>
#all-books-container {
  display: flex;
  justify-content: center;
 
}
</style>