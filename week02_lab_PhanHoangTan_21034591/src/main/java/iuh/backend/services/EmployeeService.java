package iuh.backend.services;

import iuh.backend.enums.EmployeeStatus;
import iuh.backend.models.Employee;
import iuh.backend.repositories.EmployeeRepository;


import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(){
        employeeRepository = new EmployeeRepository();
    }

    public boolean updateStatus(long id) {
        Optional<Employee> op = findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            employeeRepository.setStatus(employee, EmployeeStatus.ACTIVE);
            return true;
        }
        return false;
    }

    public boolean restEmployee(long id) {
        Optional<Employee> op = findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            employeeRepository.setStatus(employee, EmployeeStatus.TERMINATED);
            return true;
        }
        return false;
    }
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }
    public void insertEmployee(Employee employee) {
        employeeRepository.insertEmployee(employee);
    }

    public boolean updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    public boolean deleteEmployee(long id) {
        return employeeRepository.deleteEmployee(id); // Gọi trực tiếp phương thức xóa từ repository
    }


    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    public void updateStatusE(Long id, EmployeeStatus status) {
        employeeRepository.updateStatus(id, status);
    }


}
