import {Injectable} from '@angular/core';
import {SERVER_API_URL} from '../app.constants';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Car} from './cars.model';
import {createHttpParams, SearchAndSortParams} from '../util/utils';

@Injectable()
export class CarsService {

  private resourceUrl = SERVER_API_URL;

  constructor(private httpClient: HttpClient) {
  }

  getCars(params: SearchAndSortParams): Observable<HttpResponse<Car[]>> {
    const httpParams = createHttpParams(params);
    return this.httpClient.get<Car[]>(`${this.resourceUrl}/cars`,
      {params: httpParams, observe: 'response'});
  }

  getCar(id: number): Observable<HttpResponse<Car>> {
    return this.httpClient.get<Car>(`${this.resourceUrl}/cars/${id}`,
      {observe: 'response'});
  }
}
