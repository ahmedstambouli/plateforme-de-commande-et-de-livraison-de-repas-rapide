import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NaveBarPartenaireComponent } from './nave-bar-partenaire.component';

describe('NaveBarPartenaireComponent', () => {
  let component: NaveBarPartenaireComponent;
  let fixture: ComponentFixture<NaveBarPartenaireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NaveBarPartenaireComponent]
    });
    fixture = TestBed.createComponent(NaveBarPartenaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
