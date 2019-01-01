import { Product } from "./product";

export class CartItem {
    id?: number;
    product: Product;
    quantity: number;

    constructor(product: Product, quantity: number, id?: number) {
        this.product = product;
        this.quantity = quantity;
    }


}