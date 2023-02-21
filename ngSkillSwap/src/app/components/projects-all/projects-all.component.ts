import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from 'src/app/models/project';
import { Skill } from 'src/app/models/skill';
import { SkillService } from 'src/app/services/skill.service';
import { ProjectService } from 'src/app/services/project.service';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { Comment } from 'src/app/models/comment';

import { CommentService } from 'src/app/services/comment.service';
@Component({
  selector: 'app-projects-all',
  templateUrl: './projects-all.component.html',
  styleUrls: ['./projects-all.component.css'],
})
export class ProjectsAllComponent {
  title = 'ngSkillSwap';

  projects: Project[] = [];
  project: Project | null = null;
  users: User[] = [];
  selected: null | Project = null;

  newProject: Project = new Project();

  projectsBySkill: Project[] = [];

  projectsToBeDeleted: Project[] = [];

  comments: Comment[] = [];
  newComment: Comment = new Comment();
  loggedInUser: User = new User();

  skills: Skill[] = [];

  skillList: Skill[] = [];

  projectCreated = false;
  showForm: boolean = false;
  addProjectMod: Project | null = null;

  selectedSearch: string = 'all'

  constructor(
    private commentService: CommentService,
    private projectService: ProjectService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private skillService: SkillService
  ) {}

  ngOnInit() {
    this.reload();
    this.displaySkills();
    let idString = this.route.snapshot.paramMap.get('id');
    console.log('project ID' + idString);
    if (idString) {

      let projectId = +idString;
      if (!isNaN(projectId)) {
        this.projectService.show(projectId).subscribe({
          next: (project) => {
            this.displayProject(project);
          },
          error: (fail) => {
            console.log(fail);
            this.router.navigateByUrl('projectNotFound');
          },
        });
      } else {
        this.router.navigateByUrl('invalidProjectId');
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

  reload() {
    this.projectService.indexAll().subscribe({
      next: (projects) => {
        this.projects = projects;
      },
      error: (err) => {
        console.error('Error loading projects list');
        console.error(err);
      },
    });
  }

  // ...
  displayProject(project: Project | null) {
    this.selected = project;
    if (this.selected) {
      console.log('user name');

    }

    if (this.selected && this.selected.id) {
      this.getComments(this.selected.id);
    }
  }

  resetForm() {
    this.newProject = new Project();
    this.projectCreated = false;
  }

  getComments(id: number) {
    this.commentService.projectCommentIndex(id).subscribe({
      next: (comments) => {
        this.comments = comments;
        console.log(this.comments);
      },
      error: (err) => {
        console.error('Error loading project comments');
        console.error(err);
      },
    });
  }

  createComment(comment: Comment, selected: Project) {
    let id = this.selected?.id;
    if (id) {
      comment.project.id = id;
    }
    console.log(comment);
    this.commentService.createComment(comment).subscribe({
      next: (data) => {
        this.newComment.project.id = selected.id;
        this.newComment.user.id = this.loggedInUser.id;
        this.newComment = new Comment();
        this.displayProject(selected)


      },
      error: (nojoy) => {
        console.error('ProjectComponent.createComment: Error creating comment');
        console.error(nojoy);
      },
    });
  }

  displayTable() {

    console.log(this.projects);
    location.reload();


  }
  createProject: boolean = false;
  setAddProject(): void {
    this.editProject = Object.assign({}, this.selected);
  }

  addProject(project: Project ) {
    console.log(project);

    this.projectService.create(project).subscribe({
      next: (data) => {
        this.projectCreated = true;
        this.project = data;

      },
      error: (nojoy) => {
        console.error(
          'ProjectListComponent.addProject: error creating project'
        );
        console.error(nojoy);
      },
    });
  }

  skillUpdate(skillId: number, projectId: number = 0) {
    this.projectService.updateSkill(skillId, projectId).subscribe({
      next: (data) => {
        this.newProject = data;
      },

      error: (nojoy) => {
        console.error('ProjectComponent.skillUpdate: Error updating skills');
        console.error(nojoy);
      },
    });
  }

  addUser(project: Project, user: User) {

    console.log(project);
    this.projectService.addUser(project).subscribe({
      next: (data) => {
        console.log(data);
        this.project = data;
        this.displayTable();

      },
      error: (nojoy) => {
        console.error('ProjectComponent.addUser: Error adding user');
        console.error(nojoy);
      },
    });
  }

  editProject: Project | null = null;
  setEditProject(): void {
    this.editProject = Object.assign({}, this.selected);
  }

  updateProject(project: Project, goToDetail = true): void {
    this.projectService.update(project).subscribe({
      next: (updatedProject) => {
        if (goToDetail) {
          this.displayProject(updatedProject);
        } else {
          this.displayProject(null);
        }
        this.editProject = null;
        this.reload();
      },
      error: (toobad) => {
        console.error(
          'ProjectListComponent.updateProject: error updating project'
        );
        console.error(toobad);
      },
    });
  }

  deleteProject(project: Project): void {
    console.log(project);
    this.projectService.archive(project).subscribe({
      next: () => {
        this.reload();
      },
      error: (fail) => {
        console.error(
          'ProjectListComponent.deleteProject: error deleting project'
        );
      },
    });
  }

  addProjectToDelete(project: Project) {
    for (let i = 0; i < this.projectsToBeDeleted.length; i++) {
      if (project.id === this.projectsToBeDeleted[i].id) {
        this.projectsToBeDeleted.splice(i, 1);
        return;
      }
    }
    this.projectsToBeDeleted.push(project);
    console.log(project);
    console.log(this.projectsToBeDeleted);
  }

  deleteProjects(): void {
    for (let i = 0; i < this.projectsToBeDeleted.length; i++) {
      let project = this.projectsToBeDeleted[i];
      this.deleteProject(project);
    }
  }

  displaySkills() {
    this.skillService.indexAll().subscribe({
      next: (skillList) => {
        this.skillList = skillList;
      },
      error: (err) => {
        console.error('Error loading skill list: ');
        console.error(err);
      },
    });
  }

  findBySkill(skill: string){
    this.projectService.projectBySkill(skill).subscribe({
      next: (project) => {
        this.projectsBySkill = project;
        console.log(this.projectsBySkill);
      },
      error: (err) => {
      console.error('Error loading project by skill: ');
    console.error(err);}
    }

  )}

  skillsSearch = [
    'Woodworking',
    'Cooking',
    'Computing',
    'Electrician',
    'Sewing',
    'Painting',
    'Dog training',
    'Home construction',
    'Mechanic',
    'Gardening',
    'Tutoring'
  ];
}
