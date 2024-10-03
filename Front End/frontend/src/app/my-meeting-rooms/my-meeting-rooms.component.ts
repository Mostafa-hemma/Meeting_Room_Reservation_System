import { Component, OnInit, ViewChild, viewChild } from '@angular/core';
import { TableModule } from 'primeng/table';
import { SelectButtonModule } from 'primeng/selectbutton';
import { CommonModule } from '@angular/common';
import { EventModel } from '../shared/eventModel';
import { EventService } from '../shared/eventService';
import { ToolbarTemplateDemo } from "../toolbar-template-demo/toolbar-template-demo.component";
import { SidebarComponent } from "../sidebar/sidebar.component";
import { ConfirmationService, MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ButtonModule } from 'primeng/button';
import { DialogBasicDemo } from '../calender/editpopup/editpopup.component';
import { MeetingRoomService } from '../shared/meeting-room.service';
import { meetingRoomModel } from '../shared/meetingRoomModel';
import { EditMeetingRoomComponent } from './edit-meeting-room/edit-meeting-room.component';
import { SharedServiceService } from '../shared/shared-service.service';
@Component({
  selector: 'app-my-meeting-rooms',
  templateUrl: './my-meeting-rooms.component.html',
  styleUrl: './my-meeting-rooms.component.css',
  standalone: true,
  imports: [ButtonModule, ToastModule, ConfirmPopupModule,TableModule, SelectButtonModule, CommonModule, ToolbarTemplateDemo, SidebarComponent,EditMeetingRoomComponent],
  providers: [ConfirmationService, MessageService]

})
export class MyMeetingRoomsComponent implements OnInit{

  @ViewChild('editMettingRoom') editMettingRoom!:EditMeetingRoomComponent;
  showMessage:boolean=false


  meetingRooms:meetingRoomModel[]=[]
  constructor(private sharedService:SharedServiceService,private confirmationService: ConfirmationService, private messageService: MessageService,private meetingRoomService:MeetingRoomService){}
  empId=localStorage.getItem('employeeId')
  ngOnInit(): void {
    this.sharedService.messageSource.subscribe((value)=>{this.showMessage=value; 
      this.messageService.add({ severity: 'info', summary: "Confirmed", detail: "Room Status is Updated Successfully", life: 3000 });
    
    })
    
    this.meetingRoomService.getMeetingRoomsByEmpId(Number(this.empId))
    this.meetingRoomService.meetingRoomChanged.subscribe(
      (meetingRooms:meetingRoomModel[])=>{
        this.meetingRooms=meetingRooms
      }
    )

  }

  onEdit(meetingRoom:meetingRoomModel){

      const index = this.meetingRooms.findIndex(meetingRoomModel => Number(meetingRoomModel.id) === Number(meetingRoom.id));
      console.log(index)
      console.log(meetingRoom.id)
      if (index !== -1)
        {
   
          this.editMettingRoom.meetingRoom=meetingRoom
          this.editMettingRoom.showDialog(String(index))
          
          // this.editEvent.showDialog(String(index))
        }
   
    
  }


  onDelete(meetingRoom:meetingRoomModel,event:Event){
    this.confirmationService.confirm({
    
      target: event.target as EventTarget,
      message: 'Do you want to delete this Meeting Room?',
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass: 'p-button-danger p-button-sm',
      accept: () => {
        this.meetingRoomService.deleteMeetingRoom(meetingRoom.id,(message)=>{
          this.messageService.add({ severity: 'info', summary: "Confirmed", detail: message, life: 3000 });

        });

      },
      reject: () => {
          this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You did not delete this meeting room', life: 3000 });
      }
  });
  }

}
