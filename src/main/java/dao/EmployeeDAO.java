package dao;

import essence.Employee;

public interface EmployeeDAO {
    void addEmployee(Employee employee);
    void getIdEmployee (int id);
    void allEmployee();
    void modEmployee(String name, String surname, int id);
    void removeEmployee(int id);

}
