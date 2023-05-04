<template>
  <div>
    <!-- <form class="manual-book-form" v-show="!bookSubmitted"> 

        <label for="">Title</label>
        <input class="title-input" type="text" v-model="book.title">

        <label for="">Author</label>
        <input class="author-input" type="text" v-model="book.author">

        <label for="">ISBN</label>
        <input class="isbn-input" type="text" v-model="book.isbn">

        <button id="manual-save" type="submit" v-on:click.prevent="saveBook()">add book</button>

        </form> -->

    <div class="form-box" v-show="!bookSubmitted">
      <h2>manual book form</h2>
      <form>
        <div class="user-box">
          <input type="text" name="" required="" v-model="book.title" />
          <label>title</label>
        </div>
        <div class="user-box">
          <input type="text" name="" required="" v-model="book.author" />
          <label>author</label>
        </div>
        <div class="user-box">
          <input type="text" name="" required="" v-model="book.isbn" />
          <label>isbn</label>
        </div>
        <a href="#" v-on:click.prevent="saveBook()">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          submit
        </a>
      </form>
    </div>

    <reading-activity-form v-if="bookSubmitted" />
  </div>
</template>

<script>
import hiveService from "../services/HiveService";
import ReadingActivityForm from "./ReadingActivityForm.vue";

export default {
  name: "manual-entry-form",
  components: { ReadingActivityForm },

  data() {
    return {
      bookSubmitted: false,
      newBookId: 0,
      book: {
        bookId: 0,
        title: "",
        author: "",
        isbn: "",
        coverUrl:
          "https://d29xot63vimef3.cloudfront.net/image/action-comics/1-1.jpg",
      },
    };
  },

  methods: {
    setBookId(id) {
      this.$store.commit("SET_BOOK", id);
    },
    saveBook() {
      hiveService
        .addNewBook(this.book)
        .then((response) => {
          if (response.status === 200) {
           //ok
            this.newBookId = response.data;
            this.setBookId(this.newBookId);
            this.$store.commit("SET_SHOWDISPLAY_BOOL", this.$store.state);
            this.bookSubmitted = true;
            //   this.$store.commit('JUMP_TO_PARENT_VIEW', 2)
          }
        })
        .catch((error) => {
          if (error.request) {
            this.errorMsg = "Sorry The New Book Was Not Added Successfully.";
            this.$router.push({ name: "home" });
          }
        });
    },
  },
};
</script>

<style>
.book-form {
  margin: 20px auto;

  border: 2px black groove;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  width: 300px;
}

.book-form input {
  width: 75%;
  margin: 10px;
  font-size: 0.75rem;
}

.book-form button {
  margin: 10px;
  font-size: 0.75rem;
  width: 40%;
}

#search-results {
  width: 500px;
  margin: auto;
}

#title-isbn-inputs {
  display: flex;
  flex-direction: column;
  margin: auto;
}

#either-or {
  margin: auto;
}

.isbn-input {
  margin: 0 auto;
}
</style>
