<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import { Product } from "@/interfaces/product.interface";
import { useRouter } from "vue-router";
import { useProductCache } from "@/composables/useProductCache";

export default defineComponent({
  name: "ProductsTable",
  setup() {
    const { getProducts } = useProductCache();
    const products = ref<Product[]>([]);
    const router = useRouter();

    onMounted(async () => {
      products.value = await getProducts();
    });

    const goToProduct = (id: number) => {
      router.push(`/product/${id}`);
    };

    return { products, goToProduct };
  },
});
</script>

<template>
  <div class="products">
    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Stock Quantity</th>
            <th>Category</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="product in products"
            :key="product.id"
            @click="goToProduct(product.id)"
          >
            <td>{{ product.id }}</td>
            <td>{{ product.name }}</td>
            <td>{{ product.description }}</td>
            <td>${{ product.price.toFixed(2) }}</td>
            <td>{{ product.stockQuantity }}</td>
            <td>
              {{ product.category ? product.category.name : "Unclassified" }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import "@/styles/_variables.scss";

.table-wrapper {
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  min-width: 600px; /* Adjust as needed */

  th,
  td {
    border: none;
    border-top: 1px solid lighten($primary-color, 60%);
    border-bottom: 1px solid lighten($primary-color, 60%);
    padding: 8px;
    text-align: left;
  }

  td {
    color: $primary-color;
  }

  tr:nth-child(even) {
    background-color: lighten($primary-color, 70%);
  }

  tbody {
    tr:hover {
      background-color: lighten($primary-color, 60%);
      cursor: pointer;
    }
  }
}
</style>
