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

    <member-books id="membooks" v-if="this.dynamicParentPageView === 1" />

    <!-- <button id="add-book-toggle" class="button-56" @click="incrementView()" v-if="this.dynamicParentPageView === 1">add new book</button> -->

    <!-- <button id="add-book-toggle" class="button-56" @click="decrementView()" v-if="this.dynamicParentPageView === 2">back</button> -->
    
    <prize-list id="parent-prizes" v-if="dynamicParentPageView === 1" />

    <new-book-form v-if="this.dynamicParentPageView === 2" id="book-form" />
    <manual-entry-form
      v-if="this.dynamicParentPageView === 8"
      id="manual-book-form"
    />

    <pending-reading-list id="pend-prize" v-if="dynamicParentPageView === 10" />

    <div class="floor-button">
      <a
        class="go-back-btn"
        href="#"
        @click="decrementView()"
        v-if="dynamicParentPageView === 16"
      >
        <span class="circle"> </span>
        <span class="title">back</span>
        <span class="title title-hover"></span>
      </a>
    </div>

    <all-prize-list id="all-prize-list" v-if="dynamicParentPageView === 15" />
    <new-prize-form id="new-prize-form" v-if="dynamicParentPageView === 16" />

    <all-member-books
      id="all-member-books"
      v-if="dynamicParentPageView === 20"
    />

    <add-member id="add-new-member-form" v-if="dynamicParentPageView === 5" />

    <reading-activity-dispay
      id="all-activity"
      v-if="dynamicParentPageView === 30"
    />

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
        class="btn-slide"
        href="#"
        @click="incrementView()"
        v-if="dynamicParentPageView === 15"
      >
        <span class="circle"> </span>
        <span class="title">add new prize</span>
        <span class="title title-hover"></span>
      </a>
    </div>
  </div>
</template>

<script>
import MemberBooks from "../components/MemberBooks.vue";
import MemberHeader from "../components/MemberHeader.vue";
import NewBookForm from "../components/NewBookForm.vue";
import SideBar from "../components/SideBar.vue";
import ParentNav from "../components/ParentNav.vue";
import PendingReadingList from "../components/PendingReadingList.vue";
import AllPrizeList from "../components/AllPrizeList.vue";
import NewPrizeForm from "../components/NewPrizeForm.vue";
import AllMemberBooks from "../components/AllMemberBooks.vue";
import ManualEntryForm from "../components/ManualEntryForm.vue";
import AddMember from "./AddMember.vue";
import ReadingActivityDispay from "../components/ReadingActivityDisplay.vue";
import PrizeList from "../components/PrizeList.vue";

export default {
  components: {
    PrizeList,
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
    AddMember,
    ReadingActivityDispay,
  },
  name: "parent-home",
  data() {
    return {};
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

<style scoped>
.hidden {
  display: none;
}

.parent-homepage {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "nav nav nav nav nav "
    "sidebar memheader memheader memheader . "
    "sidebar prizes prizes prizes ."
    "sidebar prizes prizes prizes ."
    "sidebar mainevent mainevent mainevent . "
    "sidebar mainevent mainevent mainevent . "
    "sidebar mainevent mainevent mainevent . "
    "sidebar . addbookz . . ";
  margin-left: 20px;
}



#all-activity,
#add-new-member-form,
#all-prize-list,
#new-prize-form,
#all-member-books,
#pend-prize,
#manual-book-form,
#add-new-member-form{
  grid-area: mainevent;
  margin: 0 auto;
  grid-column: 2/4;
}



#parent-prizes {
  grid-area: prizes;
  display: flex;
  margin-top: 20px;
  justify-content: space-around;
  flex-wrap: wrap;
}

.floor-button {
  grid-area: addbookz;
  grid-column: 3;
  text-align: center;
  margin: auto;
}

#parent-side-bar {
  grid-area: sidebar;
}

#member-header {
  grid-area: memheader;
}

#membooks,
#pend-prize,
#new-prize-form,
#all-member-books,
#all-activity,
#all-prize-list,
#add-new-member-form,
#book-form 
{
  grid-area: mainevent;
  margin-top: 10px;
  margin-bottom: 15px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}
#pend-prize,
#all-activity {
  flex-direction: column;
  margin-top: 10px;
}



#all-member-books {
  display: flex;
}

#book-form {
  grid-area: mainevent;
  margin: 0 auto;
  margin-top: 10px;
  margin-bottom: 10px;
  grid-column: 3;
}

.parent-nav {
  grid-area: nav;
}
</style>