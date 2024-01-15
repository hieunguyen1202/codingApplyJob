import {
  Component,
  ElementRef,
  HostListener,
  OnInit,
  ViewChild,
} from '@angular/core';
import { Product } from '../../interface/product.interface';
import { ProductService } from '../../services/product.service';
import { Observable, from } from 'rxjs';
import AOS from 'aos';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
  @ViewChild('listJob') listJob: ElementRef | undefined;
  backgroundColorNavbar: string = 'transparent';
  textColor: string = 'white';
  boxShadowBottom: string = 'none';

  products$: Observable<Product[]> | undefined;

  responsiveOptions: any[] | undefined;

  constructor(private productService: ProductService) {}

  ngOnInit() {
    AOS.init();
    this.products$ = from(this.productService.getProductsSmall());

    this.responsiveOptions = [
      {
        breakpoint: '1199px',
        numVisible: 1,
        numScroll: 1,
      },
      {
        breakpoint: '991px',
        numVisible: 2,
        numScroll: 1,
      },
      {
        breakpoint: '767px',
        numVisible: 1,
        numScroll: 1,
      },
    ];

    this.checkWindowScroll();
  }

  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): void {
    this.checkWindowScroll(); // Check window scroll position
  }

  private checkWindowScroll(): void {
    const scrollY = window.scrollY;
    this.backgroundColorNavbar = scrollY > 0 ? '#e2e8f0' : 'transparent';
    this.textColor = scrollY > 0 ? 'black' : 'white';
    this.boxShadowBottom =
      scrollY > 0 ? '0 4px 8px rgba(0, 0, 0, 0.1)' : 'none';
  }

  getSeverity(status: string): 'success' | 'warning' | 'danger' | undefined {
    switch (status) {
      case 'INSTOCK':
        return 'success';
      case 'LOWSTOCK':
        return 'warning';
      case 'OUTOFSTOCK':
        return 'danger';
      default:
        return undefined;
    }
  }
}
