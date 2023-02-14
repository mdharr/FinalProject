import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Project } from '../models/project';
import { Comment } from '../models/comment';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private url = environment.baseUrl + 'api/projects';
private otherUrl = environment.baseUrl;

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
  //user specific list
  projectsForUser(): Observable<Project[]> {
    return this.http.get<Project[]>(this.url + "/authenticated", this.getHttpOptions()).pipe(
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
  //all projects
  indexAll(): Observable<Project[]> {
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

  completedProjects(): Observable<Project[]>{
    return this.http.get<Project[]>(this.url + "/completed", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
          new Error(
            'ProjectService.index(): error retrieving project list:'+ err
            )
            );
          })
        );
      }

  show(id: number): Observable<Project> {
    return this.http.get<Project>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
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
  return this.http.post<Project>(this.url ,project, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError( () => new Error(
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
 return this.http.put<Project>(`${this.url}/`, project, this.getHttpOptions()).pipe(
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

   updateSkill(skillId : number, projectId : number) {
    return this.http.put<Project>(`${this.url}/projectId/${projectId}/skillId/${skillId}`, null, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
          new Error(
            'ProjectService.updateSkill(): error updating project skills: ' + err
            )
        );
      })
    )
   }

   addUser(project: Project) {
    return this.http.put<Project>(`${this.url}/projectId/${project.id}/users`, project, this.getHttpOptions()).pipe(
    catchError((err: any) => {
    console.log(err);
    return throwError(
      () =>
      new Error(
        'ProjectService.addUser(): error adding user to project: ' + err
        )
    );
  })
)

}




 archive(project : Project):Observable<Project>  {
  const httpOptions = {
    headers: {
      'Content-Type': 'application/json',
    },
  };
  return this.http.put<Project>(`${this.url}/${project.id}`,project, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
        new Error(
          'ProjectService.archive(): error archiving project: ' + err
          )
      );
    })
  )}

}
