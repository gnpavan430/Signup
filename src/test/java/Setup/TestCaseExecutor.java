package Setup;


import Utilities.ExcelUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class TestCaseExecutor {
    private static JSONObject jsonObject = null;
    private static final PropertyUtils appProperty = new PropertyUtils();
    public static HashMap<Object,Object> map = null;
    private static final String jsonFile = appProperty.getPropertyValue("TestCaseJSON");
    TestCaseScenario testCaseScenario;
    TestCaseExecutor testCaseExecutor;
    PropertyUtils propertyUtils = new PropertyUtils();


   public void TestcaseJSONReader() {
       String inputStream = PropertyUtils.appProperties.get("TestCaseJSON");
       createJsonObject(inputStream);

   }
    public static void createJsonObject(String jsonDataFile) {
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(jsonDataFile));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        jsonObject = (JSONObject) obj;
    }

    /**Get Jsonobject for each testcase based on test case name
     *
     * @param testCaseName - test case name
     * @return json object for each test case
     */

    public JSONObject getJsonObject(String testCaseName){
        JSONObject testcaseObject = new JSONObject();
        testcaseObject = (JSONObject) jsonObject.get(testCaseName);
        return testcaseObject;
    }
    public  HashMap<Object,Object> createHashMap(int rowNumber,String filePath, String sheetName) throws Exception {

        try {

            ExcelUtils.setExcelFile(filePath,sheetName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object RowData = null;
        Object ColData = null;
        int LastColNum = ExcelUtils.getLastColNumber(rowNumber);
        map = new HashMap<Object,Object>();


        for (int iCol=0;iCol<LastColNum;iCol++)
        {


            ColData = ExcelUtils.getCellData(filePath,sheetName,0, iCol);
            RowData = ExcelUtils.getCellData(filePath,sheetName,rowNumber, iCol);
            if(!(map.containsKey(ColData)))
            {
                map.put(ColData, RowData);
            }
        }
        return map;

    }
    public HashMap<Object,Object> createHashMapForSpecificRow(String methodName) throws Exception {
        System.out.println("Method name is"+methodName);
        testCaseScenario= TestCaseExecutor.getTestScenario(methodName);
        testCaseExecutor = new TestCaseExecutor();
        System.out.println("Test scenario name is"+testCaseScenario.getInfo());
        System.out.println("Test data is"+testCaseScenario.getTestData());
        int rowNumber = Integer.parseInt(testCaseExecutor.getRowNumber(testCaseScenario.getTestData()));
        System.out.println("Row number is"+rowNumber);
       return createHashMap(rowNumber,propertyUtils.getPropertyValue("Path_TestData"),propertyUtils.getPropertyValue("sheetName"));



    }
    public static List<TestCaseScenario> getTestCaseScenarios() {
        createJsonObject(jsonFile);
        List<TestCaseScenario> scenarios = new ArrayList<>();
        for (Object object : jsonObject.keySet()) {

            // Get the key
            String name = (String) object;

            // Get the value
            JSONObject testcaseObject = (JSONObject) jsonObject.get(name);
            int id =  Integer.parseInt(testcaseObject.get("id").toString());

            String info = (String) testcaseObject.get("Info");
            String feature = (String) testcaseObject.get("Feature");
            String testName = getParameterFromJson(name,"testName");
            String testData = getParameterFromJson(name,"testData");
            TestCaseScenario scenario = new TestCaseScenario(id, name, testName,info, feature,testData);
            scenarios.add(scenario);
        }
        scenarios.sort(Comparator.comparing(TestCaseScenario::getId));
        return scenarios;
    }
    public static TestCaseScenario getTestScenario(String currentMethodName) {
        TestCaseScenario testCaseScenario = null;
        List<TestCaseScenario> testCaseScenarios = getTestCaseScenarios();
        for(int i=0;i<testCaseScenarios.size();i++){
            if(testCaseScenarios.get(i).getTestName().equalsIgnoreCase(currentMethodName)){
                testCaseScenario=testCaseScenarios.get(i);
            }

                
        }


        return testCaseScenario;
    }
    public String getSheetName(String testData){
        String[] parts = testData.split(",");
        String sheetName = parts[0];
        return sheetName;

    }
    public String getRowNumber(String testData){
        String[] parts = testData.split(",");
        String rowNumber = parts[1];
        return rowNumber;

    }

    /**
     * Get parameter Value from input JSON file
     *
     * @param testcaseName testcase
     * @param parameter parameter
     * @return parameter value
     */
    private static String getParameterFromJson(String testcaseName, String parameter) {
        JSONObject testcaseObject = (JSONObject) jsonObject.get(testcaseName);
        if (testcaseObject.get(parameter) != null)
            return testcaseObject.get(parameter).toString();
        return "";
    }
    public static void main(String args[]){
        List<TestCaseScenario> testCaseScenario = getTestCaseScenarios();
        for(int i=0;i<testCaseScenario.size();i++){
            System.out.println("Test case information:----------------------------");
            System.out.println("Test case name is"+testCaseScenario.get(i).getName());
        }
    }
    public void getTestDataUsingMethodName(){

    }
}