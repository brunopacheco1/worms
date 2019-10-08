import { Component, OnInit } from "@angular/core";
import { AuthService } from "src/app/services/auth.service";
import { MatchService } from "src/app/services/match.service";
import { Subscription } from "rxjs";

@Component({
  selector: "app-match",
  templateUrl: "./match.component.html",
  styleUrls: ["./match.component.scss"]
})
export class MatchComponent implements OnInit {
  constructor(private matchService: MatchService) {}

  mapSubscription: Subscription;

  roundCounter = 0;

  ngOnInit() {
    this.mapSubscription = this.matchService
      .getMatchMapEvent(this.matchService.getCurrentMatch().id)
      .subscribe(map => {
        this.roundCounter = map.roundCounter;
        console.log(map);
      });
  }
}
