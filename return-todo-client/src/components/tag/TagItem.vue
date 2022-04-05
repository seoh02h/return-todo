<template>
  <div class="tag-item" v-bind:class="{ edit: editable }">
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

    <Modal :show="showModal" @close="showModal = false" @remove="remove(tag.id)">
      <template #body>
        <div><i class="fa fa-trash-alt modal-icon"></i></div>
        <div>'{{ tag.name }}' 태그를 삭제하시겠습니까?</div>
      </template>

      <template #footer>
        <button class="modal-btn" @click="showModal = false">취소</button>
        <button class="modal-btn" @click="remove(tag.id)">삭제</button>
      </template>
    </Modal>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import Modal from "@/components/common/Modal.vue";
import EventBus from "@/eventBus.js";
export default {
  components: {
    Modal,
  },
  props: ["tag"],
  data() {
    return {
      editable: false,
      showModal: false,
    };
  },
  methods: {
    ...mapActions("tag", ["getTagList", "updateTag", "deleteTag"]),

    editableTag() {
      EventBus.$emit("tagEditClose");
      this.editable = true;
      this.$refs.input.focus();
    },
    update(id, colorCode, name) {
      this.updateTag({ id, colorCode, name });
      this.editable = false;
    },
    remove(id) {
      this.deleteTag({ id });
      this.showModal = false;
    },
    closeEdit() {
      this.editable = false;
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
  display: flex;
  justify-content: center;
  align-items: center;
  width: 360px;
  border-radius: 20px;
  font-size: 17px;
  height: 30px;
}
.input-color {
  cursor: pointer;
  border: none;
  width: 25px;
  height: 25px;
  background-color: transparent;
  margin-right: 3px;
}
input::-webkit-color-swatch {
  border-radius: 50%;
  border: 1.5px solid rgba(56, 56, 56, 0.067);
}

.input-text {
  border: none;
  background: transparent;
  font-size: 20px;
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
  color: rgba(119, 119, 119, 0.635);
  cursor: pointer;
}
i:hover {
  color: rgb(213, 148, 159);
}

.modal-icon {
  font-size: 40px;
  color: rgba(255, 0, 0, 0.635);
  margin-bottom: 10px;
}

.modal-btn {
  float: right;
  margin: 10px 0px;
  width: 50px;
  height: 30px;
  margin-left: 8px;
  background-color: rgb(239, 239, 239);
  border: none;
  color: rgb(64, 64, 64);
  font-weight: 600;
  border-radius: 5px;
  cursor: pointer;
}
.modal-btn:hover {
  background-color: rgba(191, 191, 191, 0.382);
}
</style>
