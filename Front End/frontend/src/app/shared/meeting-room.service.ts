import { Injectable } from '@angular/core';
import { meetingRoomModel } from './meetingRoomModel';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MeetingRoomService {

  meetingRooms:meetingRoomModel[]=[]
  meetingRoomChanged=new BehaviorSubject<meetingRoomModel[]>([]);

  constructor(private http:HttpClient) { }

  getMeetingRoomsByEmpId(empId:number):void{
    this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/meeting-room`).pipe(
      map(response=>response.data)
        ).subscribe(
          meetingRooms=>{
            this.meetingRooms=meetingRooms
            this.meetingRoomChanged.next(this.meetingRooms.slice())
          }
        )
  }

  updateMeetingRoom(updatedMeetingRoom:meetingRoomModel):Observable<any>{
    return this.http.put<{message:string,data:any[]}>(`http://localhost:8030/api/meeting-room`,updatedMeetingRoom).pipe(
      map(response=>response)
    )
  }

  getHrEmployees():Observable<any[]>{
    return this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/employees/hrrole`).pipe(
      map(response=>response.data)
    )
  }
  addNewMeetingRoom(newMeetingRoom:meetingRoomModel):Observable<any>{
    return this.http.post<{message:string,data:any[]}>(`http://localhost:8030/api/meeting-room`,newMeetingRoom).pipe(
      map(response=>response)
    )
  }
  deleteMeetingRoom(roomId:number,callback:(message:string)=>void):void{
    const index = this.meetingRooms.findIndex(meetingRoomModel => Number(meetingRoomModel.id) === Number(roomId));
    if (index !== -1) {
    this.http.delete<{message:string,data:any[]}>(`http://localhost:8030/api/meeting-room/${roomId}`).pipe(
      map(response=>response)
    ).subscribe(
      response=>{
        console.log("sekas :"+response.message)
        callback(response.message)
      }
    )
    this.meetingRooms.splice(index,1)
    this.meetingRoomChanged.next(this.meetingRooms.slice())


    


  }

}}
