package samplesite.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataReader {

		FileInputStream file;


		public String path;
		public static FileInputStream fis = null;
		public FileOutputStream fileOut = null;
		public Sheet sheet = null;
		private static Workbook workbook = null;
		private Row row = null;
		private Cell cell = null;
		static TreeMap<String, String> dataMap = new TreeMap<String, String>();

		public TestDataReader() throws IOException {

			try {
				
				fis = new FileInputStream(new File(
						ConfigureConstants.FILEPATH));
				
				if (ConfigureConstants.FILEPATH.endsWith("xlsx")) {
					workbook = new XSSFWorkbook(fis);
				} else if (ConfigureConstants.FILEPATH.endsWith("xls")) {
					workbook = new HSSFWorkbook(fis);
				} else {
					fis.close();
					throw new IllegalArgumentException(
							"The specified file is not Excel file");
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public LinkedHashMap<String, String>[][] gConvertToArray(List< LinkedHashMap<String, String>> objSheetData)
		{
			
			LinkedHashMap<String, String>[][] araayTestData = new LinkedHashMap[objSheetData.size()][1];
			for(int i=0;i<objSheetData.size();i++)
			{
				araayTestData[i][0]= objSheetData.get(i);
			}
			
			return araayTestData;
			
		}


		public static List< LinkedHashMap<String, String>> gFuncReadTestData(String sheetName)
				throws Exception {
			List<LinkedHashMap<String, String>> sheetData = new ArrayList<LinkedHashMap<String, String>>();
			LinkedHashMap<String, String> rowdata = new LinkedHashMap<String, String>();
			
			try {
				int firstRowNumber, lastRowNumber, firstCellNumber, noofcolumns = 0, tempcols;
				Row headerRow = null;
				Row tempRow = null;
				Row firstRow = null;

				// Get first sheet from the workbook
				Sheet sheet = workbook.getSheet(sheetName);
				firstRowNumber = sheet.getFirstRowNum();
				lastRowNumber = sheet.getLastRowNum();

				firstRow = sheet.getRow(lastRowNumber);

				firstCellNumber = firstRow.getFirstCellNum();
				for (int row = (firstRowNumber + 1); row <= lastRowNumber; row++) {
					tempRow = sheet.getRow(row);
					tempcols = tempRow.getLastCellNum();
					if (noofcolumns < tempcols) {
						noofcolumns = tempcols;
					}
				}


				String iteration;
				headerRow = sheet.getRow(0);
				for (int row = (firstRowNumber + 1); row <= lastRowNumber; row++)
				{
					
					tempRow = sheet.getRow(row);
					
					if (tempRow!=null )
					{
						rowdata = new LinkedHashMap<String, String>();

						for (int col = (firstCellNumber); col < noofcolumns; col++)
						{
							if (tempRow.getCell(col) == null)
							{
								rowdata.put(headerRow.getCell(col).toString(), "");
							}
							else
							{
								String strValue = tempRow.getCell(col).toString();
								//Get date
								if (strValue.toUpperCase().startsWith("CDATE_")) {
									strValue = getDate(strValue);
								}

								//Get time
								if (strValue.toUpperCase().startsWith("CTIME_")) {
									strValue = getTime(strValue);
								}

								//Get unique data with timestamp
								if (strValue.toUpperCase().startsWith("UNIQUE")) {
									strValue = getUniqueValue(strValue);
								}

//	                            arrayListScenario.add(strValue);
								rowdata.put(headerRow.getCell(col).toString(), strValue);

							}
						}

						sheetData.add(rowdata);
					}
				}
			}
			catch(NullPointerException ne)
			{
				System.out.println("Exception while reading test data from sheet "+sheetName);
				throw ne;
			}
			catch(Exception e)
			{
				throw e;
			}
			return sheetData;
		}

		/**
		 * @param senarioName
		 * @param tableName
		 * @param coloName
		 * @return
		 * @throws Exception
		

		/*	  1. Current Date:CDATE_TODAY -(Returns current date)
	          2.Future Date:CDATE_TODAY#4(Adds 4 days to the current date and
	              returns the value in date format specified).
	          3.Past Date: CDATE_TODAY_4(Subtract 4 days to the current date and
	              returns the value in date format specified). */
		public static String getDate(String strValue) throws ArrayIndexOutOfBoundsException,Exception
		{
			int intDays;
			try
			{
				Calendar objCal = Calendar.getInstance();

				SimpleDateFormat objSdf = new SimpleDateFormat("MM/dd/yyyy");

				objCal.setTime(objSdf.parse(getSystemDate()));

				if (strValue.trim().toUpperCase().equalsIgnoreCase("CDATE_TODAY"))
				{
					strValue = objSdf.format(objCal.getTime());
				}else if (strValue.trim().toUpperCase().contains("CDATE_TODAY_"))
				{
					String [] arrValues = strValue.toUpperCase().split("DAY_");
					intDays = Integer.parseInt(arrValues[1]);
					objCal.add(Calendar.DATE, -intDays);
					strValue = objSdf.format(objCal.getTime());
				}else if (strValue.trim().toUpperCase().contains("CDATE_TODAY#"))
				{
					String [] arrValues = strValue.toUpperCase().split("DAY#");
					intDays = Integer.parseInt(arrValues[1]);
					objCal.add(Calendar.DATE, intDays);
					strValue = objSdf.format(objCal.getTime());
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				throw new RuntimeException(e.toString());
			}
			catch(Exception e)
			{
				throw new RuntimeException(e.toString());
			}
			return strValue;
		}

		/* 1.CTIME_TODAY : Current time
	      2.CTIME_TODAY_HOURS_3:(Decrement of 3 hours to current time).
	      3.CTIME_TODAY_HOURS#3(Increment of 3 hours to current time)
	      4.CTIME_TODAY_MIN_3(Decrement of 3 minutes to current time)
	      5.CTIME_TODAY_MIN#3(Increment of 3 minutes to current time)
	      6.CTIME_TODAY_SECONDS_3(Decrement of 3 seconds to current time)
	      7.CTIME_TODAY_SECONDS#3(Increment of 3 seconds to current time)  */
		public static String getTime(String strValue) throws ArrayIndexOutOfBoundsException,Exception
		{
			int intHours;
			int intMinutes;
			int intSeconds;
			try
			{
				Calendar objCal = Calendar.getInstance();

				SimpleDateFormat objSdf = new SimpleDateFormat("hh:mm a");

				if (strValue.trim().toUpperCase().equalsIgnoreCase("CTIME_TODAY"))
				{
					strValue = objSdf.format(objCal.getTime());
				}
				else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_HOURS_"))
				{
					String [] arrValues = strValue.toUpperCase().split("HOURS_");
					intHours = Integer.parseInt(arrValues[1]);
					objCal.add(Calendar.HOUR, -intHours);
					strValue = objSdf.format(objCal.getTime());
				}
				else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_HOURS#"))
				{
					String [] arrValues = strValue.toUpperCase().split("HOURS#");
					intHours = Integer.parseInt(arrValues[1]);
					objCal.add(Calendar.HOUR, intHours);
					strValue = objSdf.format(objCal.getTime());
				}
				else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_MIN_"))
				{
					String [] arrValues = strValue.toUpperCase().split("MIN_");
					intMinutes = Integer.parseInt(arrValues[1]);
					objCal.add(Calendar.MINUTE, -intMinutes);
					strValue = objSdf.format(objCal.getTime());
				}
				else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_MIN#"))
				{
					String [] arrValues = strValue.toUpperCase().split("MIN#");
					intMinutes = Integer.parseInt(arrValues[1]);
					objCal.add(Calendar.MINUTE, intMinutes);
					strValue = objSdf.format(objCal.getTime());
				}
				else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_SECONDS_"))
				{
					String [] arrValues = strValue.toUpperCase().split("SECONDS_");
					intSeconds = Integer.parseInt(arrValues[1]);
					objCal.add(Calendar.SECOND, -intSeconds);
					strValue = objSdf.format(objCal.getTime());
				}
				else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_SECONDS#"))
				{
					String [] arrValues = strValue.toUpperCase().split("SECONDS#");
					intSeconds = Integer.parseInt(arrValues[1]);
					objCal.add(Calendar.SECOND, intSeconds);
					strValue = objSdf.format(objCal.getTime());
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				throw new RuntimeException(e.toString());
			}
			catch(Exception e)
			{
				throw new RuntimeException(e.toString());
			}
			return strValue;
		}

		/* input should start with 'unique' keyword ex : UniqueSamuel */
		public static String getUniqueValue(String strValue) throws InterruptedException
		{
			Thread.sleep(1000);
			Calendar objCalNow = Calendar.getInstance();
			if (strValue.equalsIgnoreCase(""))
			{
				return strValue;
			}
			else
			{
				strValue = strValue + objCalNow.get(Calendar.YEAR)
						+ (objCalNow.get(Calendar.MONTH)+1)
						+ objCalNow.get(Calendar.DAY_OF_MONTH)
						+ objCalNow.get(Calendar.HOUR)
						+ objCalNow.get(Calendar.MINUTE)
						+ objCalNow.get(Calendar.SECOND)
				;
				objCalNow = null;
				strValue = strValue.replace("UNIQUE", "");
				strValue = strValue.replace("unique", "");
				return strValue;
			}
		}


		public static String getSystemDate()
		{
			String strValue = "";
			try
			{
				Calendar objCal = Calendar.getInstance();
				SimpleDateFormat objSdf = new SimpleDateFormat("MM/dd/yyyy");
				strValue = objSdf.format(objCal.getTime());
			}
			catch(Exception e)
			{
				throw new RuntimeException(e.toString());
			}

			return strValue;
		}

		public static String removeLeadingCharacters(String strInput, String strLeadingCharacter, String strDelimitor)
		{
			try
			{
				String strArray[] = null;
				if (strDelimitor.length() == 0)
				{
					strArray = new String[1];
					strArray[0] = strInput;
				}
				else
				{
					strArray = strInput.split(strDelimitor);
				}
				String strTemp = "";
				strInput = "";
				for (int i= 0 ;i< strArray.length; i++)
				{
					strTemp = strArray[i].replaceAll("^"+strLeadingCharacter+"+", "");
//					strTemp = StringUtils.stripStart(strArray[i],strTrailingCharacter);
					if ( i!=strArray.length-1)
					{
						strInput = strInput+strTemp+strDelimitor;
					}
					else
					{
						strInput = strInput+strTemp;
					}
				}

			}
			catch(Exception e)
			{
				throw new RuntimeException(e.toString());
			}

			return strInput;
		}
}

