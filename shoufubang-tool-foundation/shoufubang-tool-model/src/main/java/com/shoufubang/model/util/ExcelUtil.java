package com.shoufubang.model.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * <pre>项目名称：xhcf-back-web    
 * 类名称：ExcelUtil    
 * 类描述：操作excel的类    
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2016年10月13日 下午6:00:16    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2016年10月13日 下午6:00:16    
 * 修改备注：       
 * @version </pre>
 */
public class ExcelUtil {
	private static String EXCEL_2003 = ".xls";
	private static String EXCEL_2007 = ".xlsx";
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
	
	/**
	 * <pre>readExcel(读取excel)   
	 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
	 * 创建时间：2016年10月14日 上午11:22:59    
	 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
	 * 修改时间：2016年10月14日 上午11:22:59    
	 * 修改备注： 
	 * @param file
	 * @return</pre>
	 */
	public static List<Map<String,String>> readExcel(MultipartFile file) {
		try {
			if(!file.isEmpty()) {//非空判断
				String fileName = file.getOriginalFilename(); //上传的文件名
				InputStream inputStream = file.getInputStream();
				if(fileName.toLowerCase().endsWith(EXCEL_2003)) {
					return readExcel2003ByPOI(inputStream);
				} 
				if(fileName.toLowerCase().endsWith(EXCEL_2007)) {
					return readExcel2007ByPOI(inputStream);
				}
				return null;
			} else {
				return null;
			}
		} catch (IOException e) {
			LOGGER.error("读取上传excel失败!",e.getMessage());
			return null;
		}
	}
	/**
	 * <pre>readExcel2007ByPOI(读取2007版本的excel)   
	 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
	 * 创建时间：2016年10月14日 上午11:23:31    
	 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
	 * 修改时间：2016年10月14日 上午11:23:31    
	 * 修改备注： 
	 * @param inputStream
	 * @return</pre>
	 * @throws IOException 
	 */
	private static List<Map<String,String>> readExcel2007ByPOI(InputStream inputStream) throws IOException {
		List<Map<String,String>> dataResultList;
		XSSFWorkbook workBook = null;
		try {
			dataResultList = new ArrayList<Map<String,String>>();
			workBook = new XSSFWorkbook(inputStream);
			/*//获取sheet页数量
			Integer sheetNum = wb.getNumberOfSheets();*/
			XSSFSheet sheet = workBook.getSheetAt(0); // 获取第一个sheet表
			for (int i = sheet.getTopRow(); i <= sheet.getLastRowNum() ; i++) {
				XSSFRow rowData = sheet.getRow(i);
				if (i == sheet.getTopRow()) {
					continue; //标题不读入
				}
				Map<String,String> dataMap = new HashMap<String, String>();
				for (int j = rowData.getFirstCellNum(); j < rowData.getLastCellNum(); j++) {
					XSSFCell cell = rowData.getCell(j);
					System.out.println("excel"+(j+1)+"信息："+cell.getStringCellValue());
					dataMap.put((j+1)+"", cell.getStringCellValue());
				}
			}
			return dataResultList;
		} finally {
			try {
				if(inputStream!=null) 
					inputStream.close();
				if(workBook!=null) 
					workBook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * <pre>readExcel2003ByPOI(读取2003版本excel)   
	 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
	 * 创建时间：2016年11月22日 下午4:46:36    
	 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
	 * 修改时间：2016年11月22日 下午4:46:36    
	 * 修改备注： 
	 * @param inputStream
	 * @return</pre>
	 */
	private static List<Map<String,String>> readExcel2003ByPOI(InputStream inputStream) {
		return null;
	}
	
	/**
	 * <pre>writeExcel2003ByPOI(POI工具写2003版本excel)   
	 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
	 * 创建时间：2016年11月22日 下午4:44:12    
	 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
	 * 修改时间：2016年11月22日 下午4:44:12    
	 * 修改备注： 
	 * @param title
	 * @param headers
	 * @param columnKeys
	 * @param dataList
	 * @param out
	 * @return</pre>
	 */
	public static HSSFWorkbook writeExcel2003ByPOI(String title,String[] headers,String[] columnKeys,List<Map<String,Object>> dataList,OutputStream out) {
		//声明一个工作簿
		 HSSFWorkbook workBook = new HSSFWorkbook();
		 //生成一个表格
		 HSSFSheet sheet = workBook.createSheet(title); 
		 //创建标题行
		 HSSFRow row = sheet.createRow(0); 
		 //创建标题单元格样式
		 HSSFCellStyle head_Style = workBook.createCellStyle();
		 head_Style.setAlignment(HorizontalAlignment.CENTER); //样式居中
		 head_Style.setBorderBottom(BorderStyle.THIN); //边框
		 head_Style.setBorderLeft(BorderStyle.THIN);   //边框
		 head_Style.setBorderRight(BorderStyle.THIN);  //边框
		 head_Style.setBorderTop(BorderStyle.THIN);    //边框
		 //创建标题字体
		 HSSFFont head_font=workBook.createFont();
		 head_font.setFontName("宋体");//设置头部字体为宋体
		 head_font.setBold(true); //粗体
		 head_font.setFontHeightInPoints((short) 11); //字体大小
		 //字体设置给样式
		 head_Style.setFont(head_font);
		 //设置标题行
		 for(int i=0;i<headers.length;i++) {
			 HSSFCell cellTiltle = row.createCell(i);
			 cellTiltle.setCellStyle(head_Style);
			 HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			 cellTiltle.setCellValue(text);
		 }
		//创建内容单元格样式
		HSSFCellStyle content_Style = workBook.createCellStyle();//创建数据单元格样式(数据库数据样式)
		content_Style.setBorderBottom(BorderStyle.THIN);
		content_Style.setBorderLeft(BorderStyle.THIN);
		content_Style.setBorderRight(BorderStyle.THIN);
		content_Style.setBorderTop(BorderStyle.THIN); 
		//创建标题字体
		HSSFFont content_font=workBook.createFont();
		content_font.setFontName("宋体");//设置头部字体为宋体
		content_font.setFontHeightInPoints((short) 11); //字体大小
		//字体设置给样式
		content_Style.setFont(content_font);
		//设置数据行
		for(int i=0;i<dataList.size();i++) {
			HSSFRow contentRow = sheet.createRow(i+1); 
			HSSFCell cellContent0 = contentRow.createCell(0);
			cellContent0.setCellStyle(content_Style);
			cellContent0.setCellValue(i+1);
			for(int j=0;j<columnKeys.length;j++) {
				HSSFCell cellContent = contentRow.createCell(j+1);
				cellContent.setCellStyle(content_Style);
				Object object = dataList.get(i).get(columnKeys[j]);
				object = null == object ? "" : object;
				HSSFRichTextString text = new HSSFRichTextString(String.valueOf(object));
				cellContent.setCellValue(text);
			}
		}
		//自动调整列宽
		for(int i=0;i<headers.length;i++) {
			sheet.autoSizeColumn(i);
		}
		return workBook;
	}
}
