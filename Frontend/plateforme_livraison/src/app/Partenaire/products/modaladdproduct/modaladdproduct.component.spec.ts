import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModaladdproductComponent } from './modaladdproduct.component';

describe('ModaladdproductComponent', () => {
  let component: ModaladdproductComponent;
  let fixture: ComponentFixture<ModaladdproductComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModaladdproductComponent]
    });
    fixture = TestBed.createComponent(ModaladdproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
