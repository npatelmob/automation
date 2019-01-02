package com.neo.suiteCreater;

public class createSuite {

    public static void main(String[] arg) {
        XLSReader suite = new XLSReader("/Users/nishantpatel/Documents/GitHub/automation/src/main/resources/ExcelFiles/Dataprovider_neoApp.xlsx");
        suite.getTests("select * from LoginTestCases where RUNMODE ='y'");
    }

}
