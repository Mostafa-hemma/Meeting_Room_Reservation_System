import { Component, ViewChild } from '@angular/core';
import { TableModule } from 'primeng/table';
import { SelectButtonModule } from 'primeng/selectbutton';
import { CommonModule } from '@angular/common';
import { EventModel } from '../shared/eventModel';
import { EventService } from '../shared/eventService';
import { ToolbarTemplateDemo } from "../toolbar-template-demo/toolbar-template-demo.component";
import { SidebarComponent } from "../sidebar/sidebar.component";
import { ToastModule } from 'primeng/toast';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ButtonModule } from 'primeng/button';
import { DialogBasicDemo } from '../calender/editpopup/editpopup.component';
import { EventImpl } from '@fullcalendar/core/internal';
import { SharedServiceService } from '../shared/shared-service.service';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
    selector: 'app-myevents',
    templateUrl: './myevents.component.html',
    styleUrl: './myevents.component.css',
    standalone: true,
    imports: [ButtonModule, ToastModule, ConfirmPopupModule,TableModule, SelectButtonModule, CommonModule, ToolbarTemplateDemo, SidebarComponent,DialogBasicDemo],
    providers: [ConfirmationService, MessageService]

  })
export class MyeventsComponent {
  eventsBackend!:EventModel[]
events!:EventModel[];
myEvents:any
 namee:string=""
message!:string
showMessage:boolean=false

@ViewChild('editEvent') editEvent!:DialogBasicDemo;

constructor(private sharedService:SharedServiceService,private confirmationService: ConfirmationService, private messageService: MessageService,private eventService:EventService,private sharedServie:SharedServiceService)
{

}
ngOnInit() {
  this.sharedService.messageSource.subscribe((value)=>{this.showMessage=value; 
    this.messageService.add({ severity: 'info', summary: "Confirmed", detail: "Event is Updated Successfully", life: 3000 });
  
  })

  this.eventService.eventsChanged.subscribe((events: EventModel[]) => {
    this.events = events;

});
  this.eventService.getEventsByEmpId(Number(localStorage.getItem("employeeId")));
  this.sharedServie.ToggleButton(true);
  // this.sharedServie.showMyButton=!this.sharedServie.showMyButton
  }


onEdit(eventModel:EventModel)
{
  const index = this.events.findIndex(event => Number(event.id) === Number(eventModel.id));
        if (index !== -1)
          {
     
            this.editEvent.eventDetails = (eventModel);
            console.log("event details",this.editEvent.eventDetails.description )
            this.editEvent.showDialog(String(index))
          }
}

ondelete(event:EventModel,eventt: Event){
  this.confirmationService.confirm({
    
    target: eventt.target as EventTarget,
    message: 'Do you want to delete this record?',
    icon: 'pi pi-info-circle',
    acceptButtonStyleClass: 'p-button-danger p-button-sm',
    accept: () => {
      this.eventService.deleteEvent(Number(event.id),(message) => {
       
        console.log("callback"+message); // Handle the returned message here
        this.messageService.add({ severity: 'info', summary: "Confirmed", detail: message, life: 3000 });
      });
    },
    reject: () => {
        this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected', life: 3000 });
    }
});
}
}
