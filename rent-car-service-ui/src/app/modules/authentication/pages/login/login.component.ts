import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  FormGroupDirective,
  NgForm,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../authentication.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: AuthenticationService,
    private router: Router,
    private _snackBar: MatSnackBar
  ) {
    this.form = this.formBuilder.group({
      email: [
        '',
        Validators.compose([
          Validators.required,
          Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$'),
          Validators.maxLength(50),
        ]),
      ],
      password: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(64),
        ]),
      ],
    });
   }

  ngOnInit(): void {
  }

  login(){
    // console.log(this.form.value);
    this.service.login(this.form.value.email, this.form.value.password)
                .subscribe(
                  (data) => {
                    this.service.setLoggedInUser(data);
                    this.form.reset();
                    this.router.navigate(['/auto']);
                    console.log(this.service.getLoggedInUser());
                    this.service.currentUserSubject.next(this.service.getLoggedInUserAuthority());
                  },
                (error) => {
                  if(error.status === 401){
                    this._snackBar.open('Incorrect email or password', 'Close');
                  }
                  this.form.reset();
                }
                );
  }

}
