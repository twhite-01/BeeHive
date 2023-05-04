<template>
  <div class="full-book-display" v-if="showDisplay">
    <div class="book-display">
      <div v-if="this.book.userCreated" >
      <img src="../assets/completed.png" alt="">
      </div>
      <div class="title-author">
        <h2 id="book-title">{{ book.title }}</h2>

        <h3 id="book-author">{{ book.author }}</h3>
      </div>

      <div id="book-cover-img">
        <img id="book-cover-url" :src="book.coverUrl" alt="Book Cover URL" />
      </div>

      <!-- <div class="log-button">
        <button @click="toggleBookDisplay(book.id)">log reading activity</button>
      </div> -->
    </div>
  </div>
</template>
<script>
import hiveService from "../services/HiveService.js";
//import ReadingActivityForm from './ReadingActivityForm.vue'

export default {
  name: "book-display",
  props: ["bookId"],
  //components: {ReadingActivityForm},

  data() {
    return {
      book: {
        id: 0,
        title: "",
        author: "",
        coverUrl: "",
      },
      showDisplay: true,
    };
  },

  created() {
    hiveService.getBook(this.bookId).then((response) => {
      this.book = response.data;
    });
  },

  methods: {
    toggleBookDisplay(id) {
      this.$store.commit("SET_BOOK", id);
      this.showDisplay = false;
    },
  },
};
</script>

<style>

.completed{
  background-image: url('../assets/completed.png');
  background-repeat: repeat;
}

.book-display {
  font-family: "Special Elite", cursive;
  border-radius: 8px;
  border: 10px black groove;
  padding: 10px;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
  width: 300px;
   height: 430px; 
  display: flex;
  flex-direction: column;
  margin: 10px;
  background: rgb(255, 254, 249);
  text-align: center;
  padding-bottom: 10px;
}

/* .log-button {
  padding: 12px;
  margin-bottom: 5px;
  margin-top: 5px;
} */

.title-author {
  margin-bottom: 5px;
  margin-top: 5px;
  height: 2rem;
  flex-basis: 50%;
  display: flex;
  flex-direction: column;

  justify-content: space-evenly;
}

#book-title {
  margin-top: 5px;
  margin-bottom: 0px;
  font-size: 1.5rem;
}

#book-author {
  margin-bottom: 15px;
}

#book-cover-img {
  width: 66%;
  width: 150px;
  height: 200px;
  object-fit: contain;
  margin: auto;
  margin-bottom: 15px;
}

#book-cover-url {
  width: 100%;
  height: 100%;
}
</style>