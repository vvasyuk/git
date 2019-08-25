import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class ImageService {

  constructor(private httpClient: HttpClient) { }

  getImage(imageUrl: string): Observable<Blob> {
    return this.httpClient.get(imageUrl, { responseType: 'blob' });
  }

  getText() {
    return this.httpClient.get('https://mockbin.org/bin/8023d488-a89a-45db-aba6-502414a9c523', {responseType: 'text'});
  }

}
