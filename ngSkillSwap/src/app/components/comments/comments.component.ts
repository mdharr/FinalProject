import { CommentService } from './../../services/comment.service';
import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ProjectService } from 'src/app/services/project.service';
import { UserService } from 'src/app/services/user.service';
import { Project } from 'src/app/models/project';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css'],
})
export class CommentsComponent {
  comments: Comment[] = [];
  userComments: Comment[] = [];
  projectComments: Comment[] = [];
  @Input() selectedProject: Project | null = null;
  userId: any;

  constructor(
    private commentService: CommentService,
    private userService: UserService,
    private projectService: ProjectService,
    private http: HttpClient,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  createComment(newComment: Comment, loggedInUser: User): void {
    console.log('Creating new comment:');
    console.log(loggedInUser);
    this.commentService.createComment(newComment).subscribe({
      next: (comment: Comment) => {
        console.log(comment);
        this.comments.push(comment);
        this.userComments.push(comment);
        this.authService.getLoggedInUser;
      },
      error: (fail) => {
        console.error('CommentComponent.createComment(), fail');
        console.log(fail);
      },
    });
    error: (darn: Error) => {
      console.error('RegisterComponent.register(): Error registering account');
      console.error(darn);
    };
  }

  // deleteUser(id: number) {
  //   this.userService.destroy(id).subscribe({
  //     next: () => {
  //       this.reload();
  //     },
  //     error: (fail) => {
  //       console.error('User Component.deleteUser: error deleting:');
  //       console.error(fail);
  //     },
  //   });
  // }
  // reload() {
  //   this.commentService.index().subscribe({
  //     next: (comments) => {
  //       this.userComments = comments;
  //     },
  //     error: (err) => {
  //       console.error('Error loading user list: ');
  //       console.error(err);
  //     },
  //   });
  // }
}
