import Vue from "vue";
import Vuex from "vuex";
import tag from "./modules/tag";
import todo from "./modules/todo";

Vue.use(Vuex);

export const store = new Vuex.Store({
  modules: {
    tag,
    todo,
  },
});
