import { Component, OnInit, ViewChild } from '@angular/core';

import { ButtonModule } from 'primeng/button';

import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { SelectButtonModule } from 'primeng/selectbutton';
import { ToolbarTemplateDemo } from '../../toolbar-template-demo/toolbar-template-demo.component';
import { SidebarComponent } from '../../sidebar/sidebar.component';
import { FormsModule, NgForm } from '@angular/forms';
import { EventService } from '../../shared/eventService';
import { SharedServiceService } from '../../shared/shared-service.service';
import { DropdownModule } from 'primeng/dropdown';
import { MultiSelectModule } from 'primeng/multiselect';

import { DialogModule } from 'primeng/dialog';
import { MeetingRoomService } from '../../shared/meeting-room.service';
import { meetingRoomModel } from '../../shared/meetingRoomModel';
import { MessageService } from 'primeng/api';
interface employee{
  id:number,
  email:string;

}
interface Country {
  name: string;
  id: number;
}
interface Government {
  name: string;
  id: number;
}
interface Office {
  name: string;
  id: number;
}
interface MeetingRoomStatus{
  status:string
}

@Component({
  selector: 'app-add-room',
  templateUrl: './add-room.component.html',
  styleUrl: './add-room.component.css',
  standalone: true,
  imports: [ButtonModule,DialogModule,FormsModule,MultiSelectModule,TableModule,DropdownModule, SelectButtonModule, CommonModule, ToolbarTemplateDemo]

})
export class AddRoomComponent implements OnInit {
  newMeetingRoom!:meetingRoomModel
  newMeetingRoomFloor!:string
  newMeetingRoomStatus!:string
  newMeetingRoomEmployeesId!:number[]
  newMeetingRoomOfficeId!:number
  newMeetingRoomOfficeName!:string


  @ViewChild('form') form!: NgForm;

  visible:boolean=false
  employeeIds :Number[]=[]
  countries: Country[] | undefined;
  selectedCountry: Country | undefined;
  governments: Government[] | undefined;
  selectedGovernment: Government | undefined;
  showGovernmentDropdown: boolean = false;

  offices: Office[] | undefined;
  selectedOffice: Office | undefined;
  showOfficeDropdown: boolean = false;


  meetingRoomStatuses!:MeetingRoomStatus[]
  selectedStatus!:MeetingRoomStatus

  employees:employee[]|undefined
  selectedEmployees!:employee[]

  constructor(private messageService:MessageService,private meetingRoomService:MeetingRoomService,private eventServie:EventService,private sharedService:SharedServiceService){}

  ngOnInit() {
    this.meetingRoomStatuses=[{status:"closed"},{status:"opened"}]
    this.sharedService.getAllCountries().subscribe(
      countries=>{
        this.countries=countries
        console.log(this.countries.length)
      }
    )
    this.meetingRoomService.getHrEmployees().subscribe(
      employees=>{
        this.employees=employees
      }
    )
   
   
  }

  onCountryChange(event: any) {
    this.governments=[]
    this.offices=[]
    if (this.selectedCountry) {
      this.sharedService.getAllGovernmentsByCountryId(this.selectedCountry.id).subscribe(governments => {
        this.governments = governments;
        this.showGovernmentDropdown = true;
      });
    } else {
      this.governments = undefined;
      this.showGovernmentDropdown = false;
    }
  }

  onGovChange(event:any){
    this.offices=[]
    if(this.selectedGovernment){
      this.sharedService.getAllOfficesByGovId(this.selectedGovernment.id).subscribe(offices=>{
        this.offices=offices
        this.showOfficeDropdown=true

      })
    }else{
      this.offices=undefined
      this.showOfficeDropdown=false
    }
  }


  showDialog(){
    this.visible=true

  }






  onSaveRoom(form:NgForm){
    this.newMeetingRoomFloor=this.form.value['meetingFloor']
    this.newMeetingRoomStatus=this.selectedStatus.status
    this.newMeetingRoomEmployeesId=this.form.value['selectedEmployees'].map((employee:employee)=>employee.id)
    this.newMeetingRoomOfficeId=this.form.value['selectedOffice'].id
    this.newMeetingRoomOfficeName=this.form.value['selectedOffice'].name

    this.newMeetingRoom=new meetingRoomModel(0,
      this.newMeetingRoomFloor,
      this.newMeetingRoomStatus,
      this.newMeetingRoomEmployeesId,
      this.newMeetingRoomOfficeId,
      this.newMeetingRoomOfficeName
    )
    this.meetingRoomService.addNewMeetingRoom(this.newMeetingRoom).subscribe(response=>{
      this.meetingRoomService.meetingRooms.push(response.data[0])
      this.meetingRoomService.meetingRoomChanged.next(this.meetingRoomService.meetingRooms.slice())
      this.visible=false

      this.messageService.add({ severity: 'info', summary: "Confirmed", detail: response.message, life: 3000 });

    })
    this.form.reset()

  }
}
