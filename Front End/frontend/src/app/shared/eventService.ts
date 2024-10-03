import { BehaviorSubject, Observable, Subject, map } from "rxjs";
import { EventModel } from "./eventModel";
import { state } from "@angular/animations";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";



@Injectable()
export class EventService{

    constructor(private http:HttpClient){}
    private apiUrl='http://localhost:8030/api/events'
   
        deletionMessage!:string
     events:EventModel[]=[    ]
     pendingEvents:EventModel[]=[];
    eventsChanged = new BehaviorSubject<EventModel[]>([]);

    getEventLength():string{
        return (this.events.length).toString();
    }

    getEvents():void{
         this.http.get<{message:string,data:any[]}>(this.apiUrl).pipe(
            
            map(response=>response.data)
            ).subscribe(
                events => {
                    this.events = events;
                    this.eventsChanged.next(this.events.slice());
                    console.log(events)
                },
                error => {
                    console.error('Error fetching events:', error);
                }
            );
    }
    getApprovedEvents():void{
        this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/events/approved`).pipe(
           
           map(response=>response.data)
           ).subscribe(
               events => {
                   this.events = events;
                   this.eventsChanged.next(this.events.slice());
                   console.log(events)
               },
               error => {
                   console.error('Error fetching events:', error);
               }
           );
   }
    getMeetingRoomsByOfficeId(officeId:number):Observable<any[]>{
        return this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/meeting-room/officeId/${officeId}`).pipe(
            map(response=>response.data)
        )
    }

    getMeetingRoomsById(id:number):Observable<string>{
        return this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/meeting-room/${id}`).pipe(
            map(response=>response.data[0].floor)
        )
    }
    getEventsByGovernmentId(GovernmentId:number):void{
    
        this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/events/government/${GovernmentId}`).pipe(
            map(response=>response.data)
        ) .subscribe(
            events => {
                this.events = events;
                this.eventsChanged.next(this.events.slice());
                console.log(events)
            },
            error => {
                console.error('Error fetching events:', error);
            }
        );
     
    }
    getEventsByOfficeId(officeId:number):void{
    
        this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/events/office/${officeId}`).pipe(
            map(response=>response.data)
        ) .subscribe(
            events => {
                this.events = events;
                this.eventsChanged.next(this.events.slice());
                console.log(events)
            },
            error => {
                console.error('Error fetching events:', error);
            }
        );
     
    }

    getEventsByCountry(countryId:number):void{
    
        this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/events/country/${countryId}`).pipe(
            map(response=>response.data)
        ) .subscribe(
            events => {
                this.events = events;
                this.eventsChanged.next(this.events.slice());
                console.log(events)
            },
            error => {
                console.error('Error fetching events:', error);
            }
        );
     
    }
    getEventsByMeetingRoomId(meetingRoomId:number):void{
    
        this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/events/meetingroom/${meetingRoomId}`).pipe(
            map(response=>response.data)
        ) .subscribe(
            events => {
                this.events = events;
                this.eventsChanged.next(this.events.slice());
                console.log(events)
            },
            error => {
                console.error('Error fetching events:', error);
            }
        );
     
    }
   
    getEventsByEmpId(empId:number):void{
         this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/events/empId/${empId}`).pipe(
            map(response=>response.data)
        ) .subscribe(
            events => {
                this.events = events;
                this.eventsChanged.next(this.events.slice());
                console.log(events)
            },
            error => {
                console.error('Error fetching events:', error);
            }
        );
    }

    getPendingEventsForEmpId(empId:number):void{
         this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/events/pending/${empId}`).pipe(
            map(response=>response.data)
        )
        .subscribe(
            events => {
                this.pendingEvents = events;
                this.eventsChanged.next(this.pendingEvents.slice());
            },
            error => {
                console.error('Error fetching events:', error);
            } 
        )
    }
    editEventStatus(eventModel:EventModel,empId:number):void{
        this.http.put<{message:string,data:any[]}>(`http://localhost:8030/api/events`,eventModel).pipe(
            map(response=>response)
        ).subscribe(
            
           event=>{

            this.getPendingEventsForEmpId(empId);

            this.eventsChanged.next(this.pendingEvents.slice());

            console.log(event.message)
           } 
        )
    }


    getEventData(meetingRoomId:number,employeeId:number):Observable<{meetingRoomName:string,
        employeeName:string,
        officeName:string,
        govName:string,
        countryName:string
    }>{
        return this.http.get<{meetingRoomName:string,
            employeeName:string,
            officeName:string,
            govName:string,
            countryName:string
        }>
        
        (`http://localhost:8030/api/meeting-room/getname/${meetingRoomId}/${employeeId}`)
    }
    addEvent(event:EventModel):Observable<{message:string,data:any[]}>{
        return this.http.post<{message:string,data:any[]}>(`http://localhost:8030/api/events`,event).pipe(
            map(response =>response )
        );
    }
    updateEvent(event:EventModel):Observable<{message:string,data:any[]}>{

        return this.http.put<{message:string,data:any[]}>(`http://localhost:8030/api/events`,event).pipe(
            map(response =>response )
        )

    }
    deleteEvent(id:number, callback: (message: string) => void):void
    {
        const index = this.events.findIndex(event => Number(event.id) === id);
        if (index !== -1) {
        this.http.delete<{message:string}>(`http://localhost:8030/api/events/${id}`).pipe(
            map(response =>response.message )
        ).subscribe(message=>{
            console.log("service +"+message)
            callback(message)            
            console.log("service +"+ this.deletionMessage)

            })

        this.events.splice(index,1);
        this.eventsChanged.next(this.events.slice());
        console.log("event service deletionMessage "+this.deletionMessage)

        }else{
            callback("Event not found");

        }
    }
}