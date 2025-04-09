/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Admin
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import model.Entity.Employee;
import model.DAO.EmployeeDAO;
import java.util.List;



/**
 *
 * @author Admin
 */
public class Employee_service {
    private EmployeeDAO empDAO;

    public Employee_service() {
        empDAO = new EmployeeDAO();
    }
    
    public  List<Employee> getAllEmployees() {
        return empDAO.getAllEmployees();
    }  
}
