import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-esqueci-senha',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './esqueci-senha.component.html',
})
export class EsqueciSenhaComponent {
  email = '';
  mensagem = '';
  erro = '';

  constructor(private auth: AuthService) {}

  recuperar() {
    this.erro = '';
    this.mensagem = '';
    this.auth.esqueciSenha(this.email).subscribe({
      next: (msg) => { this.mensagem = msg; },
      error: (err) => { this.erro = err.error?.erro || 'Erro ao recuperar senha.'; },
    });
  }
}
