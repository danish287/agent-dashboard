import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user/user';
import { NewUser } from '../model/newUser/new-user';

import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  private usersUrl: string;
  private delURL: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8081/users';
  }

  public findAll(): Observable<User[]> {

    return this.http.get<User[]>(this.usersUrl);
  }

  public save(newUser: NewUser) {
    newUser.name = (newUser.name).charAt(0).toUpperCase() + (newUser.name).slice(1);
    newUser.skill = (newUser.skill).charAt(0).toUpperCase() + (newUser.skill).slice(1);
    return this.http.post<NewUser>(this.usersUrl, newUser);
  }

  public delete(newUser: NewUser) {
    newUser.name = (newUser.name).charAt(0).toUpperCase() + (newUser.name).slice(1);
    newUser.skill = (newUser.skill).charAt(0).toUpperCase() + (newUser.skill).slice(1);
    
    this.delURL = this.usersUrl +"/"+ newUser.name +"/"+newUser.skill;
    console.log("DELETEURL" + this.delURL);
    return this.http.delete<NewUser>(this.delURL);
  }

  public deleteUser(newUser: NewUser){
    newUser.name = (newUser.name).charAt(0).toUpperCase() + (newUser.name).slice(1);
    newUser.skill = (newUser.skill).charAt(0).toUpperCase() + (newUser.skill).slice(1);
    
    this.delURL = this.usersUrl +"/"+ newUser.name;
    console.log("HEREE" + this.delURL);
    return this.http.delete<NewUser>(this.delURL);

  }
}