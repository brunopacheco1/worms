import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./components/login/login.component";
import { StartMatchComponent } from "./components/start-match/start-match.component";
import { AuthNotLoggedInService } from "./services/auth-not-logged-in.service";
import { AuthLoggedInService } from "./services/auth-logged-in.service";

const routes: Routes = [
  {
    path: "",
    component: LoginComponent,
    canActivate: [AuthLoggedInService]
  },
  {
    path: "start-match",
    component: StartMatchComponent,
    canActivate: [AuthNotLoggedInService]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
