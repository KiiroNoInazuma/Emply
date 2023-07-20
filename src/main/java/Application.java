import dao.EmployeeDAO;
import dao.EmployeeDaoImpl;
import essence.City;
import essence.Employee;

public class Application {
public static EmployeeDAO employeeDAO = new EmployeeDaoImpl();
    public static void main(String[] args) {
        employeeDAO.allEmployee(); //Получение списка всех объектов Employee из базы.
        employeeDAO.addEmployee(new Employee("Дима", "Тимошенко", "М", 1, new City("Москва")));//Создание (добавление) сущности Employee в таблицу.
        employeeDAO.getIdEmployee(5);//Получение конкретного объекта Employee по id.
        employeeDAO.removeEmployee(11);//Удаление конкретного объекта Employee из базы по id.
        employeeDAO.modEmployee("Тест", "Тестов", 11);//Изменение конкретного объекта Employee в базе по id.
    }
}
