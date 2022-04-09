<template>
  <div class="todo-list">
    <TodoItem :todo="todo" v-for="(todo, idx) in todoList" :key="idx" />
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
  created() {
    this.getTodoList();
  },
};
</script>

<style scoped>
.todo-list {
  overflow: scroll;
  height: 370px;
  margin-bottom: 10px;
}
</style>
