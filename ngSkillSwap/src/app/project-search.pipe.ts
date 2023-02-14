import { Pipe, PipeTransform } from '@angular/core';
import { Project } from './models/project';

@Pipe({
  name: 'projectSearch'
})
export class ProjectSearchPipe implements PipeTransform {

  transform(project: Project[], selectedSearch: string): Project[] {

    if(selectedSearch === 'all') {
      return project;
    }
    let result: Project[] = [];
    project.forEach((project) => {
      project.skills?.forEach((skill) => {
        if(skill.name === selectedSearch) {
          result.push(project);
        }
      });
    });
    return result;
  }

}
