import { Project } from "./project";
import { User } from "./user";

export class ProjectMember {

  // project member id might need a field
  // id: number;
  user: User;
  project: Project;
  comments: string;
  rating: number;

  constructor(
    user: User,
    project: Project,
    comments: string = '',
    rating: number = 0
  ) {
    this.user = user;
    this.project = project;
    this.comments = comments;
    this.rating = rating;
  }
}
