package com.example.Homework26;

import java.util.List;

public interface EmployeeServiceInterface {
    //### Шаг 5
    //    //Реализовать в сервисе три метода, которые принимают в качестве параметров firstName и lastName:
    //    //1. Добавить сотрудника.
    //    //2. Удалить сотрудника. remove(int index)
    //    //3. Найти сотрудника. contains(element) ИЛИ


    //### Шаг 7
    //Написать собственное непроверяемое исключение EmployeeStorageIsFullException,
    // которое выбрасывается, если превышен лимит количества сотрудников в фирме.
    List<Employee> findOutNumberEmployees();

    Employee findEmployees(String firstName, String lastName) throws EmployeeStorageIsFullException;

    String addEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    String removeEmployee(String firstName, String lastName);

    boolean containsEmployee(String firstName, String lastName);
}
