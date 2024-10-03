import { Component, OnInit, ViewChild } from '@angular/core';
 
import { Sidebar, SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import { AvatarModule } from 'primeng/avatar';
import { ActivatedRoute, Router } from '@angular/router';
import { SharedServiceService } from '../shared/shared-service.service';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';
 
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  standalone: true,
  imports:[SidebarModule,ButtonModule,AvatarModule,CommonModule],
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit {
  data: boolean=false;
  seen: boolean=false;
  toggleMeeting:boolean =false;
  subscription!:Subscription
  @ViewChild('sidebarRef') sidebarRef!: Sidebar;
 
  empRole=localStorage.getItem('role')
  empName=localStorage.getItem('empName')
  constructor(private sharedService: SharedServiceService ,private router:Router,private route : ActivatedRoute) {}
  ngOnInit(){
    
    this.data=false;
    this.subscription=this.sharedService.dataSource.subscribe(value => this.data = value);
    this.sharedService.changeData(false);
    if(this.empRole==='ROLE_HR'){
      this.seen=true
    }
    if(this.empRole==='ROLE_IT'){
      this.toggleMeeting=true
    }

  }

  toApprove(){
   
    this.router.navigate(['/approve'],{relativeTo:this.route})
  }
  MeetingRooms(){
    this.data=false;

    this.router.navigate(['/meetingRooms'])
    this.sharedService.changeData(false);

  }
  goToEvents()
  {
    this.data=false;
    this.router.navigate(["/events"]);
    this.sharedService.changeData(false);
    
  }
}