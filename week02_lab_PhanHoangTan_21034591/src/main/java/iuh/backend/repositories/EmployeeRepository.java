package iuh.backend.repositories;

import iuh.backend.enums.EmployeeStatus;
import iuh.backend.models.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


public class EmployeeRepository {

    private EntityManager em;
    private EntityTransaction et;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public EmployeeRepository() {
        em = Persistence.createEntityManagerFactory("MariaBD").createEntityManager();
        et = em.getTransaction();
    }
    public void close() {
        em.close();
    }
    public List<Employee> getAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
        .getResultList();
    }

    public void setStatus(Employee employee, EmployeeStatus status) {
        employee.setStatus(status);
    }

    public void insertEmployee(Employee employee) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(employee);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            logger.error(e.getMessage());
        }
    }

    public boolean updateEmployee(Employee employee) {
        EntityTransaction transaction = em.getTransaction();
        try {
            // Mở giao dịch
            transaction.begin();

            // Tìm nhân viên trong cơ sở dữ liệu
            Employee existingEmployee = em.find(Employee.class, employee.getId());
            if (existingEmployee != null) {
                // Cập nhật thông tin nhân viên
                existingEmployee.setFullName(employee.getFullName());
                existingEmployee.setDob(employee.getDob());
                existingEmployee.setEmail(employee.getEmail());
                existingEmployee.setPhone(employee.getPhone());
                existingEmployee.setAddress(employee.getAddress());
                existingEmployee.setStatus(employee.getStatus());

                // Ghi lại thay đổi
                em.merge(existingEmployee);
            } else {
                // Nếu không tìm thấy nhân viên
                logger.error("Employee not found with ID: " + employee.getId());
                return false;
            }

            // Cam kết giao dịch
            transaction.commit();
            return true;
        } catch (Exception e) {
            // Quay lại giao dịch nếu có lỗi
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Error updating employee: " + e.getMessage());
            return false;
        }
    }


    public Optional<Employee> findById(Long id) {
        TypedQuery query = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class);
        query.setParameter("id", id);
        Employee employee = (Employee) query.getSingleResult();
        return employee == null ? Optional.empty() : Optional.of(employee);
    }

    public void updateStatus(Long id, EmployeeStatus status) {
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findById", Employee.class)
                .setParameter("id", id);
        Employee employee = query.getSingleResult();
        employee.setStatus(status);
        try {
            et.begin();
            em.merge(employee);
            et.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            et.rollback();
        }
    }

    public boolean deleteEmployee(long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Employee employee = em.find(Employee.class, id);
            if (employee != null) {
                em.remove(employee);
                transaction.commit();
                return true; // Xóa thành công
            } else {
                transaction.rollback();
                return false; // Không tìm thấy nhân viên để xóa
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Error deleting employee: " + e.getMessage());
            return false; // Lỗi trong quá trình xóa
        }
    }




}
