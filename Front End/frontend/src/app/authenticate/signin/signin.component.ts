import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { MessagesModule } from 'primeng/messages';
import { Message } from 'primeng/api';
@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css',
  standalone: true,
  imports: [MessagesModule,FormsModule]
})
export class SigninComponent implements OnInit{
  message: string = '';
  messages!: Message[] ;


  constructor(private route:ActivatedRoute,private router:Router,private authService:AuthService){}

  ngOnInit() {
   
}


  onRegister(){
    this.router.navigate(["/signup"],{relativeTo:this.route})
  }
  onSignin(form:NgForm){
    const email=form.value.email;
    const password=form.value.password;
    this.authService.login(email,password).subscribe(response =>{
      console.log("login success :"+response)
      this.message = response.message;
      localStorage.setItem('jwt',response.jwt)
      localStorage.setItem('employeeId',(response.myEmployee.id).toString())
      localStorage.setItem('empOfficeId',(response.myEmployee.officeId).toString())
      localStorage.setItem("empName",response.myEmployee.firstName+" "+response.myEmployee.lastName)
      localStorage.setItem("role",response.myEmployee.role)
      localStorage.setItem("officeName",response.officeName)
      localStorage.setItem("countryName",response.countryName)
      localStorage.setItem("govName",response.govName)

      console.log("jwt : "+localStorage.getItem('jwt'))
      this.router.navigate(["/dashboard"],{relativeTo:this.route})

    }, error => {
      console.error('Login failed:', error);
      if (error.error && error.error.message) {
        this.message = error.error.message; // Set the error message from response
        this.messages = [
          { severity: 'error', detail: this.message },
      ];
      
      } else {
        this.message = 'An error occurred during login. Please try again.';
      }
    }
    )



    console.log("user email is "+email+" and user password is "+password)
  }
}
