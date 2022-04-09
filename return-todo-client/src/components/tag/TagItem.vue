<template>
  <div class="tag-item">
    <div v-if="editable" class="error">{{ error }}</div>
    <div class="tag" :class="{ edit: editable }">
      <input class="input-color" type="color" v-model="tag.colorCode" :disabled="!editable" />
      <input
        class="input-text"
        ref="input"
        type="text"
        @keyup.enter="update(tag.id, tag.colorCode, tag.name)"
        v-model="tag.name"
        :readonly="!editable"
      />

      <i v-if="!editable" v-on:click="editableTag" class="fa fa-pen fa-sm"></i>
      <i
        v-if="editable"
        @click="update(tag.id, tag.colorCode, tag.name)"
        class="fa fa-check fa-sm"
      ></i>
      <i @click="openModal" class="fa fa-trash-alt fa-sm"></i>
    </div>
    <DeleteModal :show="showModal" @close="showModal = false" @remove="remove(tag.id)">
      <template #body>
        <div>'{{ tag.name }}' 태그를 삭제하시겠습니까?</div>
      </template>
    </DeleteModal>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import DeleteModal from "@/components/common/DeleteModal.vue";
import EventBus from "@/eventBus.js";
export default {
  components: {
    DeleteModal,
  },
  props: ["tag"],
  data() {
    return {
      editable: false,
      showModal: false,
      error: "",
    };
  },
  methods: {
    ...mapActions("tag", ["getTagList", "updateTag", "deleteTag"]),
    ...mapActions("todo", ["getTodoList"]),

    editableTag() {
      EventBus.$emit("tagEditClose");
      this.editable = true;
      this.$refs.input.focus();
    },
    update(id, colorCode, name) {
      if (name.length === 0) {
        this.error = "태그를 1 글자 이상 입력하세요";
      } else {
        this.error = "";
        this.updateTag({ id, colorCode, name });
        this.editable = false;
      }
    },
    remove(id) {
      this.deleteTag({ id });

      this.showModal = false;
    },
    closeEdit() {
      this.editable = false;
      this.error = "";
      this.getTagList();
    },
    openModal() {
      EventBus.$emit("tagEditClose");
      this.showModal = true;
    },
  },
  created() {
    EventBus.$on("tagEditClose", () => {
      this.closeEdit();
    });
  },
};
</script>

<style scoped>
.tag-item {
  width: 430px;
}
.error {
  margin-left: 30px;
  color: rgb(180, 24, 24);
}
.tag {
  font-size: 17px;
  display: flex;
  width: 430px;
  align-items: center;
  justify-content: center;
  height: 34px;
  border-radius: 20px;
}
.input-color {
  cursor: pointer;
  border: none;
  width: 25px;
  height: 27px;
  background-color: transparent;
  margin-right: 3px;
  margin-bottom: 2px;
}
input::-webkit-color-swatch {
  border-radius: 50%;
  border: 1.5px solid rgba(56, 56, 56, 0.067);
}

.input-text {
  border: none;
  background: transparent;
  font-size: 20px;
  width: 300px;
}

.input-text:focus {
  outline: none;
  background: transparent;
}
.input-color:disabled {
  cursor: default;
}
.input-text:read-only {
  color: rgb(45, 45, 45);
  font-weight: 500;
  cursor: default;
}
.edit {
  background-color: rgba(232, 215, 220, 0.238);
}
i {
  margin: auto 4px;
  color: rgba(0, 0, 0, 0.635);
  cursor: pointer;
}
i:hover {
  color: #bd7c89;
}
.error {
  font-size: 12px;
  width: 300px;
}
</style>
