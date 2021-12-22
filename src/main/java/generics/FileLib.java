package generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class FileLib {
	
	public static Workbook wb;
public String getPropertyValue(String key) throws IOException {
	FileInputStream fis = new FileInputStream("./data/commondata.property");
	Properties p = new Properties();
	p.load(fis);
	return p.getProperty(key);
}
public String getExcelValue(String SheetName, int row, int cell)
		throws EncryptedDocumentException, FileNotFoundException, IOException, InvalidFormatException {

	wb = WorkbookFactory.create(new FileInputStream("./data/Test.xlsx"));
	String value = wb.getSheet(SheetName).getRow(row).getCell(cell).toString();
	return value;
}

public void setExcelValue(String SheetName, int row, int cell, String value)
		throws EncryptedDocumentException, FileNotFoundException, IOException, InvalidFormatException {
	wb = WorkbookFactory.create(new FileInputStream(new File("./data/Test.xlsx")));

	if (wb.getSheet(SheetName) == null ) {
			wb.createSheet(SheetName);
			}
	if(wb.getSheet(SheetName).getRow(row) == null) {
			wb.getSheet(SheetName).createRow(row);

			} 	
	
			wb.getSheet(SheetName).getRow(row).createCell(cell).setCellValue(value);
			wb.write(new FileOutputStream(new File("./data/Test.xlsx")));
}
}