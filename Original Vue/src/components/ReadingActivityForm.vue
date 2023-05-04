<template>
  <div class="reading-activity-form">
    <div class="book-itself">
    <book-display :book-id="newActivity.bookId" />
</div>
    <div class="book-form-box">
      <h2>reading activity form</h2>
      <form v-on:submit.prevent>
        <div class="user-box">
          <input
            type="text"
            name=""
            required=""
            v-model="newActivity.minutesRead"
          />
          <label>minutes read</label>
        </div>
        <div class="text-box">
          <label>notes:</label>
          <textarea
            rows="4"
            cols="20"
            v-model="newActivity.notes"
            class="textboxform"
          ></textarea>
        </div>

        <div class="format-dropdown">
          <label>reading format:</label>
          <select name="format" v-model.number="newActivity.formatId">
            <option value="1">paper</option>
            <option value="2">digital</option>
            <option value="3">audio</option>
            <option value="4">read-aloud reader</option>
            <option value="5">read-aloud listener</option>
            <option value="6">other format</option>
          </select>
        </div>

        <div class="box-check">
          <label>book completed: </label>
          <input
            type="checkbox"
            name="book-complete"
            class="book-complete"
            v-model="memberBook.completed"
          />
        </div>

        <a href="#" v-on:click="saveActivity()">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          submit
        </a>
      </form>
    </div>

    <!-- <form v-on:submit.prevent class="activity-form">
      <div>
      <label for="minutes-read">minutes read</label>
      <input
        class="minutes-read"
        type="text"
        v-model.number="newActivity.minutesRead"
      />
      </div>

      <div>
      <label for="notes-input">notes</label>
      <textarea rows="4" cols="20" v-model="newActivity.notes"></textarea>
      </div>

      <div>
      <label for="format">reading format:</label>
      <select id="format" name="format" v-model.number="newActivity.formatId">
        <option value="1">paper</option>
        <option value="2">digital</option>
        <option value="3">audio</option>
        <option value="4">read-aloud reader</option>
        <option value="5">read-aloud listener</option>
        <option value="6">other format</option>
      </select>
      </div>
      
      <div>
      <label for="book-complete">book completed</label>
      <input type="checkbox" name="book-complete" id="book-complete" v-model="memberBook.completed">
      </div>

      <div>
      <button id="submit-activity" type="submit" v-on:click="saveActivity()">
        submit activity
      </button>
      </div>
    </form> -->
  </div>
</template>

<script>
import hiveService from "../services/HiveService";
import BookDisplay from "./BookDisplay.vue";

export default {
  components: { BookDisplay },
  name: "reading-activity-form",
  data() {
    return {
      newActivity: {
        memberId: this.$route.params.id,
        bookId: this.$store.state.storeBookId,
        minutesRead: 0,
        notes: "",
        formatId: 0,
        requestId: 1,
        dateRead: "",
      },
      memberBook: {
        bookId: this.$store.state.storeBookId,
        memberId: this.$route.params.id,
        completed: false,
      },
      currentMember: [],
    };
  },
  created() {
    let today = new Date();
    let dateString = today.toISOString().substring(0, 10);
    this.newActivity.dateRead = dateString;

    hiveService.getMember(this.$route.params.id).then((response) => {
      this.currentMember = response.data;
      if (this.currentMember.child) {
        this.newActivity.requestId = 2;
        
      }
    });

    if(this.$route.params.childId){
      this.newActivity.memberId = this.$route.params.childId;
      this.memberBook.memberId = this.$route.params.childId;
    }
  },

  methods: {
    setBookId(id) {
      this.$store.commit("SET_BOOK", id);
    },

    saveActivity() {
      hiveService
        .addActivity(this.$route.params.id, this.newActivity)
        .then((response) => {
          if (response.status === 200) {
            hiveService
              .updateMemberBookDto(this.memberBook)
              .then((response) => {
                if (response.status === 200) {
                  if(this.$route.params.childId){
                  hiveService.getMostRecentBooks(this.$route.params.childId)
                    .then((response) => {
                      //ok
                      this.$store.commit("SET_BOOK_DISPLAY", response.data);
                    });
                  }else{
                  hiveService
                    .getMostRecentBooks(this.$route.params.id)
                    .then((response) => {
                      //ok
                      this.$store.commit("SET_BOOK_DISPLAY", response.data);
                    });
                  }
                  this.$store.commit("RESET_PARENT_VIEW", this.$store.state);
                  this.$store.commit(
                    "INCREMENT_PARENT_VIEW",
                    this.$store.state
                  );

                  this.$store.commit("RESET_CHILD_VIEW", this.$store.state);
                  this.$store.commit("INCREMENT_CHILD_VIEW", this.$store.state);

                  this.$store.commit("SET_SHOWDISPLAY_BOOL", this.$store.state);

                  // this.$router.push({name: 'parent-home', params: {id: this.$route.params.id}})
                }
              });
          }
        });

      this.setBookId(0);
      // this.$router.push({ name: "home" });
    },
  },
};
</script>

<style>

   .reading-activity-form {
  display: flex;
  justify-content: center;
 
}    
</style>