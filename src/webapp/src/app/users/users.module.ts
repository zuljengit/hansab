import {NgModule} from '@angular/core';
import {UsersComponent} from './users.component';
import {RouterModule} from '@angular/router';
import {USERS_ROUTE} from './users.route';
import {MaterialModule} from '../material/material.module';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [UsersComponent],
  imports: [
    RouterModule.forChild([USERS_ROUTE]),
    MaterialModule,
    ReactiveFormsModule
  ]
})
export class UsersModule {
}
