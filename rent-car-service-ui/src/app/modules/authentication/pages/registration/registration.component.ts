import { Component, OnInit, ViewChild } from '@angular/core';

import {
  FormGroup,
  FormControl,
  FormGroupDirective,
  NgForm,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthenticationService } from '../../authentication.service';
import { RegisterRequest } from '../../register';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  form: FormGroup;
  loading = false;
  matcher = new MyErrorStateMatcher();

  @ViewChild('registerForm')
  private registerForm!: NgForm;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private _snackBar: MatSnackBar
  ) {
    this.form = this.formBuilder.group(
      {
        email: [
          '',
          Validators.compose([
            Validators.required,
            Validators.minLength(5),
            Validators.maxLength(64),
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
        rePassword: ['', Validators.compose([])],
      },
      { validator: this.checkPasswords }
    );
  }

  ngOnInit(): void {}

  checkPasswords(form: FormGroup) {
    const pass = form.controls.password.value;
    const confirmPass = form.controls.rePassword.value;

    return pass === confirmPass ? null : { notSame: true };
  }

  register(){
    console.log(this.form.value.email);
    this._snackBar.open("dasd", "Close");
    // const formObj = this.form.getRawValue();
    // delete formObj.rePassword;
    // this.loading = true;

    // this.authenticationService.register(formObj).subscribe(
    //   (data) => {
    //     this.loading = false;
    //     this.form.reset();
    //     this.registerForm.resetForm();
    //     this._snackBar.open('Confirmation mail has been sent. Please activate your account', '', 'green-snackbar');
    //   },
    //   (err) => {
    //     this.loading = false;
    //     console.log(err);
    //     this._snackBar.openSnackBar(err, '', 'red-snackbar');
    //   }
    // );
  }

}

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(
    control: FormControl | null,
    form: FormGroupDirective | NgForm | null
  ): boolean {
    const invalidParent = !!(
      control &&
      control.parent &&
      control.parent.invalid &&
      control.parent.dirty &&
      control.parent.hasError('notSame')
    );
    return invalidParent;
  }
}
