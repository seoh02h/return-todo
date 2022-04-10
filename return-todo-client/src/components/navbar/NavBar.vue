<template>
  <div class="nav-bar">
    <router-link
      tag="div"
      class="nav-todo"
      :to="{ name: 'todo' }"
      v-bind:class="{ 'in-tag': !inTodo }"
    >
      <img src="@/assets/images/todo.png" width="25px" />
    </router-link>
    <router-link
      tag="div"
      class="nav-tag"
      :to="{ name: 'tag' }"
      v-bind:class="{ 'in-todo': inTodo }"
    >
      <img src="@/assets/images/tag.png" width="25px" />
    </router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      inTodo: true,
    };
  },
  methods: {},
  watch: {
    $route(to) {
      this.inTodo = to.name == "tag" ? false : true;
    },
  },
  created() {
    this.inTodo = this.$route.name == "tag" ? false : true;
  },
};
</script>

<style scoped>
.nav-bar {
  display: flex;
}

.nav-bar > div {
  text-decoration: none;
  height: 50px;
  line-height: 50px;
  text-align: center;
}

.nav-todo {
  width: 400px;
  border-radius: 10px 0 0 0;
}

.nav-tag {
  width: 200px;
  border-radius: 0 10px 0 0;
}
.in-todo {
  background-color: rgba(216, 216, 216, 0.568);
  border-bottom: 2px solid rgba(163, 163, 163, 0.219);
  border-left: 2px solid rgba(163, 163, 163, 0.219);
  box-shadow: inset 5px -5px 15px 10px rgba(151, 151, 151, 0.205);
}

.in-tag {
  background-color: rgba(216, 216, 216, 0.568);
  border-bottom: 2px solid rgba(163, 163, 163, 0.219);
  border-right: 2px solid rgba(163, 163, 163, 0.219);
  box-shadow: inset -5px -5px 15px 10px rgba(151, 151, 151, 0.205);
}
div {
  cursor: pointer;
}
img {
  vertical-align: middle;
}
</style>
