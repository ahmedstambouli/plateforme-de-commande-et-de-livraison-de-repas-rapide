import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDeliveryPersonComponent } from './new-delivery-person.component';

describe('NewDeliveryPersonComponent', () => {
  let component: NewDeliveryPersonComponent;
  let fixture: ComponentFixture<NewDeliveryPersonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewDeliveryPersonComponent]
    });
    fixture = TestBed.createComponent(NewDeliveryPersonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
