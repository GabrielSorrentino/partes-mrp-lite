import { Injectable } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from './modal.component';

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  constructor(
    private modalService: NgbModal
  ) { }

  // Confirmación para crear, editar o eliminar una entidad
  confirm(title: string, message: string, description: string): Promise<any>{
    const modal = this.modalService.open(ModalComponent);
    modal.componentInstance.title = title;
    modal.componentInstance.message = message;
    modal.componentInstance.description = description;
    return modal.result;
  }

  // Alerta de error o excepción
  alert(title: string, message: string, description: string): void {
    const modal = this.modalService.open(ModalComponent);
    modal.componentInstance.title = title;
    modal.componentInstance.message = message;
    modal.componentInstance.description = description;
    modal.componentInstance.isAlert = true;
  }
}
