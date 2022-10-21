import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaSobreComponent } from './area-sobre.component';

describe('AreaSobreComponent', () => {
  let component: AreaSobreComponent;
  let fixture: ComponentFixture<AreaSobreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AreaSobreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AreaSobreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
