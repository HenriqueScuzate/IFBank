import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthService } from '../../services/auth.service';
import { ContaService } from '../../services/conta.service';
import { Movimentacao } from '../../models/models';

@Component({
  selector: 'app-extrato',
  standalone: true,
  imports: [CommonModule, NavbarComponent],
  templateUrl: './extrato.component.html',
})
export class ExtratoComponent implements OnInit {
  movimentacoes: Movimentacao[] = [];
  erro = '';

  constructor(private auth: AuthService, private contaService: ContaService) {}

  ngOnInit() {
    const cliente = this.auth.getCliente();
    if (!cliente) return;
    this.contaService.buscarPorCliente(cliente.id).subscribe({
      next: (conta) => {
        this.contaService.extrato(conta.id).subscribe({
          next: (movs) => { this.movimentacoes = movs; },
          error: () => { this.erro = 'Erro ao carregar extrato.'; },
        });
      },
      error: () => { this.erro = 'Conta não encontrada.'; },
    });
  }
}
