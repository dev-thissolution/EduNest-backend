package com.edunest.error;


public class CustomErrorHolder {

    public static final CustomError INVALID_OTP = new CustomError("ERROR", "Invalid OTP", "Invalid OTP");
    public static final CustomError USER_NOT_FOUND = new CustomError("ERROR", "User Not Found", "User Not Found");
    public static final CustomError GROUP_NAME = new CustomError("ERROR","Group is required","Group name is required");
    public static final CustomError GROUP_MESSAGE = new CustomError("Error","Group message not found","Group message not found");
    public static final CustomError STATUS_NOT_FOUND = new CustomError("Error","Status not found","Status not found");
}