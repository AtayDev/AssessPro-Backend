package com.jabran.canopee.repositories;

import com.jabran.canopee.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
