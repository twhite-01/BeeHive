<template>
  <div class="hive-members-display">
      <member-list/>
  </div>
</template>

<script>

import MemberList from '../components/MemberList.vue'

export default {

    components: {
        MemberList
    }

}
</script>

<style>

    .hive-members-display{
        margin-top: 600px;
    }

</style>