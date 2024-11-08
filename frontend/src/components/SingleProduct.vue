<script lang="ts">
import { defineComponent, onMounted, ref, watch } from "vue";
import { useProductCache } from "@/composables/useProductCache";
import { Product } from "@/interfaces/product.interface";
import { useRoute, useRouter } from "vue-router";

export default defineComponent({
  name: "SingleProduct",
  setup() {
    const { getProduct, getProducts } = useProductCache();
    const product = ref<Product | null>(null);
    const products = ref<Product[]>([]);
    const route = useRoute();
    const router = useRouter();
    const id = ref(Number(route.params.id));

    const fetchProduct = async (productId: number) => {
      product.value = await getProduct(productId);
      products.value = await getProducts();
    };

    onMounted(async () => {
      await fetchProduct(id.value);
    });

    watch(route, (newRoute) => {
      id.value = Number(newRoute.params.id);
      fetchProduct(id.value);
    });

    const goToPreviousProduct = () => {
      const currentIndex = products.value.findIndex((p) => p.id === id.value);
      if (currentIndex > 0) {
        const prevId = products.value[currentIndex - 1].id;
        router.push(`/product/${prevId}`);
      }
    };

    const goToNextProduct = () => {
      const currentIndex = products.value.findIndex((p) => p.id === id.value);
      if (currentIndex < products.value.length - 1) {
        const nextId = products.value[currentIndex + 1].id;
        router.push(`/product/${nextId}`);
      }
    };

    return { product, goToNextProduct, goToPreviousProduct, products };
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
      <div class="nav-btn-wrapper">
        <button
          @click="goToPreviousProduct"
          :disabled="!products.length || products[0].id === product.id"
        >
          Previous
        </button>
        <button
          @click="goToNextProduct"
          :disabled="
            !products.length || products[products.length - 1].id === product.id
          "
        >
          Next
        </button>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
@import "@/styles/_variables.scss";
.single-product {
  max-width: 800px;
  margin: 20px auto 0;
}
.nav-btn-wrapper {
  display: flex;
  justify-content: space-between;
  max-width: 300px;
  margin: 50px auto 0;

  button {
    padding: 10px 20px;
    font-size: 1rem;
    cursor: pointer;
    border: 1px solid $primary-color;
    border-radius: 5px;
    background-color: $primary-color;
    color: white;

    &:disabled {
      cursor: not-allowed;
      background-color: lighten($disabled-color, 20%);
      border-color: lighten($disabled-color, 20%);
    }
  }
}
</style>
