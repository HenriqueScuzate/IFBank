import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ContaService } from '../../services/conta.service';
import { ClienteService } from '../../services/cliente.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { Cliente, Conta } from '../../models/models';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink, NavbarComponent],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit {
  cliente: Cliente | null = null;
  conta: Conta | null = null;

  constructor(
    private auth: AuthService,
    private contaService: ContaService,
    private clienteService: ClienteService
  ) {}

  ngOnInit() {
    const c = this.auth.getCliente();
    if (!c) return;
    this.clienteService.buscarPorId(c.id).subscribe({
      next: (cliente) => { this.cliente = cliente; },
      error: () => { this.cliente = c; }
    });
    this.contaService.buscarPorCliente(c.id).subscribe({
      next: (conta) => { this.conta = conta; },
      error: () => {},
    });
  }

  get fotoUrl(): string | null {
    if (this.cliente?.fotoUrl) {
      return `http://localhost:8080/clientes/fotos/${this.cliente.fotoUrl.replace('/fotos/', '')}`;
    }
    return null;
  }

  get inicial(): string {
    return this.cliente?.nome?.charAt(0).toUpperCase() || '?';
  }
}
