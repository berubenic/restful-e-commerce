<template>
  <nav>
    <router-link to="/">Home</router-link>
    |
    <router-link to="/products">Products</router-link>
  </nav>
  <div class="wrapper">
    <router-view />
  </div>
</template>

<style lang="scss">
@import "@/styles/_variables.scss";

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: $primary-color;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

.wrapper {
  padding: 30px;

  @media (min-width: 768px) {
    max-width: 800px;
    margin: 0 auto;
  }

  @media (min-width: 1024px) {
    max-width: 1024px;
  }

  @media (min-width: 1280px) {
    max-width: 1280px;
  }
}
</style>
<script setup lang="ts"></script>
