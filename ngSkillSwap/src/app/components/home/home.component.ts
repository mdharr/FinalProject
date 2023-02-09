import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { HomeService } from 'src/app/services/home.service';
import { Project } from 'src/app/models/project';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
constructor(private auth: AuthService, private homeServ: HomeService){}
 projects: Project[] = [];

ngOnInit(){
 this.testTestDeleteLater();
  this.reload();
  console.log("**************************reloading");
}
reload(): void{
  this.homeServ.index().subscribe({
    next: (projects) =>{
      this.projects = projects;
    },
    error:(fail) => {
      console.error('Error getting projects:')
      console.error(fail);
    }
  });
}

testTestDeleteLater(){
  // this.auth.login('admin', 'wombat1').subscribe({
  //   next: (data) => {
  //     console.log('logged in')
  //     console.log(data);
  // },
  //   error:(fail) => {
  //     console.error('Error authenticating:')
  //     console.error(fail);
  //   }
  // });
}

}
