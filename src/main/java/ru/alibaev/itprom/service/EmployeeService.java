package ru.alibaev.itprom.service;

import org.springframework.stereotype.Service;
import ru.alibaev.itprom.entity.Employee;
import ru.alibaev.itprom.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee get(Long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    public void create(Employee employee) {
        employeeRepository.save(employee);
    }

    public void update(Employee employee) {
        Employee employeeFromDB = employeeRepository.getOne(employee.getId());
        employeeFromDB.setFio(employee.getFio());
        employeeFromDB.setProfession(employee.getProfession());
        employeeFromDB.setDepartment(employee.getDepartment());
        employeeFromDB.setNote(employee.getNote());
        employeeRepository.save(employeeFromDB);
    }

    public void delete(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
