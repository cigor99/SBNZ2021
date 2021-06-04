import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { RouterModule } from '@angular/router';

import {MenubarModule} from 'primeng/menubar';
import { ButtonModule } from 'primeng/button';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { VendorsModule } from 'src/vendors/src/public-api';


@NgModule({
  declarations: [HeaderComponent],
  imports: [
    CommonModule,
    RouterModule,
    VendorsModule
  ],
  exports: [HeaderComponent],
  providers: []
})
export class CoreModule { }
