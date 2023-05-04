<template>
  <div>
    <div class="form-box" v-show="!bookSubmitted">
      <h2>add a book!</h2>
      <form>
        <div class="user-box">
          <input type="text" name="" required="" v-model="searchText" />
          <label>title</label>
        </div>
        <div class="either-or">-or-</div>
        <div class="user-box">
          <input type="text" name="" required="" v-model="searchIsbn" />
          <label>isbn</label>
        </div>
        <a href="#" v-on:click="searchBook(book.title)" class="add-book-submit">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          search
        </a>
      </form>
    </div>

    <div
      id="search-results"
      v-for="book in searchResults"
      :key="book.id"
      @click="saveBook(book)"
    >
      <search-results :bookResult="book" />
    </div>

<reading-activity-form v-if="bookSubmitted" />

    <!-- <h2>add a book!</h2>
    <form v-on:submit.prevent class="book-form" v-show="!bookSubmitted">
      <div id="title-isbn-inputs">
        <label for="title-input"></label>
        <input
          placeholder="type book title here"
          type="text"
          v-model="searchText"
        />

        <span id="either-or">-or-</span>
        

        <label for="isbn-input"></label>
        <input placeholder="type isbn here" type="text" v-model="searchIsbn" />
      </div>

      <button id="" type="submit" v-on:click="searchBook(book.title)">
        search
      </button>
    </form> -->

    <!-- <button type="submit" v-on:click="enterManually()">can't find your book?</button> -->

    <div>
      <a class="not-bee-btn-slide" href="#" @click="goSomewhere(8)" id="cant-find">
        <span class="circle"> </span>
        <span class="title">can't find your book?</span>
        <span class="title title-hover">enter manually</span>
      </a>
    </div>

    <!-- <div>
      <a class="go-back-btn" href="#" @click.prevent="goSomewhere(1)">
        <span class="circle"> </span>
        <span class="title">back</span>
        <span class="title title-hover"></span>
      </a>
    </div> -->

    <!-- <reading-activity-form v-if="bookSubmitted" /> -->
  </div>
</template>

<script>
import hiveService from "../services/HiveService";
import ReadingActivityForm from "./ReadingActivityForm.vue";
import SearchResults from "./SearchResults.vue";

export default {
  components: { ReadingActivityForm, SearchResults },
  name: "new-book-form",

  data() {
    return {
      bookSubmitted: false,
      newBookId: 0,
      searchText: "",
      searchIsbn: "",
      book: {
        bookId: 0,
        title: "wow",
        author: "great hair",
        isbn: "yes",
        coverUrl: "nice",
      },
      readyToSave: false,
      searchResults: [],
      isbnResult: {},
    };
  },

  methods: {
    setBookId(id) {
      this.$store.commit("SET_BOOK", id);
    },
    saveBook(book) {
      hiveService
        .addNewBook(book)
        .then((response) => {
          if (response.status === 200) {
            this.newBookId = response.data;
            this.setBookId(this.newBookId);
            this.$store.commit("SET_SHOWDISPLAY_BOOL", this.$store.state);
            this.bookSubmitted = true;
            this.searchResults = [];
          }
        })
        .catch((error) => {
          if (error.request) {
            this.errorMsg = "Sorry The New Book Was Not Added Successfully.";
            this.$router.push({ name: "home" });
          }
          this.$store.commit("JUMP_TO_PARENT_VIEW", 1)
        });
    },

    searchBook() {
      if (this.searchText == "" && this.searchIsbn != "") {
        hiveService.searchGoogleIsbns(this.searchIsbn).then((response) => {
          let isbnResult = response.data;
          this.searchResults.push(isbnResult);
        });
      } else if (this.searchText != "" && this.searchIsbn == "") {
        this.searchText = this.searchText.replaceAll(" ", "-");

        hiveService.searchGoogleBooks(this.searchText).then((response) => {
          this.searchResults = response.data;
        });
      }
    },

    goSomewhere(int) {
      
      if(this.$store.state.currentMember.child){
        this.$store.commit("JUMP_TO_CHILD_VIEW", int)
      }else {
        this.$store.commit("JUMP_TO_PARENT_VIEW", int);
      }
    },
  },
};
</script>

<style>
/* .book-form {
  margin: 20px auto;

  border: 2px black groove;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  width: 300px;
} */

/* .book-form input {
  width: 75%;
  margin: 10px;
  font-size: 0.75rem;
}

.book-form button {
  margin: 10px;
  font-size: 0.75rem;
  width: 40%;
} */

#search-results {
  width: 500px;
  margin: auto;
  
}

.book-page {
  display: flex;
  flex-direction: column;
  text-align: center;
}

#cant-find {
  margin: auto;
  margin-top: 10px;
}

#go-away {
  margin: auto;
  margin-top: 15px;
}

#cant-text {
  margin-top: 5px;
}

#search-results{
  
}
/* 
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
} */
</style>
