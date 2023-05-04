<template>
  <div>
    <!-- <form v-on:submit.prevent class="new-member-form"> -->
    <!-- v-show add hive family name if family name empty -->
    <div class="form-box">
      <div v-if="firstTimeUser">
        <h2>welcome to BookHive!!</h2>
        <h3 id="welcome">
          to start your un-bee-lievable hive, we need a few things from you.
        </h3>  
        <!-- <form class="family-form" v-on:submit.prevent> -->
          <div class="user-box">
           <input
           id="family-hive-name"
            type="text"
            name=""
            required=""
            v-model="hiveName.familyName"
         
        /> <label>your hive name</label>  
       </div>
        <!-- <button @click="saveFamilyName">Save</button> -->
        <!-- </form> -->
        <p>now you get to bee the first hive member:</p>
     </div>
      <h2>new member form</h2>
      <form v-on:submit.prevent>
        <div class="user-box">
          <input
            type="text"
            name=""
            required=""
            v-model="newMember.firstName"
          />
          <label>first name</label>
        </div>
        <div class="user-box">
          <input
            type="text"
            name=""
            required=""
            v-model="newMember.lastInitial"
          />
          <label>last initial</label>
        </div>
        <div class="newform-beepic">
          <img
            id="bee-avatar"
            v-bind:src="
              require('../assets/' + this.newMember.avatarId + '.png')
            "
            alt=""
          />
        </div>
        <div class="format-dropdown">
          <label>choose your avatar: </label>
          <select
            class="bee-down"
            id="avatar-id"
            v-model.number="newMember.avatarId"
          >
            <option value="blue">Blue</option>
            <option value="green">Green</option>
            <option value="pink">Pink</option>
            <option value="yellow">Yellow</option>
            <option value="orange">Orange</option>
            <option value="red">Red</option>
            <option value="magenta">Magenta</option>
          </select>
        </div>
        <div class="box-check">
          <label>child account?</label>
          <input
            id="is-child-input"
            v-model="newMember.child"
            :value="true"
            type="checkbox"
          />
        </div>
        <div class="pin-input">
          <label v-show="!newMember.child">create 4-digit pin: </label>
          <input
            class="bee-down"
            type="text"
            v-show="!newMember.child"
            v-model="newMember.pin"
            maxlength="4"
          />
        </div>
        <a href="#" v-on:click="saveMember()">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          submit
        </a>
      </form>
    </div>
    <!-- <label for="first-name-input">First Name</label>
      <input id="first-name-input" type="text" v-model="newMember.firstName" />
      <label for="last-initial-input">Last Initial</label>
      <input
        id="last-initial-input"
        type="text"
        v-model="newMember.lastInitial"
        maxlength="3"
      />
      <img id="bee-avatar" v-bind:src="require('../assets/' + this.newMember.avatarId +'.png')" alt="" />
      <label for="avatar-id">Choose Your Avatar</label>
      <select id="avatar-id" v-model.number="newMember.avatarId">
        <option value="blue">Blue</option>
        <option value="green">Green</option>
        <option value="pink">Pink</option>
        <option value="yellow">Yellow</option>
        <option value="orange">Orange</option>
        <option value="red">Red</option>
        <option value="magenta">Magenta</option>
      </select>
      <label for="is-child-input">Child Account?</label>
      <input
        id="is-child-input"
        v-model="newMember.child"
        :value="true"
        type="checkbox"
      />
      <label for="pin-input" v-show="!(newMember.child)"
        >create 4-digit pin:
      </label>
      <input
        id="pin-input"
        type="text"
        v-show="!(newMember.child)"
        v-model="newMember.pin"
        maxlength="4"
      />
      <button id="save-new-member" type="submit" v-on:click="saveMember()">
        Add Member
      </button>
    </form> -->
  </div>
</template>
<script>
import hiveService from "../services/HiveService.js";
// import FamilyNameForm from "./FamilyNameForm.vue";
export default {
  name: "new-member-form",
  // components: { FamilyNameForm },
  data() {
    return {
      newMember: {
        firstName: "",
        lastInitial: "",
        avatarId: "blue",
        child: true,
        pin: "",
      },
      hiveName: {
        id: this.$store.state.user.id,
        familyName: "",
      },
      firstTimeUser: false,
    };
  },
  methods: {
    saveMember() {
      hiveService
        .addHiveMember(this.newMember)
        .then((response) => {
          if (response.status === 200) {
            //ok
            if (this.firstTimeUser) {
              //ok
              hiveService
                .addHiveName(this.hiveName.id, this.hiveName)
                .then((response) => {
                  if (response.status != 500) {
                    //ok
                  }
                });
            }
            // }
            // if (this.$route.params) {
            //   this.$router.push({ name: "parent-home" });
            //   this.$store.commit("RESET_PARENT_VIEW", this.$store.state);
            //   this.$store.commit("INCREMENT_PARENT_VIEW", this.$store.state);
            // } else {
            this.$router.push({ name: "home" });
          }
        })
        .catch((error) => {
          if (error.request) {
            this.errorMsg = "That did not work.";
          }
          this.$router.push({ name: "home" });
        });
    },
  },
  created() {
    hiveService.getHiveMembers().then((response) => {
      let memberArray = response.data;
      if (memberArray.length === 0) {
        this.firstTimeUser = true;
      }
    });
  },
};
</script>
<style>
.new-member-form {
  margin: 20px;
  border: 2px black groove;
  display: flex;
  flex-direction: column;
  width: 400px;
  padding: 5px;
}
.new-member-form input,
.new-member-form button {
  margin: 10px;
  font-size: 1rem;
}
#bee-avatar {
  width: 100px;
}

#family-name-input {
  margin: 0 auto;
}

#welcome{
margin-bottom: 10px;
}



</style>