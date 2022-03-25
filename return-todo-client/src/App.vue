<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
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
  background-color: rgba(199, 194, 194, 0.747);
}

#app {
  width: 60%;
  height: 80%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  border-radius: 20px;
}
</style>
