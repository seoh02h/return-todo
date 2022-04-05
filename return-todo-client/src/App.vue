<template>
  <div id="app">
    <NavBar />

    <router-view />
  </div>
</template>

<script>
import NavBar from "./components/navbar/NavBar.vue";
export default {
  name: "App",
  components: {
    NavBar,
  },
};
</script>

<style>
html {
  margin: 0px;
  padding: 0px;
  width: 100%;
  height: 100%;
}
body {
  height: 100vh;
  background-color: rgba(233, 186, 85, 0.747);
}

#app {
  width: 400px;
  height: 80%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgb(250, 246, 242);
  border-radius: 10px;
  box-shadow: 0px 0px 25px 5px rgba(12, 12, 12, 0.066);
  font-family: "Noto Sans", sans-serif;
  color: rgb(45, 45, 45);
}
</style>
