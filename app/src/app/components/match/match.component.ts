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
import { PlayerInfo } from "src/app/model/player-info.model";
import { MatchMapPlayer } from "src/app/model/match-map-player.model";
import { MatchPlayerStatus } from "src/app/model/match-player-status.enum";
import { Direction } from "src/app/model/direction.enum";

@Component({
  selector: "app-match",
  host: {
    "(document:keypress)": "handleKeyboardEvent($event)"
  },
  templateUrl: "./match.component.html",
  styleUrls: ["./match.component.scss"]
})
export class MatchComponent implements OnInit, OnDestroy {
  constructor(
    private authService: AuthService,
    private matchService: MatchService
  ) {}

  mapSubscription: Subscription;

  match: MatchInfo;
  matchPlayer: MatchMapPlayer;
  loggedPlayer: PlayerInfo;

  @ViewChild("canvas", { static: true })
  canvas: ElementRef<HTMLCanvasElement>;

  private heightLimit: number;
  private widthLimit: number;
  private squareSize: number;
  private wallLimit: number;
  private ctx: CanvasRenderingContext2D;

  ngOnInit() {
    this.ctx = this.canvas.nativeElement.getContext("2d");
    this.match = this.matchService.getCurrentMatch();
    this.loggedPlayer = this.authService.getPlayer();
    this.initializeMap();
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

  onPanLeft() {
    this.matchService.updatePlayerDirection("ArrowLeft");
  }
  onPanRight() {
    this.matchService.updatePlayerDirection("ArrowRight");
  }
  onPanUp() {
    this.matchService.updatePlayerDirection("ArrowUp");
  }
  onPanDown() {
    this.matchService.updatePlayerDirection("ArrowDown");
  }

  private updateMap(map: MatchMap) {
    this.matchPlayer = map.players.find(
      player => player.playerId === this.loggedPlayer.id
    );

    this.clearMap();

    map.players.forEach(player => {
      if (player.status !== MatchPlayerStatus.DEAD) {
        player.position.forEach(point => {
          this.drawSquare(point.x, point.y, "black");
        });
      }
    });

    this.drawSquare(map.foodPosition.x, map.foodPosition.y, "red");
  }

  private drawSquare(x: number, y: number, color: string) {
    const fixedX = x * this.squareSize + this.widthLimit;
    const fixedY =
      (this.match.mapSize - y - 1) * this.squareSize + this.heightLimit;
    this.ctx.fillStyle = color;
    this.ctx.fillRect(fixedX, fixedY, this.squareSize, this.squareSize);
  }

  private clearMap() {
    const canvas = this.ctx.canvas;
    this.ctx.fillStyle = "black";
    this.ctx.fillRect(0, 0, canvas.width, canvas.height);
    this.ctx.fillStyle = "white";
    this.ctx.fillRect(
      this.widthLimit,
      this.heightLimit,
      this.wallLimit,
      this.wallLimit
    );
  }

  private initializeMap() {
    const canvas = this.ctx.canvas;
    this.wallLimit = Math.min(canvas.height, canvas.width);
    this.widthLimit = (canvas.width - this.wallLimit) / 2;
    this.heightLimit = (canvas.height - this.wallLimit) / 2;
    this.squareSize = this.wallLimit / this.match.mapSize;
  }
}
