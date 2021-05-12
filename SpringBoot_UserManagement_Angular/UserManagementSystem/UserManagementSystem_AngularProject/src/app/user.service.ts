import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  private url="http://localhost:8080/api/users";
  constructor(private httpClient: HttpClient) { 


  }
  getUserList():Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.url}`);
  }
  createUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.url}`, user);
  }
  getUserById(id: number) : Observable<User>{
    return this.httpClient.get<User>(`${this.url}/${id}`);
  }

  updateUser(id: number, user: User ): Observable<Object>{
    return this.httpClient.put(`${this.url}/ ${id}`,user);
  }

  deleteUser(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.url}/${id}`);
  }
  
  
}

