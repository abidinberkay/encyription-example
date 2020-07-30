import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user';
import { EntryService } from '../entry.service';
import { Entry } from '../entry';
import { MatTableDataSource } from '@angular/material/table';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  user = new User();
  entry = new Entry();
  entryList: Entry[];
  inputFieldText: string;
  showTable: boolean = false;
  enableDecryptButton: boolean = false;
  tableDataSource = new MatTableDataSource(this.entryList);
  displayedColumns: string[] = ['text'];
  constructor(private _router: ActivatedRoute, private _entryService: EntryService, private dialog: MatDialog) { }

ngOnInit(): void {
  this._router.queryParams.subscribe(params => {
    this.user.userId = params['userId'];
  });
  this.getEntries(this.user.userId);
  this.enableDecryptButton = false;
}

async saveEntry() {
  this.entry.userId = this.user.userId;
  let response = await this._entryService.saveEntry(this.entry)
  .then(response => {return response.json()})
  .then(response => {
      this.getEntries(this.user.userId);
      this.entry.text=""
      this.enableDecryptButton = false;
  })
  .catch(error => {this.dialog.open(ErrorDialogComponent, { data: {string: 'Something went wrong. Be sure that server side is working.'} });});
 }

 async getEntries(userId: number) {
   let response = await this._entryService.getEntries(userId)
   .then(response => {return response.json()})
   .then(response => {
       this.entryList = response;
       if(this.entryList.length > 0) {
        this.showTable = true;
        this.enableDecryptButton = false;
       }
    }
     ).catch(error => {this.dialog.open(ErrorDialogComponent, { data: {string: 'Something went wrong. Be sure that server side is working.'} });});
   }

   async decryptText() {
    this.entry.userId = this.user.userId;
    let response = await this._entryService.getDecryptedText(this.entry)
    .then(response => {return response.json()})
    .then (response => {
      if(response.error){

      }
      else {
        this.entry.text = response.text;
        this.enableDecryptButton = false;
      }
    })
    
   }

   rowClick(rowText: string) {
    this.entry.text = rowText;
    this.enableDecryptButton = true;
  }

   onSearchChange() {
     this.enableDecryptButton = false;
   }
 }
