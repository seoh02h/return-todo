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
  saveTodo(context, { content, tagId }) {
    return todo.create(content, tagId).then(() => context.dispatch("getTodoList"));
  },
  updateTodo(context, { id, content, tagId }) {
    return todo.update(id, content, tagId).then(() => context.dispatch("getTodoList"));
  },
  toggleTodoComplete(context, { id }) {
    return todo.toggleComplete(id).then(() => context.dispatch("getTodoList"));
  },

  deleteTodo(context, { id }) {
    return todo.delete(id).then(() => context.dispatch("getTodoList"));
  },
};
export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
