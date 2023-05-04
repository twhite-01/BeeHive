<template>
  <div class="hex">
    <div class="hex inner">
      <div class="hex inner2">
        <div class="hex inner3">
          <div class="hex inner4">
            <div class="bee-forreal-pic">
              <img
                class="actual-pic-bee"
                v-bind:src="
                  require('../assets/' + this.member.avatarId + '.png')
                "
                alt="user bee"
              />
            </div>
            <h2 id="avatar-name-text">
              {{ this.member.firstName.toLowerCase() }}
            </h2>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "member-card",
  props: ["currentMember"],

  data() {
    return {
      totalMinutesRead: 0,
      member: {
        id: 0,
        firstName: "",
        lastInitial: "",
        avatarId: "",
      },
    };
  },

  created() {
    this.member = this.currentMember;
  },
};
</script>

<style>
.hex.inner {
  width: 154px;
  height: 154px;
  clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  background: rgba(247, 188, 26, 0.808);
}
.hex.inner2 {
  width: 150px;
  height: 150px;
  clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  background: rgba(247, 188, 26, 0.808);
  position: relative;
  left: 3px;
  top: 3px;
}
.hex.inner3 {
  width: 146px;
  height: 146px;
  clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  background: rgb(255, 255, 255);
  position: relative;
}
.hex.inner4 {
  width: 144px;
  height: 144px;
  clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  background: rgba(226, 186, 6, 0.747);
  position: relative;
}

.bee-forreal-pic {
  width: 142px;
  height: 142px;
  clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  position: absolute;
  left: 2px;
  top: 2px;
  background: rgb(255, 255, 255);
}

.actual-pic-bee {
  width: 200px;
  height: 200px;
  clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  position: absolute;
  left: -28px;
  top: -9px;
  cursor: pointer;
}

#avatar-name-text {
  position: absolute;
  top: 65%;
  left: 45%;
  transform: translate(-50%, -50%);
  font-family: "Livvic", sans-serif;
}
</style>