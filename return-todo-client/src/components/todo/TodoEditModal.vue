<template>
  <Transition name="modal">
    <div v-if="show" class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <i @click="close" class="fa fa-times"></i>
          </div>
          <div class="modal-body">
            <img src="@/assets/images/todo.png" width="60px" />
            <div class="error">{{ error }}</div>
            <div class="tag-input-form">
              <div class="tag-color" :style="tagColor"></div>
              <select class="select-tag" v-model="formTag">
                <option value="null">선택안함</option>
                <option v-for="(tagItem, i) in tagList" :key="i" :value="tagItem">
                  {{ tagItem.name }}
                </option>
              </select>
            </div>

            <input
              ref="input"
              :value="formContent"
              @input="changeInput"
              type="text"
              class="input-text"
              placeholder="할 일을 입력하세요"
            />
          </div>

          <div class="modal-footer">
            <button class="modal-btn" @click="update">저장</button>
            <button class="modal-btn" @click="close">닫기</button>
          </div>
        </div>
      </div>
    </div>
  </Transition>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  props: ["show", "todo", "tag"],
  data() {
    return {
      formContent: "",
      formTag: null,
      error: "",
    };
  },
  methods: {
    ...mapActions("todo", ["getTodoList", "updateTodo"]),

    close() {
      this.$emit("close");
    },
    update() {
      if (this.formContent.length === 0) {
        this.error = "할 일을 1 글자 이상 입력하세요";
      } else {
        if (this.formTag) {
          this.error = "";
          this.updateTodo({ id: this.todo.id, content: this.formContent, tagId: this.formTag.id });
        } else {
          this.updateTodo({ id: this.todo.id, content: this.formContent, tagId: null });
        }
        this.$emit("close");
      }
    },
    changeInput(e) {
      this.formContent = e.target.value;
    },
  },
  computed: {
    ...mapState("tag", ["tagList"]),
    tagColor() {
      if (this.formTag) {
        return { background: this.formTag.colorCode };
      } else {
        return {};
      }
    },
  },
  created() {
    this.formContent = this.todo.content;
    this.formTag = this.tag;
  },
  watch: {
    tag: function (value) {
      this.formTag = value;
    },
    formContent: function (value) {
      if (value.length > 10) {
        this.error = "할 일은 10 글자를 초과할 수 없습니다.";
        this.formContent = value.slice(0, 10);
      } else if (value.length < 10) {
        this.error = "";
      }
    },
  },
};
</script>

<style scoped>
.modal-mask {
  position: absolute;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
  border-radius: 10px;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: 0px auto;
  background-color: #fff;
  border-radius: 2px;
  padding: 5px 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  border-radius: 10px;
}

.modal-header {
  padding-top: 5px;
  height: 17px;
}
.modal-header > * {
  float: right;
  cursor: pointer;
}

.modal-body {
  border-bottom: 1.5px solid rgba(179, 176, 178, 0.367);
}

.modal-footer {
  height: 50px;
}

.modal-default-button {
  float: right;
}

.modal-enter {
  opacity: 0;
}
.modal-leave-active {
  opacity: 0;
}
.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
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
  -webkit-appearance: none;
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
