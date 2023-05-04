<template>
  <div id="app-header">
    <img
      id="app-logo"
      src="../assets/logo.png"
      alt="stylized app name and logo"
    />
  <!-- <h1 id="app-name">BookHive</h1> -->
  </div>
</template>

<script>
export default {
  name: "app-header",
};
</script>

<style>
div#app-header {
  display: flex;
  align-content: center;
  height: 50%;
  margin-bottom: 10px;
}

#app-logo {
 margin-left: 30px;

}

#app-name{
  font-size: 4.5rem;
}

h1 {
  margin-top: 27px;
}
</style>