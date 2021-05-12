import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user:User=new User();

  submitted=false;
  constructor(private userService: UserService, 
    private router: Router) { }
     private readonly emailRegex="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
     private readonly contactRegex="^[789]\\d{9}$";
  ngOnInit(): void {
  }

  newUser():void{
    this.submitted=false;
    this.user=new User();
  }
  saveUser(){
    this.userService.createUser(this.user).subscribe(data => {
      console.log(data);
      this.user= new User();
      this.goToUserList();
    },
    error => console.log(error));
    
    
    
  }
  goToUserList(){
    this.router.navigate(['/users']);

  }
  onSubmit (){
    console.log(this.user);
    this.submitted
    this.saveUser();
  
  }

  createUser = new FormGroup({
    fname: new FormControl('',[Validators.required, Validators.minLength(2), Validators.maxLength(30)]),
     lname: new FormControl('',[Validators.required, Validators.minLength(2), Validators.maxLength(30)]),
     email: new FormControl('',[Validators.required, Validators.pattern(this.emailRegex)]),
     contact: new FormControl('',[Validators.required, Validators.pattern(this.contactRegex)]),
     city: new FormControl('',[Validators.required, Validators.minLength(2), Validators.maxLength(30)])

  });
  isInvalidAndDirty(field: string): boolean{
    const ctrl = this.createUser.get(field);
    return ctrl !==null && !ctrl.valid && ctrl.dirty;
  }

  hasError(field: string, error: string): boolean{
    const ctrl = this.createUser.get(field);
    return ctrl !==null && ctrl.dirty && ctrl.hasError(error);
  }
}
