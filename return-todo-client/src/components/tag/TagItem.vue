<template>
  <div class="tag-item" v-bind:class="{ edit: editable }">
    <input class="input-color" type="color" v-model="tag.colorCode" :disabled="!editable" />
    <input class="input-text" type="text" v-model="tag.name" :disabled="!editable" />

    <i v-if="!editable" v-on:click="editableTag" class="fa fa-pen fa-sm"></i>
    <i
      v-if="editable"
      v-on:click="editTag(tag.id, tag.colorCode, tag.name)"
      class="fa fa-check fa-sm"
    ></i>
    <i v-on:click="deleteTag(tag.id)" class="fa fa-trash-alt fa-sm"></i>
  </div>
</template>

<script>
import EventBus from "@/eventBus.js";
export default {
  data() {
    return {
      editable: false,
    };
  },
  props: ["tag"],
  methods: {
    deleteTag(id) {
      EventBus.$emit("deleteTag", id);
      this.editable = false;
    },
    editableTag() {
      this.editable = true;
    },
    editTag(id, colorCode, name) {
      this.editable = false;
      EventBus.$emit("editTag", { id, colorCode, name });
    },
  },
};
</script>

<style scoped>
.tag-item {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 360px;
  border-radius: 20px;
  font-size: 17px;
}
.input-color {
  cursor: pointer;
  border: none;
  width: 17px;
  height: 15px;
  background-color: transparent;
  margin-right: 3px;
}
input::-webkit-color-swatch {
  border-radius: 50%;
  border: 1px solid rgba(56, 56, 56, 0.289);
}

.input-text {
  border: none;
  background: transparent;
}

.input-text:focus {
  outline: none;
  background: transparent;
}
.input-text:disabled {
  color: rgb(45, 45, 45);
  font-weight: 500;
}
.edit {
  background-color: rgba(212, 187, 142, 0.238);
}
i {
  margin: auto 4px;
  color: rgba(103, 99, 92, 0.749);
}
</style>
