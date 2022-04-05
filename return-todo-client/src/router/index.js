import Vue from "vue";
import VueRouter from "vue-router";
import Todo from "../views/Todo.vue";
import Tag from "../views/Tag.vue";
import NotFound from "../views/NotFound.vue";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [
    {
      name: "todo",
      path: "/",
      component: Todo,
    },
    {
      name: "tag",
      path: "/tag",
      component: Tag,
    },
    {
      name: "notFound",
      path: "*",
      component: NotFound,
    },
  ],
});

export default router;
