import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../login/auth.service';
import { MenuItem } from '../menu-item';
import { UserService } from '../user.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit  {
 
  constructor(private userService: UserService,
    private router: Router ,private authenticationService: AuthService ) { }
  

  ngOnInit(): void {
    
}
  addUser(){
    this.router.navigate(['create-user']);

  }

  userList(){
    this.router.navigate(['users']);
  }

  userLogout(){
    this.router.navigate(['login']);
    
  }
  getuserbyid(){
    this.router.navigate(['user-id']);
  }


}





  // menuItems: MenuItem[] =[
  //   {
  //     label:'User List',
  //     icon:'slideshow',
  //     showOnMobile: true,
  //     showOnTablet: true,
  //     showOnDesktop: true,

      
  //   },
  //   {
  //   label:'Add User',
  //   icon:'login',
  //   showOnMobile: false,
  //     showOnTablet: false,
  //     showOnDesktop: true

  // },
     
  //    {
  //      label:'Search by Id',
  //      icon:'rss_feed',
  //      showOnMobile: false,
  //     showOnTablet: false,
  //     showOnDesktop: true

  //    }
  // ];
  
