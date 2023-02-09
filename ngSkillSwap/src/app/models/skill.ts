export class Skill {
  id: number;
  name: string;
  description: string;
  imageUrl: string;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    imageUrl: string = ''
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
  }
}
