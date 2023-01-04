package com.bithub.payroll.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class Order {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Status status;

    public Order(){}

    public Order(String description, Status status){
        this.description = description;
        this.status = status;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        if(!(o instanceof Order)){
            return false;
        }

        Order order = (Order) o;
        return Objects.equals(this.id, order.id)
                && Objects.equals(this.description, order.description)
                && Objects.equals(this.status, order.status);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.description, this.status);
    }

    @Override
    public String toString(){
        return "Order{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status=" + this.status + "}";
    }
}
