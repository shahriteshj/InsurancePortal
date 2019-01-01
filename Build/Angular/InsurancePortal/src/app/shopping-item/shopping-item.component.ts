import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Item } from '../model/Item';

@Component({
  selector: 'app-shopping-item',
  templateUrl: './shopping-item.component.html',
  styleUrls: ['./shopping-item.component.css']
})
export class ShoppingItemComponent implements OnInit {
  items:Item[]=[];
  item:Item;
  
  constructor() { }

  ngOnInit() {
    
  }

  AddItem(frm:NgForm){
    
    let item:Item={
      name:frm.value.name,
      quantity:Number(frm.value.quantity)
    }
    
    this.items.push(item);
  }

}
