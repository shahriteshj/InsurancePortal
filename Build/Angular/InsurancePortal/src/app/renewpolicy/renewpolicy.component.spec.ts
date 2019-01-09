import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RenewpolicyComponent } from './renewpolicy.component';

describe('RenewpolicyComponent', () => {
  let component: RenewpolicyComponent;
  let fixture: ComponentFixture<RenewpolicyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RenewpolicyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RenewpolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
