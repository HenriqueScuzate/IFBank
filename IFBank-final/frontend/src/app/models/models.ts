export interface Cliente {
  id: number;
  nome: string;
  email: string;
  telefone: string;
  endereco: string;
  perfil: string;
  aprovado: boolean;
  fotoUrl?: string;
}

export interface Conta {
  id: number;
  numeroConta: string;
  saldo: number;
  status: string;
  cliente: Cliente;
}

export interface Movimentacao {
  id: number;
  tipo: string;
  valor: number;
  data: string;
}

export interface Transferencia {
  id: number;
  valor: number;
  data: string;
  contaOrigem: Conta;
  contaDestino: Conta;
}

export interface Investimento {
  id: number;
  tipo: string;
  valor: number;
  rendimento: number;
  data: string;
}
