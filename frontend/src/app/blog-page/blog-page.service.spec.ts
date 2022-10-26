import { TestBed } from '@angular/core/testing';

import { BlogPageService } from './blog-page.service';

describe('BlogPageService', () => {
  let service: BlogPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BlogPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
