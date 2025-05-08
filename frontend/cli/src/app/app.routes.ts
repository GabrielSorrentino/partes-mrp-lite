import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ClientesComponent } from './clientes/clientes.component';
import { ClientesDetailComponent } from './clientes/clientes-detail.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'clientes', component: ClientesComponent },
    { path: 'clientes/:cuit', component: ClientesDetailComponent }
];
