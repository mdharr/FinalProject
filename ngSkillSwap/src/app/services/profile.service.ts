import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { Project } from '../models/project';
@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private url = environment.baseUrl + 'api/profile';
  constructor(private http: HttpClient, private authService: AuthService){}
  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }
  // show(id: number): Observable<Project> {
  //   return this.http.get<Project>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError(
  //         () =>
  //         new Error(
  //           'ProjectService.index(): error retrieving project list: ' + err
  //           )
  //       );
  //     })
  //   );
  // }
}
