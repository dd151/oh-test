package com.ohrm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private final String file = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\testData1.xlsx";
	private XSSFWorkbook wb = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public int getNoOfRows(String sheetName) {
		FileInputStream fis = null;
		int result = 0;
		try {
			fis = new FileInputStream(new File(file));
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
			result = sheet.getLastRowNum();
			wb.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getNoOfCols(String sheetName) {
		FileInputStream fis = null;
		int result = 0;
		try {
			fis = new FileInputStream(new File(file));
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
			row = sheet.getRow(0);
			result = row.getLastCellNum();
			wb.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object[][] getSheetData(String sheetName, int rowCount, int colCount) {
		FileInputStream fis = null;
		Object[][] data = new Object[rowCount][colCount];
		try {
			fis = new FileInputStream(new File(file));
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);

			for (int r = 1; r <= rowCount; r++) {
				row = sheet.getRow(r);
				for (int c = 0; c < colCount; c++) {
					cell = row.getCell(c);
					String cellContent = new DataFormatter().formatCellValue(cell);
					data[r - 1][c] = cellContent;
				}
			}
			wb.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
