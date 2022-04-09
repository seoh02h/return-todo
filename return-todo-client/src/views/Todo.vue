<template>
  <div>
    <TodoList />
    <TodoInput />
  </div>
</template>

<script>
import TodoList from "@/components/todo/TodoList.vue";
import TodoInput from "@/components/todo/TodoInput.vue";
export default {
  components: {
    TodoList,
    TodoInput,
  },
};
</script>

<style scoped></style>
