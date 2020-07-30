import { Injectable } from '@angular/core';
import { Entry } from './entry'

@Injectable({
  providedIn: 'root'
})
export class EntryService {

  constructor() { }

  saveEntry(entry: Entry) {
    return fetch("http://localhost:8080/saveEntry", {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'POST',
      body: JSON.stringify(entry)
    });
  }

  getEntries(userId: number) {
    let url = "http://localhost:8080/getEntriesOfUser?userId=" + userId
    return fetch(url);
  }

  getDecryptedText(entry: Entry) {
    let url = "http://localhost:8080/getDecryptedText?textToDecrypt" 
    return fetch(url, {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'POST',
      body: JSON.stringify(entry)
    });
  }
}
