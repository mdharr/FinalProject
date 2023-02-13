import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from 'src/app/models/project';
import { Skill } from 'src/app/models/skill';
import { SkillService } from 'src/app/services/skill.service';
import { ProjectService } from 'src/app/services/project.service';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-projects-all',
  templateUrl: './projects-all.component.html',
  styleUrls: ['./projects-all.component.css']
})
export class ProjectsAllComponent {

  title = 'ngSkillSwap';

  projects: Project[] = [];

  selected: null | Project = null;

  newProject: Project = new Project();

  projectsToBeDeleted: Project[] = [];

  loggedInUser: User = new User();

  skills: Skill[] = [];

  constructor(private projectService: ProjectService, private authService: AuthService, private route: ActivatedRoute, private router: Router, private skillService: SkillService,) { }

  ngOnInit() {
    this.reload();
    let idString = this.route.snapshot.paramMap.get('id');
    console.log('project ID' + idString);
    if(idString) {
      // let projectId = Number(idString);
      // let projectId = Number.parseInt(idString);
      let projectId = +idString;
      if (!isNaN(projectId)) {
        this.projectService.show(projectId).subscribe({
          next: (project) => {
            this.displayProject(project);
          },
          error: (fail) => {
            console.log(fail);
            this.router.navigateByUrl('projectNotFound');
          }
        });
      } else {
        this.router.navigateByUrl('invalidProjectId')
      }
    }

    this.reload();

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
   }
   // RELOAD METHOD MIGHT NEED SOME TINKERING
  reload() {
    this.projectService.indexAll().subscribe({
      next: (projects) => {
        this.projects = projects;
      },
      error: (err) => {
        console.error('Error loading projects list');
        console.error(err);
      }
    });
  }
  // ...
  displayProject(project: Project) {
    this.selected = project;
  }

  // ...
  displayTable() {
    this.selected = null;
  }
  createProject: boolean = false;
  setAddProject(): void {
    this.editProject = Object.assign({}, this.selected);
  }

  addProject(project: Project) {
    console.log(project);

    this.projectService.create(project).subscribe({
      next: (data) => {
        this.newProject = new Project();
        this.reload();
      },
      error: (nojoy) => {
        console.error('ProjectListComponent.addProject: error creating project');
        console.error(nojoy);
      }
    });
    this.newProject = new Project();
  }

  editProject: Project | null = null;
  setEditProject(): void {
    this.editProject = Object.assign({}, this.selected);
  }

  updateProject(project: Project, goToDetail = true): void {
    this.projectService.update(project).subscribe({
      next: (updatedProject) => {
        if(goToDetail) {
          this.selected = updatedProject;
        } else {
          this.selected = null;
        }
        this.editProject = null;
        this.reload();
      },
      error: (toobad) => {
        console.error('ProjectListComponent.updateProject: error updating project');
        console.error(toobad);
      }
    })
  }

  deleteProject(project: Project): void {
    console.log(project);
    // REFACTOR ARCHIVE METHOD TO TAKE IN A PROJECT ID
    this.projectService.archive(project).subscribe({
      // nothing to return in 'next' parenthesis because return is void
      next: () => {
        this.reload();
      },
      error: (fail) => {
        console.error('ProjectListComponent.deleteProject: error deleting project');
      }
    });
  }

  addProjectToDelete(project: Project) {
    for(let i = 0; i < this.projectsToBeDeleted.length; i++) {
      if(project.id === this.projectsToBeDeleted[i].id) {
        this.projectsToBeDeleted.splice(i, 1);
        return;
      }
    }
    this.projectsToBeDeleted.push(project);
    console.log(project);
    console.log(this.projectsToBeDeleted);


  }

  deleteProjects(): void {
    for(let i = 0; i < this.projectsToBeDeleted.length; i++) {
      let project = this.projectsToBeDeleted[i];
      this.deleteProject(project);
    }
  }

}
