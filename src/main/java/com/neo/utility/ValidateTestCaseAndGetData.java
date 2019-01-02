package com.neo.utility;
import java.util.ArrayList;
import java.util.List;

public class ValidateTestCaseAndGetData extends TestBase{

String [][]data=null;

  public String [][]  getData() throws Exception {
      if(data==null) {
          data= (String[][]) DataProvider.getData();
          return data;
      }
      else {
         return data;
      }
    }

    public List<String> getTestCasesData() throws Exception {
      List<String> testCaseData = new ArrayList<>();
      for(int i=0;i<getData().length;i++){
          testCaseData.add(getData()[i][0]);
      }
      return testCaseData;
    }

    public boolean isTestCaseNeedToTest(String testCase) throws Exception{
      if(StringOperations.isValueAvailableInList(testCase,getTestCasesData())){
          int indexOfTestCase = StringOperations.getIndexOfDataInList(testCase,getTestCasesData());
          if(getData()[indexOfTestCase][2].equalsIgnoreCase("y")){
              return true;
          }
      }
      return false;
    }

    public String getUserName(String testCase) throws Exception{
        int indexOfTestCase = StringOperations.getIndexOfDataInList(testCase,getTestCasesData());
        return getData()[indexOfTestCase][3];
    }

    public String getPassword(String testCase) throws Exception{
        int indexOfTestCase = StringOperations.getIndexOfDataInList(testCase,getTestCasesData());
        return getData()[indexOfTestCase][4];
    }
}
