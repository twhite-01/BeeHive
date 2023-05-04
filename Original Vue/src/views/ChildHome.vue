<template>
  <div>
    <div class="child-home-container">
      <!-- <member-header id="member-header"/> -->
      <child-header />
      <prize-list v-if="dynamicChildPageView === 1" />
      <member-books id="membooks" v-if="dynamicChildPageView === 1" />
      <all-member-books v-if="dynamicChildPageView === 10" />

      <div class="new-book-btn">
        <a
          class="btn-slide"
          href="#"
          @click="incrementView()"
          v-if="this.dynamicChildPageView === 1"
        >
          <span class="circle"> </span>
          <span class="title">add new book</span>
          <span class="title title-hover"></span>
        </a>
      </div>

      <div id="back-btn">
        <a
          class="go-back-btn"
          href="#"
          @click="decrementView()"
          v-if="this.dynamicChildPageView === 2"
        >
          <span class="circle"> </span>
          <span class="title">back</span>
          <span class="title title-hover"></span>
        </a>
      </div>

      <new-book-form v-if="dynamicChildPageView === 2" class="child-book-form" id="child-book-form-id"/>
      <manual-entry-form id="manual-entry-form" v-if="dynamicChildPageView === 8"/>
      <reading-activity-display id="activity-display" v-if="dynamicChildPageView === 30" />
    </div>
  </div>
</template>

<script>
//import MemberHeader from '../components/MemberHeader.vue'
import MemberBooks from "../components/MemberBooks.vue";
import PrizeList from "../components/PrizeList.vue";
import ReadingActivityDisplay from "../components/ReadingActivityDisplay.vue";
import NewBookForm from "../components/NewBookForm.vue";
import AllMemberBooks from "../components/AllMemberBooks.vue";
import ChildHeader from "../components/ChildHeader.vue";
import ManualEntryForm from "../components/ManualEntryForm.vue"

export default {
  name: "child-home",

  components: {
    PrizeList,
    //MemberHeader,
    MemberBooks,
    ReadingActivityDisplay,
    NewBookForm,
    AllMemberBooks,
    ChildHeader,
    ManualEntryForm
  },

  methods: {
    incrementView() {
      this.$store.commit("INCREMENT_CHILD_VIEW", this.$store.state);
    },

    decrementView() {
      this.$store.commit("DECREMENT_CHILD_VIEW", this.$store.state);
    },
  },

  computed: {
    dynamicChildPageView() {
      return this.$store.state.childPageView;
    },
  },
};
</script>


<style>

.child-home-container {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  flex-wrap: wrap;
}

.child-book-form {
  margin: 0 auto;
  display: flex;
  justify-content: center;
  flex-direction: column;
  margin-left: 20px;
}

#child-book-form-id{
  margin: 0 auto;
  display: flex;
  justify-content: center;
  flex-direction: column;
  flex-wrap: wrap;
}

.new-book-btn {
  margin: auto;
}

#activity-display,
#manual-entry-form{
  margin-top: 20px;
}
</style>