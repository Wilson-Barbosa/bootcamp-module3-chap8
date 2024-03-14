package com.abutua.sellerregister.controllers.exceptions;

import java.util.ArrayList;

public class ValidationError extends StandardError {
    
    private ArrayList<String> errorList = new ArrayList<>();

    // Method to add an error to the list
    public void addErrorToList(String error){
        errorList.add(error);
    }

    // Returns the list of errors
    public ArrayList<String> getErrorList(){
        return errorList;
    }

}
