import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Conta, Movimentacao } from '../models/models';

const API = 'http://localhost:8080';

@Injectable({ providedIn: 'root' })
export class ContaService {
  constructor(private http: HttpClient) {}

  buscarPorCliente(clienteId: number): Observable<Conta> {
    return this.http.get<Conta>(`${API}/conta/cliente/${clienteId}`);
  }

  buscarPorId(id: number): Observable<Conta> {
    return this.http.get<Conta>(`${API}/conta/${id}`);
  }

  saldo(contaId: number): Observable<number> {
    return this.http.get<number>(`${API}/conta/${contaId}/saldo`);
  }

  depositar(contaId: number, valor: number): Observable<Conta> {
    return this.http.post<Conta>(`${API}/conta/${contaId}/depositar`, { valor });
  }

  extrato(contaId: number): Observable<Movimentacao[]> {
    return this.http.get<Movimentacao[]>(`${API}/conta/${contaId}/extrato`);
  }
}
