import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMyReservationsComponent } from './view-my-reservations.component';

describe('ViewMyReservationsComponent', () => {
  let component: ViewMyReservationsComponent;
  let fixture: ComponentFixture<ViewMyReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewMyReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewMyReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
