import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/models/project';
import { ProjectService } from 'src/app/services/project.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {


selected : null | Project = null
projectList: Project[] = [];
editProject: Project | null = null;
newProject: Project = new Project();
projects: any;
log: any;

constructor( private projectService: ProjectService,   private route: ActivatedRoute,
  private router: Router,
  private authService: AuthService) {}

ngOnInit(): void {
  let idString = this.route.snapshot.paramMap.get('userId');
  console.log('userId: ' + idString);
  let todoId = Number(idString);
  if (!isNaN(todoId)) {
    this.projectService.show(todoId).subscribe({
      next: (project) => {
        this.selected = project;
      },
      error:(fail) => {
        console.error(fail);
        this.router.navigateByUrl('ProjectNotFound');
      }
    })
  }
  else{
    this.router.navigateByUrl('invalidProjectId');
  }
  this.reload();
}

  reload() {
    this.projectService.index().subscribe({
      next: (projectList) => {
        this.projectList = projectList;
      },
      error: (err) => {
        console.error('Error loading project list: ');
        console.error(err);
      }
    })
   }

   displayProject(project: Project) {
      this.selected = project;
   }

   displayTable(){
    this.selected = null;
   }

   addProject(project: Project) {
    this.projectService.create(project).subscribe({
      next: (data) => {
        this.newProject = new Project();
        this.reload();
      },

      error: (nojoy) => {
        console.error('ProjectComponent.addProject: Error creating project');
        console.error(nojoy);
      }
    });

  }

  setEditProject() {   this.editProject = Object.assign({}, this.selected); }

  updateProject(project: Project, goToDetail = true): void {
    //const completedDate = this.datePipe.transform(Date.now(), 'shortDate'); // 8/24/1999
    this.projectService.update(project).subscribe({
      next: (updatedProject) => {
        if (goToDetail) {
          this.selected = updatedProject;
        }
        else {
          this.selected = null;
        }
        this.editProject = null,
      this.reload();
      },
      error: (toobad) => {
        console.error('ProjectComponent.updateProject: error updating');
        console.error(toobad);
      }
    });
  }
  deleteProject(id:number) {
    this.projectService.destroy(id).subscribe({
      next: () => {
        this.reload();
      },

      error: (fail) => {
        console.error('ProjectComponent.deleteProject: error deleting:')
        console.error(fail);
      }
    });

  }


}

