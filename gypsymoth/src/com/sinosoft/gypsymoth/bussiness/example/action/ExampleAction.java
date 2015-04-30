package com.sinosoft.gypsymoth.bussiness.example.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.jfree.data.category.DefaultCategoryDataset;

import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.example.service.ExampleService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Example;
import com.sinosoft.gypsymoth.utils.ChartUtil;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.ExcelUtil;
import com.sinosoft.gypsymoth.utils.Pagination;

public class ExampleAction extends ActionSupport {
	
	private final Logger _logger = Logger.getLogger(ExampleAction.class);
	
	private ExampleService exampleService;
	private Example example;
	private String actionName;
	private String name;
	private String age;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Example getExample() {
		return example;
	}

	public void setExample(Example example) {
		this.example = example;
	}

	public ExampleService getExampleService() {
		return exampleService;
	}

	public void setExampleService(ExampleService exampleService) {
		this.exampleService = exampleService;
	}
	
	/**
	 * 查询
	 * @return
	 * @throws AppException
	 */
	public String getAllData() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			
			List<Example> list = exampleService.getAllData();
			request.setAttribute("list", list);
			_logger.info("get all data success-------");
		} catch (Exception e) {
			throw new AppException("查询全部信息", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 分页查询
	 * @return
	 * @throws AppException
	 */
	public String getAllDataByPage() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		//分页配置
		Pagination p = new Pagination(0,0,0);
		String currPage=(String)request.getParameter("goPage");
		if(currPage==null){  
			currPage="1";//” currPage”是当前页数
		}
		_logger.info("currPage:"+currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		try {
			
			Integer totleCount = exampleService.getAllDataCount();
			p.getPagination(request,totleCount, currPage1,null,null,null);
			int numOfEachPage =	Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Example> list = exampleService.getAllDataByPage(begin, numOfEachPage);
			request.setAttribute("list", list);
			_logger.info("get all data by page success-------");
		} catch (Exception e) {
			throw new AppException("分页查询全部信息", e);
		}
		this.actionName = "getAllDataByPage";
		return SUCCESS;
	}
	
	public String save()throws AppException{
		try {
			Example ex = new Example();
			ex.setAge(Integer.valueOf(age));
			ex.setName(name);
			exampleService.save(ex);
		} catch (Exception e) {
			throw new AppException("添加信息", e);
			
		}
		return SUCCESS;
	}
	
	public String select() throws Exception{

			exampleService.select();

		return SUCCESS;
	}
	
	/**
	 * 统计查询
	 * @return
	 * @throws AppException
	 */
	public String getStatistics(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map statMap = new HashMap();//查询条件
		try {
			List list = exampleService.getStatistics(statMap);
			/**
			 * 简单的数据集dataset
			 */
			DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
			for(int i=0;i<list.size();i++){
				Map returnMap = (Map)list.get(i);
				String columnKey = "";
				if("1".equals(returnMap.get("ACCOUNT_TYPE").toString())){
					columnKey = "CCIC人员";
				}
				else{
					columnKey = "客户";
				}
				dataset.addValue(Double.parseDouble(returnMap.get("NUM").toString()), "", columnKey);
			}
			/**
			 * 数组型数据集dataset
			 * 例如：
			 * double[][] data = new double[][] {{1310, 1220, 1110, 1000}, 
				{720, 700, 680, 640}, 
				{1130, 1020, 980, 800}, 
				{440, 400, 360, 300}}; 
				String[] rowKeys = { "pig", "beef", "chicken", "fish" };
				  String[] columnKeys = { "Guangzhou", "Shenzhen", "Dongguan", "Foshan" }; 
				CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
			 */
			
			 String filename = ChartUtil.createBarChartChart("用户类型统计图", "用户类型", "数量", session, dataset, 500, 300);
			request.setAttribute("filename", filename);
			
		} catch (Exception e) {
			
		}
		return SUCCESS;
	}
	
	
	/**
	 * 返回excel
	 * @return
	 * @throws AppException
	 */
	public void getExcel(){ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			List contentlist1 = new ArrayList();
			contentlist1.add("美国");
			contentlist1.add(1);
			contentlist1.add(2);
			contentlist1.add(5);
			contentlist1.add(10);
			
			List contentlist2 = new ArrayList();
			contentlist2.add("加拿大");
			contentlist2.add(10);
			contentlist2.add(2);
			contentlist2.add(6);
			contentlist2.add(2);
			
			List contentlist3 = new ArrayList();
			contentlist3.add("其他");
			contentlist3.add(10);
			contentlist3.add(2);
			contentlist3.add(6);
			contentlist3.add(2);
			
			List contentlist4 = new ArrayList();
			contentlist4.add("美国");
			contentlist4.add(1);
			contentlist4.add(2);
			contentlist4.add(5);
			contentlist4.add(10);
			
			List list = new ArrayList();
			list.add(contentlist1);
			list.add(contentlist2);
			list.add(contentlist3);
			list.add(contentlist4);
			
			
			String sheetname = "目的国业务统计";
			String formatFileName = URLEncoder.encode(sheetname, "UTF-8");
			
			HSSFWorkbook workbook = ExcelUtil.toExcel(sheetname, list);
			
			response.reset();
			response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename="+formatFileName+".xls");
            workbook.write(response.getOutputStream());
			 
			response.getOutputStream().close(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
