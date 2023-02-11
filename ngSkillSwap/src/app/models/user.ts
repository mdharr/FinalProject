import { Address } from './address';
import { Project } from './project';
import { UserSkill } from './user-skill';
export class User {
  id: number;
  username: string;
  password: string;
  role: string;
  enabled: boolean;
  firstName: string;
  lastName: string;
  availability: boolean;
  email: string;
  bio: string;
  profileImage: string;
  createdDate: string;
  lastActive: string;
  address: Address | null;
  projects: Project[];
  userSkills: UserSkill[];
  constructor(
    id: number = 0,
    username: string = '',
    password: string = '',
    role: string = '',
    enabled: boolean = false,
    firstName: string = '',
    lastName: string = '',
    availability: boolean = false,
    email: string = '',
    bio: string = '',
    profileImage: string = '',
    createdDate: string = '',
    lastActive: string = '',
    address: Address | null = null,
    projects: Project[] = [],
    userSkills: UserSkill[] = []
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.enabled = enabled;
    this.firstName = firstName;
    this.lastName = lastName;
    this.availability = availability;
    this.email = email;
    this.bio = bio;
    this.profileImage = profileImage;
    this.createdDate = createdDate;
    this.lastActive = lastActive;
    this.projects = projects;
    this.address = address;
    this.userSkills = userSkills;
  }
}
