import {Product} from "../../products/data-access/product.model";

export interface CartItem {
    id?: number;
    product: Product;
    quantity: number;
}
