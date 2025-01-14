package ConstantUtility;

public final class ConstantUtility {

	private ConstantUtility() {};
	
	private static final String excelFileName=System.getProperty("user.dir")+"\\src\\test\\resources\\Automation Test Data.xlsx";

	public static String getExcelfilename() {
		return excelFileName;
	}
}
