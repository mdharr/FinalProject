import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Project } from '../models/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private url = environment.baseUrl + 'api/projects';


  constructor(private http: HttpClient, private datePipe: DatePipe, private authService: AuthService) {}

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
            'ProjectService.index(): error retrieving project list: ' + err
            )
        );
      })
    );
  }

  show(id: number): Observable<Project> {
    return this.http.get<Project>(`${this.url}/${id}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
          new Error(
            'ProjectService.index(): error retrieving project list: ' + err
            )
        );
      })
    );
  }

  create(project: Project):Observable<Project> {
    const httpOptions = {
      headers: {
        'Content-Type': 'application/json',
      },
    };
  return this.http.post<Project>(this.url, project).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
        new Error(
          'ProjectService.index(): error creating project: ' + err
          )
      );
    })
  );
}



 update(project : Project):Observable<Project>  {
  const httpOptions = {
    headers: {
      'Content-Type': 'application/json',
    },
  };
 return this.http.put<Project>(`${this.url}/${project.id}`, project).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
        new Error(
          'ProjectService.update(): error updating project: ' + err
          )
      );
    })
  )

   }

 destroy(id : number):Observable<void>  {
  return this.http.delete<void>(`${this.url}/${id}`).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
        new Error(
          'ProjectService.delete(): error deleting project: ' + err
          )
      );
    })
  )}


}
