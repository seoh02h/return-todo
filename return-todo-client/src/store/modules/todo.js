import { todo } from "@/api";

const state = {
  todoList: [],
};

const getters = {};

const mutations = {
  setTodoList(state, payload) {
    state.todoList = payload;
  },
};

const actions = {
  getTodoList(context) {
    return todo.getList().then((todoList) => {
      context.commit("setTodoList", todoList);
    });
  },
};
export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
