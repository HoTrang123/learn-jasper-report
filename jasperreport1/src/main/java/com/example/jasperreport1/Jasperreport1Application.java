package com.example.jasperreport1;

import com.example.jasperreport1.entity.Employee;
import com.example.jasperreport1.repository.EmployeeRepository;
import com.example.jasperreport1.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Jasperreport1Application {

    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private ReportService service;

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return repository.findAll();
    }
    @GetMapping("report/{format}")
    public  String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return service.exportReport(format);
    }

    public static void main(String[] args) {
        SpringApplication.run(Jasperreport1Application.class, args);
    }

}
