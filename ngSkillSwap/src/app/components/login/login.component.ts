import { Component } from '@angular/core';
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

constructor(private authService: AuthService, private router: Router) {

}

  login(loginUser: User) {
    console.log("Logging in");
    this.authService.login(loginUser.username, loginUser.password).subscribe({
      next: (loggedInUser) => {
        console.log(loggedInUser);
        this.router.navigateByUrl('home');
      },
      error: (fail) => {
        console.error('Login failed');
        console.error(fail);
      }
    })

  }

}
