import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaPacotesComponent } from './area-pacotes.component';

describe('AreaPacotesComponent', () => {
  let component: AreaPacotesComponent;
  let fixture: ComponentFixture<AreaPacotesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AreaPacotesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AreaPacotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
