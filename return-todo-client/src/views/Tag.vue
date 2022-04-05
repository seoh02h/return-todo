<template>
  <div>
    <TagInput />
    <TagList />
  </div>
</template>

<script>
import TagList from "@/components/tag/TagList.vue";
import TagInput from "@/components/tag/TagInput.vue";
export default {
  components: {
    TagList,
    TagInput,
  },
};
</script>

<style></style>
