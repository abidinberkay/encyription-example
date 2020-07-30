import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../registration.service'
import { User } from '../user';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User();
  msg: string;

  constructor(private _service : RegistrationService, private _router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.msg = "";
  }
 
 async loginUser() {
  let response = await this._service.loginUser(this.user)
  .then(response => {return response.json()})
  .then(response => {
    if (response.error) {
      this.dialog.open(ErrorDialogComponent, { data: {string: 'User not found'} });
      this.msg = "Bad credentials"
    }
    else {
      this._router.navigate(['/mainpage'], {queryParams: {userId: response.userId}})
    }
  })
  .catch(error => {
    this.dialog.open(ErrorDialogComponent, { data: {string: 'Something went wrong. Be sure that server side is working.'} });
  });
 }
}
