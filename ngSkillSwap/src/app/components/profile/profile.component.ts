import { ProjectComponent } from './../project/project.component';
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
import { Skill } from 'src/app/models/skill';
import { SkillService } from 'src/app/services/skill.service';
import { ViewportScroller } from '@angular/common';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';


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
  editUser: null | User = null;
  display = true;
  projects: Project[] = [];
  skills: Skill[] = [];
  completedProjects: Project[] = [];


  constructor(
    private userService: UserService,
    private projectService: ProjectService,
    private http: HttpClient,
    private authService: AuthService,
    private skillService: SkillService,
    private router: Router,
    private route: ActivatedRoute,
    private viewportScroller: ViewportScroller,
    private modalService: NgbModal
  ) {}

  public onClick(elementId: string): void { this.viewportScroller.scrollToAnchor(elementId); }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  ngOnInit(): void {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
        console.log(user);

      },
      error: (error) => {
        console.log('Error getting loggedInUser Profile Component');
        console.log(error);
      },
    });
    this.display = false;
    this.projectService.indexAll().subscribe({
      next: (projects) => {
        this.projects = projects;
        this.display = true;},
    error: (error) => {
      console.log(error);
      console.log("Error loading all projects")
    }
    })
  this.checkUserAvailability();
  this.getCompletedProjects();
  }
  reload(): void {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
      },
      error: (error) => {
        console.log('Error getting loggedInUser Profile Component');
        console.log(error);
      },
    });
  }

  editInformation(user: User): void {
    console.log('in editInformation');
    this.userService.update(user).subscribe({
      next: (user) => {
        user = this.loggedInUser;
        this.modalService.dismissAll();
        this.reload();
      },
      error: (error) => {
        console.log('Error getting loggedInUser Profile Component');
        console.log(error);
      },
    });
  }

  scrollToElement($element: { scrollIntoView: (arg0: { behavior: string; block: string; inline: string; }) => void; }): void {
    console.log($element);
    $element.scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"});
  }

  closeResult: string = '';

  /**
   * Write code on Method
   *
   * @return response()
   */
  open(content:any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  /**
   * Write code on Method
   *
   * @return response()
   */
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  // KEEP WORKING ON THIS
  checkUserAvailability() {
    let j = 0;
    for(let i = 0; i < this.loggedInUser.projects.length; i++) {
      if(this.loggedInUser.projects[i].enabled) {
        j++;
      }
    }
    if(j === 0) {
      this.loggedInUser.availability = true;
    } else {
      this.loggedInUser.availability = false;
    }
  }

  getCompletedProjects(){
    this.projectService.completedProjects().subscribe({
      next: (projects) => {
        this.completedProjects = projects;
      },
      error: (err) => {
        console.error(err);
        console.error('error retrieving completed projects: ' + err);
      }
  });
}
}
