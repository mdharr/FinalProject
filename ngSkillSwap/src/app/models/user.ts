export class User {
  id: number;
  username: string;
  password: string;
  role: string;
  enabled: boolean;
  constructor(
    id: number = 0,
    username: string = '',
    password: string = '',
    role: string = '',
    enabled: boolean = false
  ){
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.enabled = enabled;
  }
}
