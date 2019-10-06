import { TestBed } from '@angular/core/testing';

import { AuthLoggedInService } from './auth-logged-in.service';

describe('AuthLoggedInService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthLoggedInService = TestBed.get(AuthLoggedInService);
    expect(service).toBeTruthy();
  });
});
