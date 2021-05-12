import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateUserComponent } from './create-user/create-user.component';
import { LoginComponent } from './login/login.component';
import { SearchByIdComponent } from './search-by-id/search-by-id.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserListComponent } from './user-list/user-list.component';

const routes: Routes = [
 
  {path:'login',component:LoginComponent},
  {path:'users',component: UserListComponent},
  {path:'create-user',component:CreateUserComponent},
  // {path:'',redirectTo:'users',pathMatch:'full'},
  {path:'', component: LoginComponent},
  {path:'update-user/:id', component:UpdateUserComponent},
  {path: 'user-details/:id', component: UserDetailsComponent},
  {path:'logout', component:LoginComponent},
  {path:'user-id/:id',component:SearchByIdComponent},
  {path:'user-id',component:SearchByIdComponent}
  


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
