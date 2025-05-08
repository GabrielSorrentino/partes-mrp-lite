import { Component } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import { ModalService } from '../modal/modal.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule, Location, UpperCasePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-clientes-detail',
  imports: [UpperCasePipe, FormsModule, CommonModule],
  template: `
    <div *ngIf="cliente">
      <h2>{{ cliente.razonSocial| uppercase }}</h2>
      <form #form="ngForm">
        <div class="form-group">
          <button (click)="goBack()" class="btn btn-danger">Atrás</button>
          <button (click)="save()" class="btn btn-success" [disabled]="form.invalid">Guardar</button>
          <label for="razonSocial">Razón Social/Nombre:</label>
          <input
            name="razonSocial"
            placeholder="Nombre/razón social"
            class="form-control"
            [(ngModel)]="cliente.razonSocial"
            required #razonSocial="ngModel"
          >
          <div *ngIf="razonSocial.invalid && (razonSocial.dirty || razonSocial.touched)" class="alert">
            <div *ngIf="razonSocial.errors?.['required']">
              La razón social del cliente es requerida.
            </div>
          </div>
        </div>
        <div class="form-group">
          <label for="cuit">CUIT:</label>
          <input name="cuit" placeholder="CUIT" class="form-control" [(ngModel)]="cliente.cuit">
        </div>
      </form>
    </div>
  `,
  styles: ``
})
export class ClientesDetailComponent {
  cliente!: Cliente;

  constructor(
    private route: ActivatedRoute,
    private clienteService: ClienteService,
    private modalService: ModalService,
    private location: Location
  ) { }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.clienteService.save(this.cliente).subscribe((dataPackage) => {
      if (dataPackage.status !== 200) {
        this.modalService.alert(
          "Error al guardar",
          dataPackage.message,
          "Error al guardar o crear el cliente."
        );
      } else {
        this.cliente = <Cliente>dataPackage.data;
        this.goBack();
      }
    });
  }

  get(): void {
    const cuit = this.route.snapshot.paramMap.get('cuit')!; //Un string, 'new' o el CUIT del cliente
    if(cuit === 'new'){
      this.cliente = <Cliente>{}; //Si el string es new, lo asignamos como un string
    } else{
      //Sino, el string es un CUIT real (un número), y el método ClienteService.get(number) debe tratarlo como tal (+cuit)
      this.clienteService.get(+cuit).subscribe(dataPackage => this.cliente = <Cliente>dataPackage.data);
    }
  }

  ngOnInit() {
    this.get();
  }
}
