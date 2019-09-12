INSERT INTO PLAYER(nickname) VALUES ('player_test_1');
INSERT INTO PLAYER(nickname) VALUES ('player_test_2');

INSERT INTO MATCH(wall, opponent_body, difficulty, play_mode, number_of_players, map_size, status) VALUES ('SOLID', 'SOLID', 'EASY', 'SURVIVAL', 1, 30, 'RUNNING');

INSERT INTO MATCH_PLAYER(player_id, match_id, worm_length, status) VALUES (1, 1, 2, 'PLAYING');

INSERT INTO MATCH(wall, opponent_body, difficulty, play_mode, number_of_players, map_size, status) VALUES ('SOLID', 'SOLID', 'EASY', 'SURVIVAL', 1, 30, 'WAITING_FOR_PLAYERS');
