package com.jabran.canopee.repositories;

import com.jabran.canopee.entities.ModeEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeEvaluationRepository extends JpaRepository<ModeEvaluation, Integer> {
}
