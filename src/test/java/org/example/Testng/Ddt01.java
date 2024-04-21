package org.example.Testng;import org.testng.annotations.Test;

public class Ddt01 {
    @Test(dataProvider = "getData", dataProviderClass = UtilExcel.class)
    public void testLoginData(String username, String password ) {
        System.out.println("UserName - " + username);
        System.out.println("Password - " + password);
        //System.out.println("ER - " + ER);

        // Login to app API


        // Write the code the Login POST request
        //

    }


}