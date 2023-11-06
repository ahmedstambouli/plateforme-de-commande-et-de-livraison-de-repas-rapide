import { TestBed } from '@angular/core/testing';

import { ServiceLivreurService } from './service-livreur.service';

describe('ServiceLivreurService', () => {
  let service: ServiceLivreurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceLivreurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
