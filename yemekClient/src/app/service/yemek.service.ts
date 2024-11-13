import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Yemek } from '../beans/yemek';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class YemekService {

  constructor(
    private http: HttpClient
  ) { }
  getirTumYemekler() {
    const token = localStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
    return this.http.get<Yemek[]>('http://localhost:8080/yemek', { headers });
  }
  ekleGuncelle(yemek: Yemek) {
    const token = localStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
    return this.http.post<Yemek>('http://localhost:8080/yemek', yemek, { headers });
  }
  sil(id: number) {
    const token = localStorage.getItem('token');
    const headers = { Authorization: `Bearer ${token}` };
    return this.http.delete('http://localhost:8080/yemek', { headers, body: { id } });
  }
}