import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppImagesComponent } from './app-images.component';

describe('AppImagesComponent', () => {
  let component: AppImagesComponent;
  let fixture: ComponentFixture<AppImagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppImagesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppImagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
