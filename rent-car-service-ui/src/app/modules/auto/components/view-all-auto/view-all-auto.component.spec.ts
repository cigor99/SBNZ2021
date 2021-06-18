import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllAutoComponent } from './view-all-auto.component';

describe('ViewAllAutoComponent', () => {
  let component: ViewAllAutoComponent;
  let fixture: ComponentFixture<ViewAllAutoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewAllAutoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllAutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
