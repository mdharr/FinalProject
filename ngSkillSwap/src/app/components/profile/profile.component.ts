import { UserService } from './../../services/user.service';
import { User } from 'src/app/models/user';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
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
export class ProfileComponent implements OnInit {
  private url = environment.baseUrl + 'api/users';

  loggedInUser: User = new User();
  userProjectList: Project[] = [];
  selected: null | User = null;

  constructor(
    private userService: UserService,
    private http: HttpClient,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
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
ngOnInit(): void{
  this.authService.getLoggedInUser().subscribe({
    next: (user) => {
      this.loggedInUser = user;
  },
  error: (error) => {
    console.log("Error getting loggedInUser Profile Component");
    console.log(error);

}})
}
}
