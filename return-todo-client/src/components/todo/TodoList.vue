<template>
  <div class="todo-list">
    <TodoItem :todo="todo" v-for="(todo, idx) in todoList" :key="todoList[idx].id" />
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import TodoItem from "@/components/todo/TodoItem.vue";
export default {
  components: {
    TodoItem,
  },
  computed: {
    ...mapState("todo", ["todoList"]),
  },
  methods: {
    ...mapActions("todo", ["getTodoList"]),
  },
  mounted() {
    this.getTodoList();
  },
};
</script>

<style scoped>
.todo-list {
  margin-top: 25px;
  overflow: scroll;
  overflow-x: hidden;
  height: 330px;
  margin-bottom: 10px;
}
</style>
