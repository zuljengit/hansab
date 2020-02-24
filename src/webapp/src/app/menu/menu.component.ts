import {Component} from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {

  navigateToUsersPage(): void {
    window.location.href = '/users';
  }

  navigateToCarsPage(): void {
    window.location.href = '/cars';
  }
}
