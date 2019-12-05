package ru.alibaev.itprom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alibaev.itprom.entity.Department;
import ru.alibaev.itprom.entity.Profession;
import ru.alibaev.itprom.service.DepartmentService;
import ru.alibaev.itprom.service.ProfessionService;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/getAll")
    public String getAllDepartments (Model model) {
        return "/department/departments";
    }

    @PostMapping("/getAll")
    public @ResponseBody
    List<Department> getAllDepartmentsPost () {
        return departmentService.getAll();
    }

    @GetMapping("/get")
    public String getDepartment (@RequestParam Long departmentId, Model model) {
        model.addAttribute("department", departmentService.get(departmentId));
        model.addAttribute("parentDepartment", departmentService.get(departmentId).getParentDepartment());
        model.addAttribute("departmentsList", departmentService.getAll());
        return "/department/department";
    }

    @GetMapping("/create")
    public String createNewDepartment (Model model) {
        List<Department> departmentsList = departmentService.getAll();
        model.addAttribute("departmentDefault", departmentsList.get(0));
        departmentsList.remove(0);
        model.addAttribute("departmentsList", departmentsList);
        return "/department/new_department";
    }

    @PostMapping("/create")
    public @ResponseBody void createDepartment (@RequestBody Department department) {
        departmentService.create(department);
    }

    @PostMapping("/edit")
    public @ResponseBody void editDepartment (@RequestBody Department department) {
        departmentService.update(department);
    }

    @PostMapping("/delete")
    public @ResponseBody void deleteDepartment (@RequestParam Long departmentId) {
        departmentService.delete(departmentId);
    }
}
