import { MatchStatus } from "./match-status.enum";
import { MapPoint } from "./map-point.model";
import { MatchMapPlayer } from "./match-map-player.model";

export interface MatchMap {
  matchId: number;
  roundCounter: number;
  mapSize: number;
  players: MatchMapPlayer[];
  foodPosition: MapPoint;
  status: MatchStatus;
}
