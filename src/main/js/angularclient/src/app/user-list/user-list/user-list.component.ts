import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user';
import { UserService } from '../../service/user-service.service';
 
@Component({
  selector: '',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService) {

  }

  ngOnInit() {
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

  headers.append('Origin','http://localhost:8081/users');
  headers.append("Access-Control-Allow-Origin", "");
  headers.append("Access-Control-Allow-Methods", 'GET, POST, PUT, DELETE, OPTIONS');
  headers.append("Access-Control-Allow-Credentials", "true");

  console.log("HEADERS"+headers.get('Access-Control-Allow-Origin'));


  fetch('http://localhost:8081/users', {
    mode: 'no-cors',
    credentials: 'include',
    method: 'GET',
    headers: headers
}).then(response => response.json()).then(json => console.log(json)).catch(error => console.log('Authorization failed : ' + error.message));

    
    console.log('HELOOOOO');
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }
}