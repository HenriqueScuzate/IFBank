import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../navbar/navbar.component';
import { ClienteService } from '../../services/cliente.service';
import { Cliente } from '../../models/models';

@Component({
  selector: 'app-gerente',
  standalone: true,
  imports: [CommonModule, NavbarComponent],
  templateUrl: './gerente.component.html',
})
export class GerenteComponent implements OnInit {
  pendentes: Cliente[] = [];
  aprovados: Cliente[] = [];
  aba: 'pendentes' | 'aprovados' = 'pendentes';
  mensagem = '';
  erro = '';

  constructor(private clienteService: ClienteService) {}

  ngOnInit() {
    this.carregarPendentes();
    this.carregarAprovados();
  }

  carregarPendentes() {
    this.clienteService.listarPendentes().subscribe({
      next: (res: any) => { this.pendentes = res.content ?? res; },
      error: () => { this.erro = 'Erro ao carregar pendentes.'; },
    });
  }

  carregarAprovados() {
    this.clienteService.listarAprovados().subscribe({
      next: (res: any) => {
        // Filtra o próprio gerente da lista
        this.aprovados = (res.content ?? res).filter((c: Cliente) => c.perfil === 'CLIENTE');
      },
      error: () => {},
    });
  }

  aprovar(id: number) {
    this.mensagem = '';
    this.erro = '';
    this.clienteService.aprovar(id).subscribe({
      next: () => {
        this.mensagem = 'Cliente aprovado com sucesso!';
        this.carregarPendentes();
        this.carregarAprovados();
      },
      error: (err: any) => { this.erro = err.error?.erro || 'Erro ao aprovar.'; },
    });
  }

  rejeitar(id: number) {
    if (!confirm('Confirmar rejeição?')) return;
    this.mensagem = '';
    this.erro = '';
    this.clienteService.rejeitar(id).subscribe({
      next: () => {
        this.mensagem = 'Cliente rejeitado.';
        this.carregarPendentes();
      },
      error: (err: any) => { this.erro = err.error?.erro || 'Erro ao rejeitar.'; },
    });
  }

  fotoUrl(cliente: Cliente): string | null {
    if (cliente.fotoUrl) {
      return `http://localhost:8080/clientes/fotos/${cliente.fotoUrl.replace('/fotos/', '')}`;
    }
    return null;
  }

  inicial(nome: string): string {
    return nome?.charAt(0).toUpperCase() || '?';
  }
}
