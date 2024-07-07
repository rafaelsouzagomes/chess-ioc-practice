package com.game.chess.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.chess.models.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>  {
 
}
