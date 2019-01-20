package com;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    List<Employee> findByNameStartsWithIgnoreCase (String name);
}
