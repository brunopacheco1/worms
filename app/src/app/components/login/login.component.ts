import { Component, OnInit } from "@angular/core";
import { PlayerService } from "src/app/services/player.service";
import { Router } from "@angular/router";
import { AuthService } from "src/app/services/auth.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"]
})
export class LoginComponent implements OnInit {
  constructor(private authService: AuthService) {}

  nickname = "";

  ngOnInit() {}

  login() {
    this.authService.login(this.nickname);
  }
}
