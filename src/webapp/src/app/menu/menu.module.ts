import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from '@angular/router';
import {MENU_ROUTE} from './menu.route';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from '../app-routing.module';
import {MenuComponent} from './menu.component';

@NgModule({
  declarations: [MenuComponent],
  imports: [
    RouterModule.forChild([MENU_ROUTE]),
    CommonModule,
    BrowserModule,
    AppRoutingModule
  ]
})
export class MenuModule { }
