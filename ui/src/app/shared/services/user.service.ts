import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ApiService } from "src/app/core/services/api.service";
import { User } from "src/app/core/services/model/user.model";

@Injectable({
    providedIn: 'root',
})
export class UserService {


  private readonly pathBase = 'users/';

  constructor(private apiService: ApiService) {
  }

  public getById(id: number): Observable<User> {
    return this.apiService.get(this.pathBase + id);
  }

  public getAll(): Observable<Array<User>> {
    return this.apiService.get(this.pathBase);
  }

  public save(user: User): Observable<User> {
    return this.apiService.post(this.pathBase, user);
  }

  public update(user: User): Observable<User> {
    return this.apiService.put(this.pathBase, user);
  }

  public delete(id: number): Observable<void> {
    return this.apiService.delete(this.pathBase + id);
  }

}
