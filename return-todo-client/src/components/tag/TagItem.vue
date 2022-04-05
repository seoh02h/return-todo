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
        <div>'{{ tag.name }}' 태그를 삭제하겠습니까?</div>
      </template>

      <template #footer>
        <button class="modal-close-btn" @click="showModal = false">취소</button>
        <button class="modal-remove-btn" @click="remove(tag.id)">삭제</button>
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
    ...mapActions("tag", ["updateTag", "deleteTag"]),

    editableTag() {
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
    },
    openModal() {
      this.closeEdit();
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
  border: 1.5px solid rgba(56, 56, 56, 0.089);
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
  background-color: rgba(231, 197, 207, 0.238);
}
i {
  margin: auto 4px;
  color: rgba(126, 114, 114, 0.749);
  cursor: pointer;
}
i:hover {
  color: rgb(164, 121, 128);
}

.modal-icon {
  font-size: 40px;
  color: rgb(236, 109, 109);
  margin-bottom: 10px;
}

.modal-close-btn {
  float: right;
  margin: 10px;
  width: 50px;
  height: 30px;
  background-color: rgb(255, 255, 255);
  border: 2px solid rgb(215, 202, 202);
  border-radius: 5px;
}

.modal-remove-btn {
  float: right;
  margin: 10px;
  width: 50px;
  height: 30px;
  margin-right: 0px;
  background-color: rgb(255, 255, 255);
  border: 2px solid rgb(226, 119, 119);
  color: rgb(226, 119, 119);
  border-radius: 5px;
}
</style>
