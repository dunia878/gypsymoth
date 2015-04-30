package com.sinosoft.gypsymoth.bussiness.statistics.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.sinosoft.gypsymoth.bussiness.statistics.service.StatisticsBoatService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.utils.ChartUtil;
import com.sinosoft.gypsymoth.utils.ExcelUtil;

public class StatisticsBoatAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private StatisticsBoatService statisticsBoatService;

	private static final Logger _logger = Logger
			.getLogger(StatisticsWormAction.class);

	private String parentId;

	private String isChild;

	private String orgId;

	private String selectOrg;

	private String portId;

	private static int TABLE_WEITH = 120;

	public StatisticsBoatService getStatisticsBoatService() {
		return statisticsBoatService;
	}

	public void setStatisticsBoatService(
			StatisticsBoatService statisticsBoatService) {
		this.statisticsBoatService = statisticsBoatService;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIsChild() {
		return isChild;
	}

	public void setIsChild(String isChild) {
		this.isChild = isChild;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSelectOrg() {
		return selectOrg;
	}

	public void setSelectOrg(String selectOrg) {
		this.selectOrg = selectOrg;
	}

	public String getPortId() {
		return portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}

	/**
	 * 初始化数据
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getInitBoat() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR) - 5;
		List yearList = new ArrayList();
		for (int i = 0; i < 5; i++) {
			year = year + 1;
			Map map = new HashMap();
			map.put("year", year);
			yearList.add(map);
		}
		request.setAttribute("yearList", yearList);
		return SUCCESS;
	}

	/**
	 * 根据权限获取一级机构菜单
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getFirstOrgList() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map selectMap = new HashMap();
		selectMap.put("parentId", "");
		selectMap.put("isChild", "1");
		List orgList = new ArrayList();
		try {
			orgList = statisticsBoatService.getOrgList(selectMap);
		} catch (Exception e) {
			throw new AppException("获取机构列表", e);
		}
		request.setAttribute("firstOrgList", orgList);
		request.setAttribute("type", 1);
		return SUCCESS;
	}

	/**
	 * 根据权限获取二级机构菜单
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getSecondOrgList() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		parentId = (String) request.getParameter("input");
		Map selectMap = new HashMap();
		selectMap.put("parentId", parentId);
		selectMap.put("isChild", "2");
		List orgList = new ArrayList();
		try {
			orgList = statisticsBoatService.getOrgList(selectMap);
		} catch (Exception e) {
			throw new AppException("获取机构列表", e);
		}
		request.setAttribute("secondOrgList", orgList);
		request.setAttribute("type", 2);
		return SUCCESS;
	}

	/**
	 * 获取港口列表
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getPortList() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		orgId = (String) request.getParameter("input");
		Map selectMap = new HashMap();
		selectMap.put("orgId", orgId);
		List portList = new ArrayList();
		try {
			portList = statisticsBoatService.getPortList(selectMap);
		} catch (Exception e) {
			throw new AppException("获取港口列表", e);
		}
		request.setAttribute("portList", portList);
		request.setAttribute("type", 3);
		return SUCCESS;
	}

	/**
	 * 船舶类型统计
	 * 
	 * @return
	 */
	public String getTypeStatistics() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String yearBegin = request.getParameter("yearBegin");
		String yearEnd = request.getParameter("yearEnd");
		String month_begin = request.getParameter("beginmonth");
		String month_end = request.getParameter("endmonth");

		String vesseltype = request.getParameter("vesseltype");
		String selectOrg = request.getParameter("selectOrg");
		String orgId = request.getParameter("orgId");
		String parentId = request.getParameter("parentId");
		String portId = request.getParameter("portId");

		String ifAll = "";
		HashMap oneMap = null;
		HashMap map = new HashMap();
		map.put("vesseltype", vesseltype);
		String date_begin = "";
		String date_end = "";
		try {
			if (yearBegin != null && !yearBegin.equals("-1") && yearEnd != null
					&& !yearEnd.equals("-1")) {
				date_begin = yearBegin + "-" + month_begin;
				date_end = yearEnd + "-" + month_end;
				map.put("date_begin", date_begin);
				map.put("date_end", date_end);
			}
			if ("0".equals(selectOrg)) {
				ifAll = "all";
			}
			map.put("selectOrg", selectOrg);
			map.put("orgId", orgId);
			map.put("parentId", parentId);
			map.put("portId", portId);
			map.put("ifAll", ifAll);
			List onetempList = statisticsBoatService.getTypeStatistics(map);
			if (onetempList != null && onetempList.size() > 0) {
				oneMap = (HashMap) onetempList.get(0);
			}
			String all;
			if ("-1".equals(vesseltype)) {
				request.setAttribute("all", "1");
				all = "1";
			} else {
				request.setAttribute("all", "0");
				request.setAttribute("vesseltype", vesseltype);
				all = "0";
			}
			String orgImg = createOrgImg(onetempList, all, vesseltype,
					selectOrg);
			String typeImg = createTypeImg(onetempList, all, vesseltype);
			request.setAttribute("list", onetempList);
			request.setAttribute("orgImg", orgImg);
			request.setAttribute("type", selectOrg);
			request.setAttribute("orgMap", oneMap);
			request.setAttribute("typeImg", typeImg);
			request.setAttribute("oneMap", oneMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 船舶类型机构业务量柱状图
	 * 
	 * @param orgMap
	 * @return
	 */
	public String createOrgImg(List list, String all, String vesseltype,
			String type) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String filename = "";
		int weith = 500;
		int hight = 300;
		double total = 0;
		double totalAll = 0;
		try {
			/** 简单的数据集dataset */
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (int i = 0; i < list.size(); i++) {
				Map returnMap = (Map) list.get(i);
				double CN1 = Double.valueOf(returnMap.get("CN1").toString());
				double CN2 = Double.valueOf(returnMap.get("CN2").toString());
				double CN3 = Double.valueOf(returnMap.get("CN3").toString());
				double CN4 = Double.valueOf(returnMap.get("CN4").toString());
				double CN5 = Double.valueOf(returnMap.get("CN5").toString());
				double CN6 = Double.valueOf(returnMap.get("CN6").toString());
				double CN7 = Double.valueOf(returnMap.get("CN7").toString());
				double CN8 = Double.valueOf(returnMap.get("CN8").toString());
				double CN9 = Double.valueOf(returnMap.get("CN9").toString());
				double CN10 = Double.valueOf(returnMap.get("CN10").toString());
				double CN11 = Double.valueOf(returnMap.get("CN11").toString());
				double CN12 = Double.valueOf(returnMap.get("CN12").toString());
				total = 0;
				if ("1".equals(all) || "Container Vessel".equals(vesseltype)) {
					total = total + CN1;
				}
				if ("1".equals(all) || "General Cargo Ship".equals(vesseltype)) {
					total = total + CN2;
				}
				if ("1".equals(all) || "Bulk Carrier".equals(vesseltype)) {
					total = total + CN3;
				}
				if ("1".equals(all)
						|| "Multi-purpose vessel".equals(vesseltype)) {
					total = total + CN4;
				}
				if ("1".equals(all) || "Oil Tanker".equals(vesseltype)) {
					total = total + CN5;
				}
				if ("1".equals(all)
						|| "Roll on/Roll off //Vessel".equals(vesseltype)) {
					total = total + CN6;
				}
				if ("1".equals(all) || "Refrigerated Vessel".equals(vesseltype)) {
					total = total + CN7;
				}
				if ("1".equals(all)
						|| "Vehicle and Passenger Ferry".equals(vesseltype)) {
					total = total + CN8;
				}
				if ("1".equals(all) || "Heavy-cargo Carrier".equals(vesseltype)) {
					total = total + CN9;
				}
				if ("1".equals(all)
						|| "Lighter Aboard Ship--LASH".equals(vesseltype)) {
					total = total + CN10;
				}
				if ("1".equals(all)
						|| "Liquefied natural gas carrier--LNC"
								.equals(vesseltype)) {
					total = total + CN11;
				}
				if ("1".equals(all)
						|| "Liquefied petroleum gas carrier--LPC"
								.equals(vesseltype)) {
					total = total + CN12;
				}
				if (!"0".equals(selectOrg)) {
					String orgName = returnMap.get("ORG_SNAME").toString();
					dataset.addValue(total, "", orgName);
				}
				totalAll = totalAll + total;
			}
			if ("0".equals(selectOrg)) {
				dataset.addValue(totalAll, "", "检验公司");
				weith = 3 * TABLE_WEITH;
			} else if (list != null && list.size() > 0) {
				weith = list.size() * TABLE_WEITH > weith ? list.size()
						* TABLE_WEITH : weith;
			}
			dataset.addValue(totalAll, "", "总计");

			filename = ChartUtil.createBarChartChart("船舶类型统计图", "机构船舶类型统计",
					"业务量(船次)", session, dataset, weith, hight);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("weith", weith);
		request.setAttribute("hight", hight);
		String graphURL = request.getContextPath() + "/DisplayChart?filename="
				+ filename;
		return graphURL;
	}

	/**
	 * 船舶类型柱状图
	 * 
	 * @param oneMap
	 * @return
	 */
	public String createTypeImg(List list, String all, String vesseltype) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String filename = "";
		int width = 500;
		int height = 300;
		try {
			/** 简单的数据集dataset */
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			double CN1 = 0;
			double CN2 = 0;
			double CN3 = 0;
			double CN4 = 0;
			double CN5 = 0;
			double CN6 = 0;
			double CN7 = 0;
			double CN8 = 0;
			double CN9 = 0;
			double CN10 = 0;
			double CN11 = 0;
			double CN12 = 0;
			for (int i = 0; i < list.size(); i++) {
				Map returnMap = (Map) list.get(i);
				CN1 += Double.valueOf(returnMap.get("CN1").toString());
				CN2 += Double.valueOf(returnMap.get("CN2").toString());
				CN3 += Double.valueOf(returnMap.get("CN3").toString());
				CN4 += Double.valueOf(returnMap.get("CN4").toString());
				CN5 += Double.valueOf(returnMap.get("CN5").toString());
				CN6 += Double.valueOf(returnMap.get("CN6").toString());
				CN7 += Double.valueOf(returnMap.get("CN7").toString());
				CN8 += Double.valueOf(returnMap.get("CN8").toString());
				CN9 += Double.valueOf(returnMap.get("CN9").toString());
				CN10 += Double.valueOf(returnMap.get("CN10").toString());
				CN11 += Double.valueOf(returnMap.get("CN11").toString());
				CN12 += Double.valueOf(returnMap.get("CN12").toString());
			}
			request.setAttribute("C1", (int) CN1);
			request.setAttribute("C2", (int) CN2);
			request.setAttribute("C3", (int) CN3);
			request.setAttribute("C4", (int) CN4);
			request.setAttribute("C5", (int) CN5);
			request.setAttribute("C6", (int) CN6);
			request.setAttribute("C7", (int) CN7);
			request.setAttribute("C8", (int) CN8);
			request.setAttribute("C9", (int) CN9);
			request.setAttribute("C10", (int) CN10);
			request.setAttribute("C11", (int) CN11);
			request.setAttribute("C12", (int) CN12);

			double num = 0;

			if ("1".equals(all) || "Container Vessel".equals(vesseltype)) {
				dataset.addValue(CN1, "", "Container Vessel");
				num = CN1;
			}
			if ("1".equals(all) || "General Cargo Ship".equals(vesseltype)) {
				dataset.addValue(CN2, "", "General Cargo Ship");
				num = CN2;
			}
			if ("1".equals(all) || "Bulk Carrier".equals(vesseltype)) {
				dataset.addValue(CN3, "", "Bulk Carrier");
				num = CN3;
			}
			if ("1".equals(all) || "Multi-purpose vessel".equals(vesseltype)) {
				dataset.addValue(CN4, "", "Multi-purpose vessel");
				num = CN4;
			}
			if ("1".equals(all) || "Oil Tanker".equals(vesseltype)) {
				dataset.addValue(CN5, "", "Oil Tanker");
				num = CN5;
			}
			if ("1".equals(all)
					|| "Roll on/Roll off //Vessel".equals(vesseltype)) {
				dataset.addValue(CN6, "", "Roll on/Roll off //Vessel");
				num = CN6;
			}
			if ("1".equals(all) || "Refrigerated Vessel".equals(vesseltype)) {
				dataset.addValue(CN7, "", "Refrigerated Vessel");
				num = CN7;
			}
			if ("1".equals(all)
					|| "Vehicle and Passenger Ferry".equals(vesseltype)) {
				dataset.addValue(CN8, "", "Vehicle and Passenger Ferry");
				num = CN8;
			}
			if ("1".equals(all) || "Heavy-cargo Carrier".equals(vesseltype)) {
				dataset.addValue(CN9, "", "Heavy-cargo Carrier");
				num = CN9;
			}
			if ("1".equals(all)
					|| "Lighter Aboard Ship--LASH".equals(vesseltype)) {
				dataset.addValue(CN10, "", "Lighter Aboard Ship--LASH");
				num = CN10;
			}
			if ("1".equals(all)
					|| "Liquefied natural gas carrier--LNC".equals(vesseltype)) {
				dataset
						.addValue(CN11, "",
								"Liquefied natural gas carrier--LNC");
				num = CN11;
			}
			if ("1".equals(all)
					|| "Liquefied petroleum gas carrier--LPC"
							.equals(vesseltype)) {
				dataset.addValue(CN12, "",
						"Liquefied petroleum gas carrier--LPC");
				num = CN12;
			}
			double total;
			if ("1".equals(all)) {
				total = CN1 + CN2 + CN3 + CN4 + CN5 + CN6 + CN7 + CN8 + CN9
						+ CN10 + CN11 + CN12;
			} else {
				total = num;
			}
			request.setAttribute("total", (int) total);
			dataset.addValue(total, "", "总计");
			if (list != null && list.size() > 0) {
				if ("1".equals(all)) {
					width = 13 * TABLE_WEITH;
				} else {
					width = 6 * TABLE_WEITH;
				}
			}
			filename = ChartUtil.createBarChartChart("船舶类型统计图", "船舶类型统计",
					"业务量(船次)", session, dataset, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String graphURL = request.getContextPath() + "/DisplayChart?filename="
				+ filename;
		return graphURL;
	}

	/**
	 * 船舶吨位统计
	 * 
	 * @return
	 */
	public String getTonStatistics() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String yearBegin = request.getParameter("yearBegin");
		String yearEnd = request.getParameter("yearEnd");
		String month_begin = request.getParameter("beginmonth");
		String month_end = request.getParameter("endmonth");
		String[] beginTon = request.getParameterValues("beginTon");
		String[] endTon = request.getParameterValues("endTon");
		int totle = 0;
		List totlelist = new ArrayList();
		HashMap oneMap = null;
		HashMap map = new HashMap();
		String date_begin = "";
		String date_end = "";
		try {
			if (yearBegin != null && !yearBegin.equals("-1") && yearEnd != null
					&& !yearEnd.equals("-1")) {
				date_begin = yearBegin + "-" + month_begin;
				date_end = yearEnd + "-" + month_end;
				map.put("date_begin", date_begin);
				map.put("date_end", date_end);
			}

			if (selectOrg != null && selectOrg.equals("1")) { // 统计一级公司的信息
				if (parentId != null && !parentId.equals("")) {
					map.put("org4", parentId.substring(0, 2));
				}
			} else if (selectOrg != null && selectOrg.equals("2")) { // 统计二级公司的信息
				map.put("org4", orgId);
			} else if (selectOrg != null && selectOrg.equals("3")) { // 统计港口的信息
				if (orgId != null && !orgId.equals("")) { // 二级对应所有港口
					map.put("org4", orgId);
				} else if (parentId != null && !parentId.equals("")) { // 二级选择全部，则统计内容为一级对应所有港口
					map.put("org4", parentId.substring(0, 2));
				}
				map.put("portid", portId);
			}
			if (beginTon != null && beginTon.length != 0) {
				map.put("beginTon", beginTon);
			}
			if (endTon != null && endTon.length != 0) {
				map.put("endTon", endTon);
			}
			List titlelist = new ArrayList();
			;
			for (int i = 0; i < beginTon.length; i++) {
				String title = beginTon[i] + "-" + endTon[i];
				HashMap titlemap = new HashMap();
				titlemap.put("title", title);
				titlelist.add(titlemap);
			}
			List onetempList = statisticsBoatService.getTonStatistics(map);
			if (onetempList != null && onetempList.size() > 0) {
				for (int i = 0; i < onetempList.size(); i++) {
					// 得到客户及统计结果列表
					HashMap monmap = (HashMap) onetempList.get(i);
					for (int j = 0; j < titlelist.size(); j++) { // 得到单行记录
						String cn = monmap.get("CN" + j).toString();
						totle = totle + Integer.valueOf(cn);
						if (i == 0) {
							totlelist.add(Integer.valueOf(cn));
						} else {
							int temp = (Integer) totlelist.get(j);
							totlelist.set(j, temp + Integer.valueOf(cn));
						}
					}

				}
			}
			String orgImg = createOrgTonImg(onetempList, beginTon, endTon);
			String tonImg = createTonImg(onetempList, beginTon, endTon);
			request.setAttribute("titlelist", titlelist);
			request.setAttribute("list", onetempList);
			request.setAttribute("orgImg", orgImg);
			request.setAttribute("orgMap", oneMap);
			request.setAttribute("tonImg", tonImg);
			request.setAttribute("oneMap", oneMap);
			request.setAttribute("totlelist", totlelist);
			request.setAttribute("totle", totle);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 船舶吨位机构业务量柱状图
	 * 
	 * @param orgMap
	 * @return
	 */
	public String createOrgTonImg(List list, String[] beginTon, String[] endTon) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String filename = "";
		int weith = 500;
		int hight = 300;
		try {
			/** 简单的数据集dataset */
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (int i = 0; i < list.size(); i++) {
				Map returnMap = (Map) list.get(i);
				double total = 0;
				for (int j = 0; j < beginTon.length; j++) {
					double CN = Double.valueOf(returnMap.get("CN" + j)
							.toString());
					total = total + CN;
				}
				String orgName = returnMap.get("ORG_SNAME").toString();
				dataset.addValue(total, "", orgName);
			}
			if (list != null && list.size() > 0) {
				weith = list.size() * TABLE_WEITH > weith ? list.size()
						* TABLE_WEITH : weith;
			}
			filename = ChartUtil.createBarChartChart("船舶吨位统计图", "机构船舶吨位统计",
					"业务量(船次)", session, dataset, weith, hight);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String graphURL = request.getContextPath() + "/DisplayChart?filename="
				+ filename;
		return graphURL;
	}

	/**
	 * 船舶吨位柱状图
	 * 
	 * @param oneMap
	 * @return
	 */
	public String createTonImg(List list, String[] beginTon, String[] endTon) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String filename = "";
		int weith = 500;
		int hight = 300;
		try {
			/** 简单的数据集dataset */
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			double total = 0;
			for (int i = 0; i < list.size(); i++) {
				Map returnMap = (Map) list.get(i);
				for (int j = 0; j < beginTon.length; j++) {
					double CN = Double.valueOf(returnMap.get("CN" + j)
							.toString());
					total = total + CN;
					request.setAttribute("C" + j, (int) CN);
					dataset.addValue(CN, "", beginTon[j] + "-" + endTon[j]
							+ "吨");
				}

			}
			dataset.addValue(total, "", "总计");
			request.setAttribute("total", (int) total);

			weith = beginTon.length * TABLE_WEITH > weith ? beginTon.length
					* TABLE_WEITH : weith;

			filename = ChartUtil.createBarChartChart("船舶吨位统计图", "船舶吨位统计",
					"业务量(船次)", session, dataset, weith, hight);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String graphURL = request.getContextPath() + "/DisplayChart?filename="
				+ filename;
		return graphURL;
	}

	/**
	 * 导出船舶类型表格
	 */
	public void getTypeExcel() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String[] excel_title_hidden = request
				.getParameterValues("excel_title_hidden");
		String[] excel_list_hidden = request
				.getParameterValues("excel_list_hidden");
		String[] excel_totle_hidden = request
				.getParameterValues("excel_totle_hidden");
		try {
			List list = new ArrayList();
			if (excel_title_hidden != null && excel_title_hidden.length > 0) {
				List titlelist = new ArrayList();
				int num = excel_title_hidden.length;
				for (int i = 0; i < excel_title_hidden.length; i++)
					// 第一行
					titlelist.add(excel_title_hidden[i]);
				list.add(titlelist);

				int colnum = excel_list_hidden.length
						/ excel_title_hidden.length;
				for (int i = 0; i < colnum; i++) {
					List contentlist = new ArrayList();
					for (int j = 0; j < num; j++) {
						int cur = i * num + j;
						contentlist.add(excel_list_hidden[cur]);
					}
					list.add(contentlist);
				}
				List totlelist = new ArrayList();
				for (int i = 0; i < excel_totle_hidden.length; i++)
					// 合计行
					totlelist.add(excel_totle_hidden[i]);
				list.add(totlelist);

			}

			String sheetname = "船舶类型统计";
			String formatFileName = URLEncoder.encode(sheetname, "UTF-8");

			HSSFWorkbook workbook = ExcelUtil.toExcel(sheetname, list);

			response.reset();
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ formatFileName + ".xls");
			workbook.write(response.getOutputStream());

			response.getOutputStream().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出船舶吨位表格
	 */
	public void getTonExcel() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String[] excel_title_hidden = request
				.getParameterValues("excel_title_hidden");
		String[] excel_list_hidden = request
				.getParameterValues("excel_list_hidden");
		String[] excel_totle_hidden = request
				.getParameterValues("excel_totle_hidden");
		try {
			List list = new ArrayList();
			if (excel_title_hidden != null && excel_title_hidden.length > 0) {
				List titlelist = new ArrayList();
				int num = excel_title_hidden.length;
				for (int i = 0; i < excel_title_hidden.length; i++)
					// 第一行
					titlelist.add(excel_title_hidden[i]);
				list.add(titlelist);

				int colnum = excel_list_hidden.length
						/ excel_title_hidden.length;
				for (int i = 0; i < colnum; i++) {
					List contentlist = new ArrayList();
					for (int j = 0; j < num; j++) {
						int cur = i * num + j;
						contentlist.add(excel_list_hidden[cur]);
					}
					list.add(contentlist);
				}
				List totlelist = new ArrayList();
				for (int i = 0; i < excel_totle_hidden.length; i++)
					// 合计行
					totlelist.add(excel_totle_hidden[i]);
				list.add(totlelist);

			}

			String sheetname = "船舶吨位统计";
			String formatFileName = URLEncoder.encode(sheetname, "UTF-8");

			HSSFWorkbook workbook = ExcelUtil.toExcel(sheetname, list);

			response.reset();
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ formatFileName + ".xls");
			workbook.write(response.getOutputStream());

			response.getOutputStream().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
