<template>
  <div class="tag-input">
    <div class="error">
      {{ error }}
    </div>
    <div class="input-form" @click="closeElse">
      <input class="input-color" type="color" v-model="colorCode" />
      <input
        class="input-text"
        @keyup.enter="addTag"
        type="text"
        @input="changeInput"
        :value="name"
        placeholder="태그를 입력하세요"
      />
      <i @click="addTag" class="far fa-plus-square"></i>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import EventBus from "@/eventBus.js";
export default {
  data() {
    return {
      colorCode: "#ffffff",
      name: "",
      error: "",
    };
  },
  computed: {
    ...mapGetters("tag", ["getTagTotalNum"]),
  },
  methods: {
    ...mapActions("tag", ["getTagList", "saveTag"]),
    addTag() {
      if (this.getTagTotalNum == 8) {
        this.error = "태그는 최대 8개 생성 가능합니다.";
      } else if (this.name.length === 0) {
        this.error = "태그를 1 글자 이상 입력하세요";
      } else {
        this.error = "";
        this.saveTag({ colorCode: this.colorCode, name: this.name });
        this.clearInput();
      }
    },
    changeInput(e) {
      this.name = e.target.value;
    },
    clearInput() {
      this.colorCode = "#ffffff";
      this.name = "";
    },
    closeElse() {
      EventBus.$emit("tagEditClose");
    },
  },
  created() {
    this.error = "";
  },
  watch: {
    name: function (value) {
      if (value.length > 6) {
        this.error = "태그는 6 글자를 초과할 수 없습니다.";
        this.name = value.slice(0, 6);
      } else if (value.length < 6) {
        this.error = "";
      }
    },
  },
};
</script>

<style scoped>
.tag-input {
  height: 80px;
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
  color: #bd7c89;
}
.error {
  margin-top: 20px;
  height: 20px;
  color: rgb(180, 24, 24);
  font-size: 14px;
  margin-left: 170px;
}
</style>
