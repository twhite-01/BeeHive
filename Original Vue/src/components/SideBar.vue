<template>
  <div>
    <!-- <a href="#" @click.prevent="addNewMember()">add member</a> -->

    <div class="side-bar-display">
      <div
        class="member-card"
        v-for="member in memberList"
        v-bind:key="member.id"
        id="member-card-sizing"
        @click="routeToParentChild(member)"
      >
        <member-card
          v-if="member.id == parentId || member.child"
          :current-member="member"
        />
      </div>
    </div>
  </div>
</template>

<script>
import MemberCard from "./MemberCard.vue";
import hiveService from "../services/HiveService.js";

export default {
  name: "side-bar",

  components: { MemberCard },

  data() {
    return {
      memberList: this.$store.state.hiveMembers,
      parentId: this.$store.state.parentPathParam,
    };
  },

  created() {
    hiveService.getHiveMembers().then((response) => {
      this.memberList = response.data;
      this.$store.commit("SET_HIVE_MEMBERS", response.data);
    });
  },

  methods: {
    routeToParentChild(member) {
      this.$store.commit("SET_CURRENT_MEMBER", member.id);
      this.$store.commit("SET_MINUTES_READ", member.id);
      this.$store.commit("SET_MEMBER_BOOKS", member.id);

      hiveService.getReadsByMember(member.id).then((response) => {
        this.$store.commit("SET_READING_LIST", response.data);
      });

      hiveService.getMostRecentBooks(member.id).then((response) => {
        this.$store.commit("SET_BOOK_DISPLAY", response.data);
      });

      this.$store.commit("RESET_PARENT_VIEW", this.$store.state);

      if (member.child) {
        this.$store.commit("INCREMENT_PARENT_VIEW", this.$store.state);
        this.$router.push({
          name: "parent-child-home",
          params: { id: this.parentId, childId: member.id },
        });
      } else {
        this.$store.commit("INCREMENT_PARENT_VIEW", this.$store.state);
        this.$router.push({ name: "parent-home", params: { id: member.id } });
      }
    },

    addNewMember() {
      this.$store.commit("JUMP_TO_PARENT_VIEW", 5);
    },
  },
};
</script>

<style>
.side-bar-display {
  display: flex;
  flex-direction: column;
  width: 200px;
}
</style>