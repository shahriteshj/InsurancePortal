import { Component, OnInit } from '@angular/core';

import { Item } from '../model/Item';
import { Product } from '../model/product';
import { ProductService } from '../service/product.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[];
  toggleForm: Boolean = false;
  selectedProduct: Product;

  constructor(private _productService: ProductService) { }

  ngOnInit() {
    this.getProducts();
    console.log(this.products);

  }

  getProducts() {
    return this._productService.getProducts().subscribe(products => { this.products = <Product[]>products; });
  }


  addProduct(frm: NgForm): void {

    let product: Product = {

      name: frm.value.name,
      price: Number(frm.value.price),
      photoPath: frm.value.photo
    }
    console.log()
    this._productService.addProduct(product).subscribe(product => { this.getProducts(); })

  }


  updateProduct(editfrm: NgForm) {
    let newProduct: Product = {
      id: editfrm.value.id,
      name: editfrm.value.name,
      price: Number(this.selectedProduct.price),
      photoPath: editfrm.value.photo,

    }
    this._productService.updateProduct(newProduct)
      .subscribe(result => {
        console.log('original product to be updated with old values' + result);
        this.getProducts();
        this.toggleForm = false;
      })

  }


  showEditform(product) {
    this.selectedProduct = product;
    this.toggleForm = true;
  }

  showAddForm() {
    this.toggleForm = false;
  }

  deleteItem(id) {
    this._productService.removeProduct(id)
      .subscribe(result => {
        console.log(result);

        for (var i = 0; i < this.products.length; i++) {
          if (id == this.products[i].id) {
            this.products.splice(i, 1);
          }
        }
      })
  }


}
