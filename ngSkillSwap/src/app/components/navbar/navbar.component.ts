import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  public isCollapsed = false;

  constructor(private authService: AuthService) {

  }


  loggedIn(): boolean {
    return this.authService.checkLogin();
  }

}
