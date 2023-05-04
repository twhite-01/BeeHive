package com.techelevator.dao;

public enum BeeHiveSql {


    //USERS
    GET_USER_BY_ID( "SELECT * FROM users WHERE user_id = ?"),
    GET_ALL_USERS( "select * from users"),
    UPDATE_FAMILY_NAME("UPDATE users SET family_name = ? WHERE user_id = ?"),
    INSERT_USER_FOR_CREATION("insert into users (username,password_hash,role) values (?,?,?) ");


    private final String sqlString;

    BeeHiveSql(String sqlString) {
        this.sqlString = sqlString;
    }

    public String getSqlString() {
        return sqlString;
    }


}
