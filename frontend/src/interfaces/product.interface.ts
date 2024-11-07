import { Category } from "@/interfaces/category.interface";

export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  stockQuantity: number;
  imagePath: string;
  category?: Category;
}
