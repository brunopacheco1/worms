import { MatchPlayerStatus } from "./match-player-status.enum";

export interface MatchPlayerInfo {
  id: number;
  nickname: String;
  status: MatchPlayerStatus;
  wormLength: number;
}
