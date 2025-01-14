package DataProviderUtility;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private ExcelUtils() {
	};
	private static FileInputStream fis;
	private static XSSFWorkbook workbook;
	
	public static List<Map<String, String>> getTestDetails(String filePath,String sheetName) {
		try {
			fis=new FileInputStream(filePath);
		} catch (Exception e) {
			throw new RuntimeException("Unable to load the excel file in the file path");
		}
		
		try {
			workbook=new XSSFWorkbook(fis);
		}catch (Exception e) {
			throw new RuntimeException("Unable to open the workbook");
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Map<String, String> map=null;
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		int noOfRows=sheet.getLastRowNum();
		int noOfColums=sheet.getRow(0).getLastCellNum();
		
		for(int i=1;i<=noOfRows;i++)
		{
			map=new HashMap<String, String>();
			for(int j=0;j<noOfColums;j++)
			{
				String key=sheet.getRow(0).getCell(j).getStringCellValue();
				String value=sheet.getRow(i).getCell(j).getStringCellValue();
				map.put(key, value);
			}
			list.add(map);
		}
		return list;
	}
}
