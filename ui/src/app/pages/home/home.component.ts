import { AfterViewInit, Component, Inject, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/core/services/model/user.model';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements AfterViewInit {
  displayedColumns: string[] = ['id', 'name', 'surname', 'age', 'country', 'actions'];
  data: User[] = [];
  dataSource = new MatTableDataSource(this.data);

  constructor(private userService: UserService,
              public dialog: MatDialog) {}

  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.userService.getAll().subscribe(users => this.dataSource.data = users);
    this.dataSource.sort = this.sort;
  }

  saveUser(): void {
    const dialogRef = this.dialog.open(UserCreateDialog, {
      data: {name: '', surname: '', age: 1, country: 'Brasil'} as User,
      width: '600px'
    });

    dialogRef.afterClosed().subscribe((user: User) => {
      if (user && user.name && user.surname && user.age && user.country) {
        this.userService.save(user).subscribe(newUser => {
          this.dataSource.data = [...this.dataSource.data, newUser];
        });
      }
    });
  }

  editUser(user: User): void {
    const dialogRef = this.dialog.open(UserCreateDialog, {
      data: {...user},
      width: '600px'
    });

    dialogRef.afterClosed().subscribe((user: User) => {
      if (user && user.id && user.name && user.surname && user.age && user.country) {
        this.userService.update(user).subscribe(userUpdated => {
          this.dataSource.data = this.dataSource.data.map(item => item.id === userUpdated.id ? {...userUpdated} : item);
        });
      }
    });
  }

  deleteUser(id: number): void {
    const dialogRef = this.dialog.open(UserDeleteDialog);

    dialogRef.afterClosed().subscribe(isConfirm => {
      if (isConfirm) {
        this.userService.delete(id).subscribe(() => {
          this.dataSource.data = this.dataSource.data.filter(item => item.id !== id);
        });
      }
    });
  }

  showUserInfos(id: number) {
    this.userService.getById(id).subscribe((user) => {
      console.log(user)
      this.dialog.open(UserInfoDialog, {data: user, minWidth: '300px'});
    });
  }
}

@Component({
  selector: 'user-create-dialog',
  templateUrl: 'user-create-dialog.html',
  styleUrls: ['./home.component.scss'],
  standalone: true,
  imports: [MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule, MatSelectModule],
})
export class UserCreateDialog {
  constructor(
    public dialogRef: MatDialogRef<UserCreateDialog>,
    @Inject(MAT_DIALOG_DATA) public user: User,
  ) {}

  onCancelClick(): void {
    this.dialogRef.close();
  }
}

@Component({
  selector: 'user-delete-dialog',
  templateUrl: 'user-delete-dialog.html',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule],
})
export class UserDeleteDialog {
  constructor(public dialogRef: MatDialogRef<UserDeleteDialog>) {}
}

@Component({
  selector: 'user-infos-dialog',
  templateUrl: 'user-infos-dialog.html',
  standalone: true,
  imports: [MatDialogModule],
})
export class UserInfoDialog {
  constructor(
    public dialogRef: MatDialogRef<UserInfoDialog>,
    @Inject(MAT_DIALOG_DATA) public user: User,
  ) {}
}