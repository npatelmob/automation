package com.neo.utility;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class DataProvider extends TestBase {

    @org.testng.annotations.DataProvider(name = "TC")
    public static Object[][] getData() throws Exception {

        Class cls = Class.forName(classPath);
        String[] classData = classPath.split("\\.");
        String className = classData[classData.length - 1];

        Method[] methods = cls.getDeclaredMethods();
        List<String> testMethodList = new ArrayList<>();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().contains("TC")) {
                testMethodList.add(methods[i].getName());
            }
        }
        File file = new File(System.getProperty("user.dir") + dataProvider);
        Workbook workbook = WorkbookFactory.create(file);
        workbook.getNumberOfSheets();
        String[] sheetList = new String[]{className};
        Sheet sheet;
        Iterator<Row> rowIterator;
        DataFormatter dataFormatter = new DataFormatter();

        HashMap<String, List<String>> hm = new HashMap<String, List<String>>();
        List<String> list = new ArrayList();
        List<String> tempHeaderList = new ArrayList();
        List<String> tempcellValues = new ArrayList();
        List<String> headerList = new ArrayList();

        int temp = 0;

        for (int i = 0; i < sheetList.length; i++) {
            sheet = workbook.getSheet(sheetList[i]);
            rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                if (row.getCell(0) == null) {
                    break;
                }
                temp = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    list.add(dataFormatter.formatCellValue(cell));
                    temp = temp + 1;
                    list = StringOperations.removeBlankValue(list);
                }
                System.out.println();
            }
            for (int j = 0; j < temp; j++) {
                tempHeaderList.add(list.get(j));
                headerList.add(list.get(j));
            }
            for (int j = 0; j < list.size() - temp; j++) {
                tempcellValues.add(list.get(temp + j));
            }
            list.removeAll(list);

            for (int j = 0; j < tempHeaderList.size(); j++) {
                int count = tempcellValues.size() / tempHeaderList.size();
                List values = new ArrayList();
                temp = j;
                for (int k = 0; k < count; k++) {
                    values.add(tempcellValues.get(temp));
                    temp = temp + tempHeaderList.size();
                }
                hm.put(String.valueOf(tempHeaderList.get(j)), values);
                System.out.println();
            }
            tempcellValues.removeAll(tempcellValues);
            tempHeaderList.removeAll(tempHeaderList);
        }
        int testCaseSize = 0;
        if (testMethodList.size() < hm.get("TCID").size()) {
            testCaseSize = testMethodList.size();
        } else {
            testCaseSize = hm.get("TCID").size();
        }

        String[][] inputData = new String[testCaseSize][hm.keySet().size()];
        for (int i = 0; i < hm.get("TCID").size(); i++) {
            if (StringOperations.isValueAvailableInList(hm.get("TCID").get(i), testMethodList)) {
                for (int j = 0; j < hm.size(); j++) {
                    inputData[i][j] = hm.get(headerList.get(j)).get(i);
                }
            }
        }

        return inputData;
    }


}
