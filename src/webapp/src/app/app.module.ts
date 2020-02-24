import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {MenuModule} from './menu/menu.module';
import {UsersModule} from './users/users.module';
import {CarsModule} from './cars/cars.module';
import {UsersService} from './users/users.service';
import {AppRoutingModule} from './app-routing.module';
import {CarsService} from './cars/cars.service';
import {BrowserModule} from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialModule} from './material/material.module';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    MenuModule,
    UsersModule,
    CarsModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [UsersService, CarsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
