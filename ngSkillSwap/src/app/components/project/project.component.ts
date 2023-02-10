import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/models/project';
import { ProjectService } from 'src/app/services/project.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { SkillService } from 'src/app/services/skill.service';
import { Skill } from 'src/app/models/skill';
@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css'],
})
export class ProjectComponent implements OnInit {
  selected: null | Project = null;
  projectList: Project[] = [];
  editProject: Project | null = null;
  addProjectMod: Project | null = null;
  project: Project = new Project();
  projects: any;
  log: any;
  skillList: Skill[] = [];
  projectCreated = false;

  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private skillService: SkillService,
  ) {}

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('userId');
    if (idString) {
      console.log('userId: ' + idString);
      let userId = Number(idString);
      if (!isNaN(userId)) {
        this.projectService.show(userId).subscribe({
          next: (project) => {
            this.selected = project;
            this.router.navigateByUrl('/project');
          },
          error: (fail) => {
            console.error(fail);
            this.router.navigateByUrl('ProjectNotFound');
          },
        });
      } else {
        this.router.navigateByUrl('ProjectNotFound');
      }
    }
    this.displaySkills();
    this.reload();
  }

  reload() {
    this.projectService.projectsForUser().subscribe({
      next: (projectList) => {
        this.projectList = projectList;
        this.projectCreated = false;
        this.project = new Project();
      },
      error: (err) => {
        console.error('Error loading project list: ');
        console.error(err);
      },
    });
  }

  displayProject(project: Project) {
    this.selected = project;
  }

  displayTable() {
    this.selected = null;
  }

  setAddProject() {
    this.addProjectMod =  Object.assign({}, this.selected);
  }

  addProject(project: Project) {
    this.projectService.create(project).subscribe({
      next: (data) => {
        this.project = data;
        this.projectCreated = true;

      },

      error: (nojoy) => {
        console.error('ProjectComponent.addProject: Error creating project');
        console.error(nojoy);
      },
    });
  }

  skillUpdate(skillId : number, projectId : number) {
    this.projectService.updateSkill(skillId, projectId).subscribe ({
      next: (data) => {
        this.project = data;
    },

    error: (nojoy) => {
      console.error('ProjectComponent.skillUpdate: Error updating skills');
      console.error(nojoy);
    },
  });
  }


  setEditProject() {
    this.editProject = Object.assign({}, this.selected);
  }

  updateProject(project: Project,): void {
    const httpOptions = {
      headers: {
        'Content-Type': 'application/json',
      },
    };
    this.projectService.update(project).subscribe({
      next: (updatedProject) => {

          this.selected = updatedProject;


        (this.editProject = null), this.reload();
      },
      error: (toobad) => {
        console.error('ProjectComponent.updateProject: error updating');
        console.error(toobad);
      },
    });
  }

  checkSkill(skill : Skill) {
  if (this.editProject) {
    for ( let i = 0; i < this.editProject.skills.length; i++) {
      if (this.editProject?.skills[i].id === skill.id) {
        return true;
    }

    }
  }
    return false;
  }


  // deleteProject(id: number) {
  //   this.projectService.destroy(id).subscribe({
  //     next: () => {
  //       this.reload();
  //     },

  //     error: (fail) => {
  //       console.error('ProjectComponent.deleteProject: error deleting:');
  //       console.error(fail);
  //     },
  //   });
  // }


  displaySkills() {
    this.skillService.indexAll().subscribe({
      next: (skillList) => {
        this.skillList = skillList;
      },
      error: (err) => {
        console.error('Error loading skill list: ');
        console.error(err);
      },
  })
}
}
