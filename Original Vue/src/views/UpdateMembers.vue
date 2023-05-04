<template>
  <div>
      <update-member-list/>
      <router-link :to="{name: 'add-member'}">Add Member</router-link>
  </div>
</template>

<script>
import UpdateMemberList from '../components/UpdateMemberList.vue'
export default {
  components: {UpdateMemberList},

}
</script>

<style>

</style>