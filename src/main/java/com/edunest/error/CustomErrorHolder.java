package com.edunest.error;

public class CustomErrorHolder {

    // Teacher
    public static final CustomError TEACHER_NOT_FOUND = new CustomError("ERROR", "Teacher Not Found", "Teacher not found with given ID");
    public static final CustomError INVALID_CREDENTIALS = new CustomError("ERROR", "Invalid Credentials", "Invalid email or password");
    public static final CustomError ACCOUNT_INACTIVE = new CustomError("ERROR", "Account Inactive", "Account is inactive. Please contact admin");
    public static final CustomError TEACHER_ALREADY_EXISTS = new CustomError("ERROR", "Teacher Already Exists", "Teacher with this email already exists");

    // Auth
    public static final CustomError UNAUTHORIZED = new CustomError("ERROR", "Unauthorized", "You are not authorized to access this resource");
    public static final CustomError TOKEN_EXPIRED = new CustomError("ERROR", "Token Expired", "Your session has expired. Please login again");
}