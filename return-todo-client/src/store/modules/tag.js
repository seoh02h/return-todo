import { tag } from "@/api";
const state = {
  tagList: [],
};
const getters = {};
const mutations = {
  setTagList(state, payload) {
    state.tagList = payload;
  },
};
const actions = {
  getTagList(context) {
    return tag.getList().then((tagList) => {
      context.commit("setTagList", tagList);
    });
  },
  saveTag(context, { colorCode, name }) {
    return tag.create(colorCode, name).then(() => context.dispatch("getTagList"));
  },
  updateTag(context, { id, colorCode, name }) {
    return tag.update(id, colorCode, name).then(() => context.dispatch("getTagList"));
  },
  deleteTag(context, { id }) {
    return tag.delete(id).then(() => context.dispatch("getTagList"));
  },
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
