import axios from "axios";

export const todo = {
  getList(complete, tagId) {
    return axios
      .get("/todo", {
        params: {
          complete,
          tagId,
        },
      })
      .then((res) => res.data)
      .catch((err) => console.log(err));
  },
  get(id) {
    return axios
      .get("/todo/" + id)
      .then((res) => res.data)
      .catch((err) => console.log(err));
  },
  create(content, tagId) {
    return axios
      .post("/todo", {
        content,
        tagId,
      })
      .then((res) => res.data)
      .catch((err) => {
        console.log(err);
      });
  },
  update(id, content, tagId) {
    return axios
      .put("/todo/" + id, {
        content,
        tagId,
      })
      .then((res) => res.data)
      .catch((err) => {
        console.log(err);
      });
  },
  toggleComplete(id) {
    return axios
      .put("/todo/" + id + "/complete", {})
      .then((res) => res.data)
      .catch((err) => {
        console.log(err);
      });
  },
  delete(id) {
    return axios
      .delete("/todo/" + id)
      .then((res) => res.data)
      .catch((err) => {
        console.log(err);
      });
  },
};
export const tag = {
  getList() {
    return axios
      .get("/tag")
      .then((res) => res.data)
      .catch((err) => console.log(err));
  },
  get(id) {
    return axios
      .get("/tag/" + id)
      .then((res) => res.data)
      .catch((err) => console.log(err));
  },
  create(colorCode, name) {
    return axios
      .post("/tag", {
        colorCode,
        name,
      })
      .then((res) => res.data)
      .catch((err) => {
        console.log(err.response.data.message);
      });
  },
  update(id, colorCode, name) {
    return axios
      .put("/tag/" + id, {
        colorCode,
        name,
      })
      .then((res) => res.data)
      .catch((err) => {
        console.log(err.response.data.message);
      });
  },
  delete(id) {
    return axios
      .delete("/tag/" + id)
      .then((res) => res.data)
      .catch((err) => {
        console.log(err.response.data.message);
      });
  },
};
