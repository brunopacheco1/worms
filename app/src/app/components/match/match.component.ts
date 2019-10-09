import { Component, OnInit } from "@angular/core";
import { AuthService } from "src/app/services/auth.service";
import { MatchService } from "src/app/services/match.service";
import { Subscription } from "rxjs";
import { MatchInfo } from "src/app/model/match-info.model";
import { MapPoint } from "src/app/model/map-point.model";
import { MatchMap } from "src/app/model/ match-map.model";

@Component({
  selector: "app-match",
  templateUrl: "./match.component.html",
  styleUrls: ["./match.component.scss"]
})
export class MatchComponent implements OnInit {
  constructor(private matchService: MatchService) {}

  mapSubscription: Subscription;

  match: MatchInfo;

  points: Map<string, MapPoint> = new Map();

  occupiedPoints: MapPoint[] = [];

  ngOnInit() {
    this.match = this.matchService.getCurrentMatch();
    this.drawInitialMap();
    this.mapSubscription = this.matchService
      .getMatchMapEvent(this.match.id)
      .subscribe(map => {
        this.updateMap(map);
      });
  }

  private updateMap(map: MatchMap) {
    this.occupiedPoints.forEach(point => {
      point.occupied = false;
      this.points.set(`${point.x}_${point.x}`, point);
    });

    this.occupiedPoints = [];

    map.players.forEach(player => {
      player.position.forEach(point => {
        point.occupied = true;
        this.points.set(`${point.x}_${point.x}`, point);
        this.occupiedPoints.push(point);
      });
    });

    map.foodPosition.occupied = true;
    this.points.set(
      `${map.foodPosition.x}_${map.foodPosition.x}`,
      map.foodPosition
    );
    this.occupiedPoints.push(map.foodPosition);
  }

  private drawInitialMap() {
    for (let x = 0; x < this.match.mapSize; x++) {
      for (let y = 0; y < this.match.mapSize; y++) {
        this.points.set(`${x}_${y}`, { x, y, occupied: false });
      }
    }
  }

  getPointDraw(x: number, y: number) {
    return this.points.get(`${x}_${this.match.mapSize - y}`).occupied
      ? "x"
      : "0";
  }
}
