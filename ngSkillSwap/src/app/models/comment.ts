import { Project } from "./project";

export class Comment {
  id: number;
  datePosted: string;
  comment: string;
  project: Project;
  userId: number;
  inReplyToId: number;

  constructor(
    id: number = 0,
    datePosted: string = '',
    comment: string = '',
    project: Project = new Project(),
    userId: number = 0,
    inReplyToId: number = 0
  ) {
    this.id = id;
    this.datePosted = datePosted;
    this.comment = comment;
    this.project = project;
    this.userId = userId;
    this.inReplyToId = inReplyToId;
  }
}
