import { Project } from "./project";

export class ProjectImage {

  id: number;
  imageUrl: string;
  caption: string;
  createdDate: string;
  project: Project;

  constructor(
    id: number = 0,
    imageUrl: string = '',
    caption: string = '',
    createdDate: string = '',
    project: Project
  ) {
    this.id = id;
    this.imageUrl = imageUrl;
    this.caption = caption;
    this.createdDate = createdDate;
    this.project = project;
  }
}
