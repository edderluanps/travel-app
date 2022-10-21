import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaLocaisComponent } from './area-locais.component';

describe('AreaLocaisComponent', () => {
  let component: AreaLocaisComponent;
  let fixture: ComponentFixture<AreaLocaisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AreaLocaisComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AreaLocaisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
