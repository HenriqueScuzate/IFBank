import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthService } from '../../services/auth.service';
import { ContaService } from '../../services/conta.service';
import { TransferenciaService } from '../../services/transferencia.service';

@Component({
  selector: 'app-transferencia',
  standalone: true,
  imports: [CommonModule, FormsModule, NavbarComponent],
  templateUrl: './transferencia.component.html',
})
export class TransferenciaComponent implements OnInit {
  contaOrigemId: number = 0;
  numeroContaDestino: string = '';
  valor: number = 0;
  mensagem = '';
  erro = '';

  constructor(
    private auth: AuthService,
    private contaService: ContaService,
    private transferenciaService: TransferenciaService,
  ) {}

  ngOnInit() {
    const cliente = this.auth.getCliente();
    if (cliente) {
      this.contaService.buscarPorCliente(cliente.id).subscribe({
        next: (conta) => {
          this.contaOrigemId = conta.id;
        },
        error: () => {},
      });
    }
  }

  transferir() {
    this.mensagem = '';
    this.erro = '';
    if (!this.numeroContaDestino || !this.valor) {
      this.erro = 'Preencha todos os campos.';
      return;
    }
    this.transferenciaService
      .realizar(this.contaOrigemId, this.numeroContaDestino, this.valor)
      .subscribe({
        next: () => {
          this.mensagem = 'Transferência realizada com sucesso!';
          this.valor = 0;
          this.numeroContaDestino = '';
        },
        error: (err) => {
          this.erro = err.error?.erro || 'Erro ao realizar transferência.';
        },
      });
  }
}
