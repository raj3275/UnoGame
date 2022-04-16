/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;


public class T1Test {
    
    public T1Test() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCheckLengthGood() {
        System.out.println("checkLength Good");
        String pass = "password";
        boolean expResult = true;
        boolean result = T1.checkLength(pass);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckLengthBad() {
        System.out.println("checkLength Bad");
        String pass = "pass";
        boolean expResult = false;
        boolean result = T1.checkLength(pass);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckLengthBoundary() {
        System.out.println("checkLength Boundary");
        String pass = " ";
        boolean expResult = false;
        boolean result = T1.checkLength(pass);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckLenGood() {
        System.out.println("checkLen Good");
        String name = "harleen";
        boolean expResult = true;
        boolean result = T1.checkLen(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckLenBad() {
        System.out.println("checkLen Bad");
        String name = "har";
        boolean expResult = false;
        boolean result = T1.checkLen(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckLenBoundary() {
        System.out.println("checkLen Boundary");
        String name = " ";
        boolean expResult = false;
        boolean result = T1.checkLen(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckNumGood() {
        System.out.println("checkNum Good");
        int age = 20;
        boolean expResult = true;
        boolean result = T1.checkNum(age);
        assertEquals(expResult, result);
        
    }
    
     @Test
    public void testCheckNumBad() {
        System.out.println("checkNum Bad");
        int age = 5;
        boolean expResult = false;
        boolean result = T1.checkNum(age);
        assertEquals(expResult, result);
        
    }
    
     @Test
    public void testCheckNumBoundary() {
        System.out.println("checkNum Boundary");
        int age = 9;
        boolean expResult = false;
        boolean result = T1.checkNum(age);
        assertEquals(expResult, result);
        
    }

   
    

}