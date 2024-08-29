package com.jabran.canopee.repositories;

import com.jabran.canopee.entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    List<Evaluation> findByAgentId(int agentId);
    List<Evaluation> findByTeamId(int teamId);
}
