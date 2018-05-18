package zhf.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

    public static void main(String[] args) {
	ExcelRead ex = new ExcelRead();
	try {
	    List<String> lstExcelData = ex
		    .exportListFromExcel("C:\\importExcel\\2018 Insurance GH Booking List_20180427.xlsx", "GH Details");
	    ex.listBlankLineClear(lstExcelData);
	    for (int i = 0; i < lstExcelData.size(); i++) {
		String strLineData = (String) lstExcelData.get(i);
		System.out.println(strLineData);
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    // 判断Excel的版本,获取Workbook
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
	Workbook wb = null;
	if (file.getName().endsWith(GhCommon.EXCEL_XLS)) { // Excel 2003
	    wb = new HSSFWorkbook(in);
	} else if (file.getName().endsWith(GhCommon.EXCEL_XLSX)) { // Excel 2007/2010
	    wb = new XSSFWorkbook(in);
	}
	return wb;
    }

    // 判断文件是否是excel
    public static void checkExcelVaild(File file) throws Exception {
	if (!file.exists()) {
	    throw new Exception("文件不存在");
	}
	if (!(file.isFile()
		&& (file.getName().endsWith(GhCommon.EXCEL_XLS) || file.getName().endsWith(GhCommon.EXCEL_XLSX)))) {
	    throw new Exception("文件不是Excel");
	}
    }

    // 由指定的Sheet导出至List
    public List<String> exportListFromExcel(String filePath, String sheetName) throws IOException {

	SimpleDateFormat fmt = new SimpleDateFormat(GhCommon.YYYYMMDD);
	List<String> lstExcelData = new ArrayList<String>();
	File file = new File(filePath); // 创建文件对象
	FileInputStream fs = null;
	try {
	    // 同时支持Excel 2003、2007
	    fs = new FileInputStream(file); // 文件流
	    Workbook workbook = getWorkbok(fs, file);

	    // 获取要遍历的Sheet
	    Sheet sheet = workbook.getSheet(sheetName); 

	    // 为跳过第一行目录设置count
	    int count = 0;
	    StringBuffer rowValue = null;

	    for (Row row : sheet) {
		// 跳过第一行的目录
		if (count == 0) {
		    count++;
		    continue;
		}
		rowValue = new StringBuffer();
		for (Cell cell : row) {
		    if (cell.toString() == null) {
			continue;
		    }
		    int cellType = cell.getCellType();
		    String cellValue = "";
		    switch (cellType) {
		    case Cell.CELL_TYPE_STRING: // 文本
			cellValue = cell.getRichStringCellValue().getString();
			break;
		    case Cell.CELL_TYPE_NUMERIC: // 数字、日期
			// 日期格式
			short style = cell.getCellStyle().getDataFormat();
			if (DateUtil.isCellDateFormatted(cell)) {
			    cellValue = fmt.format(cell.getDateCellValue());
			} else if (style == 178) {// yyyy年MM月dd日
			    cellValue = fmt.format(cell.getDateCellValue());
			} else {
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    cellValue = String.valueOf(cell.getRichStringCellValue().getString());
			}
			break;
		    case Cell.CELL_TYPE_FORMULA: // 公式
			// 得到对应单元格的公式
			// cellValue = cell.getCellFormula() + "#";
			// 得到对应单元格的字符串
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cellValue = String.valueOf(cell.getRichStringCellValue().getString());
			break;
		    case Cell.CELL_TYPE_BOOLEAN: // 布尔型
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		    case Cell.CELL_TYPE_BLANK: // 空白
			cellValue = cell.getStringCellValue();
			break;
		    case Cell.CELL_TYPE_ERROR: // 错误
			cellValue = GhCommon.BLANK;
			break;
		    default:
			cellValue = GhCommon.BLANK;
			break;
		    }
		    // System.out.print(cellValue);
		    rowValue.append(cellValue);
		    rowValue.append(GhCommon.ALARM);
		}
		String strRowValue = rowValue.toString();
		// System.out.println(strRowValue);
		int intRowValueLen = strRowValue.length();
		strRowValue = strRowValue.substring(0, intRowValueLen - 1);
		lstExcelData.add(strRowValue);
		// System.out.println(strRowValue);
	    }

	    // 關閉文件
	    fs.close();

	} catch (IOException e) {
	    throw e;
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (fs != null) {
		fs.close();
	    }
	}

	return lstExcelData;
    }

    // 清除空白行
    public void listBlankLineClear(List inputList) throws IOException {

	if (inputList != null) {
	    for (int i = 0; i < inputList.size(); i++) {
		String strLineData = (String) inputList.get(i);
		String[] strLineDateArray = strLineData.split(GhCommon.ALARM);
		// 姓名为空时，清除此行
		if (strLineDateArray.length < 3) {
		    inputList.remove(i);
		    i--;
		}
	    }
	}

    }

}