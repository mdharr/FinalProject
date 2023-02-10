import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Skill } from '../models/skill';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class SkillService {
  private url = environment.baseUrl + 'api/skills';
  constructor(private http: HttpClient, private datePipe: DatePipe, private authService: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }
    //all projects
    indexAll(): Observable<Skill[]> {
      return this.http.get<Skill[]>(this.url, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
            new Error(
              'SkillService.index(): error retrieving skill list: ' + err
              )
          );
        })
      );
    }

    show(id: number): Observable<Skill> {
      return this.http.get<Skill>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
            new Error(
              'SkillService.index(): error retrieving skill list: ' + err
              )
          );
        })
      );
    }


    // create(skill: Skill):Observable<Skill> {
    //   const httpOptions = {
    //     headers: {
    //       'Content-Type': 'application/json',
    //     },
    //   };
    // return this.http.post<Skill>(this.url ,skill, this.getHttpOptions()).pipe(
    //   catchError((err: any) => {
    //     console.log(err);
    //     return throwError( () => new Error(
    //         'SkillService.index(): error c: ' + err
    //         )
    //     );
    //   })
    // );
  }

