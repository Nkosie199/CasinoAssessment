package com.assessment.casino.data.repository;

import com.assessment.casino.data.entity.Player;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
  Iterable<Player> findAll();
  Player findById(int id);
  List<Player> findByUsername(String username);
}
