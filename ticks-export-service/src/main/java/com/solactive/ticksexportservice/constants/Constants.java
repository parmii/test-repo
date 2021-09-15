package com.solactive.ticksexportservice.constants;

public class Constants {
    public static final String CSV_COLUMNS = "TIMESTAMP,PRICE,CLOSE_PRICE,CURRENCY,RIC";
    public static final String MEDIA_TYPE_TEXT_CSV = "text/csv";
    public static final String TICKS_API_SUMMARY = "Create New Ticks";
    public static final String MSG_TICK_CREATED_SUCCESSFULLY = "Tick Created Successfully";
    public static final String EXPORT_API_SUMMARY = "Export Ticks CSV by RIC if Closed price is available";
    public static final String EXPORT_API_SUCCESS_DESCRIPTION = "CSV Generated Successfully";
}
