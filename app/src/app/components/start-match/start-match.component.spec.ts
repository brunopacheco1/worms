import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StartMatchComponent } from './start-match.component';

describe('StartMatchComponent', () => {
  let component: StartMatchComponent;
  let fixture: ComponentFixture<StartMatchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StartMatchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StartMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
