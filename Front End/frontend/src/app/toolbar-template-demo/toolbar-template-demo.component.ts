import { Component, OnInit, ViewChild } from '@angular/core';
import { ToolbarModule } from 'primeng/toolbar';
import { AvatarModule } from 'primeng/avatar';
import {  MessageService, SharedModule } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { AddeventComponent } from '../calender/addevent/addevent.component';
import { Subscription } from 'rxjs';
import { EventService } from '../shared/eventService';
import { EventModel } from '../shared/eventModel';
import { SharedServiceService } from '../shared/shared-service.service';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { CommonModule } from '@angular/common';
import { AddRoomComponent } from '../my-meeting-rooms/add-room/add-room.component';

@Component({
    selector: 'toolbar-template-demo',
    templateUrl: './toolbar-template-demo.component.html',
    standalone: true,
    imports: [ToolbarModule, ButtonModule,CommonModule, AvatarModule, SharedModule,AddRoomComponent, AddeventComponent, SidebarComponent,CommonModule]
})
export class ToolbarTemplateDemo implements OnInit {
  @ViewChild('addEvent') addEvent!:AddeventComponent;
  @ViewChild('addRoom') addRoom!:AddRoomComponent
  seen: boolean=false;
  toggleMeeting:Boolean=false;

  empRole=localStorage.getItem('role')
  subscription!: Subscription;
  constructor(private messageService:MessageService,private sharedService:SharedServiceService,private route:ActivatedRoute,private router:Router,private eventService:EventService){}
  ngOnInit(): void {
 
    this.subscription=this.sharedService.meetingSource.subscribe(value => this.toggleMeeting = value);
    if(this.empRole==='ROLE_IT'){
      this.toggleMeeting=true
    }
  }


  onSidebar(){
    this.sharedService.changeData(true)
  }

  onAddEvent(){
    console.log("hello dear")
    this.addEvent.showDialog()
    
  }

  toDashboard()
  {
    this.router.navigate(['/dashboard'],{relativeTo:this.route})
  }
  onAddMeetingRoom(){
    this.addRoom.showDialog()
    
  }

  onSignout(){
    localStorage.removeItem('jwt');
    this.router.navigate(['/signin'],{relativeTo:this.route})
  }
}