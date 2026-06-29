import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../models/models';

const API = 'http://localhost:8080';

@Injectable({ providedIn: 'root' })
export class AuthService {
  constructor(private http: HttpClient) {}

  login(email: string, senha: string): Observable<Cliente> {
    return this.http.post<Cliente>(`${API}/auth/login`, { email, senha });
  }

  cadastro(dados: any): Observable<Cliente> {
    return this.http.post<Cliente>(`${API}/auth/cadastro`, dados);
  }

  esqueciSenha(email: string): Observable<string> {
    return this.http.post(`${API}/auth/esqueci-senha`, { email }, { responseType: 'text' });
  }

  logout(): void {
    localStorage.removeItem('cliente');
  }

  salvarCliente(cliente: Cliente): void {
    localStorage.setItem('cliente', JSON.stringify(cliente));
  }

  getCliente(): Cliente | null {
    const dados = localStorage.getItem('cliente');
    return dados ? JSON.parse(dados) : null;
  }

  isLogado(): boolean {
    return !!this.getCliente();
  }

  isGerente(): boolean {
    const c = this.getCliente();
    return !!c && c.perfil === 'GERENTE';
  }
}
