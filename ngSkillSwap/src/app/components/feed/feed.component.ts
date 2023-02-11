import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from 'src/app/models/project';
import { AuthService } from 'src/app/services/auth.service';
import { ProjectService } from 'src/app/services/project.service';
import { SkillService } from 'src/app/services/skill.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {
  display = true;
  projects: Project[] = [];

  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private skillService: SkillService,
  ) {}
  ngOnInit(): void {
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
  }
}
