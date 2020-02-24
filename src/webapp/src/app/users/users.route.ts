import { Route } from '@angular/router';

import { UsersComponent } from './users.component';

export const USERS_ROUTE: Route = {
  path: 'users',
  component: UsersComponent,
  data: {
    authorities: [],
    pageTitle: 'Users'
  }
};
