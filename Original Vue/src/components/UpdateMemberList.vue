<template>
  <div class="update-member-list">
    <h2 class="choose-member">Choose a member to view/update:</h2>
    <div
      class="update-member-display"
      v-for="member in memberList"
      v-bind:key="member.id"
    >
      <router-link :to="{ name: 'member-info', params: { id: member.id }}">
        <div class="member-details-link">{{ member.firstName }} {{ member.lastInitial }}</div>
      </router-link>
    </div>
  </div>
  
</template>

<script>

import hiveService from "../services/HiveService.js";

export default {
    name: "update-member-list",

  data() {
    return {
      memberList: [],
    };
  },

  created() {
    hiveService.getHiveMembers().then((response) => {
      this.memberList = response.data;
    });
  },
};
</script>

<style>

.member-list-display{
  padding-bottom: 10px;
}

.my-hive-members{
  margin-bottom: 6px
}

.member-link{
  padding: 2px
}

</style>