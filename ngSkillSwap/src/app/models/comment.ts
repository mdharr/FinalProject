import { Project } from "./project";
import { User } from "./user";

export class Comment {
  id: number;
  datePosted: string;
  comment: string;
  project: Project;
  user: User;
  inReplyToId: number;

  constructor(
    id: number = 0,
    datePosted: string = '',
    comment: string = '',
    project: Project = new Project (),
    user: User = new User(),
    inReplyToId: number = 0
  ) {
    this.id = id;
    this.datePosted = datePosted;
    this.comment = comment;
    this.project = project;
    this.user = user;
    this.inReplyToId = inReplyToId;
  }
}
