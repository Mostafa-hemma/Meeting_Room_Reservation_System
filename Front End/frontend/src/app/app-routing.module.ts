import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './authenticate/signup/signup.component';
import { SigninComponent } from './authenticate/signin/signin.component';
import { CalenderGridComponent } from './calender/calender-grid/calender-grid.component';
import { MyeventsComponent } from './myevents/myevents.component';
import { AuthGuard } from './auth.guard';
import { ApprovalComponent } from './approval/approval.component';
import { MyMeetingRoomsComponent } from './my-meeting-rooms/my-meeting-rooms.component';

const routes: Routes = [
  {path:'',redirectTo:'/signin',pathMatch:"full"},
  {path:'signup',component:SignupComponent},
  {path:'signin',component:SigninComponent},
  {path:'dashboard',component:CalenderGridComponent ,canActivate:[AuthGuard]},
  {path:'events',component:MyeventsComponent,canActivate:[AuthGuard]},
  {path:'approve',component:ApprovalComponent ,canActivate:[AuthGuard]},
  {path:'meetingRooms',component:MyMeetingRoomsComponent,canActivate:[AuthGuard]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 


}
