import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, throwError, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { Project } from '../models/project';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  private url = environment.baseUrl + 'api/projects';
  constructor(private http: HttpClient, private authService: AuthService) {}
  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }
  index(): Observable<Project[]> {
    return this.http.get<Project[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'HomeService.index() error retrieving list of todos' + err
            )
        );
      })
    );
  }
}
