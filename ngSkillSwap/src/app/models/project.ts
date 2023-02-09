export class Project {
  id: number;
  name: string;
  datePosted: Date;
  description: string;
  activeStatus: boolean;
  userId: number;
  imagePrimary: string;
  startDate: Date;
  projectedDate: Date;

  constructor(
    id: number = 0,
    name: string = '',
    datePosted: Date,
    description: string = '',
    activeStatus: boolean = false,
    userId: number = 0,
    imagePrimary: string = '',
    startDate: Date,
    projectedDate: Date
  ) {
    this.id = id;
    this.name = name;
    this.datePosted = datePosted;
    this.description = description;
    this.activeStatus = activeStatus;
    this.userId = userId;
    this.imagePrimary = imagePrimary;
    this.startDate = startDate;
    this.projectedDate = projectedDate;
  }
}
