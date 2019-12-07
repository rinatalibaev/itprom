package ru.alibaev.itprom.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String fio;
    private Long profession;
    private Long department;
    private String note;
}
