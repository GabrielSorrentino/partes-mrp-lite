import { Component } from '@angular/core';
import { PaginationComponent } from '../pagination/pagination.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ClienteService } from './cliente.service';
import { ModalService } from '../modal/modal.service';
import { ResultsPage } from '../results-page';

@Component({
  selector: 'app-clientes',
  imports: [CommonModule, RouterModule, PaginationComponent],
  template: `
    <h2>Clientes</h2>
    <div class="table-responsive">
      <table class="table table-striped table-sm">
        <thead>
          <tr>
            <th>#</th>
            <th>CUIT</th>
            <th>Razón Social</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let cliente of resultsPage.content; index as i">
            <td>{{ i+1 }}</td>
            <td>{{ cliente.cuit }}</td>
            <td>{{ cliente.razonSocial }}</td>
            <td>
              <a routerLink="/clientes/{{ cliente.cuit }}"><i class="fa fa-pencil"></i></a>
              <a (click)="remove(cliente.id)" [routerLink]=""><i class="fa fa-remove"></i></a>
            </td>
          </tr>
        </tbody>
        <tfoot>
          <app-pagination
            [totalPages]="resultsPage.totalPages"
            [currentPage]="currentPage"
            (pageChangeRequested)="onPageChangeRequested($event)"
            [number]="resultsPage.number"
            [hidden]="resultsPage.numberOfElements < 1"
          ></app-pagination>
        </tfoot>
      </table>
    </div>
  `,
  styles: ``
})
export class ClientesComponent {
  resultsPage: ResultsPage = <ResultsPage>{};
  currentPage: number = 1;

  constructor(
    private clienteService: ClienteService,
    private modalService: ModalService
  ){}

  remove(id: number): void {
    let that = this;
    this.modalService
      .confirm("Eliminar cliente", "Está seguro de que desea eliminar el cliente?", "Si elimina el cliente, no lo podrá volver a utilizar.")
      .then(
        function () {
          that.clienteService.remove(id).subscribe({
            next: (dataPackage) => {
              if (dataPackage.status === 409) {
                that.modalService.alert(
                  "Error al eliminar",
                  dataPackage.message,
                  ""
                );
              }
              that.getClientes();
            }
          });
        }
      );
  }

  onPageChangeRequested(page: number): void {
    this.currentPage = page;
    this.getClientes();
  }

  getClientes(): void {
    this.clienteService.byPage(this.currentPage, 10)
      .subscribe(dataPackage => this.resultsPage = <ResultsPage>dataPackage.data);
  }

  ngOnInit() {
    this.getClientes();
  }
}
