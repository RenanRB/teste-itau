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

  public save(user: User): Observable<any> {
    return this.apiService.post<any>(this.pathBase, user);
  }

}
