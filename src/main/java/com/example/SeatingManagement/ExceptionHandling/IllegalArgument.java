package com.example.SeatingManagement.ExceptionHandling;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IllegalArgument extends IllegalArgumentException{
    String resourceName;
    Integer curr;
    Integer updated;
    public IllegalArgument(String resourceName, Integer curr, Integer updated) {
        super(String.format(" %s Cannot Decrease from  %d  to %d",resourceName,curr,updated));
        this.resourceName = resourceName;
        this.curr = curr;
        this.updated = updated;
    }
}
