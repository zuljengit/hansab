import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {UsersService} from './users.service';
import {HttpResponse} from '@angular/common/http';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {SearchAndSortParams} from '../util/utils';
import {User} from './users.model';
import {FormControl} from '@angular/forms';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';
import {forkJoin} from 'rxjs';
import {Car} from '../cars/cars.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements AfterViewInit {

  displayedColumns: string[] = ['id', 'name', 'info'];
  dataSource = new MatTableDataSource<User>();
  totalElements: number;
  search: FormControl = new FormControl();
  user: User;
  userCars: Car[];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) matSort: MatSort;

  constructor(private usersService: UsersService) {
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
    this.populateUsers();
  }

  searchData(value: string): void {
    this.dataSource.filter = value.trim();
    this.populateUsers();
  }

  populateUsers(): void {
    this.usersService.getUsers(this.getParams())
      .subscribe((response: HttpResponse<User[]>) => {
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

  getUserInfo(id: number): void {
    forkJoin([this.usersService.getUser(id), this.usersService.getUserCars(id)])
      .subscribe((response) => {
        this.user = response[0].body;
        this.userCars = response[1].body;
      });
  }
}
