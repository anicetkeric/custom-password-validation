import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { IUserData, UserData } from './user-data.model';
import { HttpClient } from '@angular/common/http';
import { environment } from './../environments/environment';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'webapp';

  signupForm: FormGroup;
  submitted = false;
  apiResponse = { message: '', error: false };
  errorFieldSubmitted = {};

  constructor(
    private fb: FormBuilder,
    private http: HttpClient
  ) {

  }
  ngOnInit(): void {
    this.signupForm = this.fb.group({
      name: [null, [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      pass: [null, [Validators.required]],
      rePass: [null, [Validators.required]],
      terms: ['', Validators.requiredTrue],
    });
  }

  get registerFormControl() {
    return this.signupForm.controls;
  }

  save(): void {
    this.submitted = true;
    const user = this.createFromForm();

    this.http.post(environment.apiUrl + '/registration', user, { responseType: 'text' }).subscribe(
      data => { 
        this.errorFieldSubmitted = {};
        this.apiResponse.error = false;
        this.apiResponse.message = 'Successful registration';
      },
      error => {
        const errorResponse = JSON.parse(error.error);
        this.apiResponse.error = true;
        this.apiResponse.message = 'Registration error';
        if (errorResponse.error && errorResponse.message === 'VALIDATION_FAILED') {
          this.errorFieldSubmitted = errorResponse.data;
        }
      }
    );
  }

  private createFromForm(): IUserData {
    return {
      ...new UserData(),
      username: this.signupForm.get(['name']).value,
      email: this.signupForm.get(['email']).value,
      password: this.signupForm.get(['pass']).value,
      confirmPassword: this.signupForm.get(['rePass']).value
    };
  }
}
