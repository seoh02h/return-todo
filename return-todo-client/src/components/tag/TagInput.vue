<template>
  <div class="tag-input">
    <div class="input-form">
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
export default {
  data() {
    return {
      colorCode: "#ffffff",
      name: "",
    };
  },
  methods: {
    addTag() {
      this.$emit("addTag", this.colorCode, this.name);
      this.clearInput();
    },
    clearInput() {
      this.colorCode = "#ffffff";
      this.name = "";
    },
  },
};
</script>

<style scoped>
.tag-input {
  height: 100px;
  display: flex;
}
.input-form {
  margin: auto;
  height: 40px;
  width: 300px;
  border-radius: 50px;
  display: flex;
  background-color: rgba(197, 195, 195, 0.195);
  align-items: center;
  justify-content: center;
}
.input-color {
  cursor: pointer;
  background-color: rgb(222, 222, 222);
  border: none;
  width: 32px;
  height: 30px;
  border-radius: 50%;
  border: 1px solid rgb(210, 210, 210);
  margin-right: 10px;
  color: #2f2f2f;
}
input::-webkit-color-swatch {
  border: 2px solid rgba(123, 123, 123, 0.357);
  border-radius: 50%;
}
.input-text {
  border: none;
  background: transparent;
}
.input-text::placeholder {
  color: rgba(18, 18, 18, 0.282);
  font-size: 15px;
}
.input-text:focus {
  outline: none;
  background: transparent;
}
i {
  font-size: 20px;
  color: rgb(91, 89, 85);
}
</style>
