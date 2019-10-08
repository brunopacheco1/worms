import { Component, OnInit } from "@angular/core";
import { AuthService } from "./services/auth.service";
import { PlayerInfo } from "./model/player-info.model";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent {
  constructor(private authService: AuthService) {}

  isLogged(): boolean {
    return this.authService.isLogged();
  }

  player(): PlayerInfo {
    return this.authService.getPlayer();
  }

  logout() {
    this.authService.logout();
  }
}
