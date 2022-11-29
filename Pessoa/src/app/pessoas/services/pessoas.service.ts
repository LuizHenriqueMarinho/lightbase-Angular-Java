import { Injectable } from '@angular/core';
import { Pessoa } from '../model/pessoa';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { first } from 'rxjs/operators';
import { delay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PessoasService {

  private readonly API = 'http://localhost:8080/pessoas'; //http://localhost8080/pessoas

  constructor(private httpClient: HttpClient) { }

  list()
  {
    return this.httpClient.get<Pessoa[]>(this.API) //isso é um tipo Observable, versão melhorada do primisses
    .pipe(   //permite manipular com rxjs
      first(),
      delay(500),
      tap(pessoas => console.log(pessoas)) //recebe lista => faz o que quiser com ela
    );
  }

  save(record: Partial<Pessoa>) //aceita que o objeto esteja faltando atributos
  {
    return this.httpClient.post<Pessoa>(`${this.API}/save`, record)  //o post está enviando Pessoa para o backend, deve conster o end pont, no caso "/save"
  }

  update(id: number, record: Partial<Pessoa>)
  {
    return this.httpClient.put<Pessoa>(`${this.API}/${id}`, record)
  }

  remove(id: number)
  {
    return this.httpClient.delete<Pessoa>(`${this.API}/${id}`)
  }

  getById(id: number)
  {
    return this.httpClient.get<Pessoa>(`${this.API}/${id}`)
  }

}
