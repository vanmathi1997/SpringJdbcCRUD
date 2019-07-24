package com.stackroute;

import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    JdbcTemplate jdbcTemplate;
    public JdbcTemplate getJdbcTemplate()
    {
       return  jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int saveEmployee(Employee e){
        String query="insert into employee values('"+e.getEmp_id()+"','"+e.getEmp_name()+"','"+e.getEmp_age()+"','"+e.getGender()+"')";
        return jdbcTemplate.update(query);
    }
    public int updateEmployee(int id,String name){
        String query="update employee set emp_name='"+name+"'where emp_id='"+id+"' ";
        return jdbcTemplate.update(query);
    }
    public int deleteEmployee(Employee e){
        String query="delete from employee where emp_id='"+e.getEmp_id()+"' ";
        return jdbcTemplate.update(query);

    }
    public List<Employee> retrieveEmployees()
    {
        String query="Select * from employee";
        final List<Employee> list=new ArrayList<Employee>();
        return jdbcTemplate.query(query, new ResultSetExtractor<List<Employee>>() {
            @Override
            public List<Employee> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

                while(resultSet.next())
                {
                    Employee e=new Employee();
                    e.setEmp_id(resultSet.getInt(1));
                    e.setEmp_name(resultSet.getString(2));
                    e.setEmp_age(resultSet.getInt(3));
                    e.setGender(resultSet.getString(4));
                    list.add(e);
                }
                return list;
            }
        });
    }
}
