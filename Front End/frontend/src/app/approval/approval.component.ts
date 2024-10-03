import { Component, OnInit } from '@angular/core';
import { EventModel } from '../shared/eventModel';
import { EventService } from '../shared/eventService';
import { ToastModule } from 'primeng/toast';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ConfirmationService, MessageService } from 'primeng/api';

import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { SelectButtonModule } from 'primeng/selectbutton';
import { CommonModule } from '@angular/common';
import { ToolbarTemplateDemo } from '../toolbar-template-demo/toolbar-template-demo.component';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { DialogBasicDemo } from '../calender/editpopup/editpopup.component';
 
 
@Component({
  selector: 'app-approval',
  templateUrl: './approval.component.html',
  styleUrl: './approval.component.css',
  standalone: true,
  imports: [ButtonModule, ToastModule, ConfirmPopupModule,TableModule, SelectButtonModule, CommonModule, ToolbarTemplateDemo, SidebarComponent,DialogBasicDemo],
  providers: [ConfirmationService, MessageService]
})
export class ApprovalComponent implements OnInit {
  pendingEvents:EventModel[]=[];
  constructor(private confirmationService: ConfirmationService, private messageService: MessageService,private eventService:EventService)
  {}
  ngOnInit(): void {
        this.eventService.getPendingEventsForEmpId(Number(localStorage.getItem("employeeId")));

    this.eventService.eventsChanged.subscribe((events:EventModel[])=>{
      this.eventService.pendingEvents=events;
      this.pendingEvents=this.eventService.pendingEvents
      if (this.eventService.pendingEvents && this.eventService.pendingEvents.length > 0 && this.eventService.pendingEvents[0].employeeDto) {
        console.log("eeeeeeeeeeeeeeeeeeeeeeeee events: " + this.eventService.pendingEvents[0].employeeDto.firstName);
      } else {
        console.log("Pending events are empty or employeeDto is undefined");
        this.eventService.pendingEvents=[]
      }
    })

  }
 
 
  onApprove(eventModel:EventModel,event:Event)
  {

    this.confirmationService.confirm({
    
      target: event.target as EventTarget,
      message: 'Do you want to approve this event?',
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass: 'p-button-submit p-button-sm',
      rejectButtonStyleClass:'p-button-danger p-button-sm',
      accept: () => {
        eventModel.status="approved"
        this.eventService.editEventStatus(eventModel,Number(localStorage.getItem("employeeId")))
        
        this.messageService.add({ severity: 'info', summary: "Confirmed", detail: "you approved this event", life: 3000 });

      },
      reject: () => {
          this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'you did not approve this event', life: 3000 });
      }
  });  }
  onDecline(eventModel:EventModel,event:Event)
  {    this.confirmationService.confirm({
    
      target: event.target as EventTarget,
      message: 'Do you want to decline this event?',
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass: 'p-button-danger p-button-sm',
      accept: () => {
        eventModel.status="declined"
        this.eventService.editEventStatus(eventModel,Number(localStorage.getItem("employeeId")))

        this.messageService.add({ severity: 'info', summary: "Confirmed", detail: "you declined this event", life: 3000 });

      },
      reject: () => {
          this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'you did not decline this event', life: 3000 });
      }
  });

    }
}