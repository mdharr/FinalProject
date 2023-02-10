import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginUser: User = new User;

constructor(private authService: AuthService, private router: Router, private modalService: NgbModal) {

}

  login(loginUser: User) {
    console.log("Logging in");
    this.authService.login(loginUser.username, loginUser.password).subscribe({
      next: (loggedInUser) => {
        console.log(loggedInUser);
        this.modalService.dismissAll();
        this.router.navigateByUrl('home');
      },
      error: (fail) => {
        console.error('Login failed');
        console.error(fail);
      }
    })

  }

}
