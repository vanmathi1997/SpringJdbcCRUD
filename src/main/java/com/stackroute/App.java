package com.stackroute;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");

        EmployeeDao dao=(EmployeeDao)ctx.getBean("edao");
        //Inserting values
        int status=dao.saveEmployee(new Employee(102,"Amit",35,"Male"));
        System.out.println(status);
        //Updating table
        System.out.println("Updating table");
        status=dao.updateEmployee(102,"Unknown");
        System.out.println(status);
        System.out.println("Delete record");
        Employee e=new Employee();
        e.setEmp_id(101);
        status=dao.deleteEmployee(e);
        System.out.println(status);
        System.out.println("Queries performed");
        List<Employee> list=dao.retrieveEmployees();
        for(Employee employee:list)
        {
            System.out.println(employee);
        }

    }
}
