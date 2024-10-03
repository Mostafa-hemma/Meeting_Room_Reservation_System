import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CalendarModule } from 'primeng/calendar';
import { Form, FormsModule, NgForm } from '@angular/forms';
import { EventService } from '../../shared/eventService';
import { EventModel } from '../../shared/eventModel';
import { CommonModule } from '@angular/common';
import { SharedServiceService } from '../../shared/shared-service.service';
import { MessagesModule } from 'primeng/messages';
import { Message } from 'primeng/api';
import { DropdownModule } from 'primeng/dropdown';
import { meetingRoomModel } from '../../shared/meetingRoomModel';
import { MeetingRoomService } from '../../shared/meeting-room.service';


interface MeetingRoomStatus{
  status:string
}


@Component({
  selector: 'app-edit-meeting-room',
  templateUrl: './edit-meeting-room.component.html',
  styleUrl: './edit-meeting-room.component.css',
  standalone: true,
    imports: [FormsModule,DialogModule,MessagesModule,DropdownModule,FormsModule,CalendarModule, ButtonModule,CommonModule, InputTextModule]
})
export class EditMeetingRoomComponent implements OnInit{
  @ViewChild('form') form!: NgForm;
  updatedMeetingRoom!:meetingRoomModel
  meetingRoomStatuses!:MeetingRoomStatus[]
  selectedStatus!:string
  messages!: Message[] ;
  showMessage:boolean=false
  ngOnInit(): void {
  this.meetingRoomStatuses=[{status:"closed"},{status:"opened"}]
  }
  visible: boolean = false;
  @Input()meetingRoom!:meetingRoomModel;
  index!:string
  constructor(private sharedService:SharedServiceService,private meetingRoomService:MeetingRoomService){}

  showDialog(index:string){
    this.messages=[]
    this.index=index
    this.selectedStatus=this.meetingRoom.status
    this.form.setValue({
      "MeetingRoomFloor":this.meetingRoom.floor,
      "meetingRoomOffice":this.meetingRoom.officeName,
      "meetingRoomStatus":this.selectedStatus
    })
    this.visible=true
  }


  onSaveEdit(form:NgForm){
    console.log("wekooooo "+this.form.controls['meetingRoomStatus'].value.status+"      "+this.meetingRoom.id)
    this.updatedMeetingRoom=new meetingRoomModel(this.meetingRoom.id,
      this.meetingRoom.floor,
      this.form.controls['meetingRoomStatus'].value.status,
      this.meetingRoom.employeeId,
      this.meetingRoom.officeId,
      this.meetingRoom.officeName
    )
    console.log(this.index)
    console.log(this.meetingRoom.id)
    this.meetingRoomService.updateMeetingRoom(this.updatedMeetingRoom).subscribe(response=>{
      if(response.data.length!==0){
        this.meetingRoomService.meetingRooms[Number(this.index)]=response.data[0]
        this.meetingRoomService.meetingRooms[Number(this.index)].officeName=this.meetingRoom.officeName
        this.meetingRoomService.meetingRoomChanged.next(this.meetingRoomService.meetingRooms.slice())
        this.visible=false
        this.sharedService.toggleMessage(true)
      }else{
        this.messages = [
      
          { severity: 'error', detail: response.message },
  
      ];
      }
   
      console.log(response.message)
    }
      
    )
  }

}
