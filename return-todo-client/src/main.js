import Vue from "vue";
import App from "./App.vue";
import axios from "axios";
import { store } from "./store/store.js";
import router from "./router";

Vue.config.productionTip = false;

axios.defaults.baseURL = "http://return-todo-server.seohyuni.com/api";
Vue.prototype.$axios = axios;

new Vue({
  el: "#app",
  store,
  router,
  render: (h) => h(App),
});
