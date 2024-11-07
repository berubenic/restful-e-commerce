<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import { useProductCache } from "@/composables/useProductCache";
import { Product } from "@/interfaces/product.interface";
import { useRoute } from "vue-router";

export default defineComponent({
  name: "SingleProduct",
  setup() {
    const { getProduct } = useProductCache();
    const product = ref<Product | null>(null);
    const route = useRoute();
    const id = Number(route.params.id);

    onMounted(async () => {
      product.value = await getProduct(id);
    });

    return { product };
  },
});
</script>

<template>
  <div class="single-product">
    <div v-if="product">
      <h1>{{ product.name }}</h1>
      <p>{{ product.description }}</p>
      <p>Price: ${{ product.price.toFixed(2) }}</p>
      <p>Stock Quantity: {{ product.stockQuantity }}</p>
      <p>
        Category:
        {{ product.category ? product.category.name : "Unclassified" }}
      </p>
    </div>
  </div>
</template>
