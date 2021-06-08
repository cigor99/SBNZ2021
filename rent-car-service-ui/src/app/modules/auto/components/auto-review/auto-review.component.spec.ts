import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutoReviewComponent } from './auto-review.component';

describe('AutoReviewComponent', () => {
  let component: AutoReviewComponent;
  let fixture: ComponentFixture<AutoReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutoReviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutoReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
