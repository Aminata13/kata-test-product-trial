import {Injectable, inject, signal, computed} from "@angular/core";
import { CartItem, Product } from "./product.model";
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, of, tap } from "rxjs";

@Injectable({
    providedIn: "root"
}) export class ProductsService {

    private readonly http = inject(HttpClient);
    private readonly path = "/api/products";

    private readonly _products = signal<Product[]>([]);

    public readonly products = this._products.asReadonly();

    private items = signal<CartItem[]>([]);

    cartItems = this.items.asReadonly();

    totalItems = computed(() =>
        this.items().reduce((total, item) => total + item.quantity, 0)
    );
    totalPrice = computed(() =>
        this.items().reduce((total, item) => total + (item.product.price * item.quantity), 0)
    );

    public get(): Observable<Product[]> {
        return this.http.get<Product[]>(this.path).pipe(
            catchError((error) => {
                return this.http.get<Product[]>("assets/products.json");
            }),
            tap((products) => this._products.set(products)),
        );
    }

    public create(product: Product): Observable<boolean> {
        return this.http.post<boolean>(this.path, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => [product, ...products])),
        );
    }

    public update(product: Product): Observable<boolean> {
        return this.http.patch<boolean>(`${this.path}/${product.id}`, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => {
                return products.map(p => p.id === product.id ? product : p)
            })),
        );
    }

    public delete(productId: number): Observable<boolean> {
        return this.http.delete<boolean>(`${this.path}/${productId}`).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => products.filter(product => product.id !== productId))),
        );
    }

    addItem(product: Product): void {
        const currentItems = this.items();
        const existingItem = currentItems.find(item => item.product.id === product.id);

        if (existingItem) {
            this.items.update(items =>
                items.map(item =>
                    item.product.id === product.id
                        ? { ...item, quantity: item.quantity + 1 }
                        : item
              )
            );
        } else {
            this.items.update(items => [...items, { product, quantity: 1 }]);
        }
    }

    removeItem(productId: string | number): void {
        this.items.update(items => items.filter(item => item.product.id !== productId));
    }

}
