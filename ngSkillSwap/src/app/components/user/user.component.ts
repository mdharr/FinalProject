import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Project } from 'src/app/models/project';
import { User } from 'src/app/models/user';
import { Comment } from 'src/app/models/comment';

import { AuthService } from 'src/app/services/auth.service';
import { CommentService } from 'src/app/services/comment.service';
import { ProjectService } from 'src/app/services/project.service';
import { UserService } from 'src/app/services/user.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  private url = environment.baseUrl + 'api/users';

  selected: null | User = null;
  userList: User[] = [];
  newUser: User = new User();
  editUser: User | null = null;
  user: User | null = null;
  comments: Comment[] = [];

  constructor(
    private userService: UserService,
    private projectService: ProjectService,
    private http: HttpClient,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private commentService: CommentService
  ) {}

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('userId');
    if (idString) {
      console.log('******************');

      console.log('userId: ' + idString);
      let userId = Number(idString);
      if (!isNaN(userId)) {
        this.userService.show(userId).subscribe({
          next: (user) => {
            console.log('user: ' + user);
            console.log(user.id);
            console.log('ngOnInit method in user');
            this.selected = user;
          },
          error: (fail) => {
            console.error(fail);
            this.router.navigateByUrl('UserNotFound');
          },
        });
      } else {
        this.router.navigateByUrl('invalidUserId');
      }
    }
    this.reload();
  }

  reload() {
    this.userService.index().subscribe({
      next: (userList) => {
        this.userList = userList;
      },
      error: (err) => {
        console.error('Error loading user list: ');
        console.error(err);
      },
    });
  }

  displayUser(user: User) {
    this.selected = user;
  }

  displayTable() {
    this.selected = null;
  }

  setEditUser() {
    this.editUser = Object.assign({}, this.selected);
  }

  updateUserAdmin(user: User, id: number): void {
    console.log('in editInformation');
    this.userService.disableAdmin(user).subscribe({
      next: (user) => {
        user.enabled = false;
        this.reload();
      },
      error: (error) => {
        console.log('Error getting loggedInUser Profile Component');
        console.log(error);
      },
    });
  }

  getCommentsForUser() {
    this.commentService.userCommentIndex(this.selected!.id).subscribe({
      next: (comments) => {
        this.comments = comments;
      },
      error: (err) => {
        console.error(err);
        console.error('error retrieving comments for user');
      },
    });
  }

  deleteUser(id: number) {

  }
}
