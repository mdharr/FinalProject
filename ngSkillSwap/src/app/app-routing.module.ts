import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { FeedComponent } from './components/feed/feed.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ProjectComponent } from './components/project/project.component';
import { ProjectsAllComponent } from './components/projects-all/projects-all.component';
import { RegisterComponent } from './components/register/register.component';
import { SkillComponent } from './components/skill/skill.component';
import { UserComponent } from './components/user/user.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'about', component: AboutComponent },
  { path: 'skill', component: SkillComponent },
  { path: 'project', component: ProjectComponent },
  { path: 'projects-all', component: ProjectsAllComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: UserComponent },
  { path: 'feed', component: FeedComponent},
  { path: '**', component: NotFoundComponent }, //page not found route
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true }), RouterModule.forRoot(routes, { anchorScrolling: 'enabled'})],
  exports: [RouterModule],
})
export class AppRoutingModule {}
