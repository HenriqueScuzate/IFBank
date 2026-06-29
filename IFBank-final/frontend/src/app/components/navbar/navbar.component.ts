import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ClienteService } from '../../services/cliente.service';
import { CommonModule } from '@angular/common';
import { Cliente } from '../../models/models';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './navbar.component.html',
})
export class NavbarComponent implements OnInit {
  clienteAtualizado: Cliente | null = null;

  constructor(
    public auth: AuthService,
    private router: Router,
    private clienteService: ClienteService
  ) {}

  ngOnInit() {
    const c = this.auth.getCliente();
    if (c) {
      this.clienteService.buscarPorId(c.id).subscribe({
        next: (cliente) => { this.clienteAtualizado = cliente; },
        error: () => { this.clienteAtualizado = c; }
      });
    }
  }

  get cliente() {
    return this.clienteAtualizado || this.auth.getCliente();
  }

  get fotoUrl(): string | null {
    if (this.cliente?.fotoUrl) {
      return this.clienteService.urlFoto(this.cliente.fotoUrl);
    }
    return null;
  }

  get inicial(): string {
    return this.cliente?.nome?.charAt(0).toUpperCase() || '?';
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
