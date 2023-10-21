import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/core/services/model/user.model';
import { UserService } from 'src/app/shared/services/user.service';

export interface PeriodicElement {
  name: string;
  id: number;
  surname: string;
  age: number;
  country: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements AfterViewInit {
  displayedColumns: string[] = ['id', 'name', 'surname', 'age', 'country'];
  data: PeriodicElement[] = [
    {id: 1, name: 'Hydrogen', surname: 'sdf', age: 10,country: 'Brasil'},
    {id: 2, name: 'Helium', surname: 'sdf', age: 20,country: 'Brasil'},
    {id: 3, name: 'Lithium', surname: 'sdf', age: 20,country: 'Brasil'},
    {id: 4, name: 'Beryllium', surname: 'sdf', age: 20,country: 'Brasil'},
    {id: 5, name: 'Boron', surname: 'sdf', age: 20,country: 'Brasil'},
    {id: 6, name: 'Carbon', surname: 'fds', age: 20,country: 'Brasil'},
    {id: 7, name: 'Nitrogen', surname: 'fd', age: 21,country: 'Brasil'},
    {id: 8, name: 'Oxygen', surname: 'fs', age: 22,country: 'Brasil'},
    {id: 9, name: 'Fluorine', surname: 'gsdf', age: 23,country: 'Brasil'},
    {id: 10, name: 'Neon', surname: 'vva', age: 24,country: 'Brasil'},
  ];
  dataSource = new MatTableDataSource(this.data);

  constructor(private _liveAnnouncer: LiveAnnouncer,
              private userService: UserService) {}

  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  saveUser(): void {
    const user = {
      name: 'Renan',
      surname: 'Bertoldo',
      age: 28,
      country: 'Portugal'
    } as User;
    this.userService.save(user).subscribe(res => {
      this.dataSource.data = [...this.dataSource.data, res];
    });
  }
}
