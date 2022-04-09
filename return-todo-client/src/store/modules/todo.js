import { todo } from "@/api";

const state = {
  todoList: [],
  complete: null,
  tagId: null,
};

const getters = {};

const mutations = {
  setTodoList(state, payload) {
    state.todoList = payload;
  },
  setCompleteTrue(state) {
    state.complete = true;
  },
  setCompleteNull(state) {
    state.complete = null;
  },
  setCompleteFalse(state) {
    state.complete = false;
  },
  setTagId(state, tagId) {
    state.tagId = tagId;
  },
};

const actions = {
  getTodoList(context) {
    return todo.getList(context.state.complete, context.state.tagId).then((todoList) => {
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
