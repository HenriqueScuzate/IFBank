import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthService } from '../../services/auth.service';
import { ContaService } from '../../services/conta.service';
import { InvestimentoService } from '../../services/investimento.service';
import { Investimento } from '../../models/models';

@Component({
  selector: 'app-investimentos',
  standalone: true,
  imports: [CommonModule, FormsModule, NavbarComponent],
  templateUrl: './investimentos.component.html',
})
export class InvestimentosComponent implements OnInit {
  contaId: number = 0;
  tipo = 'CDB';
  valor: number = 0;
  investimentos: Investimento[] = [];
  mensagem = '';
  erro = '';

  constructor(
    private auth: AuthService,
    private contaService: ContaService,
    private investimentoService: InvestimentoService
  ) {}

  ngOnInit() {
    const cliente = this.auth.getCliente();
    if (cliente) {
      this.contaService.buscarPorCliente(cliente.id).subscribe({
        next: (conta) => {
          this.contaId = conta.id;
          this.carregarInvestimentos();
        },
        error: () => {},
      });
    }
  }

  carregarInvestimentos() {
    this.investimentoService.listar(this.contaId).subscribe({
      next: (inv) => { this.investimentos = inv; },
      error: () => {},
    });
  }

  aplicar() {
    this.mensagem = '';
    this.erro = '';
    if (!this.valor || this.valor <= 0) {
      this.erro = 'Informe um valor válido.';
      return;
    }
    this.investimentoService.aplicar(this.contaId, this.tipo, this.valor).subscribe({
      next: () => {
        this.mensagem = 'Investimento aplicado com sucesso!';
        this.valor = 0;
        this.carregarInvestimentos();
      },
      error: (err) => {
        this.erro = err.error?.erro || 'Erro ao aplicar investimento.';
      },
    });
  }

  resgatar(id: number) {
    this.mensagem = '';
    this.erro = '';
    this.investimentoService.resgatar(id).subscribe({
      next: () => {
        this.mensagem = 'Resgate realizado com sucesso!';
        this.carregarInvestimentos();
      },
      error: (err) => {
        this.erro = err.error?.erro || 'Erro ao resgatar.';
      },
    });
  }
}
