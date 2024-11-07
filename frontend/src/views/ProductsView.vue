<template>
  <ProductsTable />
</template>

<script lang="ts">
import ProductsTable from "@/components/ProductsTable.vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "ProductsView",
  components: {
    ProductsTable,
  },
});
</script>
