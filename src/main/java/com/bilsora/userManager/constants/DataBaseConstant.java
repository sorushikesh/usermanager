package com.bilsora.userManager.constants;

public class DataBaseConstant {

  // TABLES
  public static final String TABLE_USER = "user";
  public static final String TABLE_ROLES = "roles";

  // COLUMNS
  public static final String COLUMN_USER_ID = "user_id";
  public static final String COLUMN_FIRST_NAME = "first_name";
  public static final String COLUMN_LAST_NAME = "last_name";
  public static final String COLUMN_EMAIL = "email";
  public static final String COLUMN_ROLE = "role";
  public static final String COLUMN_ROLE_ID = "role_id";

  private DataBaseConstant() {
    // private constructor
  }
}
