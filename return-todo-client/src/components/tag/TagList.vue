<template>
  <div class="tag-list">
    <TagItem v-bind:tag="tag" v-for="(tag, idx) in tagList" v-bind:key="idx" />
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
  margin-bottom: 15px;
}
</style>
