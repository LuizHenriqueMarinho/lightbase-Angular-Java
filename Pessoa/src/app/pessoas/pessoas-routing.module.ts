import { PessoasComponent } from './pessoas/pessoas.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PessoaFormComponent } from './pessoa-form/pessoa-form.component';
import { PessoasFormEditComponent } from './pessoas-form-edit/pessoas-form-edit.component';

const routes: Routes = [
  {path: '', component: PessoasComponent },
  {path: 'new', component: PessoaFormComponent },
  {path: 'edit/:id', component: PessoasFormEditComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PessoasRoutingModule { }
