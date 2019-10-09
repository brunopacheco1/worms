import {
  Component,
  OnInit,
  ViewChild,
  ElementRef,
  OnDestroy,
  HostListener
} from "@angular/core";
import { AuthService } from "src/app/services/auth.service";
import { MatchService } from "src/app/services/match.service";
import { Subscription } from "rxjs";
import { MatchInfo } from "src/app/model/match-info.model";
import { MapPoint } from "src/app/model/map-point.model";
import { MatchMap } from "src/app/model/ match-map.model";

@Component({
  selector: "app-match",
  host: {
    "(document:keypress)": "handleKeyboardEvent($event)"
  },
  templateUrl: "./match.component.html",
  styleUrls: ["./match.component.scss"]
})
export class MatchComponent implements OnInit, OnDestroy {
  constructor(private matchService: MatchService) {}

  mapSubscription: Subscription;

  match: MatchInfo;

  @ViewChild("canvas", { static: true })
  canvas: ElementRef<HTMLCanvasElement>;

  private ctx: CanvasRenderingContext2D;

  ngOnInit() {
    this.ctx = this.canvas.nativeElement.getContext("2d");
    this.match = this.matchService.getCurrentMatch();
    this.mapSubscription = this.matchService
      .getMatchMapEvent(this.match.id)
      .subscribe(map => {
        this.updateMap(map);
      });
  }

  ngOnDestroy() {
    this.mapSubscription.unsubscribe();
  }

  @HostListener("window:keyup", ["$event"])
  keyEvent(event: KeyboardEvent) {
    this.matchService.updatePlayerDirection(event.key);
  }

  private updateMap(map: MatchMap) {
    this.clearMap();

    map.players.forEach(player => {
      player.position.forEach(point => {
        this.drawSquare(point.x, point.y, "black");
      });
    });

    this.drawSquare(map.foodPosition.x, map.foodPosition.y, "red");
  }

  private drawSquare(x: number, y: number, color: string) {
    const canvas = this.ctx.canvas;
    const width = canvas.width / this.match.mapSize;
    this.ctx.fillStyle = color;
    this.ctx.fillRect(
      x * width,
      (this.match.mapSize - y) * width,
      width,
      width
    );
  }

  private clearMap() {
    const canvas = this.ctx.canvas;
    this.ctx.clearRect(0, 0, canvas.width, canvas.height);
  }
}
