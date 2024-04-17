package org.example.Testng;

import org.testng.annotations.*;

public class Testng001 {

    @BeforeSuite
    void demo3(){
        // Why = Becasue We can add some excel file, database,csv files to read
        System.out.println("Before Suite");
    }
    @BeforeMethod
    void demo4(){
        // Why = Becasue We can add some excel file, database,csv files to read
        System.out.println("Before Method");
    }
    @Test
   void demo5(){
        System.out.println("Main Method");
    }
    @Test
    void demo56(){
        System.out.println("Main 2");
    }
    @AfterMethod
    void demo6(){
        // Why = We can close the files
        System.out.println("After Method");
    }
}
