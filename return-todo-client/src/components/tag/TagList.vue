<template>
  <div class="tag-list">
    <TagItem v-bind:tag="tag" v-for="(tag, idx) in tagList" v-bind:key="idx" />
  </div>
</template>

<script>
import TagItem from "./TagItem.vue";
export default {
  components: {
    TagItem,
  },
  props: ["tagList"],
};
</script>

<style scoped>
.tag-item {
  margin: 0 auto;
  margin-bottom: 10px;
}
</style>
