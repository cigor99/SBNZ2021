import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFormAutoComponent } from './add-form-auto.component';

describe('AddFormAutoComponent', () => {
  let component: AddFormAutoComponent;
  let fixture: ComponentFixture<AddFormAutoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddFormAutoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFormAutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
