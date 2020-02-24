import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router';
import {CarsComponent} from './cars.component';
import {CARS_ROUTE} from './cars.route';
import {MaterialModule} from '../material/material.module';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [CarsComponent],
  imports: [RouterModule.forChild([CARS_ROUTE]),
    MaterialModule,
    ReactiveFormsModule
  ]
})
export class CarsModule { }
