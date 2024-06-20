package com.tours.tourisme.model;

import com.tours.tourisme.model.exception.BadRequestException;
import lombok.Getter;

@Getter
public class BoundedPageSize {
    public static final int MAX_PAGE= 500;
    private final int value;

    public BoundedPageSize(int value){
        if(value < 1){
            throw new BadRequestException("Page size must be >= 1");
        }
        if(value > MAX_PAGE){
            throw new BadRequestException("Page size must be < " + MAX_PAGE);
        }
        this.value = value;
    }
}

