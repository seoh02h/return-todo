import Vue from "vue";
import Vuex from "vuex";
import tag from "./modules/tag";

Vue.use(Vuex);

export const store = new Vuex.Store({
  modules: {
    tag,
  },
});
