import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoasFormEditComponent } from './pessoas-form-edit.component';

describe('PessoasFormEditComponent', () => {
  let component: PessoasFormEditComponent;
  let fixture: ComponentFixture<PessoasFormEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PessoasFormEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PessoasFormEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
