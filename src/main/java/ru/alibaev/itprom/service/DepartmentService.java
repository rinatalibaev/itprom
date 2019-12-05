package ru.alibaev.itprom.service;

import org.springframework.stereotype.Service;
import ru.alibaev.itprom.entity.Department;
import ru.alibaev.itprom.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }


    public Department get(Long departmentId) {
        return departmentRepository.getOne(departmentId);
    }

    public void create(Department department) {
        departmentRepository.save(department);
    }

    public void update(Department department) {
        Department departmentFromDB = departmentRepository.getOne(department.getId());
        departmentFromDB.setName(department.getName());
        departmentFromDB.setNote(department.getNote());
        departmentFromDB.setParentDepartment(department.getParentDepartment());
        departmentRepository.save(departmentFromDB);
    }

    public void delete(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
