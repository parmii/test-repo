package com.solactive.ticksconsumerservice.constants;

public class ErrorConstants {
    public static final String CLOSE_PRICE_NOT_FOUND_MESSAGE = "Closed price not available for the given RIC";
    public static final String VALIDATION_FAILED = "Validation Failed";
    public static final String TICK_PRICE_VALIDATION_MESSAGE = "Please provide tick price";
    public static final String TICK_CURRENCY_VALIDATION_MESSAGE = "Please provide tick currency";
    public static final String TICK_RIC_VALIDATION_MESSAGE = "Please provide tick RIC";
    public static final String TICK_TIMESTAMP_VALIDATION_MESSAGE = "Timestamp cannot be null";
    public static final String TICK_NOT_FOUND = "Tick not found for the given RIC";
    public static final String AUTHENTICATION_FAILED_MESSAGE = "Incorrect username or password";
    public static final String MSG_ACCESS_DENIED="Access is Denied, User should have `ADMIN` role";
}
