<template>
  <div>
    <div id="nav-bar"></div>
    <a href="#" @click.prevent="jumpToView(5)" id="add-mem">add member</a> |
    <a href="#" @click.prevent="jumpToView(10)"
      >check pending requests({{ this.requestList.length }})</a
    >
    |
    <a href="#" @click.prevent="jumpToView(15)">prizes</a>
  </div>
</template>

<script>
export default {
  name: "parent-nav",

  computed: {
    requestList() {
      return this.$store.state.requestList;
    },
  },

  methods: {
    jumpToView(viewNum) {
      this.$store.commit("JUMP_TO_PARENT_VIEW", viewNum);
    },
    goHome() {
      this.$store.commit("RESET_PARENT_VIEW", this.$store.state);
      this.$store.commit("INCREMENT_PARENT_VIEW", this.$store.state);
    },
  },
};
</script>

<style>
</style>