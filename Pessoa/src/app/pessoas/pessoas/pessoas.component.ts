import { Component } from '@angular/core';
import { Pessoa } from '../model/pessoa';
import { PessoasService } from '../services/pessoas.service';
import { Observable, of } from 'rxjs';
import { catchError, } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-pessoas',
  templateUrl: './pessoas.component.html',
  styleUrls: ['./pessoas.component.scss']
})
export class PessoasComponent {

  pessoas$: Observable<Pessoa[]>; //por convenção, usaa-se observable com $
  displayedColumns = ['name', 'cpf' , 'idade', 'peso', 'bairro', 'cidade', 'estado', 'dataNasc', 'actions'];


  constructor(
    private pessoaService: PessoasService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    )
  {}

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  ngOnInit(): void
  {
    this.onList();
  }

  onAdd() {
    console.log("onAdd");
    this.router.navigate(['new'], {relativeTo: this.route})
  }

  onUpdate(id: number)
  {
    this.router.navigate(['edit', id], {relativeTo: this.route})

  }

  onRemove(id: number)
  {
      console.log(id)
      this.pessoaService.remove(id)
        .subscribe({
          next: () => { this.snackBar.open("usuário removido", "", {duration: 3000}),  this.onList()},
          error: () => {this.snackBar.open("erro ao remover usuário", "", {duration: 3000})}
        })
  }

  onList()
  {
    this.pessoas$ = this.pessoaService.list() //criar fun
    .pipe(
      catchError(error => {
        this.onError('erro ao carregar cursos.');
        return of([]) //serve para retornar algo e sair do loading spinner caso de algum erro
      })
    )
    //console.log(this.pessoas$)
  }
}
