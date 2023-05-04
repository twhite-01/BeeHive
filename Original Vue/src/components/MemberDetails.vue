<template>
  <div class="view-update-member">
    <button @click="showForm = !showForm">edit member</button>
    <button>delete member</button>

    <div class="form-box" v-show="showForm === true">
      <h2>new member form</h2>
      <form v-on:submit.prevent>
        <div class="user-box">
          <input type="text" name="" required="" v-model="member.firstName" />
          <label>first name</label>
        </div>
        <div class="user-box">
          <input type="text" name="" required="" v-model="member.lastInitial" />
          <label>last initial</label>
        </div>

        <label for="avatar-id">choose your avatar</label>
        <input id="avatar-id" type="text" v-model.number="member.avatarId" />

        <label for="is-child-input">child account?</label>
        <input
          id="is-child-input"
          v-model="member.child"
          :value="true"
          type="checkbox"
        />

        <label for="pin-input" v-show="!member.child"
          >create 4-digit pin:
        </label>
        <input
          id="pin-input"
          type="text"
          v-show="!member.child"
          v-model="member.pin"
          maxlength="4"
        />

        <a href="#" v-on:click="updateMember()">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          submit
        </a>
      </form>
    </div>

    <!-- <member-books/> -->
    <!-- <button @click="showForm=!showForm">edit member</button>
    <button>delete member</button>
    <form v-on:submit.prevent class="edit-member-form" v-show="showForm === true">
      <label for="first-name-input">first name</label>
      <input id="first-name-input" type="text" v-model="member.firstName"/>

      <label for="last-initial-input">last initial</label>
      <input id="last-initial-input" type="text" v-model="member.lastInitial" />

      <label for="avatar-id">choose your avatar</label>
      <input id="avatar-id" type="text" v-model="member.avatarId"/>

      <label for="pin-required-input">pin required?</label>
      <input id="pin-required-input" type="checkbox" v-model="member.pinRequired"/>

      <label for="is-child-input">child account?</label>
      <input
        id="is-child-input"
        v-model="member.isChild"
        :value="true"
        type="checkbox"
      />

      <label for="pin-input" v-show="!(member.isChild)"
        >create a 4-digit pin:
      </label>
      <input
        id="pin-input"
        type="text"
        v-show="!(member.isChild)"
        v-model="member.pin"
      />

      <button  id="update-member" type="submit" v-on:click="updateMember()">Save updates</button>
    </form> -->
  </div>
</template>


<script>
import hiveService from "../services/HiveService";
// import MemberBooks from './MemberBooks.vue';

export default {
  // components: { MemberBooks },
  name: "member-details",

  data() {
    return {
      showForm: false,
      member: {
        firstName: "",
        lastInitial: "",
        avatarId: "",
        pinRequired: false,
        isChild: false,
        pin: "",
      },
    };
  },

  created() {
    hiveService.getMember(this.$route.params.id).then((response) => {
      this.member = response.data;
    });
  },

  methods: {
    updateMember() {
      hiveService
        .updateMember(this.member)
        .then((response) => {
          if (response.status === 200) {
            //ok
          }
          this.$router.push({ name: "home" });
        })
        .catch((error) => {
          if (error.request) {
            this.errorMsg = "That did not work.";
          }
          this.$router.push({ name: "home" });
        });
    },
  },
};
</script>

<style>
.edit-member-form {
  margin: 20px;
  border: 2px black groove;
  display: flex;
  flex-direction: column;
  width: 400px;
  height: 400px;
  padding: 5px;
}

.edit-member-form input,
.edit-member-form button {
  margin: 10px;
  font-size: 1rem;
}
</style>
