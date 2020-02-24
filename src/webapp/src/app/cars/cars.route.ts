import { Route } from '@angular/router';

import { CarsComponent } from './cars.component';

export const CARS_ROUTE: Route = {
  path: 'cars',
  component: CarsComponent,
  data: {
    authorities: [],
    pageTitle: 'Cars'
  }
};
