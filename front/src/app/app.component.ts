import {
  Component,
  inject,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { ProductsService } from "./products/data-access/products.service";
import { BadgeModule } from 'primeng/badge';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ButtonModule } from "primeng/button";


@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, BadgeModule, OverlayPanelModule, ButtonModule],
})
export class AppComponent {

  public readonly productsService = inject(ProductsService);
  cartItemCount = this.productsService.totalItems;
  title = "ALTEN SHOP";
}
