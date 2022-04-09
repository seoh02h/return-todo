<template>
  <div>
    <TodoSearch />
    <TodoList />
    <TodoInput />
  </div>
</template>

<script>
import TodoSearch from "@/components/todo/TodoSearch.vue";
import TodoList from "@/components/todo/TodoList.vue";
import TodoInput from "@/components/todo/TodoInput.vue";
export default {
  components: {
    TodoSearch,
    TodoList,
    TodoInput,
  },
};
</script>

<style scoped></style>
