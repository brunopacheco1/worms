import { Injectable, NgZone } from "@angular/core";
import { AuthService } from "./auth.service";
import { HttpClient } from "@angular/common/http";
import { NewMatchPlayer } from "../model/new-match-player.model";
import { MatchInfo } from "../model/match-info.model";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { MatchMap } from "../model/ match-map.model";
import { Direction } from "../model/direction.enum";
import { PlayerAction } from "../model/player-action.model";

@Injectable({
  providedIn: "root"
})
export class MatchService {
  private static CURRENT_MATCH_FIELD = "CURRENT_MATCH";

  constructor(
    private zone: NgZone,
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
          MatchService.CURRENT_MATCH_FIELD,
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
          MatchService.CURRENT_MATCH_FIELD,
          JSON.stringify(matchInfo)
        );
        this.router.navigate(["match"]);
      });
  }

  getCurrentMatch(): MatchInfo {
    return JSON.parse(localStorage.getItem(MatchService.CURRENT_MATCH_FIELD));
  }

  getMatchMapEvent(matchId: number): Observable<MatchMap> {
    return Observable.create(observer => {
      const eventSource = new EventSource(`/api/v1/match/${matchId}/map`);

      eventSource.onmessage = event => {
        this.zone.run(() => {
          observer.next(JSON.parse(event.data));
        });
      };

      eventSource.onerror = error => {
        this.zone.run(() => {
          observer.error(error);
          console.log(error);
        });
      };
    });
  }

  updatePlayerDirection(key: string) {
    let direction = null;
    switch (key) {
      case "ArrowUp":
        direction = Direction.UP;
        break;
      case "ArrowDown":
        direction = Direction.DOWN;
        break;
      case "ArrowLeft":
        direction = Direction.LEFT;
        break;
      case "ArrowRight":
        direction = Direction.RIGHT;
        break;
    }

    console.log(direction);
    console.log(key);

    if (direction) {
      const player = this.authService.getPlayer();
      const match = this.getCurrentMatch();
      const playerAction: PlayerAction = { playerId: player.id, direction };
      this.http
        .put(`/api/v1/match/${match.id}/rounds`, playerAction)
        .subscribe(matchInfo => {});
    }
  }
}
