package org.example.Testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Testing004 {
    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                new Object[]{"Admin", "Admin"},
                new Object[]{"Admin", "Password12"},
                new Object[]{"Admin", "Password123"},
                new Object[]{"Admin", "Password12345"},
                new Object[]{"Admin", "Password123676776"}
        };
    }
        @Test(dataProvider = "getData" )
        public void loginTest(String Username, String Password){
            System.out.println(Username);
            System.out.println(Password);
        }
    }

