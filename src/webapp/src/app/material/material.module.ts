import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatButtonModule} from '@angular/material/button';

@NgModule({
  imports: [CommonModule, MatToolbarModule, MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule,
    MatButtonModule],
  exports: [CommonModule, MatToolbarModule, MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule,
    MatButtonModule],
})
export class MaterialModule {
}
