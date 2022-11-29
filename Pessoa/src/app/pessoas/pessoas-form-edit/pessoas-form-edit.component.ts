import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { PessoasService } from '../services/pessoas.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common'
import { Pessoa } from '../model/pessoa';
import { ActivatedRoute } from '@angular/router';
import { map, switchMap } from 'rxjs';

@Component({
  selector: 'app-pessoas-form-edit',
  templateUrl: './pessoas-form-edit.component.html',
  styleUrls: ['./pessoas-form-edit.component.scss']
})
export class PessoasFormEditComponent {
  pessoaEdited: Pessoa;
  idPessoa: number;

  form = this.formBuilder.group(
    {
        nome: [""],
        cpf: [""],
        idade: [0],
        peso: [0],
        bairro: [""],
        cidade: [""],
        estado: [""],
        dataNasc: [""]
    });

    constructor(
      private formBuilder: NonNullableFormBuilder,      //não permite valores null
      private service: PessoasService,
      private snackBar: MatSnackBar,
      private location: Location,
      private route: ActivatedRoute) //existem dois locations, tem que importar
      {

      }

      ngOnInit(): void {
        this.route.params.pipe(
          map((params: any) => params['id']),
          switchMap((id: any) => this.service.getById(id))
        )
          .subscribe(pessoa => this.updateForm(pessoa))
      }

      updateForm(pessoa: Pessoa) {
        this.idPessoa = pessoa.id;
        this.form.patchValue({  //seta o valor do campo [""] => nome
          nome: pessoa.nome,
          cpf: pessoa.cpf,
          idade: pessoa.idade,
          peso: pessoa.peso,
          bairro: pessoa.bairro,
          cidade: pessoa.cidade,
          estado: pessoa.estado,
          dataNasc: pessoa.dataNasc
        })
      }

      onSubmitEdit()
      {
        this.service.update(this.idPessoa, this.form.value)
        .subscribe({
          next: () => {this.onSuccess()},
          error: () => {this.onError()}
        })
      }

      private onSuccess()
      {
        this.snackBar.open("usuário editado", "", {duration: 3000})
      }

      private onError() {
        this.snackBar.open("erro ao editar usuário", "", {duration: 3000})
      }
}
