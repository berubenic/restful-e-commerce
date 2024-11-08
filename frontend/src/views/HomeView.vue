<template>
  <div class="home">
    <h1>Assignment 2 - RESTful E-Commerce</h1>

    <div class="text-wrapper">
      <p>
        This app features two primary models: <strong>Product</strong> and
        <strong>Category</strong>. Both models support full
        <strong>CRUD</strong> (Create, Read, Update, Delete) operations,
        allowing users to manage products and their associated categories. The
        application leverages <i>Vue.js</i> for the frontend, with
        <i>Vue Router</i> for navigation and <i>Axios</i> for API requests to
        the <i>Spring Boot</i> web service.
      </p>
    </div>

    <div class="left-aligned models-wrapper">
      <div class="model-wrapper">
        <div class="section">
          <h2>Product</h2>

          <ul>
            <li>Product ID</li>
            <li>Name</li>
            <li>Description</li>
            <li>Price</li>
            <li>Stock Quantity</li>
            <li>Image</li>
            <li>Category</li>
          </ul>
        </div>

        <div class="section">
          <h3>Operations</h3>

          <ul>
            <li>Create</li>
            <li>
              Read
              <ul>
                <li>
                  <router-link to="/products">All</router-link>
                </li>
                <li>
                  <router-link to="/product/1">One</router-link>
                </li>
              </ul>
            </li>
            <li>Update</li>
            <li>Delete</li>
          </ul>
        </div>
      </div>

      <div class="model-wrapper">
        <div class="section">
          <h2>Category</h2>

          <ul>
            <li>Category ID</li>
            <li>Name</li>
            <li>Description</li>
          </ul>
        </div>

        <div class="section">
          <h3>Operations</h3>

          <ul>
            <li>Create</li>
            <li>
              Read
              <ul>
                <li>All</li>
                <li>One</li>
              </ul>
            </li>
            <li>Update</li>
            <li>Delete</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "HomeView",
  components: {},
});
</script>

<style scoped>
@import "@/styles/_variables.scss";

.home {
  text-align: center;
  max-width: 800px;
  margin: 0 auto;
}

.left-aligned {
  text-align: left;
}

.models-wrapper {
  display: flex;
  justify-content: space-evenly;
}

.model-wrapper {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-top: 2rem;
  border: 1px solid rgba(44, 62, 80, 0.58);
  border-radius: 5px;
  padding: 10px 50px 20px 20px;
}

h1 {
  margin-top: 0;
}

h2 {
  margin-top: 2rem;
  text-decoration: underline;
}

h3 {
  margin-top: 1rem;
  text-decoration: underline;
}

p {
  font-size: 1.25rem;
  line-height: 1.5;
}
</style>
