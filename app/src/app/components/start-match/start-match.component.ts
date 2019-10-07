import { Component, OnInit } from "@angular/core";
import { AuthService } from "src/app/services/auth.service";
import { MatchService } from "src/app/services/match.service";

@Component({
  selector: "app-start-match",
  templateUrl: "./start-match.component.html",
  styleUrls: ["./start-match.component.scss"]
})
export class StartMatchComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private matchService: MatchService
  ) {}

  ngOnInit() {}

  startSingleplayer() {
    this.matchService.startSingleplayer();
  }

  startMultiplayer() {
    this.matchService.startMultiplayer();
  }

  logout() {
    this.authService.logout();
  }
}
