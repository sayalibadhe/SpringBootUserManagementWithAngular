import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-search-by-id',
  templateUrl: './search-by-id.component.html',
  styleUrls: ['./search-by-id.component.css']
})
export class SearchByIdComponent implements OnInit {

  id:any=0;
  n:number;
  c:number;
  b:boolean=true;
  a:boolean=false;
  n1:number=-1;
  n2:number=1;
  constructor(private userService:UserService,private route:ActivatedRoute,private router:Router) {
    this.id=route.snapshot.paramMap.get("id");
    console.log(this.id);
  }
   users: User=new User();
  ngOnInit(): void {
    this.getUserById();
    this.n=this.id;
  }
  getValue(n:number){
    this.router.navigate(['user-id',n]);
  }
 getUserById(){
   this.userService.getUserById(this.id).subscribe(data=>{
     this.users=data;
     this.b=false;
    },
   (error)=>{
     if(this.id==null){
       this.a=false;
     }
     else{
       this.a=true;
     }
   }
);
}
}