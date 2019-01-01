import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';
import { ProductService } from '../service/product.service';
import { Item } from '../model/Item';
import { NgForm, AbstractControl, FormGroup } from '@angular/forms';
import { CartItem } from '../model/CartItem';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  cartItems: CartItem[] = [];
  products: Product[];
  cartItem: CartItem;

  constructor(private _productService: ProductService) { }

  ngOnInit() {
    this.getProducts().subscribe(products => {
    this.products = <Product[]>products;
      let i: number = 1;
      for (let p of this.products) {
        let cartItem1: CartItem = {
          id: i,
          product: <Product>p,
          quantity: 0
        }
        this.cartItems.push(cartItem1);
        i = i + 1;
      }
    });
    console.log(this.cartItems);
  }

  getProducts() {
    return this._productService.getProducts();
  }

  AddItem(frm: NgForm) {

    //let frmMap:AbstractControl = frm.controls;
    let group: FormGroup = frm.control
    this.logKeyValuePairs(group);
  }

  logKeyValuePairs(group: FormGroup): void {

    Object.keys(group.controls).forEach((key: string) => {
      const abstractControl = group.get(key);
      if (abstractControl instanceof FormGroup) {
        this.logKeyValuePairs(abstractControl);
      } else {
        console.log('Key: ' + key + ' Value: ' + abstractControl.value);
      }
    }
    );
  }
  // frm.controls.forEach(element => {
  //   frm.get(element).   
  // });
  //   let item: Item = {

  //     name: frm.value.name,
  //     quantity: Number(frm.value.quantity)
  //   }
  // }

}
