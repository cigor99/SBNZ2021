import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedSearchAutoComponent } from './advanced-search-auto.component';

describe('AdvancedSearchAutoComponent', () => {
  let component: AdvancedSearchAutoComponent;
  let fixture: ComponentFixture<AdvancedSearchAutoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvancedSearchAutoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedSearchAutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
