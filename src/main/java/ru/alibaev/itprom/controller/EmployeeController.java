package ru.alibaev.itprom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alibaev.itprom.dto.EmployeeDTO;
import ru.alibaev.itprom.entity.Department;
import ru.alibaev.itprom.entity.Employee;
import ru.alibaev.itprom.entity.Profession;
import ru.alibaev.itprom.service.DepartmentService;
import ru.alibaev.itprom.service.EmployeeService;
import ru.alibaev.itprom.service.ProfessionService;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private ProfessionService professionService;


    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, ProfessionService professionService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.professionService = professionService;
    }

    @GetMapping("/getAll")
    public String getAllEmployees (Model model) {
        return "/employee/employees";
    }

    @PostMapping("/getAll")
    public @ResponseBody
    List<Employee> getAllEmployeesPost () {
        return employeeService.getAll();
    }

    @GetMapping("/get")
    public String getEmployee (@RequestParam Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.get(employeeId));
        model.addAttribute("employeesList", employeeService.getAll());
        model.addAttribute("departmentsList", departmentService.getAll());
        model.addAttribute("professionsList", professionService.getAll());
        return "/employee/employee";
    }

    @GetMapping("/create")
    public String createNewEmployee (Model model) {
        List<Profession> professionsList = professionService.getAll();
        List<Department> departmentsList = departmentService.getAll();
        model.addAttribute("professionDefault", professionsList.isEmpty() ? "" : professionsList.get(0));
        model.addAttribute("departmentDefault", departmentsList.isEmpty() ? "" : departmentsList.get(0));
        if (!professionsList.isEmpty()) professionsList.remove(0);
        if (!departmentsList.isEmpty()) departmentsList.remove(0);
        model.addAttribute("professionsList", professionsList);
        model.addAttribute("departmentsList", departmentsList);
        return "/employee/new_employee";
    }

    @PostMapping("/create")
    public @ResponseBody void createEmployee (@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFio(employeeDTO.getFio());
        employee.setNote(employeeDTO.getNote());
        employee.setProfession(professionService.get(employeeDTO.getProfession()));
        employee.setDepartment(departmentService.get(employeeDTO.getDepartment()));
        employeeService.create(employee);
    }

    @PostMapping("/edit")
    public @ResponseBody void editEmployee (@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @PostMapping("/delete")
    public @ResponseBody void deleteEmployee (@RequestParam Long employeeId) {
        employeeService.delete(employeeId);
    }
}
