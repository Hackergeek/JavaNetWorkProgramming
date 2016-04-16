/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 对象序列化;

import java.io.Serializable;

/**
 *
 * @author skyward
 */
public class Employee implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int employeeNumber;
    private String employeeName;
    public Employee(int num, String name) {
        employeeName = name;
        employeeNumber = num;
    }
    
    public int getEmployeeNumber() {
        return this.employeeNumber;
    }
    
    public void setEmployeeNumber(int num) {
        employeeNumber = num;
    }
    
    public String getEmployeeName() {
        return this.employeeName;
    }
    
    public void setEmployeeName(String name) {
        employeeName = name;
    }
    
} 
