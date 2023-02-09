import { User } from 'src/app/models/user';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Project } from 'src/app/models/project';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent {
  loggedInUser: User = new User();
  private url = environment.baseUrl + 'api/projects';

  constructor(
    private projectService: ProjectService,
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}
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

  update(project: Project): Observable<Project> {
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
            new Error('ProjectService.update(): error updating project: ' + err)
        );
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('ProjectService.delete(): error deleting project: ' + err)
        );
      })
    );
  }
}
