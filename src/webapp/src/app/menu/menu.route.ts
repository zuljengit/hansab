import { Route } from '@angular/router';

import { MenuComponent } from './menu.component';

export const MENU_ROUTE: Route = {
  path: '',
  component: MenuComponent,
  data: {
    authorities: [],
    pageTitle: 'Menu page'
  }
};
