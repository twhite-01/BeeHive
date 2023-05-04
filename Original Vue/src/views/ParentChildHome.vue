<template>
  <div class="parent-homepage">
    <div id="parent-nav">
      <parent-nav />
    </div>

    <side-bar id="parent-side-bar" />

    <member-header
      :member-id="this.$store.state.currentMember"
      id="member-header"
    />

    <!-- <member-details :currentMember="this.$store.state.currentMember" /> -->

    <member-books id="membooks" v-if="this.dynamicParentPageView === 1" />

   <div class="floor-button">
      <a
        class="btn-slide"
        href="#"
        @click="incrementView()"
        v-if="this.dynamicParentPageView === 1"
      >
        <span class="circle"> </span>
        <span class="title">add new book</span>
        <span class="title title-hover"></span>
      </a>
    </div>

    <div class="floor-button">
      <a
        class="go-back-btn"
        href="#"
        @click="decrementView()"
        v-if="dynamicParentPageView === 2"
      >
        <span class="circle"> </span>
        <span class="title">back</span>
        <span class="title title-hover"></span>
      </a>
    </div>

    <new-book-form v-if="this.dynamicParentPageView === 2" id="book-form" />
    <manual-entry-form v-if="this.dynamicParentPageView === 8" />

    <pending-reading-list v-if="dynamicParentPageView === 10" />

    <all-prize-list v-if="dynamicParentPageView === 15" />
    <new-prize-form v-if="dynamicParentPageView === 15" />

    <all-member-books id="all-child-books" v-if="dynamicParentPageView === 20" />
    <reading-activity-dispay
      id="all-activity"
      v-if="dynamicParentPageView === 30"
    />
  </div>
</template>

<script>
import MemberBooks from "../components/MemberBooks.vue";
//import MemberCard from "../components/MemberCard.vue";
import MemberHeader from "../components/MemberHeader.vue";
import NewBookForm from "../components/NewBookForm.vue";
import SideBar from "../components/SideBar.vue";
import ParentNav from "../components/ParentNav.vue";
import PendingReadingList from "../components/PendingReadingList.vue";
import AllPrizeList from "../components/AllPrizeList.vue";
import NewPrizeForm from "../components/NewPrizeForm.vue";
import AllMemberBooks from "../components/AllMemberBooks.vue";
import ManualEntryForm from "../components/ManualEntryForm.vue";
// import MemberDetails from "../components/MemberDetails.vue";
import ReadingActivityDispay from "../components/ReadingActivityDisplay.vue";

export default {
  components: {
    MemberBooks,
    MemberHeader,
    SideBar,
    NewBookForm,
    ParentNav,
    PendingReadingList,
    AllPrizeList,
    NewPrizeForm,
    AllMemberBooks,
    ManualEntryForm,
    // MemberDetails,
    ReadingActivityDispay,
  },
  name: "parent-child-home",
  data() {
    return {
      kidMemberId: this.$route.params.childId,
    };
  },

  methods: {
    incrementView() {
      this.$store.commit("INCREMENT_PARENT_VIEW", this.$store.state);
    },

    decrementView() {
      this.$store.commit("DECREMENT_PARENT_VIEW", this.$store.state);
    },
  },

  computed: {
    dynamicParentPageView() {
      return this.$store.state.parentPageView;
    },
  },
};
</script>

<style>
.hidden {
  display: none;
}

.parent-homepage {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "nav nav nav nav nav "
    "sidebar memheader memheader memheader . "
    "sidebar mainevent mainevent mainevent . "
    "sidebar mainevent mainevent mainevent . "
    "sidebar mainevent mainevent mainevent . "
    "sidebar . . . . "
    "sidebar . addbook . . ";
}

#parent-side-bar {
  grid-area: sidebar;
}

#all-child-books,
#all-activity,
#book-form{
  display: flex;
  justify-content: space-around;
  grid-area: mainevent;
  flex-wrap: wrap;
}


#member-header {
  grid-area: memheader;
}

#membooks {
  grid-area: mainevent;
  margin-bottom: 15px;
  display: flex;
    justify-content: center;
}

#book-form {
  grid-area: mainevent;
  margin: 0 auto;
  grid-column: 3;
  margin-top: 10px;
}

#parent-nav {
  grid-area: nav;
}
</style>