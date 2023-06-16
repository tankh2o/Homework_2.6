package com.example.Homework26;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {


    //### Шаг 9
    //Добавить в методы из шага 5 новые исключения.
    /*1. В метод с добавлением сотрудника нужно добавить выброс исключения
    из шага 7 в ситуации, когда коллекция переполнена.
      2. В метод с добавлением сотрудника нужно добавить выброс исключения
      из шага 8 в ситуации, когда добавляемый сотрудник уже имеется в коллекции.
      3. В метод с удалением сотрудника нужно добавить выброс исключения
      из шага 6 в ситуации, когда удаляемый сотрудник не найден.
      4. В метод с поиском сотрудника нужно добавить выброс исключения
      из шага 6 в ситуации, когда сотрудник не найден.*/
//### Шаг 10
//Реализовать EmployeeController, который имеет поле EmployeeService.
//Объявить конструктор с этим параметром, чтобы заинджектить EmployeeService в EmployeeController.
//### Шаг 11
//Объявить в контроллере 3 метода:
//1. По адресу /employee/add?firstName=Ivan&lastName=Ivanov должен вернуться объект Employee в формате JSON,
// т.е. { "firstName": "Ivan", "lastName": "Ivanov" }, или исключение ArrayIsFull в случае переполнения коллекции или EmployeeAlreadyAdded в случае, когда сотрудник уже существует.
//2. По адресу /employee/remove?firstName=Ivan&lastName=Ivanov должен вернуться объект Employee в формате JSON,
// т.е. { "firstName": "Ivan", "lastName": "Ivanov" }, или исключение со статусом EmployeeNotFound, если сотрудник отсутствует.
//3. По адресу /employee/find?firstName=Ivan&lastName=Ivanov должен вернуться объект Employee в формате JSON,
// т.е. { "firstName": "Ivan", "lastName": "Ivanov" }, или исключение со статусом EmployeeNotFound, если такой сотрудник отсутствует.
//### Шаг 12
//Написать метод, который выводит в браузер список всех сотрудников в формате JSON (необходимо вернуть объект списка).
    public List<Employee> employees = new ArrayList<>(5);
    public static final int MAX_SIZE = 5;
//    //### Шаг 6
    //    //Написать собственное непроверяемое исключение EmployeeNotFoundException,
    //    // которое выбрасывается, если сотрудник не найден.

    //### Шаг 7
    //Написать собственное непроверяемое исключение EmployeeStorageIsFullException,
    // которое выбрасывается, если превышен лимит количества сотрудников в фирме.
    @Override
    public List<Employee> findOutNumberEmployees() {
        return employees;
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
