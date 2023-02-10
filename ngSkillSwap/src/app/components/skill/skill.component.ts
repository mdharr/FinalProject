import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Skill } from 'src/app/models/skill';
import { AuthService } from 'src/app/services/auth.service';
import { SkillService } from 'src/app/services/skill.service';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css']
})
export class SkillComponent {

skillList: Skill[]= [];
selected: null | Skill = null;

  constructor(
    private skillService: SkillService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('skillId');
    if (idString) {
      console.log('skillId: ' + idString);
      let skillId = Number(idString);
      if (!isNaN(skillId)) {
        this.skillService.show(skillId).subscribe({
          next: (skill) => {
            this.selected = skill;
            this.router.navigateByUrl('/skill');
          },
          error: (fail) => {
            console.error(fail);
            this.router.navigateByUrl('SkillNotFound');
          },
        });
      } else {
        this.router.navigateByUrl('SkillNotFound');
      }
    }
    this.reload();
  }

  reload() {
    this.skillService.indexAll().subscribe({
      next: (skillList) => {
        this.skillList = skillList;
      },
      error: (err) => {
        console.error('Error loading project list: ');
        console.error(err);
      },
    });
  }


}
