import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Auto, RezervacijaRequest } from '../auto';

@Component({
  selector: 'app-reservation-dialog',
  templateUrl: './reservation-dialog.component.html',
  styleUrls: ['./reservation-dialog.component.scss']
})
export class ReservationDialogComponent implements OnInit {

  form: FormGroup;
  todayDate: Date = new Date();

  constructor(
    public dialogRef: MatDialogRef<ReservationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Auto
  ) {
    this.form = new FormGroup({
      start: new FormControl('',[Validators.required]),
      end: new FormControl('', [Validators.required])
    });
   }

  ngOnInit(): void {
  }

  onNoClick(): void{
    this.dialogRef.close();
  }

  onSaveClick(): void{
    const req: RezervacijaRequest = {
      autoId: this.data.id,
      pocetakRezervacije: this.form.value.start,
      krajRezervacije: this.form.value.end
    };
    this.dialogRef.close(req);
  }

}
