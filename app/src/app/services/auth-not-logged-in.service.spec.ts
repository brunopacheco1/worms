import { TestBed } from '@angular/core/testing';

import { AuthNotLoggedInService } from './auth-not-logged-in.service';

describe('AuthNotLoggedInService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthNotLoggedInService = TestBed.get(AuthNotLoggedInService);
    expect(service).toBeTruthy();
  });
});
