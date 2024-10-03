import {  Component, Input, OnInit, ViewChild } from '@angular/core';
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

interface meetingRoom {
  name: string;
  id: number;
}

@Component({
    selector: 'dialog-basic-demo',
    templateUrl: './editpopup.component.html',
    standalone: true,
    imports: [DialogModule,MessagesModule,DropdownModule,FormsModule,CalendarModule, ButtonModule,CommonModule, InputTextModule]
})
export class DialogBasicDemo implements OnInit{
   
    @Input() eventDetails: any;
    meetingRooms: meetingRoom[] =[];
    
    message: string = '';
    messages!: Message[] ;

    @ViewChild('form') form!: NgForm;
    constructor(private eventService:EventService,private sharedService:SharedServiceService){}

    id:string='';
    title: string = '';
    description: string = '';
    status: string = '';
    startDate:string ='';
    endDate:string='';
    date!: String ;
     updatedEvent!:EventModel
    meetingRoooomId:any
    employeeIddd:any

    meetingRoomName!:string;
    showMyButton=false;
    meetingReserver!:string
    holdingEvent:EventModel | undefined
    ngOnInit(): void {
      this.sharedService.buttonSource.subscribe(value => this.showMyButton = value);

      }
   

    visible: boolean = false;

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

    formatTime(date: string | Date): string {
      if (!date) return '';
      const d = new Date(date);
      
      const hours = (`0${d.getHours()}`).slice(-2);
      const minutes = (`0${d.getMinutes()}`).slice(-2);
      
      return `${hours}:${minutes}`;
    }

    showDialog(id:string) {
       if (this.eventDetails ) {
        this.meetingRooms=[]
        this.eventService.getMeetingRoomsByOfficeId(Number(localStorage.getItem('empOfficeId'))).subscribe(response => {
          response.forEach(meetingRoom => {this.meetingRooms.push(meetingRoom)});
          console.log("sayed"+this.meetingRooms[0].name);
        });
        console.log("event details in show dialog "+this.eventDetails.description)
            this.id=id
            this.title =this.eventDetails.name ||this.eventDetails.title || "";
            this.description = this.eventDetails.description ||this.eventDetails.extendedProps.description || '';
            this.status=this.eventDetails.status || this.eventDetails.extendedProps.status ||'';
            this.startDate = this.formatDate(this.eventDetails.startDate)||this.formatDate(this.eventDetails.start) ||  this.formatDate(this.eventDetails.date+'T'+this.eventDetails.startTime) ||'';
            console.log("this.eventDetails.endDate",this.eventDetails.end)
            this.endDate=this.eventDetails.endTime ||this.formatTime(this.eventDetails.endTime)|| this.formatTime(this.eventDetails.end)|| '';
   
            this.meetingRoooomId=this.eventDetails.meetingRoomId || this.eventDetails.extendedProps.meetingRoomId
            this.employeeIddd=this.eventDetails.employeeId || this.eventDetails.extendedProps.employeeId
            console.log("wwwwwwwwwwwwwwwwwwwwwwwwwwww "+this.employeeIddd)
            this.eventService.getEventData( this.meetingRoooomId,
              this.employeeIddd
            ).subscribe(
              (response)=> {

                this.meetingReserver=response.employeeName
                this.meetingRoomName=response.meetingRoomName

                if(this.showMyButton){
                  
                  this.form.setValue({
                    "meetingName":this.title,
                    "meetingDescription":this.description,
                    "meetingStartDate":this.startDate,
                    "meetingEndDate":this.endDate,
                    "meetingStatus":this.status,
                    "meetingReserver":this.meetingReserver,
                    "reserverCountry" : localStorage.getItem("countryName"),
                    "reserverGov" :localStorage.getItem("govName"),
                    "reserverOffice": localStorage.getItem("officeName"),
                    "meetingRoom":this.meetingRoomName
                })
                }else{
                  this.form.setValue({
                    "meetingName":this.title,
                    "meetingDescription":this.description,
                    "meetingStartDate":this.startDate,
                    "meetingEndDate":this.endDate,
                    "meetingStatus":this.status,
                    "meetingReserver":this.meetingReserver,
                    "reserverCountry" : response.countryName,
                    "reserverGov" :response.govName,
                    "reserverOffice":response.officeName,
                    "meetingRoom":response.meetingRoomName
                })
                }
              
              this.visible = true;
              }
            )
        

     
          }
    }
    onSaveEdit(form:Form){
      const value=this.form.value
      const[date, startTime] = value['meetingStartDate'].split('T');
      console.log("sswwwwwwwwww "+this.meetingRoooomId)
      if(localStorage.getItem("role")!=="ROLE_JUNIOR"){
        this.updatedEvent=new EventModel(Number(this.eventDetails.id),
        value['meetingName'],
        startTime,
        value['meetingEndDate'],
        date,
        value['meetingDescription'],
        "approved",
        
        this.form.value.meetingRoom.id,
       this.employeeIddd,
      );
      }else{
        this.updatedEvent=new EventModel(Number(this.eventDetails.id),
        value['meetingName'],
        startTime,
        value['meetingEndDate'],
        date,
        value['meetingDescription'],
        "pending",
        
        this.form.value.meetingRoom.id,
       this.employeeIddd,
      );
      }
    


     this.eventService.updateEvent(this.updatedEvent).subscribe(response=>{
      this.message=response.message
      console.log("*************************"+ this.form.value.meetingRoom.name)
      this.holdingEvent=this.eventService.events[Number(this.id)]
      this.eventService.events[Number(this.id)]=(response.data[0]);
      this.eventService.eventsChanged.next(this.eventService.events.slice());
      this.visible=false
      this.sharedService.messageSource.next(true)

     }, error => 
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
    )
    }
}