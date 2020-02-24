import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {CarsService} from './cars.service';
import {Car} from './cars.model';
import {MatTableDataSource} from '@angular/material/table';
import {FormControl} from '@angular/forms';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';
import {SearchAndSortParams} from '../util/utils';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.scss']
})
export class CarsComponent implements AfterViewInit {

  displayedColumns: string[] = ['id', 'make', 'model', 'numberplate', 'info'];
  dataSource = new MatTableDataSource<Car>();
  totalElements: number;
  search: FormControl = new FormControl();
  car: Car;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) matSort: MatSort;

  constructor(private carsService: CarsService) {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.matSort;
  }

  ngAfterViewInit(): void {
    this.search.valueChanges
      .pipe(debounceTime(500), distinctUntilChanged())
      .subscribe((val: string) => {
        this.paginator.pageIndex = 0;
        this.searchData(val);
      });
    this.matSort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
    this.populateCars();
  }

  searchData(value: string): void {
    this.dataSource.filter = value.trim();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
    this.populateCars();
  }

  populateCars(): void {
    this.carsService.getCars(this.getParams())
      .subscribe((response: HttpResponse<Car[]>) => {
        this.totalElements = Number(response.headers.get('Total-Elements'));
        this.dataSource.data = response.body;
      });
  }

  getParams(): SearchAndSortParams {
    return new SearchAndSortParams(
      this.paginator.pageIndex,
      this.paginator.pageSize,
      this.matSort.active + ',' + this.matSort.direction,
      this.dataSource.filter
    );
  }

  getCarInfo(id: number): void {
    this.carsService.getCar(id)
      .subscribe((response) => {
        this.car = response.body;
      });
  }
}
