package com.generic.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.*;

import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.sun.org.apache.bcel.internal.classfile.LocalVariable;

import java.io.*;
import java.text.MessageFormat;
import java.util.Calendar;


public class XlsUtils {
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	private XSSFCellStyle my_style = null;
	private XSSFFont my_font = null;

	public static loggerUtils logs = SelTestCase.logs;

	public XlsUtils(String path) {
		System.out.println("Inputs file: " + path);

		this.path = path;
		try {
			fis = new FileInputStream(path);
			ZipSecureFile.setMinInflateRatio(0);
			try {
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);
			} finally {
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// returns the row count in a sheet
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}

	// returns the data from a cell
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";
			if (cell.getCellTypeEnum() == CellType.STRING)
				return  ((cell.getStringCellValue().equals("null")) ? "":cell.getStringCellValue());
			else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in the form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
				}

				return cellText;
			} else if (cell.getCellTypeEnum() == CellType.BLANK)
				return "";
			else
				return cell.getStringCellValue();

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
	}

	// returns the data from a cell
	public String getCellData(String sheetName,int colNum,int rowNum){
		try {
			if (rowNum <= 0)
			{
				logs.debug("row number is not correct: " + row.toString());
				return "";
			}
			//logs.debug("getting data from sheet: " + sheetName);
			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
			{
				logs.debug("Shee is not exist: "+ sheetName);
				return "";
			}

			sheet = workbook.getSheetAt(index);
			
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			if (cell.getCellTypeEnum() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
				// Abeer change: use NumberToTextConverter to get the correct number value as
				// written in Excel sheet
				String cellText = NumberToTextConverter.toText(cell.getNumericCellValue());
				/*
				 * String cellText = String.valueOf(cell.getNumericCellValue()); if
				 * (cellText.contains(".0")) { cellText = cellText.replace(".0", ""); }
				 */
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
				}

				return cellText;
			}else if (cell.getCellTypeEnum() ==  CellType.BOOLEAN) {
				logs.debug("Cell is boolean: " + cell.getCellTypeEnum() + " " +String.valueOf(cell.getBooleanCellValue()) );
				cell.setCellType(CellType.STRING);
				logs.debug("Cell is not fit to any type : " + cell.getCellTypeEnum() + "sheet:ROW/COL: " + sheetName+":"+colNum +"/"+rowNum );
				return String.valueOf(cell.getBooleanCellValue());
			}else if (cell.getCellTypeEnum() == CellType.BLANK)
				return "";
			else {
				logs.debug("Cell is not fit to any type : " + cell.getCellTypeEnum() + " sheet:ROW/COL: " + sheetName+":"+colNum +"/"+rowNum );
				cell.setCellType(CellType.STRING);
				logs.debug("Cell new Value is  :  " + cell.getStringCellValue());
				return String.valueOf(cell.getStringCellValue());
			}
		} catch (Exception e) {

			logs.debug("row " + rowNum + " or column " + colNum + " does not exist  in xls");
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
    }// getCellData()

	public int getColName(String colName) {
		row = sheet.getRow(5);
		int colNum = -1;
	
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(colName))
			{
				colNum = i;
				break;
			}
		}
		return colNum;
	}//getColName

	private int getRowNumberOfPlant(String sheetName, String rowName) 
	{
		int guidCol = 3;
		return getRowNumber(sheetName, rowName, guidCol );
	}
	
	private int getRowNumberOfUser(String sheetName, String rowName) 
	{
		int guidCol = 2;
		return getRowNumber(sheetName, rowName, guidCol );
	}
	
	private int getRowNumber(String sheetName, String rowName, int guidCol) {
		int rowNum = -1;  
		int plantNamesStarts = 4 ; 
		for (int rowNumber = plantNamesStarts; rowNumber < getRowCount(sheetName); rowNumber++) {
			row = sheet.getRow(rowNumber);
			if (row.getCell(guidCol).getStringCellValue().trim().toLowerCase().equals(rowName.toLowerCase()))
			{
				rowNum = rowNumber;
				break;
			}
			
		}
		
		//logs.debug("Row number is: "+rowNum +" for rowName: "+rowName);
		return rowNum+1;
	}//getRowNumber
	
	// returns true if data is set successfully else false - write plant data 
	public boolean setCellData(String sheetName, int colNumber, String PlantName, String data, boolean valid) {
		int rowNumber = getRowNumberOfPlant(sheetName, PlantName);
		//logs.debug(MessageFormat.format(LoggingMsg.SHEET_NAME_LOCATION_TO_WRITE, sheetName, PlantName, colNumber, data));
		return setCellData(sheetName, colNumber, rowNumber, data, valid);

	}
	
	// returns true if data is set successfully else false - write user valid / not valid  
	public boolean setUserValid(String user, boolean valid) {
	int guideCol = 2;
	int rowNumber = getRowNumberOfUser(SheetVariables.GeneralTab, user);
	return setValid(user, guideCol,rowNumber, valid);
	}
	
	// returns true if data is set successfully else false - write plant valid / not valid
	public boolean setPlantValid(String plant, boolean valid) {
		int guideCol = 3;
		int rowNumber = getRowNumberOfPlant(SheetVariables.GeneralTab, plant);
		return setValid(plant, guideCol,rowNumber ,valid);
	}
		
	// returns true if data is set successfully else false - write valid / not valid
	public boolean setValid(String user,int guideCol, int guidRow, boolean valid) {

		String[] Sheets = { SheetVariables.GeneralTab,SheetVariables.PerformanceTab, SheetVariables.Aggregation, SheetVariables.HealthTab,
				SheetVariables.HeatMapTab, SheetVariables.InsightsTabs };
		boolean writeUsersatus = true;
		
		for (String sheet : Sheets)
		{
			logs.debug("marking user/ plant " + user + " as  valid " + valid);
			writeUsersatus = writeUsersatus &  setCellData(sheet, guideCol, guidRow, user, valid);
		}
		return writeUsersatus;
		

	}
	
	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName, int colNumber, int rowNumber, String data, boolean valid) {
		try {
						
			int rowNum = rowNumber;
			int sheetIndex = workbook.getSheetIndex(sheetName);
			
			sheet = workbook.getSheetAt(sheetIndex);

			if (sheetIndex == -1 || rowNum == 0) {
				logs.debug(MessageFormat.format(LoggingMsg.NOT_EXIST_MSG, "Sheet ") + " or row is not exist");
				return false;
			} else {
				row = sheet.getRow(rowNum - 1);
				if (row == null) {
					logs.debug("row is valid");
					row = sheet.createRow(rowNum - 1);
				} else {
					
					cell = row.getCell(colNumber);
					if (cell == null) {
						cell = row.createCell(colNumber);
					}
					
					my_style = workbook.createCellStyle();
					
					//red color for differences 
					if (data.contains("Red")) {
						my_font = workbook.createFont();
						my_font.setColor(XSSFFont.COLOR_RED);
						my_font.setFamily(XSSFFont.DEFAULT_FONT_SIZE);
						my_font.setFontName("Arial");
						my_style.setFont(my_font);
						data = data.replace("Red", "");
					}
					
					//red color for differences 
					if (data.contains("Blue")) {
						my_font = workbook.createFont();
						my_font.setColor(IndexedColors.BLUE.getIndex());
						my_font.setFamily(XSSFFont.DEFAULT_FONT_SIZE);
						my_font.setFontName("Arial");
						my_style.setFont(my_font);
						data = data.replace("Blue", "");
					}
					
					//mark invalid cells with red background
					if (valid) {
						my_style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
					} else {
						my_style.setFillForegroundColor(IndexedColors.RED.getIndex());
					}
					my_style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
					
					//maintain boarders 
					my_style.setBorderBottom(BorderStyle.THIN);  
					my_style.setBottomBorderColor(IndexedColors.BLACK.getIndex());  
					my_style.setBorderRight(BorderStyle.THIN);  
					my_style.setRightBorderColor(IndexedColors.BLACK.getIndex());  
					my_style.setBorderTop(BorderStyle.THIN);  
					my_style.setTopBorderColor(IndexedColors.BLACK.getIndex());  
					
					cell.setCellStyle(my_style);
					cell.setCellValue(data);
					//logs.debug("setting data done ");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}// setCell

	public void writeExcelFile() throws FileNotFoundException, IOException, InterruptedException {
		try {
			logs.debug("Writing data to excel sheet");
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			logs.debug("Writing Done");
		} catch (Exception e) {
			logs.debug("closing the file failed");
			e.printStackTrace();
		} finally {
			logs.debug("try to clean file");
			if (fileOut != null) {
				try {
					Thread.sleep(4000);
					logs.debug("closing file again");
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void readExcelFile() throws FileNotFoundException, IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
	}

	// find whether sheets exists
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();

	}

	public int getCellRowNum(String sheetName, String colName, String cellValue) {

		for (int i = 1; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
				return i;
			}
		}
		return -1;

	}

}
