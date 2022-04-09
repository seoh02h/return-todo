<template>
  <div>
    <div class="todo-item" v-bind:class="{ 'todo-complete': todo.complete }">
      <div class="todo" @click="toggle(todo.id)">
        <div v-if="todo.complete" class="complete-img">
          <img src="@/assets/images/complete.png" class="check-box" />
        </div>
        <div v-else>
          <img src="@/assets/images/incomplete.png" class="check-box" />
        </div>

        <div>{{ todo.content }}</div>
        <div class="tag" v-if="loadTag">
          <div class="tag-color-circle" :style="tagColor"></div>
          <div v-if="tag">
            {{ this.tag.name }}
          </div>
        </div>
        <div class="createdAt">
          <div>{{ formatDate }}</div>
        </div>
      </div>
      <div class="todo-btn">
        <i class="fa fa-pen fa-sm" @click="showEditModal = true"></i>
        <i class="fa fa-trash-alt fa-sm" @click="showDeleteModal = true"></i>
      </div>
    </div>
    <DeleteModal :show="showDeleteModal" @close="showDeleteModal = false" @remove="remove(todo.id)">
      <template #body>
        <div>'{{ todo.content }}' 할 일을 삭제하시겠습니까?</div>
      </template>
    </DeleteModal>
    <TodoEditModal :show="showEditModal" :todo="todo" :tag="tag" @close="showEditModal = false">
    </TodoEditModal>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import DeleteModal from "@/components/common/DeleteModal.vue";
import TodoEditModal from "@/components/todo/TodoEditModal.vue";
import { todo, tag } from "@/api";
export default {
  props: ["todo"],
  components: {
    TodoEditModal,
    DeleteModal,
  },
  data() {
    return {
      tag: null,
      loadTag: false,
      showDeleteModal: false,
      showEditModal: false,
    };
  },
  methods: {
    ...mapActions("todo", ["toggleTodoComplete", "deleteTodo", "updateTodo"]),
    getTagInfo() {
      todo.get(this.todo.id).then((todo) => {
        if (todo.tagId) {
          tag.get(todo.tagId).then((tag) => {
            this.tag = tag;
            this.loadTag = true;
          });
        } else {
          this.loadTag = false;
          this.tag = null;
        }
      });
    },
    toggle(id) {
      this.toggleTodoComplete({ id });
    },
    remove(id) {
      this.deleteTodo({ id });
      this.showDeleteModal = false;
    },
  },
  computed: {
    ...mapState("tag", ["tagList"]),
    formatDate() {
      return this.todo.createdAt.slice(5, 7) + "." + this.todo.createdAt.slice(8, 10);
    },
    tagColor() {
      if (this.tag) {
        return { background: this.tag.colorCode };
      } else {
        return {};
      }
    },
  },
  created() {
    this.getTagInfo();
  },
  watch: {
    todo: function () {
      this.getTagInfo();
    },
  },
};
</script>

<style scoped>
.todo-item {
  margin: 17px 30px;
  justify-content: center;
  display: flex;
  font-size: 18px;
}
.todo {
  height: 30px;
  line-height: 30px;
  display: flex;
  width: 380px;
  cursor: pointer;
}
.todo-btn {
  height: 30px;
  line-height: 28px;
  margin-left: 10px;
}
.check-box {
  margin-right: 10px;
  width: 18px;
  opacity: 0.8;
}

img {
  padding-bottom: 2px;
  vertical-align: middle;
}

.todo-complete {
  color: #827f78;
}
.todo-complete i {
  color: #aba9a4;
}
.todo-complete .check-box {
  opacity: 0.6;
  width: 20px;
  margin-right: 8px;
}

.createdAt {
  font-size: 13px;
  margin-left: auto;
}

.tag {
  display: flex;
  align-items: center;
  font-size: 13px;
  margin-left: 8px;
  background-color: rgba(223, 223, 223, 0.343);
  border-radius: 10px;
  margin: 5px;
  padding: 6px;
  padding-right: 8px;
}

.tag-color {
  padding-bottom: 1.5px;
}

.input-color {
  border: none;
  width: 18px;
  height: 20px;
  background-color: transparent;
}

input::-webkit-color-swatch {
  border-radius: 50%;
  border: 1.5px solid rgba(56, 56, 56, 0.067);
}

i {
  font-size: 15px;
  margin: auto 4px;
  color: rgba(0, 0, 0, 0.635);

  cursor: pointer;
}
i:hover {
  color: #bd7c89;
}
.tag-color-circle {
  width: 9px;
  height: 8px;
  background-color: transparent;
  border-radius: 50%;
  border: 1px solid rgba(228, 228, 228, 0.54);
  margin-right: 3px;
}
</style>
