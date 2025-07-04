import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal',
  imports: [CommonModule],
  template: `
    <div class="modal-header">
      <h4 class="modal-title" id="modal-title">{{title}}</h4>
      <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss()">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <p>
        <strong>{{message}}</strong>
      </p>
      <p *ngIf="description">
        <strong>{{description}}</strong>
      </p>
    </div>
    <div class="modal-footer">
      <button *ngIf="!isAlert" type="button" class="btn btn-outline-secondary" (click)="modal.dismiss()">Cancelar</button>
      <button *ngIf="!isAlert" type="button" class="btn btn-success" (click)="modal.close()">Aceptar</button>
      <button *ngIf="isAlert" type="button" class="btn btn-danger" (click)="modal.close()">Cerrar</button>
    </div>
  `,
  styles: ``
})
export class ModalComponent {
  constructor(
    public modal: NgbActiveModal
  ){

  }
  title = "";
  message = "";
  description = "";
  isAlert = false; // si es alerta de excepción/error, o mensaje de confirmación
}
