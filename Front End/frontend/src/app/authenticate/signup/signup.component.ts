import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import {  ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
constructor( private route:ActivatedRoute,private router:Router){}

isSignedUp=false;


onRegister(form:NgForm){
  const name=form.value.name
  const email=form.value.email
  const password=form.value.password
  console.log("user is signed up ** users name : "+name+" email :"+email+" password : "+password)
  this.isSignedUp=true
  if(this.isSignedUp){
    this.router.navigate(['/signin'],{relativeTo:this.route})
  }

}

  onBackToSignin(){
    this.router.navigate(['/signin'],{relativeTo:this.route})
  }
}
