import { Component } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { PessoasService } from '../services/pessoas.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common'
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-pessoa-form',
  templateUrl: './pessoa-form.component.html',
  styleUrls: ['./pessoa-form.component.scss']
})
export class PessoaFormComponent {


    form = this.formBuilder.group(
      {
          nome: ["", Validators.required],
          cpf: [""],
          idade: [0],
          peso: [0],
          bairro: [""],
          cidade: [""],
          estado: [""],
          dataNasc: [""]
      });

    constructor(
      private formBuilder: NonNullableFormBuilder, //não permite valores null
      private service: PessoasService,
      private snackBar: MatSnackBar,
      private location: Location) //existem dois locations, tem que importar
    {

    }

    ngOnInit(): void
    {

    }

    onSubmit()
    {
      this.service.save(this.form.value)
      .subscribe({
        next: () => {this.onSuccess()},
        error: () => {this.onError()}
      })
    }

    onCancel()
    {
      this.location.back();
    }

    private onSuccess()
    {
      this.snackBar.open("usuário cadastrado", "", {duration: 3000})
      this.onCancel(); // para voltar para pagina inicial
    }

    private onError() {
      this.snackBar.open("erro ao cadastrar usuário", "", {duration: 3000})
    }

}
