import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePartenaireComponent } from './update-partenaire.component';

describe('UpdatePartenaireComponent', () => {
  let component: UpdatePartenaireComponent;
  let fixture: ComponentFixture<UpdatePartenaireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdatePartenaireComponent]
    });
    fixture = TestBed.createComponent(UpdatePartenaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
