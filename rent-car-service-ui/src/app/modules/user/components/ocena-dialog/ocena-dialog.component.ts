import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-ocena-dialog',
  templateUrl: './ocena-dialog.component.html',
  styleUrls: ['./ocena-dialog.component.scss']
})
export class OcenaDialogComponent implements OnInit {

  todayDate: Date;
  form: FormGroup;
  ocene: number[] = [1,2,3,4,5];

  constructor(
    public dialogRef: MatDialogRef<OcenaDialogComponent>,
  ) {
    this.todayDate = new Date();
    this.form = new FormGroup({
      selectedGrade: new FormControl('',[Validators.required]),
    });
   }

  ngOnInit(): void {
  }

  onNoClick(): void{
    this.dialogRef.close();
  }

  onSaveClick(): void{
    this.dialogRef.close(this.form.value.selectedGrade);
  }

}
