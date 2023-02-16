# FinalProject
Hello World
## Description
Skill Swap is an application designed to connect people in an effort to educate and assist them with their projects around the home. It is an asynchronous application that has been deployed on AWS.
On Skill Swap you are able to create an account using the register button that pops up with a modal asking for you for your email, a unique username, and a password. The accounts have passwords that are encrypted using Spring Security and saved as a string so that way we are not saving the raw password and it is not able to be leaked.
After you have created an account with a unique username, the page reloads on to the home page. Here you can see the project board, my projects, and my profile options on the nav bar.
You can go to the Project Board where all open projects are listed. If logged in as a regular user, you can see the project owner's first name, the name of the project, a brief description, skill, and an image. When clicking on any of those, you are brought to the detailed view. Once there you can view the full description, the date the project was posted, the date the project owner wants to begin the project, and the projected completion date. If anyone has volunteered to assist with the project, they are listed under the project member section. Here you are able to comment on the project as well. If no one has commented, it says "Be the first to comment", but once it is commented on that message goes away.
On the project board as well you can add a project. You can put a name for the project, description, link to an image, start date, and a projected completion date. Once those fields have been entered, then you click on the add skills option. Depending on the project, select what skills you believe are needed and then hit save. After saving you are shown the detail view of the project.
Once you have a project, you can go to the my projects section. If you have projects in process, you have a card with the details. If it is clicked on you can view the details of the project, edit it, or complete the project.
If completed you can click completed then the project is moved to the completed section.
On my profile, it will show you your personal information, if you just created an account, you'll need to hit the update profile button in the bottom right corner to add information.
From here you can view what information you listed. The end points on the navigate bar are about, work, skills and email.
Work shows your previous projects, skills show what your personal skills are -- wood working, electrical, cooking, etc. The contact information would show if you are currently busy or not. When your account is created, you are set to automatically available.
One additional feature we have implemented is the Admin feature where the admin has the ability to archive any projects to remove them from the projects board.
## Technologies Used
- Java
- Angular
- JavaScript
- TypeScript
- HTML
- CSS
- Bootstrap
- MySQL Workbench
- SQL
- Springboot
- JPA
- Spring Tool Suite
- VSCode
- PostMan

## Lessons Learned
This project is the culmination of the 16 week Full Stack Coding Bootcamp where we finally got to see everything we worked for come together.
Here we learned the importance of developing the backend and testing in PostMan to confirm what was and wasn't working before moving to Angular.  Working with the entities, understanding composite keys, completing the mapping caused issues when we did not do it correctly and had to go back to rework some of our existing points to ensure that we were accessing the data correctly.
Git Branching really helped us work separately on the project when we had different ideas before pushing all of our commits and being able to add all of the features that we wanted.
We go to know the importance of the reading the HTTP error codes whether it be a mapping, end point, or database issue.
