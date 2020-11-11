import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../service/user-service.service';
import { NewUser } from "../../model/newUser/new-user";

@Component({
  selector: 'app-delete-user',
  templateUrl: './delete-user.component.html',
  styleUrls: ['./delete-user.component.css']
})
export class DeleteUserComponent implements OnInit {

  newU: NewUser;

  constructor(
    private route: ActivatedRoute, 
      private router: Router, 
        private userService: UserService) {
  }


  ngOnInit() {
  
    console.log("HIII");

    // this.userService.delete(this.newU)
    // this.gotoUserList();
    this.gotoUserList()
  }
 
  gotoUserList() {
    this.router.navigate(['/users']);
  }
 



}


