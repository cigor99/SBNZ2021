import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private authService: AuthenticationService, private router: Router) { }

  user: string;
  authenticated: boolean;
  route: string;

  ngOnInit(): void {
    // this.role = this.authService.getUserRole();
    this.authService.currentUserSubject$.subscribe(us => {
      this.user = us;
      if(!!us){
        this.authenticated = true;
      }else{
        this.authenticated = false;
      }
    })
    this.route = this.router.url;
  }


  get isAuthorized(){
    return this.authService.getUserRole != null;
  }

  get isAdmin(){
    return this.authService.getUserRole() == 'ROLE_ADMIN';
  }

  get isUser(){
    return this.authService.getUserRole() == 'ROLE_USER';
  }

  get isLogin(){
    return this.router.url === '/auth/login';
  }

  get isRegister(){
    return this.router.url === '/auth/register';
  }

  logout(){
    this.user = "";
    this.authenticated = false;
    this.authService.logout();
  }

}
