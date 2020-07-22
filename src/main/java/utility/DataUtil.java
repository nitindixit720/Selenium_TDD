package main.java.utility;


import com.aventstack.extentreports.Status;
import main.java.pombase.BasePage;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

public class DataUtil extends BasePage {


    public DataUtil() {
        super(driver, extentTest);
    }


	/*
     * Data provider class for reading test data and verify method should run or
	 * not.
	 *
	 */

    @DataProvider(name = "getData")
    public static Object[][] getData(Method method) {
        Object[][] data = null;
        try {
            ExcelReader excelread = new ExcelReader();
            String sheetName = method.getDeclaringClass().getSimpleName();

            int testStartRowNum = 0;
            while (!excelread.getCellData(sheetName, 0, testStartRowNum).equalsIgnoreCase(method.getName().toString())) {
                testStartRowNum++;
            }
            System.out.println("Test starts from row - " + testStartRowNum);

            int dataStartRowNum = testStartRowNum + 2;
            int rows = 0;
            while (!excelread.getCellData(sheetName, 0, dataStartRowNum + rows).equals("")) {
                rows++;
            }
            System.out.println("Total rows are  - " + rows);

            int colStartRowNum = testStartRowNum + 1;
            int cols = 0;
            while (!excelread.getCellData(sheetName, cols, colStartRowNum).equals("")) {
                cols++;
            }
            System.out.println("Total cols are  - " + cols);

            data = new Object[rows][1];
            int dataRow = 0;
            LinkedHashMap<String, String> table = null;
            for (int rNum = dataStartRowNum; rNum < dataStartRowNum + rows; rNum++) {
                table = new LinkedHashMap<String, String>();
                for (int cNum = 0; cNum < cols; cNum++) {
                    String key = excelread.getCellData(sheetName, cNum, colStartRowNum).toLowerCase().trim();
                    String value = excelread.getCellData(sheetName, cNum, rNum).trim();
                    table.put(key, value);
                    System.out.println(
                            "----------------Key----------------" + key + "--------value-------------" + value);
                }
                System.out.println("---------------------" + table);
                data[dataRow][0] = table;
                dataRow++;
            }


            if (data == null) {
                return new Object[0][0];

            }

        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Exception:  " + e);
        }
        return data;
    }
}
