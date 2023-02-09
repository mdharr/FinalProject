import { Skill } from "./skill";
import { User } from "./user";

export class UserSkill {

  // might need id field for user skill
  // id: number;
  experienceId: number;
  user: User;
  skill: Skill;

  constructor(
    experienceId: number = 0,
    user: User,
    skill: Skill
  ) {
    this.experienceId = experienceId;
    this.user = user;
    this.skill = skill;
  }
}
