package com.bithub.payroll.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Domain/Model for Employee Entity
 * Id, Name, Role
 * Getters, Setters
 *
 * @author Kiril Mishevski
 * @version 1.0.0
 * @since 1/3/2023
 *
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String role;

    /**
     * default constructor
     */
    public Employee(){}

    public Employee(String firstname, String lastname, String role){
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.firstname + " " + this.lastname;
    }

    public void setName(String name){
        String[] parts = name.split(" ");
        this.firstname = parts[0];
        this.lastname = parts[1];
    }

    public String getFirstname(){
        return this.firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }

    /**
     * Overiiding equals() to compare two Complex objects
     * @param o
     * @return Employee
     */
    @Override
    public boolean equals(Object o){

        //If the object is compared with itself then return true
        if(this == o){
            return true;
        }

        //Check if o[input] is an instance of Employee or not "null instanceof [type]" also returns false
        if(!(o instanceof Employee)){
            return false;
        }

        //typecast o[input] to Employee so that we can compare data members
        Employee employee = (Employee) o;

        //Compare data members and return accordingly
        return Objects.equals(this.id, employee.id)
                && Objects.equals(this.firstname, employee.firstname)
                && Objects.equals(this.lastname, employee.lastname)
                && Objects.equals(this.role, employee.role);
    }

    //Hashing the Employee contents for an alternative mechanism to identify it
    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.firstname, this.lastname, this.role);
    }
    @Override
    public String toString(){
        return "Employee{" + "id=" + this.id + ", first='" + this.firstname + '\'' + ", lastname='" + this.lastname + '\'' + ", role='" + this.role + '\'' + '}';
    }
}
