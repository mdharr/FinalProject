export class Comment {
  id: number;
  datePosted: string;
  comment: string;
  projectId: number;
  userId: number;
  inReplyToId: number;

  constructor(
    id: number = 0,
    datePosted: string = '',
    comment: string = '',
    projectId: number = 0,
    userId: number = 0,
    inReplyToId: number = 0
  ) {
    this.id = id;
    this.datePosted = datePosted;
    this.comment = comment;
    this.projectId = projectId;
    this.userId = userId;
    this.inReplyToId = inReplyToId;
  }
}
