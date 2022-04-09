<template>
  <Transition name="modal">
    <div v-if="show" class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <i @click="close" class="fa fa-times"></i>
          </div>
          <div class="modal-body">
            <div><i class="fa fa-trash-alt body-icon"></i></div>
            <slot name="body">default body</slot>
          </div>
          <div class="modal-footer">
            <button @click="close">닫기</button>
            <button @click="remove">삭제</button>
          </div>
        </div>
      </div>
    </div>
  </Transition>
</template>

<script>
export default {
  props: {
    show: Boolean,
  },
  methods: {
    close() {
      this.$emit("close");
    },
    remove() {
      this.$emit("remove");
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
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 10px;
}

.body-icon {
  font-size: 40px;
  color: rgb(193, 43, 43);
  margin-bottom: 10px;
}
.modal-footer {
  height: 50px;
}

.modal-footer button {
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
.modal-footer button:hover {
  background-color: rgba(191, 191, 191, 0.382);
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
</style>
