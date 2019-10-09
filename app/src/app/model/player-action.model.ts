import { Direction } from "./direction.enum";

export interface PlayerAction {
  playerId: number;
  direction: Direction;
}
