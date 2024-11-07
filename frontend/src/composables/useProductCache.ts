// src/composables/useProductCache.ts
import { ref } from "vue";
import backendApi from "@/api/backend-api";
import { Product } from "@/interfaces/product.interface";
import { ProductFactory } from "@/factories/product.factory";

const productCache = ref<{ [key: number]: Product }>({});

export function useProductCache() {
  const getProduct = async (id: number) => {
    if (!productCache.value[id]) {
      const response = await backendApi.product(id);
      if (typeof response.data === "object" && response.data !== null) {
        productCache.value[id] = ProductFactory.create(response.data);
      } else {
        console.error("Expected an object but got:", response.data);
      }
    }
    return productCache.value[id];
  };

  const getProducts = async () => {
    if (Object.keys(productCache.value).length === 0) {
      const response = await backendApi.products();
      if (Array.isArray(response.data)) {
        response.data.forEach((product: Product) => {
          productCache.value[product.id] = ProductFactory.create(product);
        });
      } else {
        console.error("Expected an array but got:", response.data);
      }
    }
    return Object.values(productCache.value);
  };

  return { getProduct, getProducts };
}
