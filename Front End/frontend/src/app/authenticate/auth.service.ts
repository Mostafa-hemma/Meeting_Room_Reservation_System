import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


/*
{
    "message": "success",
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb3NheWVkQGVqYWRhLmNvbSIsImV4cCI6MTcxODAwNjcyMCwiaWF0IjoxNzE4MDA2NTQwfQ.QRsmFAxEMZ5tDEVY4IpzoZOYTr_egtWos54YRpa7bdw",
    "myEmployee": {
        "id": 2,
        "firstName": "Mohamed",
        "lastName": "Sayed",
        "email": "mosayed@ejada.com",
        "password": "****",
        "site": "Giza",
        "role": "ROLE_JONUR"
    }
}
*/
interface LoginResponse{
  message:string;
  jwt:string;
  myEmployee:{
    id:number;
    firstName:string;
    lastName:string;
    email:string;
    password:string;
    site:string;
    role:string;
    officeId:number
  }
  officeName:string;
  countryName:string;
  govName:string;
}


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url='http://localhost:8030';
  constructor(private http:HttpClient) { }

  login(email:string,password:string):Observable<LoginResponse>{
    return this.http.post<LoginResponse>(`${this.url}/authenticate`,{email,password});
  }

  getJwtToken(){
   return localStorage.getItem('jwt')
  }

}
