import { Injectable } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({
  providedIn: "root"
})
export class AuthService {
  private static LOGIN_FIELD = "USER_LOGIN";

  constructor(private router: Router) {}

  getNickname(): string {
    return localStorage.getItem(AuthService.LOGIN_FIELD);
  }

  login(nickname: string) {
    localStorage.setItem(AuthService.LOGIN_FIELD, nickname);
    this.router.navigate(["start-match"]);
  }

  logout() {
    localStorage.removeItem(AuthService.LOGIN_FIELD);
    this.router.navigate([""]);
  }

  isLogged(): boolean {
    return localStorage.getItem(AuthService.LOGIN_FIELD) !== null;
  }
}
