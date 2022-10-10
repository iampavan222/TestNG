package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static Workbook wb;
	public static Sheet sh;
	public static Row row;
	public static Cell cell;
	public static CellStyle style;
	
	public static int getRowCount(String xlfile,String xlsheet) throws IOException {
	fi= new FileInputStream(xlfile);
	wb = new XSSFWorkbook(fi);
	sh=wb.getSheet(xlsheet);
	int rowcount=sh.getLastRowNum();
	wb.close();
	return rowcount;
   }
	
	public static short getCellCount(String xlfile,String xlsheet,int rnum) throws IOException {
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		sh=wb.getSheet(xlsheet);
		row=sh.getRow(rnum);
		short cellcount=row.getLastCellNum();
		wb.close();
		return cellcount;
	}
	
	public static String getStringData(String xlfile,String xlsheet,int rnum,int cnum) throws IOException {
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		sh=wb.getSheet(xlsheet);
		row=sh.getRow(rnum);
		String data;
		try {
		cell=row.getCell(cnum);
		data=cell.getStringCellValue();
		}
		catch(Exception e) {
			data=" ";
		}
		wb.close();
		return data;
    }
	
	public static double getNumericData(String xlfile,String xlsheet,int rnum,int cnum) throws IOException {
		fi= new FileInputStream(xlfile);
		wb= new XSSFWorkbook(fi);
		sh=wb.getSheet(xlsheet);
		row=sh.getRow(rnum);
		double data;
		try {
			cell=row.getCell(cnum);
			data=cell.getNumericCellValue();
		}catch(Exception e) {
			data=0.0;
		}
		wb.close();
		return data;
	}
	
	public static boolean getBooleanData(String xlfile,String xlsheet,int rnum,int cnum) throws IOException {
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		sh=wb.getSheet(xlsheet);
		row=sh.getRow(rnum);
		boolean data;
		try {
			cell=row.getCell(cnum);
			data=cell.getBooleanCellValue();
		}catch(Exception e) {
			data=false;
		}
		wb.close();
		return data;
	}
	
	public static void setCellData(String xlfile,String xlsheet,int rnum,int cnum,String data) throws IOException {
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook(fi);
	sh=wb.getSheet(xlsheet);
	row=sh.getRow(rnum);
	cell=row.createCell(cnum);
	cell.setCellValue(data);
	
	fo= new FileOutputStream(xlfile);
	wb.write(fo);
	wb.close();
	}
	
	public static void setGreenColour(String xlfile,String xlsheet,int rnum,int cnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		row = sh.getRow(rnum);
		cell = row.getCell(cnum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
	}
	public static void setRedColour(String xlfile,String xlsheet,int rnum,int cnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		row = sh.getRow(rnum);
		cell = row.getCell(cnum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
	}

}
