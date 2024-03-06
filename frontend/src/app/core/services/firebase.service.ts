import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { IAuthRegisterReq } from '@models/auth.model';
import { UserResponse } from '@models/user.model';
import { initializeApp } from 'firebase/app';

@Injectable({
  providedIn: 'root',
})
export class FirebaseService {
  // TODO: Add SDKs for Firebase products that you want to use
  // https://firebase.google.com/docs/web/setup#available-libraries

  // Your web app's Firebase configuration
  firebaseConfig = {
    apiKey: 'AIzaSyACBpu4radfY3D3F2hQFLHXW-z57Z1YCGE',
    authDomain: 'greenpoint-4d185.firebaseapp.com',
    projectId: 'greenpoint-4d185',
    storageBucket: 'greenpoint-4d185.appspot.com',
    messagingSenderId: '1044811322164',
    appId: '1:1044811322164:web:05da204ebb0ddd6542cb61',
  };

  // Initialize Firebase
  app = initializeApp(this.firebaseConfig);
  getApp() {
    return this.app;
  }
}
