import { Component, OnInit, Query, Input } from '@angular/core';
import { User } from '../model/user';

@Component({
  selector: 'app-query-details',
  templateUrl: './query-details.component.html',
  styleUrls: ['./query-details.component.css']
})
export class QueryDetailsComponent implements OnInit {

  @Input() queryId: number;
  
  constructor() { }

  ngOnInit() {
    console.log(this.queryId);
  }


}
