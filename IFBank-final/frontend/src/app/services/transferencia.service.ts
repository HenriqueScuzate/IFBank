import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transferencia } from '../models/models';

const API = 'http://localhost:8080';

@Injectable({ providedIn: 'root' })
export class TransferenciaService {
  constructor(private http: HttpClient) {}

  realizar(
    contaOrigemId: number,
    numeroContaDestino: string,
    valor: number,
  ): Observable<Transferencia> {
    return this.http.post<Transferencia>(`${API}/transferencias`, {
      contaOrigemId,
      numeroContaDestino,
      valor,
    });
  }
}
