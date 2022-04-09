<template>
  <div class="todo-input">
    <button @click="openModal" class="add-btn"><i class="fa fa-plus"></i></button>
    <Modal :show="showModal" @close="showModal = false">
      <template #body>
        <div class="modal-body">
          <img src="@/assets/images/todo.png" width="60px" />
          <div class="error">{{ error }}</div>

          <div class="tag-input-form">
            <div class="tag-color" :style="tagColor"></div>
            <select class="select-tag" v-model="tag">
              <option value="null">선택안함</option>
              <option v-for="(tag, i) in tagList" :key="i" :value="tag">
                {{ tag.name }}
              </option>
            </select>
          </div>
          <input
            ref="input"
            :value="content"
            @input="changeInput"
            @keypress.enter="addTodo"
            type="text"
            class="input-text"
            placeholder="할 일을 입력하세요"
          />
        </div>
      </template>

      <template #footer>
        <button class="modal-btn" @click="addTodo">저장</button>
        <button class="modal-btn" @click="showModal = false">닫기</button>
      </template>
    </Modal>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import Modal from "@/components/common/Modal.vue";
export default {
  components: {
    Modal,
  },
  computed: {
    ...mapState("tag", ["tagList"]),
    tagColor() {
      if (this.tag) {
        return { background: this.tag.colorCode };
      } else {
        return {
          background: "rgba(64, 64, 64, 0.263)",
        };
      }
    },
  },
  data() {
    return {
      content: "",
      tag: null,
      showModal: false,
      error: "",
    };
  },
  methods: {
    ...mapActions("tag", ["getTagList"]),
    ...mapActions("todo", ["saveTodo"]),
    openModal() {
      this.showModal = true;
    },
    addTodo() {
      if (this.content.length === 0) {
        this.error = "할 일을 1 글자 이상 입력하세요";
      } else {
        this.error = "";
        if (this.tag) {
          this.saveTodo({ content: this.content, tagId: this.tag.id });
        } else {
          this.saveTodo({ content: this.content, tagId: null });
        }
        this.content = "";
        this.tag = null;
        this.showModal = false;
      }
    },
    changeInput(e) {
      this.content = e.target.value;
    },
  },
  created() {
    this.getTagList();
  },
  watch: {
    content: function (value) {
      if (value.length > 10) {
        this.error = "할 일은 10 글자를 초과할 수 없습니다.";
        this.content = value.slice(0, 10);
      } else if (value.length < 10) {
        this.error = "";
      }
    },
  },
};
</script>

<style scoped>
.todo-input {
  width: 100%;
  display: flex;
  justify-content: center;
  cursor: pointer;
}

.add-btn {
  background-color: transparent;
  border: none;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  line-height: 50px;
  font-size: 30px;
  color: rgb(244, 238, 238);
  background-color: #bd7c89;
  box-shadow: 3px 3px 10px 2px rgba(94, 94, 94, 0.128);

  cursor: pointer;
}

.add-btn:hover {
  background-color: #bd7281;
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

.modal-body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 2px;
  padding-bottom: 10px;
}
.tag-input-form {
  display: flex;
  justify-content: center;
  align-items: center;
}
.input-text {
  border: none;
  background-color: rgba(128, 128, 128, 0.191);
  border-radius: 20px;
  width: 220px;
  height: 33px;
  margin: 5px;
  font-size: 14px;
  padding: 0 20px;
}
.input-text:focus {
  outline: none;
}
.input-text::placeholder {
  color: rgba(18, 18, 18, 0.282);
  font-size: 14px;
}

select {
  -webkit-appearance: none; /* 네이티브 외형 감추기 */
  -moz-appearance: none;
  appearance: none;
  border-radius: 4px;
  width: 150px;
  height: 30px;
  border: none;
  cursor: pointer;
  color: black;
  font-size: 14px;
  margin-right: 70px;
}
select:focus {
  outline: none;
}
.tag-color {
  width: 15px;
  height: 15px;
  background-color: transparent;
  border-radius: 50%;
  border: 1px solid rgba(228, 228, 228, 0.54);
  margin-right: 5px;
  margin-left: 4px;
  cursor: default;
}
.error {
  padding-top: 10px;
  height: 15px;
  color: rgb(180, 24, 24);
  font-size: 12px;
}
</style>
