<template>
  <div class="todo-search">
    <div class="search-complete" @click="toggleComplete">{{ getCompleteState }}</div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapState("tag", ["tagList"]),
    ...mapState("todo", ["complete"]),
    getCompleteState() {
      if (this.complete === null) {
        return "전체";
      } else if (this.complete === true) {
        return "완료";
      } else {
        return "미완료";
      }
    },
  },
  methods: {
    ...mapActions("tag", ["getTagList"]),
    ...mapActions("todo", ["getTodoList"]),
    ...mapMutations("todo", ["setCompleteTrue", "setCompleteFalse", "setCompleteNull"]),
    toggleComplete() {
      if (this.complete === null) {
        this.setCompleteTrue();
      } else if (this.complete === true) {
        this.setCompleteFalse();
      } else {
        this.setCompleteNull();
      }
      this.getTodoList();
      console.log(this.complete);
    },
  },
  created() {
    this.getTagList();
  },
};
</script>

<style scoped>
.todo-search {
  height: 40px;
  width: 500px;
  margin: 0 auto;
  margin-top: 20px;
  background-color: rgba(226, 218, 219, 0.317);
  border-radius: 10px;
  display: flex;
  align-items: center;
  padding: 0 10px;
}
.tag {
  margin: 0 5px;
}
.search-complete {
  background-color: #bd7c89;
  border-radius: 20px;
  width: 60px;
  text-align: center;
  height: 30px;
  line-height: 30px;
  cursor: pointer;
  color: white;
}
</style>
