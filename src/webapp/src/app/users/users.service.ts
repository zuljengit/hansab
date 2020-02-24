import {Injectable} from '@angular/core';
import {SERVER_API_URL} from '../app.constants';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {createHttpParams, SearchAndSortParams} from '../util/utils';
import {User} from './users.model';
import {Car} from '../cars/cars.model';

@Injectable()
export class UsersService {

  private resourceUrl = SERVER_API_URL;

  constructor(private httpClient: HttpClient) {
  }

  getUsers(params: SearchAndSortParams): Observable<HttpResponse<User[]>> {
    const httpParams = createHttpParams(params);
    return this.httpClient.get<User[]>(`${this.resourceUrl}/users`,
      {params: httpParams, observe: 'response'});
  }

  getUser(id: number): Observable<HttpResponse<User>> {
    return this.httpClient.get<User>(`${this.resourceUrl}/users/${id}`,
      {observe: 'response'});
  }

  getUserCars(id: number): Observable<HttpResponse<Car[]>> {
    return this.httpClient.get<Car[]>(`${this.resourceUrl}/users/${id}/cars`,
      {observe: 'response'});
  }
}
