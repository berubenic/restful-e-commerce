<template>
  <SingleProduct />
</template>

<script lang="ts">
import SingleProduct from "@/components/SingleProduct.vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "ProductsView",
  components: {
    SingleProduct,
  },
});
</script>
