import axios from "axios";

export const tag = {
  getList() {
    return axios
      .get("/tag")
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
