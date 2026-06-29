import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Investimento } from '../models/models';

const API = 'http://localhost:8080';

@Injectable({ providedIn: 'root' })
export class InvestimentoService {
  constructor(private http: HttpClient) {}

  aplicar(contaId: number, tipo: string, valor: number): Observable<Investimento> {
    return this.http.post<Investimento>(`${API}/investimentos`, { contaId, tipo, valor });
  }

  resgatar(investimentoId: number): Observable<Investimento> {
    return this.http.delete<Investimento>(`${API}/investimentos/resgatar/${investimentoId}`);
  }

  listar(contaId: number): Observable<Investimento[]> {
    return this.http.get<Investimento[]>(`${API}/investimentos/conta/${contaId}`);
  }
}
