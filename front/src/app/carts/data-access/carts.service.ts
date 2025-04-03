import {computed, Injectable, signal} from '@angular/core';
import {Product} from "../../products/data-access/product.model";
import {CartItem} from "./cartItem.model";

@Injectable({
  providedIn: 'root'
})
export class CartsService {

    private _items = signal<CartItem[]>([]);

    public readonly cartItems = this._items.asReadonly();

    totalItems = computed(() =>
        this._items().reduce((total, item) => total + item.quantity, 0)
    );
    totalPrice = computed(() =>
        this._items().reduce((total, item) => total + (item.product.price * item.quantity), 0)
    );

    public addItem(product: Product): void {
        const currentItems = this._items();
        const existingItem = currentItems.find(item => item.product.id === product.id);

        if (existingItem) {
            this._items.update(items =>
                items.map(item =>
                    item.product.id === product.id
                        ? { ...item, quantity: item.quantity + 1 }
                        : item
                )
            );
        } else {
            this._items.update(items => [...items, { product, quantity: 1 }]);
        }
    }

    public removeItem(productId: string | number): void {
        this._items.update(items => items.filter(item => item.product.id !== productId));
    }

}
