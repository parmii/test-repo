package com.solactive.ticksconsumerservice.constants;

public class Constants {
    public static final String MSG_TICK_CREATED_SUCCESSFULLY="Tick Created Successfully";
    public static final String MSG_TICK_FETCHED_SUCCESSFULLY="Tick Fetched Successfully";
    public static final String MSG_JWT_GENERATED_SUCCESSFULLY="JWT Generated Successfully";
    public static final String TICKS_API_SUMMARY="Create New Ticks";
    public static final String TICKS_API_DESCRIPTION="This API is used to add new ticks in the application, pass the Tick Schema to call create the new Tick";
    public static final String TICKS_LOOKUP_API_SUMMARY="Lookup all tick values for a particular RIC";
    public static final String TICKS_LOOKUP_API_DESCRIPTION="This API is protected by Authority ADMIN, user must have `ADMIN` role to access the API, A JWT token should be generated using /generate API and passed as a Bearer Token in the header";
    public static final String GENERATE_TOKEN_API_SUMMARY="Generate JWT token based on username and password";
}
