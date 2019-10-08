import { MapPoint } from "./map-point.model";
import { MatchPlayerStatus } from "./match-player-status.enum";
import { Direction } from "./direction.enum";

export interface MatchMapPlayer {
  playerId: number;
  status: MatchPlayerStatus;
  wormLength: number;
  direction: Direction;
  position: MapPoint[];
}
