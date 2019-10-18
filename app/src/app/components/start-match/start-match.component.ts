import { Component, OnInit } from "@angular/core";
import { AuthService } from "src/app/services/auth.service";
import { MatchService } from "src/app/services/match.service";

@Component({
  selector: "app-start-match",
  templateUrl: "./start-match.component.html",
  styleUrls: ["./start-match.component.scss"]
})
export class StartMatchComponent implements OnInit {
  constructor(private matchService: MatchService) {}

  ngOnInit() {}

  startOnePlayerMode() {
    this.matchService.startRandomMatch(1);
  }

  startTwoPlayersMode() {
    this.matchService.startRandomMatch(2);
  }

  startFourPlayersMode() {
    this.matchService.startRandomMatch(4);
  }
}
