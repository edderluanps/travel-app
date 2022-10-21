import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaBlogComponent } from './area-blog.component';

describe('AreaBlogComponent', () => {
  let component: AreaBlogComponent;
  let fixture: ComponentFixture<AreaBlogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AreaBlogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AreaBlogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
