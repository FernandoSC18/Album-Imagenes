import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppAlbumsComponent } from './app-albums.component';

describe('AppAlbumsComponent', () => {
  let component: AppAlbumsComponent;
  let fixture: ComponentFixture<AppAlbumsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppAlbumsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppAlbumsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
