package com.jabran.canopee.repositories;

import java.util.List;
import com.jabran.canopee.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findByManagerId(int managerId);
}
