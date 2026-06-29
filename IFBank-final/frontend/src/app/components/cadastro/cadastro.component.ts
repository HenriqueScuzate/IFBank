import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './cadastro.component.html',
})
export class CadastroComponent {
  nome = '';
  email = '';
  telefone = '';
  endereco = '';
  senha = '';
  mensagem = '';
  erro = '';

  constructor(private auth: AuthService, private router: Router) {}

  cadastrar() {
    this.erro = '';
    this.mensagem = '';
    this.auth.cadastro({ nome: this.nome, email: this.email, telefone: this.telefone, endereco: this.endereco, senha: this.senha }).subscribe({
      next: () => {
        this.mensagem = 'Cadastro realizado! Aguarde a aprovação do gerente.';
      },
      error: (err) => {
        this.erro = err.error?.erro || 'Erro ao cadastrar.';
      },
    });
  }
}
