<template>
  <div class="todo-search">
    <div class="search">
      <div class="search-complete" @click="toggleComplete">{{ getCompleteState }}</div>
      <div
        v-for="(tag, idx) in tagList"
        :key="tagList[idx].id"
        class="tag"
        :class="{ 'selected-tag': isSelected(tag.id) }"
        @click="selectTag(tag.id)"
      >
        <div :style="{ background: tag.colorCode }" class="tag-color"></div>
        <div>{{ tag.name }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
export default {
  computed: {
    ...mapState("tag", ["tagList"]),
    ...mapState("todo", ["complete", "tagId"]),
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
    ...mapMutations("todo", ["setCompleteTrue", "setCompleteFalse", "setCompleteNull", "setTagId"]),
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
    isSelected(tagId) {
      if (this.tagId === tagId) {
        return true;
      } else {
        return false;
      }
    },
    selectTag(id) {
      if (this.tagId == id) {
        this.setTagId(null);
      } else {
        this.setTagId(id);
      }
      this.getTodoList();
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
  margin-top: 25px;
  background-color: rgba(226, 218, 219, 0.317);
  border-radius: 10px;
  padding: 0 20px;
  box-shadow: 0px 0px 10px 2px rgba(94, 94, 94, 0.128);
}
.search {
  height: 40px;
  display: flex;
  align-items: center;
  overflow: auto;
  white-space: nowrap;
}
.search-complete {
  background-color: #bd7c89;
  border-radius: 20px;
  text-align: center;
  height: 30px;
  line-height: 30px;
  cursor: pointer;
  color: white;
  padding: 0 13px;
  margin-right: 8px;
}
.tag {
  margin-right: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(203, 203, 203, 0.291);
  height: 24px;
  line-height: 25px;
  border-radius: 20px;
  padding: 0 8px;
  cursor: pointer;
  font-size: 14px;
  border: 2px solid rgba(214, 214, 214, 0.063);
}
.tag-color {
  width: 15px;
  height: 15px;
  border-radius: 50%;
  border: 1.5px solid rgba(228, 228, 228, 0.54);
  margin-right: 5px;
}
.selected-tag {
  background-color: #8686864a;
}
</style>
