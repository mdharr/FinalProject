export class Project {
  id: number;
  name: string;
  datePosted: string;
  description: string;
  activeStatus: boolean;
  userId: number;
  imagePrimary: string;
  startDate: string;
  projectedDate: string;

  constructor(
    id: number = 0,
    name: string = '',
    datePosted:string = '',
    description: string = '',
    activeStatus: boolean = false,
    userId: number = 0,
    imagePrimary: string = '',
    startDate: string = '',
    projectedDate: string = ''
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