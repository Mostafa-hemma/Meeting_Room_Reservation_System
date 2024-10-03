import {  Component, Input, OnInit, ViewChild } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CalendarModule } from 'primeng/calendar';
import { Form, FormsModule, NgForm } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { EventService } from '../../shared/eventService';
import { EventModel } from '../../shared/eventModel';
import { DropdownModule } from 'primeng/dropdown';
import { Message, MessageService } from 'primeng/api';
import { MessagesModule } from 'primeng/messages';
import { CommonModule } from '@angular/common';
import { SharedServiceService } from '../../shared/shared-service.service';

interface meetingRoom {
  name: string;
  id: number;
}

@Component({
  selector: 'app-addevent',
  templateUrl: './addevent.component.html',
  standalone: true,
  imports: [DialogModule,MessagesModule,FormsModule,CalendarModule, ButtonModule,DropdownModule, InputTextModule,CommonModule]
})
export class AddeventComponent implements OnInit{

  @ViewChild('form') form!: NgForm;
  visible: boolean = false;
  meetingRooms: meetingRoom[] =[];
  newEvent!:EventModel;
  showMyButton!:boolean;

  message: string = '';
  messages!: Message[] ;
  constructor(private messageService:MessageService,private eventService:EventService,private sharedService:SharedServiceService){}
  ngOnInit(): void {
    this.sharedService.buttonSource.subscribe(value => this.showMyButton = value);

    // this.eventService.getMeetingRoomsByOfficeId(Number(localStorage.getItem('empOfficeId'))).subscribe(response => {
    //   response.forEach(meetingRoom => {this.meetingRooms.push(meetingRoom)});
    //   console.log(this.meetingRooms);
    // });
  }

  formatDate(date: string | Date): string {
    if (!date) return '';
    const d = new Date(date);
    const year = d.getFullYear();
    const month = (`0${d.getMonth() + 1}`).slice(-2); // Add leading zero
    const day = (`0${d.getDate()}`).slice(-2); // Add leading zero
    const hours = (`0${d.getHours()}`).slice(-2);
    const minutes = (`0${d.getMinutes()}`).slice(-2);
    return `${year}-${month}-${day}T${hours}:${minutes}`;
  }

  showDialog() {
          this.form.setValue({
            "meetingName":"",
            "meetingReserver":localStorage.getItem("empName"),
            "reserverCountry" : localStorage.getItem("countryName"),
            "reserverGov" :localStorage.getItem("govName"),
            "reserverOffice": localStorage.getItem("officeName"),
            "meetingStartDate":"",
            "meetingEndTime":"",
            "meetingRoom":"",
            "meetingDescription":""
          })
          this.meetingRooms=[]
          this.visible = true;
          this.eventService.getMeetingRoomsByOfficeId(Number(localStorage.getItem('empOfficeId'))).subscribe(response => {
            response.forEach(meetingRoom => {this.meetingRooms.push(meetingRoom)});
            console.log(this.meetingRooms);
          });
  }


  onSaveEvent(form:Form){
    const title=this.form.value.meetingName
    const startDate=this.form.value.meetingStartDate
    const[date, time] = startDate.split('T');
    const endTime=this.form.value.meetingEndTime
    const description=this.form.value.meetingDescription
    if(localStorage.getItem('role')!=='ROLE_JUNIOR'){
       this.newEvent=new EventModel(0,title,time,endTime,date,description,"approved"
        ,this.form.value.meetingRoom.id,Number(localStorage.getItem('employeeId')),{floor:this.form.value.meetingRoom.floor,
          status:this.form.value.meetingRoom.status
        })

    }else{
       this.newEvent=new EventModel(0,title,time,endTime,date,description,"pending"
        ,this.form.value.meetingRoom.id,Number(localStorage.getItem('employeeId')),{floor:this.form.value.meetingRoom.floor,
          status:this.form.value.meetingRoom.status
        })
    }

    this.eventService.addEvent(this.newEvent).subscribe(
      response => {
        console.log('Event created:', response.message);
        this.eventService.events.push(response.data[0]);
        if(this.showMyButton){
          this.eventService.eventsChanged.next(this.eventService.events.slice());

          
        }else{
          if(response.data[0].status==='approved'||response.data[0].status==='pending'){
            this.eventService.eventsChanged.next(this.eventService.events.slice());

          }
        }
       
        this.form.reset()
    
        this.visible=false
        this.messageService.add({ severity: 'info', summary: "Confirmed", detail: response.message, life: 3000 });

   
      },
      error => 
      {
        console.error('add event failed:', error);
        if (error.error && error.error.message) {
          this.message = error.error.message; // Set the error message from response
          this.messages = [
            { severity: 'error', detail: this.message },
        ];        
        
        } else {
          console.error('Error creating event:', error);
        }
      }
    );
   
  }

}



