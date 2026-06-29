import { Routes } from '@angular/router';
import { authGuard, gerenteGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path: 'login',
    loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent),
  },
  {
    path: 'cadastro',
    loadComponent: () => import('./components/cadastro/cadastro.component').then(m => m.CadastroComponent),
  },
  {
    path: 'esqueci-senha',
    loadComponent: () => import('./components/esqueci-senha/esqueci-senha.component').then(m => m.EsqueciSenhaComponent),
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./components/dashboard/dashboard.component').then(m => m.DashboardComponent),
    canActivate: [authGuard],
  },
  {
    path: 'conta',
    loadComponent: () => import('./components/conta/conta.component').then(m => m.ContaComponent),
    canActivate: [authGuard],
  },
  {
    path: 'extrato',
    loadComponent: () => import('./components/extrato/extrato.component').then(m => m.ExtratoComponent),
    canActivate: [authGuard],
  },
  {
    path: 'transferencia',
    loadComponent: () => import('./components/transferencia/transferencia.component').then(m => m.TransferenciaComponent),
    canActivate: [authGuard],
  },
  {
    path: 'investimentos',
    loadComponent: () => import('./components/investimentos/investimentos.component').then(m => m.InvestimentosComponent),
    canActivate: [authGuard],
  },
  {
    path: 'gerente',
    loadComponent: () => import('./components/gerente/gerente.component').then(m => m.GerenteComponent),
    canActivate: [authGuard, gerenteGuard],
  },
  { path: '**', redirectTo: 'login' },
];
