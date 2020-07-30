import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { MatDialog } from '@angular/material/dialog';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {


  user = new User();
  msg: string = "";

  constructor(private _service : RegistrationService, private _router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  async registerUser() {
    let response = await this._service.registerUser(this.user)
    .then(response => {return response.json()})
    .then(response => {
      if (response.error) {
        this.dialog.open(ErrorDialogComponent, { data: {string: 'User already exist'} });
    } else {
        this.dialog.open(RegisterDialogComponent);
        this._router.navigate(['/'])
    }
    })
    .catch(error => {
      this.dialog.open(ErrorDialogComponent, { data: {string: 'Something went wrong. Be sure that server side is working.'} });
    });
   }

  goBackToLoginPage() {
    this._router.navigate(['/']);
  }

}
