<template>
  <div>
    <TagInput v-on:addTag="saveTag" />
    <TagList v-bind:tagList="tagList" v-on:deleteTag="deleteTag" />
  </div>
</template>

<script>
import axios from "axios";
import EventBus from "@/eventBus.js";
import TagList from "@/components/tag/TagList.vue";
import TagInput from "@/components/tag/TagInput.vue";
export default {
  components: {
    TagList,
    TagInput,
  },
  data() {
    return {
      tagList: [],
    };
  },
  methods: {
    getTagList() {
      axios
        .get("http://localhost:8080/api/tag")
        .then((res) => {
          this.tagList = res.data;
        })
        .catch((err) => console.log(err));
    },
    saveTag(colorCode, name) {
      axios
        .post("http://localhost:8080/api/tag", {
          colorCode,
          name,
        })
        .then(() => {
          this.getTagList();
        })
        .catch((err) => {
          console.log(err.response.data.message);
        });
    },
    editTag(id, colorCode, name) {
      axios
        .put("http://localhost:8080/api/tag/" + id, {
          colorCode,
          name,
        })
        .then(() => {
          this.getTagList();
        })
        .catch((err) => {
          console.log(err.response.data.message);
        });
    },
    deleteTag(id) {
      axios
        .delete("http://localhost:8080/api/tag/" + id)
        .then(() => {
          this.getTagList();
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    EventBus.$on("deleteTag", (tagId) => {
      this.deleteTag(tagId);
    });
    EventBus.$on("editTag", (payload) => {
      this.editTag(payload.id, payload.colorCode, payload.name);
    });
    this.getTagList();
  },
};
</script>

<style></style>
