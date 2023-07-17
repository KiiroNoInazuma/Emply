package dao;

import connect.ConnectMyBase;
import essence.City;
import essence.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDAO {
    private final Connection connection = ConnectMyBase.getConnect("url", "user", "password");

    private void result(ResultSet resultSet) throws SQLException {
        boolean check = false;
        while (resultSet.next()) {
            String nameEmployee = resultSet.getString("name");
            String secondNameEmployee = resultSet.getString("second_name");
            String sex = resultSet.getString("sex");
            int cityId = resultSet.getInt("city_id");
            String cityName = resultSet.getString("city_name");
            City city = new City(cityName);
            Employee employee = new Employee(nameEmployee, secondNameEmployee, sex, cityId, city);
            System.out.println(employee);
            check = true;
        }
        if (!check) System.out.println("Данные не найдены!");
    }

    @Override
    public void addEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO employee (name, second_name, sex, city_id) VALUES ((?),(?),(?),(?))");
            preparedStatement.setString(1, employee.name());
            preparedStatement.setString(2, employee.secondName());
            preparedStatement.setString(3, employee.sex());
            preparedStatement.setInt(4, employee.cityId());
            preparedStatement.executeUpdate();
            System.out.println("Новый сотрудник успешно добавлен.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getIdEmployee(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from employee e join city c using (city_id) where e.id = (?)");
            preparedStatement.setInt(1, id);
            result(preparedStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void allEmployee() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employee e join city c using (city_id)");
            result(preparedStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modEmployee(String name, String surname, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update employee set name = (?), second_name = (?) where id = (?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Данные обновлены.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeEmployee(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from employee where id = (?)");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Сотрудник успешно удален.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
