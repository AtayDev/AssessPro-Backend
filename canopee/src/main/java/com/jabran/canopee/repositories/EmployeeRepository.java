package com.jabran.canopee.repositories;

import com.jabran.canopee.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT * FROM Employee e WHERE e.fname = ?1 ", nativeQuery = true)
    List<Employee> findByFirstName(String fname);
}
