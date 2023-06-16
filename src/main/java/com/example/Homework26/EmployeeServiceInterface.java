package com.example.Homework26;

import java.util.List;

public interface EmployeeServiceInterface {
    List<Employee> findOutNumberEmployees();

    Employee findEmployees(String firstName, String lastName) throws EmployeeStorageIsFullException;

    String addEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    String removeEmployee(String firstName, String lastName);

    boolean containsEmployee(String firstName, String lastName);
}
