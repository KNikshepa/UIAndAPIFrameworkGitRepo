package DataProviderUtility;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import ConstantUtility.ConstantUtility;

public class DataProviderUtility {

	//@DataProvider(parallel = true)
	@DataProvider
	public static Object[] getData(Method m) {
		String testCaseName = m.getName();
		System.out.println("Test Case Name is "+testCaseName);
		List<Map<String, String>> list = ExcelUtils.getTestDetails(ConstantUtility.getExcelfilename(),
				"AutomationTestData");
		System.out.println(list+" is the actual data before filter");

		// Filter data for the specific test case name
		List<Map<String, String>> filteredList=new ArrayList<Map<String,String>>();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).get("Test Case Name").equals(testCaseName))
			{
				if(list.get(i).get("Execute").equals("Yes"))
				{
					filteredList.add(list.get(i));
				}
			}
		}
		System.out.println("Filtered list is "+filteredList.toString());
		return filteredList.toArray();
	}
}
