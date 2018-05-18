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

    // �ж�Excel�İ汾,��ȡWorkbook
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
	Workbook wb = null;
	if (file.getName().endsWith(GhCommon.EXCEL_XLS)) { // Excel 2003
	    wb = new HSSFWorkbook(in);
	} else if (file.getName().endsWith(GhCommon.EXCEL_XLSX)) { // Excel 2007/2010
	    wb = new XSSFWorkbook(in);
	}
	return wb;
    }

    // �ж��ļ��Ƿ���excel
    public static void checkExcelVaild(File file) throws Exception {
	if (!file.exists()) {
	    throw new Exception("�ļ�������");
	}
	if (!(file.isFile()
		&& (file.getName().endsWith(GhCommon.EXCEL_XLS) || file.getName().endsWith(GhCommon.EXCEL_XLSX)))) {
	    throw new Exception("�ļ�����Excel");
	}
    }

    // ��ָ����Sheet������List
    public List<String> exportListFromExcel(String filePath, String sheetName) throws IOException {

	SimpleDateFormat fmt = new SimpleDateFormat(GhCommon.YYYYMMDD);
	List<String> lstExcelData = new ArrayList<String>();
	File file = new File(filePath); // �����ļ�����
	FileInputStream fs = null;
	try {
	    // ͬʱ֧��Excel 2003��2007
	    fs = new FileInputStream(file); // �ļ���
	    Workbook workbook = getWorkbok(fs, file);

	    // ��ȡҪ������Sheet
	    Sheet sheet = workbook.getSheet(sheetName); 

	    // Ϊ������һ��Ŀ¼����count
	    int count = 0;
	    StringBuffer rowValue = null;

	    for (Row row : sheet) {
		// ������һ�е�Ŀ¼
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
		    case Cell.CELL_TYPE_STRING: // �ı�
			cellValue = cell.getRichStringCellValue().getString();
			break;
		    case Cell.CELL_TYPE_NUMERIC: // ���֡�����
			// ���ڸ�ʽ
			short style = cell.getCellStyle().getDataFormat();
			if (DateUtil.isCellDateFormatted(cell)) {
			    cellValue = fmt.format(cell.getDateCellValue());
			} else if (style == 178) {// yyyy��MM��dd��
			    cellValue = fmt.format(cell.getDateCellValue());
			} else {
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    cellValue = String.valueOf(cell.getRichStringCellValue().getString());
			}
			break;
		    case Cell.CELL_TYPE_FORMULA: // ��ʽ
			// �õ���Ӧ��Ԫ��Ĺ�ʽ
			// cellValue = cell.getCellFormula() + "#";
			// �õ���Ӧ��Ԫ����ַ���
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cellValue = String.valueOf(cell.getRichStringCellValue().getString());
			break;
		    case Cell.CELL_TYPE_BOOLEAN: // ������
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		    case Cell.CELL_TYPE_BLANK: // �հ�
			cellValue = cell.getStringCellValue();
			break;
		    case Cell.CELL_TYPE_ERROR: // ����
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

	    // �P�]�ļ�
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

    // ����հ���
    public void listBlankLineClear(List inputList) throws IOException {

	if (inputList != null) {
	    for (int i = 0; i < inputList.size(); i++) {
		String strLineData = (String) inputList.get(i);
		String[] strLineDateArray = strLineData.split(GhCommon.ALARM);
		// ����Ϊ��ʱ���������
		if (strLineDateArray.length < 3) {
		    inputList.remove(i);
		    i--;
		}
	    }
	}

    }

}