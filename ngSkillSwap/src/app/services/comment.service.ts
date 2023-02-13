import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../models/comment';

import { Project } from '../models/project';
import { AuthService } from './auth.service';
import { ProjectService } from './project.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private url = environment.baseUrl + 'api';

  constructor(
    private projectService: ProjectService,
    private userService: UserService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private http: HttpClient,
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

  createComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('CommentService.createComment(): error creating comment.')
        );
      })
    );
  }

  // projects/{pid}/comments
  projectCommentIndex(projectId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.url + '/projects/' + projectId + '/comments', this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('error retrieving comments for user: ' + err)
          );
        })
      );
  }

  userCommentIndex(userId: number): Observable<Comment[]> {
    return this.http
      .get<Comment[]>(
        this.url + 'users/' + userId + '/comments',
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error('UserService.index(): error retrieving user: ' + err)
          );
        })
      );
  }

  // index(): Observable<Comment[]> {
  //   return this.http
  //     .get<Comment[]>(
  //       this.url + 'projects/' + projectId + '/comments',
  //       this.getHttpOptions()
  //     )
  //     .pipe(
  //       catchError((err: any) => {
  //         console.log(err);
  //         return throwError(
  //           () =>
  //             new Error('UserService.index(): error retrieving user: ' + err)
  //         );
  //       })
  //     );
  // }

  show(id: number): Observable<Comment> {
    return this.http.get<Comment>(`${this.url}/${id}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('UserService.index(): error retrieving user list: ' + err)
        );
      })
    );
  }
}
