package com.blackjack.tiit.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long>{	
	@Query("select s from Player s where s.name like ?1")
	Player findByName(String name);
}
