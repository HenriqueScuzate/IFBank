import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  email = '';
  senha = '';
  erro = '';

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    this.erro = '';
    this.auth.login(this.email, this.senha).subscribe({
      next: (cliente) => {
        this.auth.salvarCliente(cliente);
        if (cliente.perfil === 'GERENTE') {
          this.router.navigate(['/gerente']);
        } else {
          this.router.navigate(['/dashboard']);
        }
      },
      error: (err) => {
        this.erro = err.error?.erro || 'Erro ao fazer login.';
      },
    });
  }
}
