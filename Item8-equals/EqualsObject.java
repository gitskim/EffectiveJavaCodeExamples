package com.company;

/**
 * Created by kimsuh on 3/27/17.
 */
public class EqualsObject {
    @Override
    public boolean equals(Object obj) {
        // equals method can be implemented in multiple ways. 
        super.equals(obj);
        if (obj instanceof  EqualsObject) {
            return true;
        } else {
            return false;
        }
    }
}
