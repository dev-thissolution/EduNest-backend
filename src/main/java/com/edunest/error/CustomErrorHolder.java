package com.edunest.error;

public class CustomErrorHolder {

    // Teacher
    public static final CustomError TEACHER_NOT_FOUND = new CustomError("ERROR", "Teacher Not Found", "Teacher not found with given ID");
    public static final CustomError INVALID_CREDENTIALS = new CustomError("ERROR", "Invalid Credentials", "Invalid email or password");
    public static final CustomError ACCOUNT_INACTIVE = new CustomError("ERROR", "Account Inactive", "Account is inactive. Please contact admin");
    public static final CustomError TEACHER_ALREADY_EXISTS = new CustomError("ERROR", "Teacher Already Exists", "Teacher with this email already exists");

    // Role
    public static final CustomError ROLE_NOT_FOUND = new CustomError("ERROR", "Role Not Found", "Role not found with given ID");
    public static final CustomError ROLE_ALREADY_EXISTS = new CustomError("ERROR", "Role Already Exists", "Role with this name already exists");

    // Tenant
    public static final CustomError TENANT_NOT_FOUND = new CustomError("ERROR", "Tenant Not Found", "Tenant not found with given ID");

    // Auth
    public static final CustomError UNAUTHORIZED = new CustomError("ERROR", "Unauthorized", "You are not authorized to access this resource");
    public static final CustomError TOKEN_EXPIRED = new CustomError("ERROR", "Token Expired", "Your session has expired. Please login again");
}