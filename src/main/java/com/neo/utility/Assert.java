package com.neo.utility;


public class Assert extends TestBase{

    public static void assertTrue(Boolean actual, String message){
        if(!actual){
            test.fail(message);
            org.testng.Assert.assertTrue(actual,message+"\n");
        }
    }

    public static void assertFalse(Boolean actual, String message){
        if(actual){
            test.fail(message);
            org.testng.Assert.assertFalse(actual,message+"\n");
        }
    }

    public static void assertEqual(String actual, String expected, String message){
        if(!actual.equals(expected)){
            test.fail(message);
            org.testng.Assert.assertEquals(actual,expected,message+"\n");
        }
    }

    public static void assertEqual(int actual, int expected, String message){
        if(!(actual == expected)){
            test.fail(message);
            org.testng.Assert.assertEquals(actual,expected,message+"\n");
        }
    }

    public static void assertEqual(double actual, double expected, String message){
        if(!(actual == expected)){
            test.fail(message);
            org.testng.Assert.assertEquals(actual,expected,message+"\n");
        }
    }

    public static void assertNotNull(Object object, String message){
        if(object==null){
            test.fail(message);
            org.testng.Assert.assertNotNull(object,message+"\n");
        }
    }

    public static void assertNull(Object object, String message){
        if(!(object==null)){
            test.fail(message);
            org.testng.Assert.assertNull(object,message+"\n");
        }
    }

}
