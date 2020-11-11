import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8081/users';
  }

  public findAll(): Observable<User[]> {
    console.log("GREETINGS");
  //   fetch('http://localhost:8081/users').then(
  //   function(response) {
  //     if (response.status !== 200) {
  //       console.log('Looks like there was a problem. Status Code: ' + response.status);
  //       return;
  //     }

  //     // Examine the text in the response
  //     response.json().then(function(data) {
  //       console.log(data);
  //     });
  //   }
  // )
  let headers = new Headers();

  headers.append('Content-Type', 'application/json');
  headers.append('Accept', 'application/json');
  headers.append('credentials', 'include');

  headers.append('Origin','http://localhost:4200');
  headers.append('Origin','http://localhost:8081/users');
  headers.append("Access-Control-Allow-Methods", 'GET, POST, PUT, DELETE, OPTIONS');
  headers.append("Access-Control-Allow-Credentials", "true");

  console.log("HEADERS "+headers.get('Access-Control-Allow-Origin'));


  fetch('http://localhost:8081/users', {
    mode: 'no-cors',
    credentials: 'include',
    method: 'GET',
    headers: headers
}).then(response => response.json()).then(json => console.log(json)).catch(error => console.log('Authorization failed : ' + error.message));

    


    return this.http.get<User[]>(this.usersUrl);
  }

  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }
}