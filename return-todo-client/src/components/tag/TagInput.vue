<template>
  <div class="tag-input">
    <div class="input-form" @click="closeElse">
      <input class="input-color" type="color" v-model="colorCode" />
      <input
        class="input-text"
        @keyup.enter="addTag"
        type="text"
        v-model="name"
        placeholder="태그를 입력하세요"
      />
      <i @click="addTag" class="far fa-plus-square"></i>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import EventBus from "@/eventBus.js";
export default {
  data() {
    return {
      colorCode: "#ffffff",
      name: "",
    };
  },
  methods: {
    ...mapActions("tag", ["getTagList", "saveTag"]),
    addTag() {
      this.saveTag({ colorCode: this.colorCode, name: this.name });
      this.clearInput();
    },
    clearInput() {
      this.colorCode = "#ffffff";
      this.name = "";
    },
    closeElse() {
      EventBus.$emit("tagEditClose");
    },
  },
};
</script>

<style scoped>
.tag-input {
  height: 120px;
  display: flex;
}
.input-form {
  margin: auto;
  height: 50px;
  width: 320px;
  border-radius: 50px;
  display: flex;
  background-color: rgba(197, 195, 195, 0.195);
  align-items: center;
  justify-content: center;
}
.input-color {
  cursor: pointer;
  border: none;
  width: 35px;
  height: 35px;
  border-radius: 50%;
  margin-right: 2px;
}
input::-webkit-color-swatch {
  border: 2px solid rgba(74, 74, 74, 0.148);
  border-radius: 50%;
}

.input-text {
  height: 30px;
  line-height: 30px;
  padding-top: 2px;
  border: none;
  background: transparent;
  width: 180px;
  font-size: 18px;
}
.input-text::placeholder {
  color: rgba(18, 18, 18, 0.282);
  font-size: 18px;
}

.input-text:focus {
  outline: none;
  background: transparent;
}
i {
  font-size: 20px;
  color: rgb(91, 89, 85);
  cursor: pointer;
}
i:hover {
  color: rgb(176 187 222);
}
</style>
