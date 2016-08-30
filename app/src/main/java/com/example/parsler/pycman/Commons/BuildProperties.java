package com.example.parsler.pycman.Commons;

public class BuildProperties {

    // SQLite Database credentials
    public static final String DATABASE_NAME = "OMS_Backup.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "order_backup";

    // Order Management Service Database keys
    public static final String KEY_PICKUP_ID = "pickupId";
    public static final String KEY_IS_ACTIVE = "isActive";
    public static final String KEY_IS_READ = "isRead";
    public static final String KEY_PICKUP_IS_COMPLETED = "pickupDataIsCompleted";
    public static final String KEY_DROP_IS_COMPLETED = "dropDataIsCompleted";
    public static final String KEY_PAYMENT_IS_COMPLETED = "paymentDataIsCompleted";
}
