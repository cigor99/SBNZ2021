import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class HeaderComponent implements OnInit {

  items: MenuItem[];

  commonItems: MenuItem[] = [
    {
      label: 'Home',
      icon: 'pi pi-home',
      routerLink: ['']
    },
    // {
    //   label: 'Issue Certificate',
    //   icon: 'pi pi-map',
    //   routerLink: ['/certificates/issue-certificate']
    // },
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
