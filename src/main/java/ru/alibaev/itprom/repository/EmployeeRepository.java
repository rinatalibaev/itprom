package ru.alibaev.itprom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alibaev.itprom.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}