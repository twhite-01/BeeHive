<template>
  <div>
    <div v-if="showDisplay" id="book-display-and-button">
      <div
        class="member-book-list"
        id="member-home-book-display"
        v-for="bookInt in bookList"
        v-bind:key="bookInt"
      >
        <book-display v-bind:bookId="bookInt" />

        <div>
          <a class="btn-slide" href="#" @click="toggleDisplay(bookInt)">
            <span class="circle"> </span>
            <span class="title">log reading</span>
            <span class="title title-hover"></span>
          </a>
        </div>
        <!-- <button id="log-reading-activity-button" class="button-56" @click="toggleDisplay(bookInt)">log reading activity</button> -->
      </div>
    </div>

    <div v-if="!showDisplay">
      <reading-activity-form />
      <!-- <button @click="toggleDisplay(0)">cancel</button> -->
      <div>
        <a class="go-back-btn" href="#" @click="toggleDisplay(0)">
          <span class="circle"> </span>
          <span class="title">cancel</span>
          <span class="title title-hover"></span>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
//import hiveService from "../services/HiveService.js";
import BookDisplay from "./BookDisplay.vue";
import ReadingActivityForm from "./ReadingActivityForm.vue";

export default {
  name: "member-books",
  components: { BookDisplay, ReadingActivityForm },
  data() {
    return {
      //bookList: [],
    };
  },

  methods: {
    toggleDisplay(id) {
      this.$store.commit("SET_BOOK", id);

      // this.$store.commit('RESET_PARENT_VIEW', 0)
      this.$store.commit("SET_SHOWDISPLAY_BOOL", this.state);
      //this.showDisplay = !this.showDisplay;
    },
  },

  computed: {
    bookList() {
      return this.$store.state.threeDisplayBooks;
    },

    showDisplay() {
      return this.$store.state.showDisplay;
    },
  },
};
</script>

<style>
.member-book-list {
  display: flex;
  flex-direction: column;

  justify-content: space-around;
}

#book-display-and-button {
  display: flex;
  justify-content: space-evenly;
  text-align: center;
}

#back-btn {
  margin: auto;
  text-align: center;
}

/* .button-56 {
  align-items: center;
  background-color: green;
  border: 2px solid #111;
  border-radius: 8px;
  box-sizing: border-box; 
  color: #111;
  cursor: pointer;
  display: flex;
  font-family: Inter,sans-serif;
  font-size: 16px;
  width: fit-content;
  height: 48px;
  justify-content: center;
  line-height: 24px;
  max-width: 100%;
  padding: 0 25px;
  position: relative;
  text-align: center;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  margin-left:33px
}

.button-56:after {
  background-color: #111;
  border-radius: 8px;
  content: "";
  display: block;
  height: 48px;
  left: 0;
  width: 100%;
  position: absolute;
  top: -2px;
  transform: translate(8px, 8px);
  transition: transform .2s ease-out;
  z-index: -1;
}

.button-56:hover:after {
  transform: translate(0, 0);
}

.button-56:active {
  background-color: green;
  outline: 0;
}

.button-56:hover {
  outline: 0;
}

@media (min-width: 768px) {
  .button-56 {
    padding: 0 40px;
  }
}  */
</style>