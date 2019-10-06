import { Component, OnInit } from "@angular/core";
import { AuthService } from "src/app/services/auth.service";

@Component({
  selector: "app-start-match",
  templateUrl: "./start-match.component.html",
  styleUrls: ["./start-match.component.scss"]
})
export class StartMatchComponent implements OnInit {
  constructor(private authService: AuthService) {}

  ngOnInit() {}

  logout() {
    this.authService.logout();
  }
}
