package com.example.ravi.utils;


public class ResponseCode {

    public static int SUCCESS = 5000;
    public static int DOES_NOT_EXIST = 5001;
    public static int EMAIL_ALREADY_EXIST = 5002;
    public static int PHONE_ALREADY_EXIST = 5003;
    public static int NULL_ARGUMENT = 5004;
    public static int FAIL = 5005;
    public static int UNAUTHORIZED = 5006;
    public static int OPERATION_NOT_ALLOWED = 5007;
    public static int PASSWORD_DO_NO_MATCH = 5006;

    public static  int WAIT = 5008;
    public static int ONE_OF_THEM_EXIST = 5009;



    public static final String MSG_DOES_NOT_EXIST = "Record not found";

    public static final String MSG_VENDOR_EXIST = "vendor approved";

    public static final String MSG_VERIFY_EXIST = "tin number verified";

    public static final String MSG_DOES_EXIST = "Record found";
    public static final String MSG_OPERATION_SUCCESSFUL = "Operation successful";
    public static final String MSG_OPERATION_UNSUCCESSFUL = "Operation failed";

    public static final String tokenType = "Bearer";
    public static final String loginSuccessfulMessage = "Login successful";
    public static final String loginUnsuccessfulMessage = "Failed ";
    public static final String userIdMissing = "userId is null ";
    public static final String verify = " your tin number has not verified";
    public static final String tinNumberMissing = " tin number is null";




}

