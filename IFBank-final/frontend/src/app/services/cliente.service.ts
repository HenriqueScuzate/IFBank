import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../models/models';

const API = 'http://localhost:8080';

@Injectable({ providedIn: 'root' })
export class ClienteService {
  constructor(private http: HttpClient) {}

  listarPendentes(): Observable<any> {
    return this.http.get(`${API}/gerente/clientes-pendentes`);
  }

  listarAprovados(): Observable<any> {
    return this.http.get(`${API}/gerente/clientes-aprovados`);
  }

  aprovar(id: number): Observable<Cliente> {
    return this.http.put<Cliente>(`${API}/gerente/aprovar/${id}`, {});
  }

  rejeitar(id: number): Observable<string> {
    return this.http.delete(`${API}/gerente/rejeitar/${id}`, { responseType: 'text' });
  }

  buscarPorId(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${API}/clientes/${id}`);
  }

  atualizar(id: number, dados: any): Observable<Cliente> {
    return this.http.put<Cliente>(`${API}/clientes/${id}`, dados);
  }

  uploadFoto(clienteId: number, arquivo: File): Observable<Cliente> {
    const form = new FormData();
    form.append('arquivo', arquivo);
    return this.http.post<Cliente>(`${API}/clientes/${clienteId}/foto`, form);
  }

  urlFoto(fotoUrl: string): string {
    return `http://localhost:8080/clientes/fotos/${fotoUrl.replace('/fotos/', '')}`;
  }
}
