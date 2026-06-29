import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthService } from '../../services/auth.service';
import { ContaService } from '../../services/conta.service';
import { ClienteService } from '../../services/cliente.service';
import { Conta, Cliente } from '../../models/models';

@Component({
  selector: 'app-conta',
  standalone: true,
  imports: [CommonModule, FormsModule, NavbarComponent],
  templateUrl: './conta.component.html',
})
export class ContaComponent implements OnInit {
  cliente: Cliente | null = null;
  conta: Conta | null = null;

  // Edição
  editando = false;
  nome = '';
  telefone = '';
  endereco = '';
  novaSenha = '';

  // Depósito
  valorDeposito: number = 0;

  // Foto
  fotoSelecionada: File | null = null;

  mensagem = '';
  erro = '';

  constructor(
    private auth: AuthService,
    private contaService: ContaService,
    private clienteService: ClienteService
  ) {}

  ngOnInit() {
    const c = this.auth.getCliente();
    if (!c) return;
    this.carregarCliente(c.id);
    this.contaService.buscarPorCliente(c.id).subscribe({
      next: (conta) => { this.conta = conta; },
      error: () => {},
    });
  }

  carregarCliente(id: number) {
    this.clienteService.buscarPorId(id).subscribe({
      next: (cliente) => {
        this.cliente = cliente;
        this.nome = cliente.nome;
        this.telefone = cliente.telefone || '';
        this.endereco = cliente.endereco || '';
        this.auth.salvarCliente(cliente);
      },
      error: () => {},
    });
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

  salvarEdicao() {
    if (!this.cliente) return;
    this.mensagem = '';
    this.erro = '';
    const dados: any = { nome: this.nome, telefone: this.telefone, endereco: this.endereco };
    if (this.novaSenha) dados.senha = this.novaSenha;

    this.clienteService.atualizar(this.cliente.id, dados).subscribe({
      next: (c) => {
        this.auth.salvarCliente(c);
        this.cliente = c;
        this.editando = false;
        this.novaSenha = '';
        this.mensagem = 'Dados atualizados com sucesso!';
      },
      error: () => { this.erro = 'Erro ao atualizar dados.'; },
    });
  }

  selecionarFoto(event: any) {
    this.fotoSelecionada = event.target.files[0];
  }

  enviarFoto() {
    if (!this.cliente || !this.fotoSelecionada) return;
    this.mensagem = '';
    this.erro = '';
    this.clienteService.uploadFoto(this.cliente.id, this.fotoSelecionada).subscribe({
      next: (c) => {
        this.auth.salvarCliente(c);
        this.cliente = c;
        this.fotoSelecionada = null;
        this.mensagem = 'Foto atualizada com sucesso!';
      },
      error: () => { this.erro = 'Erro ao enviar foto.'; },
    });
  }

  depositar() {
    if (!this.conta || !this.valorDeposito || this.valorDeposito <= 0) {
      this.erro = 'Informe um valor válido para depósito.';
      return;
    }
    this.mensagem = '';
    this.erro = '';
    this.contaService.depositar(this.conta.id, this.valorDeposito).subscribe({
      next: (c) => {
        this.conta = c;
        this.valorDeposito = 0;
        this.mensagem = `Depósito de R$ ${c.saldo.toFixed(2).replace('.', ',')} realizado! Novo saldo: R$ ${c.saldo.toFixed(2).replace('.', ',')}`;
      },
      error: (err) => { this.erro = err.error?.erro || 'Erro ao realizar depósito.'; },
    });
  }
}
