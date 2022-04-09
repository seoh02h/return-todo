<template>
  <div id="app">
    <div class="main">
      <NavBar />
      <router-view />
    </div>
  </div>
</template>

<script>
import NavBar from "./components/navbar/NavBar.vue";
export default {
  name: "App",
  components: {
    NavBar,
  },
  created() {},
};
</script>

<style>
html body {
  margin: 0px;
  padding: 0px;
  width: 100%;
  height: 100%;
}
#app {
  height: 100vh;
  background-image: url("./assets/images/background.jpeg");
  background-repeat: no-repeat;
  background-size: cover;
}

.main {
  width: 600px;
  height: 550px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgb(250, 246, 242);
  border-radius: 10px;
  box-shadow: 0px 0px 25px 5px rgba(12, 12, 12, 0.128);
  font-family: "Noto Sans", sans-serif;
  color: #37352f;
}
</style>
