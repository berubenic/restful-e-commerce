import { Category } from "@/interfaces/category.interface";

export class CategoryFactory {
  static create(categoryParams: Category): Category {
    return {
      id: categoryParams.id,
      name: categoryParams.name,
      description: categoryParams.description,
    };
  }
}
