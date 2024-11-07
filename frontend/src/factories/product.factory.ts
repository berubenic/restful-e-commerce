import { Product } from "@/interfaces/product.interface";
import { CategoryFactory } from "@/factories/category.factory";

export class ProductFactory {
  static create(productParams: Product): Product {
    return {
      id: productParams.id,
      name: productParams.name,
      description: productParams.description,
      price: productParams.price,
      stockQuantity: productParams.stockQuantity,
      imagePath: productParams.imagePath,
      category: productParams.category
        ? CategoryFactory.create(productParams.category)
        : undefined,
    };
  }
}
