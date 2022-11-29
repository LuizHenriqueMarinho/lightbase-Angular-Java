import { AppMaterialModule } from './../shared/app-material/app-material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';

import { PessoasRoutingModule } from './pessoas-routing.module';
import { PessoasComponent } from './pessoas/pessoas.component';
import { PessoaFormComponent } from './pessoa-form/pessoa-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PessoasFormEditComponent } from './pessoas-form-edit/pessoas-form-edit.component';

@NgModule({
  declarations: [
    PessoasComponent,
    PessoaFormComponent,
    PessoasFormEditComponent
  ],
  imports: [
    CommonModule,
    PessoasRoutingModule,
    AppMaterialModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class PessoasModule { }
