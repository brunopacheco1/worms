import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { HttpClient } from "@angular/common/http";
import { NewMatchPlayer } from "../model/new-match-player.model";
import { MatchInfo } from "../model/match-info.model";
import { Router } from "@angular/router";

@Injectable({
  providedIn: "root"
})
export class MatchService {
  private static CURRENT_PLAYER_FIELD = "CURRENT_MATCH";

  constructor(
    private http: HttpClient,
    private router: Router,
    private authService: AuthService
  ) {}

  startSingleplayer() {
    const player = this.authService.getPlayer();
    const playerMatch: NewMatchPlayer = { playerId: player.id };
    this.http
      .post<MatchInfo>("/api/v1/match/players", playerMatch)
      .subscribe(matchInfo => {
        localStorage.setItem(
          MatchService.CURRENT_PLAYER_FIELD,
          JSON.stringify(matchInfo)
        );
        this.router.navigate(["match"]);
      });
  }

  startMultiplayer() {
    const player = this.authService.getPlayer();
    const playerMatch: NewMatchPlayer = { playerId: player.id };
    this.http
      .post<MatchInfo>("/api/v1/match/players", playerMatch)
      .subscribe(matchInfo => {
        localStorage.setItem(
          MatchService.CURRENT_PLAYER_FIELD,
          JSON.stringify(matchInfo)
        );
        this.router.navigate(["match"]);
      });
  }

  getCurrentMatch(): MatchInfo {
    const currentMatchStr = localStorage.getItem(
      MatchService.CURRENT_PLAYER_FIELD
    );
    if (currentMatchStr !== null) {
      return JSON.parse(localStorage.getItem(currentMatchStr));
    }
    return null;
  }
}
