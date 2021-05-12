import { Component, OnInit, ViewChild } from '@angular/core';

import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  page = 10;
  count = 0;
  tableSize = 5;
  tableSizes = [3, 6, 9, 12];
  fname:any;
  users : User[]
  constructor(private userService: UserService,
     private router: Router ) { }

 
  ngOnInit(): void {
    
    this.getAllUsers();
    
  }

  private getAllUsers(){
    this.userService.getUserList().subscribe(data =>{
      this.users=data;

    });
  }
  updateUser(id: number){
    this.router.navigate(['update-user',id]);

  }

  deleteUser(id: number){
    this.userService.deleteUser(id).subscribe(data =>{
      console.log(data);
      this.getAllUsers();
    })
  }

  detailsUser(id: number){
    this.router.navigate(['user-details',id]);
  }
  onTableDataChange(event){
    this.page = event;
    this.getAllUsers();
  }  



  









  onTableSizeChange(event): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.getAllUsers();
  } 
  
  
  }