<template>
  <div class="hive-display-container">
    <h2 id="hive-display-header">The Buzz Brigade</h2>
    <div class="member-list-display">
      <div
        class="member-display"
        v-for="member in memberList"
        v-bind:key="member.id"
        @click.stop="routeToCorrectHomepage(member)"
      >
        <member-card :current-member="member" />
      </div>
    </div>
  </div>
</template>

<script>
import hiveService from "../services/HiveService.js";
import MemberCard from "./MemberCard.vue";

export default {
  name: "member-list",

  components: { MemberCard },

  data() {
    return {
      memberList: [],
    };
  },

  created() {
    hiveService.getHiveMembers().then((response) => {
      this.memberList = response.data;
      this.$store.commit("SET_HIVE_MEMBERS", response.data);
    });
  },

  methods: {
    routeToCorrectHomepage(member) {
      hiveService.getReadsByMember(member.id).then((response) => {
        this.$store.commit("SET_READING_LIST", response.data);
      });

      this.$store.commit("SET_MEMBER_BOOKS", member.id);
      this.$store.commit("SET_PRIZELIST", this.state);

      hiveService.getMostRecentBooks(member.id).then((response) => {
        this.$store.commit("SET_BOOK_DISPLAY", response.data);
      });

      this.$store.commit("SET_CURRENT_MEMBER", member.id);
      this.$store.commit("SET_MINUTES_READ", member.id);

      this.$store.commit("INCREMENT_CHILD_VIEW", this.$store.state);

      if (member.child) {
        this.$router.push({ name: "child-home", params: { id: member.id } });
      } else {
        this.$router.push({
          name: "pin-validation",
          params: { id: member.id },
        });
      }
    },
  },
};
</script>

<style>
.member-list-display {
  padding-bottom: 10px;
  display: flex;
  width: 66%;
  justify-content: space-evenly;
  margin: 0 auto;
  margin-top: 90px;
  flex-flow: row wrap;
}

 h2 {
  text-align: center;
  font-size: 4em;
  margin-bottom: 10px;
} 



#hive-display-header {
  font-family: "Livvic", sans-serif;
}

.member-link {
  padding: 2px;
}

.hive-display-container {
  margin-top: 50px;
}

#hive-display-header {
  font-size: 2.5rem;
}
</style>