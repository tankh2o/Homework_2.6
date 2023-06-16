package com.example.Homework26;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    /*3. Возврат статуса осуществляется с помощью аннотации @ResponseStatus.
    4. Никакой логики в контроллере быть не должно. Всю работу с коллекцией
    выполняет сервис, а контроллер только формирует сообщение с результатом работы сервиса.
    5. В случае отсутствия одного из параметров Spring должен самостоятельно
    выводить страницу с ошибкой (аннотация @RequestParam).
    7. Spring сам конвертирует ваш объект Employee в JSON. Достаточно просто
    вернуть его через return в методе контроллера.*/
    private final EmployeeServiceInterface employeeServiceInterface;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public EmployeeController(EmployeeServiceInterface employeeServiceInterface) {
        this.employeeServiceInterface = employeeServiceInterface;
    }
    @GetMapping(path = "/find/out")
    public List<Employee> findOutNumberEmployees(){
        return employeeServiceInterface.findOutNumberEmployees();
    }
    @GetMapping(path = "/add")
    public String addEmployee(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeServiceInterface.addEmployee(firstName, lastName);
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeServiceInterface.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeServiceInterface.findEmployees(firstName, lastName);
    }
}
