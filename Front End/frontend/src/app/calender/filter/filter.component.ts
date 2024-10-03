import { Component, OnInit, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { EventService } from '../../shared/eventService';
import { SharedServiceService } from '../../shared/shared-service.service';
import { CommonModule } from '@angular/common';

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
interface MeetingRoom {
  floor: string;
  id: number;
}

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css',
  standalone: true,
  imports: [FormsModule,ButtonModule,CommonModule, DropdownModule]
})
export class FilterComponent implements OnInit{
  @ViewChild('form') form!: NgForm;

  countries: Country[] | undefined;
  selectedCountry: Country | undefined;
  governments: Government[] | undefined;
  selectedGovernment: Government | undefined;
  showGovernmentDropdown: boolean = false;

  offices: Office[] | undefined;
  selectedOffice: Office | undefined;
  showOfficeDropdown: boolean = false;

  meetingRooms: MeetingRoom[] | undefined;
  selectedMeetingRoom: MeetingRoom | undefined;
  showMeetingRoomDropdown: boolean = false;
    constructor(private eventServie:EventService,private sharedService:SharedServiceService){}

    ngOnInit() {
      this.sharedService.getAllCountries().subscribe(
        countries=>{
          this.countries=countries
          console.log(this.countries.length)
        }
      )
     
     
    }
    onReset(){
   
      this.form.resetForm()
      this.offices=undefined
      this.governments = undefined;
       this.showGovernmentDropdown=false
       this.showOfficeDropdown=false
       this.showMeetingRoomDropdown=false
    }
   
    onCountryChange(event: any) {
      this.governments=[]
      this.meetingRooms=[]
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
      this.meetingRooms=[]
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

    onOfficeChange(event:any){
      this.meetingRooms=[]

      if(this.selectedOffice){
        this.sharedService.getMeetingRoomsByOfficeId(this.selectedOffice.id).subscribe(meetingRooms=>{
          this.meetingRooms=meetingRooms
          console.log("wekooooo "+this.meetingRooms[1].floor)
          this.showMeetingRoomDropdown=true

        })
      }else{
        this.meetingRooms=undefined
        this.showMeetingRoomDropdown=true
      }
    }


    onSearch(form:NgForm){

      if(form.controls['selectedMeetingRoom'] &&form.controls['selectedMeetingRoom'].value){
        this.eventServie.getEventsByMeetingRoomId(form.controls['selectedMeetingRoom'].value.id)
        this.eventServie.eventsChanged.next(this.eventServie.events.slice());
      }
      else if(form.controls['selectedOffice'] &&form.controls['selectedOffice'].value){
        this.eventServie.getEventsByOfficeId(form.controls['selectedOffice'].value.id)
        this.eventServie.eventsChanged.next(this.eventServie.events.slice());
      }
      else if(form.controls['selectedGovernment'] && form.controls['selectedGovernment'].value){
        this.eventServie.getEventsByGovernmentId(form.controls['selectedGovernment'].value.id)
        this.eventServie.eventsChanged.next(this.eventServie.events.slice());
     
      }else if(form.controls['selectedCountry'] && form.controls['selectedCountry'].value){
        this.eventServie.getEventsByCountry(form.controls['selectedCountry'].value.id)
        this.eventServie.eventsChanged.next(this.eventServie.events.slice());
      }else{
        this.eventServie.getApprovedEvents()
        this.eventServie.eventsChanged.next(this.eventServie.events.slice());
      }
     

    }

}
