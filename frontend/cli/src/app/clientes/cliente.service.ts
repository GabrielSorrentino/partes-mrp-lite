import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DataPackage } from '../data-package';
import { Cliente } from './cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private clientesUrl = 'rest/clientes';

  constructor(
    private http: HttpClient
  ) { }

  all(): Observable<DataPackage> {
    return this.http.get<DataPackage>(this.clientesUrl);
  }

  get(cuit: number): Observable<DataPackage> {
    return this.http.get<DataPackage>(`${this.clientesUrl}/cuit/${cuit}`);
  }

  save(cliente: Cliente): Observable<DataPackage> {
    return cliente.id
      ? this.http.put<DataPackage>(this.clientesUrl, cliente)
      : this.http.post<DataPackage>(this.clientesUrl, cliente);
  }

  remove(id: number): Observable<DataPackage>{
    return this.http.delete<DataPackage>(`${this.clientesUrl}/${id}`);
  }

  byPage(page: number, size: number): Observable<DataPackage>{
    return this.http.get<DataPackage>(`${this.clientesUrl}/page?page=${page - 1}&size=${size}`);
  }

  search(searchTerm: string): Observable<DataPackage>{
    return this.http.get<DataPackage>(`${this.clientesUrl}/search/${searchTerm}`);
  }
}
