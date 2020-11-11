import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NewUser } from '../../model/newUser/new-user';
import { UserService } from '../../service/user-service.service';
 
@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {
 
  newUser: NewUser;
 
  constructor(
    private route: ActivatedRoute, 
      private router: Router, 
        private userService: UserService) {
    this.newUser = new NewUser();
  }
 
  onSubmit() {

    // this.userService.save(this.newUser).subscribe(result => this.gotoUserList());
  }
  addOwner(){
     this.userService.save(this.newUser).subscribe(result => this.gotoUserList());
  }
  deleteOwner(){
    console.log("DELETE");
  }
 
  gotoUserList() {
    this.router.navigate(['/users']);
  }
}