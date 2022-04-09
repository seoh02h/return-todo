<template>
  <div class="tag-list">
    <TagItem :tag="tag" v-for="(tag, idx) in tagList" :key="idx" />
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import TagItem from "./TagItem.vue";
export default {
  components: {
    TagItem,
  },
  computed: {
    ...mapState("tag", ["tagList"]),
  },
  methods: {
    ...mapActions("tag", ["getTagList"]),
  },
  created() {
    this.getTagList();
  },
};
</script>

<style scoped>
.tag-item {
  margin: 0 auto;
}
</style>
