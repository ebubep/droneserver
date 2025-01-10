
package com.dronelab.droneserver.util;


public class StatusCode {

    //general codes
    public static final String SUCCESS = "SUCCESS";
    public static final int DUPLICATE_INSERT = 505;
    public static final int UNKNOWN_ERROR = 100;
    public static final int JSON_SYNTAX_ERROR = 105;

    public static final String OVERLOAD = "OVERLOAD";

    //Drone state codes
    public static final int IDLE = 1;
    public static final int LOADING = 2;
    public static final int LOADED = 3;
    public static final int DELIVERING = 4;
    public static final int DELIVERED = 5;
    public static final int RETURNING = 6;


}
