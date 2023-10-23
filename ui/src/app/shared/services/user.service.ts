import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "src/app/core/services/model/user.model";

@Injectable({
    providedIn: 'root',
})
export class UserService {


  private readonly pathBase = 'http://localhost:8000/users/';

  constructor(private http: HttpClient) {
  }

  public getById(id: number): Observable<User> {
    return this.http.get<User>(this.pathBase + id);
  }

  public getAll(): Observable<Array<User>> {
    return this.http.get<Array<User>>(this.pathBase);
  }

  public save(user: User): Observable<User> {
    return this.http.post<User>(this.pathBase, user);
  }

  public update(user: User): Observable<User> {
    return this.http.put<User>(this.pathBase, user);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(this.pathBase + id);
  }

}
