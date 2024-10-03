import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { HttpClient } from "@angular/common/http";


interface Country {
  name: string;
  id: number;
}


@Injectable({
  providedIn: 'root'
})
export class SharedServiceService {

  countries!:Country[]

   dataSource = new BehaviorSubject<boolean>(false);
   buttonSource=new BehaviorSubject<boolean>(false);
   meetingSource=new BehaviorSubject<boolean>(false);
   messageSource=new BehaviorSubject<boolean>(false);
  changeData(value: boolean) {
    this.dataSource.next(value);
    console.log("inside service");
   
  }
  ToggleButton(value:boolean)
  {
    this.buttonSource.next(value)
  }

  ToggleMeeting(value:boolean)
  {
    this.meetingSource.next(value)
  }
  toggleMessage(value:boolean){
    this.messageSource.next(value)
  }
 
  constructor(private http:HttpClient){}

  getAllCountries():Observable<any[]>{
    return this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/country`).pipe(
      map(response=>response.data)
    )
  }
  getAllGovernmentsByCountryId(countryId:number):Observable<any[]>{
    return this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/government/country/${countryId}`).pipe(
      map(response=>response.data)
    )
  }
  getMeetingRoomsByOfficeId(officeId:number):Observable<any[]>{
    return this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/meeting-room/officeId/${officeId}`).pipe(
        map(response=>response.data)
    )
}
  getAllOfficesByGovId(govId:number):Observable<any[]>{
    return this.http.get<{message:string,data:any[]}>(`http://localhost:8030/api/office/government/${govId}`).pipe(
      map(response=>response.data)
    )
  }
}
