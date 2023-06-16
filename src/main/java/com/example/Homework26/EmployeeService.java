package com.example.Homework26;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    public List<Employee> employees = new ArrayList<>(5);
    public static final int MAX_SIZE = 5;
    @Override
    public List<Employee> findOutNumberEmployees() {
        return employees;
    }

    @Override
    public String addEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже есть в базе данных.");
        }
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Вы забыли прописать фамилию или имя сотрудника.");
        }
        if (employees.size() == MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме.");
        }
        employees.add(employee);
        return "В базу данных добавлен новый сотрудник: " + employee.getFirstName() + " " + employee.getLastName();
    }
    @Override
    public String removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) != null && employees.get(i).equals(employee)) {
                employees.remove(employee);
                return "Сотрудник " + employee.getFirstName() + " " + employee.getLastName() + " удалён из базы данных.";
            }
        }
        throw new EmployeeNotFoundException("Такого сотрудника нет в базе данных.");
    }
    @Override
    public Employee findEmployees(String firstName, String lastName) throws EmployeeStorageIsFullException {
        for (Employee employee : employees) {
            if (firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName())) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Такого сотрудника нет в базе данных.");
    }
    @Override
    public boolean containsEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i <= employees.size(); i++) {
            if (employees.get(i) != null && employees.get(i).equals(employee)) {
                return true;
            }
        }
        return false;
    }
}
