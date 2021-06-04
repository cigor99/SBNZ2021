import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-advanced-search-auto',
  templateUrl: './advanced-search-auto.component.html',
  styleUrls: ['./advanced-search-auto.component.scss']
})
export class AdvancedSearchAutoComponent implements OnInit {

  searchForm: FormGroup;
  ekoCheckbox = new FormControl(false);

  constructor(private formBuilder:FormBuilder)
  {
    this.searchForm = this.formBuilder.group({
      svrha:['', Validators.required],
      dodatnaOprema:['', Validators.required],
      dodaciZaUdobnost:['', Validators.required],
      brojPutnika:['', Validators.required],
      ekoloskaVoznja: this.ekoCheckbox,
      budzet:['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

}
