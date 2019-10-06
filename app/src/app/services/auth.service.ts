import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";
import { NewPlayer } from "../model/new-player.model";
import { Player } from "../model/player.model";
import { tap } from "rxjs/operators";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class AuthService {
  private static PLAYER_FIELD = "LOGGED_USER";

  constructor(private http: HttpClient, private router: Router) {}

  login(nickname: string): Observable<Player> {
    const newPlayer: NewPlayer = { nickname };
    console.log(newPlayer);
    return this.http
      .post<Player>("http://localhost:5000/v1/player", newPlayer)
      .pipe(
        tap(player => {
          localStorage.setItem(
            AuthService.PLAYER_FIELD,
            JSON.stringify(player)
          );
          this.router.navigate(["start-match"]);
          return player;
        })
      );
  }

  logout() {
    localStorage.removeItem(AuthService.PLAYER_FIELD);
    this.router.navigate([""]);
  }

  isLogged(): boolean {
    return localStorage.getItem(AuthService.PLAYER_FIELD) !== null;
  }

  getPlayer(): Player {
    return JSON.parse(localStorage.getItem(AuthService.PLAYER_FIELD));
  }
}
