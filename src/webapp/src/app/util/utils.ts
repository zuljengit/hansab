import {HttpParams} from '@angular/common/http';

export class SearchAndSortParams {
  constructor(
    public page: number,
    public size: number,
    public sort: string,
    public find: string) {
  }
}

export const createHttpParams = (params: SearchAndSortParams): HttpParams => {
  let httpParams: HttpParams = new HttpParams();
  Object.keys(params).forEach(function (key: string) {
      if (key === 'find') {
        if (params[key]) {
          httpParams = httpParams.append(key, params[key]);
        }
      } else {
        httpParams = httpParams.append(key, params[key]);
      }
    });
  return httpParams;
};
