import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OcenaDialogComponent } from './ocena-dialog.component';

describe('OcenaDialogComponent', () => {
  let component: OcenaDialogComponent;
  let fixture: ComponentFixture<OcenaDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OcenaDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OcenaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
