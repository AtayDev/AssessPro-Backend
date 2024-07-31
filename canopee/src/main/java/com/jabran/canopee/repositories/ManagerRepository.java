package com.jabran.canopee.repositories;

import com.jabran.canopee.entities.HeadManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<HeadManager, Integer> {
}
