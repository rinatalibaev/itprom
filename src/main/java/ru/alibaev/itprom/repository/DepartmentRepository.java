package ru.alibaev.itprom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alibaev.itprom.entity.Department;
import ru.alibaev.itprom.entity.Profession;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}