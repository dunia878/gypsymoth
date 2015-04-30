package com.sinosoft.gypsymoth.bussiness.bulletin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.bulletin.service.BulletinService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Bulletin;
import com.sinosoft.gypsymoth.pojo.ExamPort;
import com.sinosoft.gypsymoth.pojo.Examinelog;
import com.sinosoft.gypsymoth.pojo.Linkus;
import com.sinosoft.gypsymoth.pojo.Operator;
import com.sinosoft.gypsymoth.utils.BusinessState;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;

public class BulletinAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Logger _logger = Logger.getLogger(BulletinAction.class);

	private BulletinService bulletinService;
	private Bulletin bulletin;
	private String actionName;
	private Linkus linkus;
	private String linkusid;
	private String bulletinId;
	private String bulletinName;
	private String bulletinContent;
	private String bulletinContents;
	private String accountId;
	private String accountName;
	private Long bulletinState;
	private Long bulletinTip;
	private Long bulletinTips;
	private Date bulletinTime;
	private Long bulletinLanguage;
	private String[] ids;
	private String[] idArray;
	private long[] delid = new long[] {};
	private File getUpload;
	private String[] getUploadFileName;
	private String packName;
	private String filename;
	private String filepath;

	private File[] certificate;
	private String[] certificateFileName;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public File getGetUpload() {
		return getUpload;
	}

	public void setGetUpload(File getUpload) {
		this.getUpload = getUpload;
	}

	public String[] getGetUploadFileName() {
		return getUploadFileName;
	}

	public void setGetUploadFileName(String[] getUploadFileName) {
		this.getUploadFileName = getUploadFileName;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public long[] getDelid() {
		return delid;
	}

	public void setDelid(long[] delid) {
		this.delid = delid;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getBulletinId() {
		return bulletinId;
	}

	public void setBulletinId(String bulletinId) {
		this.bulletinId = bulletinId;
	}

	public String getBulletinName() {
		return bulletinName;
	}

	public void setBulletinName(String bulletinName) {
		this.bulletinName = bulletinName;
	}

	public String getBulletinContent() {
		return bulletinContent;
	}

	public void setBulletinContent(String bulletinContent) {
		this.bulletinContent = bulletinContent;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Long getBulletinState() {
		return bulletinState;
	}

	public void setBulletinState(Long bulletinState) {
		this.bulletinState = bulletinState;
	}

	public Long getBulletinTip() {
		return bulletinTip;
	}

	public void setBulletinTip(Long bulletinTip) {
		this.bulletinTip = bulletinTip;
	}

	public Date getBulletinTime() {
		return bulletinTime;
	}

	public void setBulletinTime(Date bulletinTime) {
		this.bulletinTime = bulletinTime;
	}

	public Long getBulletinTips() {
		return bulletinTips;
	}

	public void setBulletinTips(Long bulletinTips) {
		this.bulletinTips = bulletinTips;
	}

	public Long getBulletinLanguage() {
		return bulletinLanguage;
	}

	public void setBulletinLanguage(Long bulletinLanguage) {
		this.bulletinLanguage = bulletinLanguage;
	}

	public BulletinService getBulletinService() {
		return bulletinService;
	}

	public void setBulletinService(BulletinService bulletinService) {
		this.bulletinService = bulletinService;
	}

	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Logger get_logger() {
		return _logger;
	}

	/**
	 * 已登录的用户(非管理级别) 分页显示所有公告
	 */
	public String getAllADataByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (ServletActionContext.getContext().getLocale().toString().equals(
				"en_US")) {
			// 分页配置
			Pagination p = new Pagination(0, 0, 0);
			String currPage = (String) request.getParameter("goPage");
			if (currPage == null) {
				currPage = "1";// ” currPage”是当前页数
			}
			_logger.info("currPage:" + currPage);
			Integer currPage1 = Integer.parseInt(currPage);

			try {
				Integer totleCount = bulletinService.getAllADataCount();
				p.getPagination(request, totleCount, currPage1, null, null,
						null);
				int numOfEachPage = Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				List<Bulletin> bulletinlist = bulletinService
						.getAllADataByPage(begin, numOfEachPage);
				request.setAttribute("bulletinlist", bulletinlist);
				_logger
						.info("get all data by page  for LOGIN  A  success-------");
			} catch (Exception e) {
				throw new AppException("分页查询全部信息", e);
			}
		} else {
			Pagination p = new Pagination(0, 0, 0);
			String currPage = (String) request.getParameter("goPage");
			if (currPage == null) {
				currPage = "1";// ” currPage”是当前页数
			}
			_logger.info("currPage:" + currPage);
			Integer currPage1 = Integer.parseInt(currPage);

			try {
				Integer totleCount = bulletinService.getAllADataCountCH();
				p.getPagination(request, totleCount, currPage1, null, null,
						null);
				int numOfEachPage = Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				List<Bulletin> bulletinlist = bulletinService
						.getAllADataByPageCH(begin, numOfEachPage);
				request.setAttribute("bulletinlist", bulletinlist);
				_logger
						.info("get all data by page  for LOGIN  A  success-------");
			} catch (Exception e) {
				throw new AppException("分页查询全部信息", e);
			}
		}
		this.actionName = "getAllADataByPage";
		return SUCCESS;
	}

	/**
	 * 新增公告
	 * 
	 * @return
	 * @throws Exception
	 */
	// public String save()throws Exception{
	// filename = getUploadFileName[0];
	// bulletinService.save(bulletin,getUpload, filename, packName);
	// _logger.info("save one data success-------");
	// return SUCCESS;
	// }
	public String save() throws AppException {
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map session = ActionContext.getContext().getSession();
			HttpServletRequest request = ServletActionContext.getRequest();
			String bulletinCon = request.getParameter("bulletinContent");// 获得保存CKedit内容
			String realpath = ServletActionContext.getServletContext()
					.getRealPath("/files");
			// Bulletin bulletin = new Bulletin();
			if (certificate != null) {
				String cName = "";
				Date date = new Date();

				String datestr = sdf.format(date);

				File savedir = new File(realpath + "\\/" + datestr);
				if (!savedir.exists())
					savedir.mkdirs();
				for (int i = 0; i < certificate.length; i++) {
					int pos = certificateFileName[i].lastIndexOf(".");// struts2
																		// 规定:certificate
																		// 是一个file
																		// 要想得到它的名字就在后面+FileName
					String last = "." // 此处意思是取其名称的后缀名
							+ certificateFileName[i].substring(pos + 1);// 获得后缀名
					String fileName = new Date().getTime() + i + last;// 合成图片的名称
																		// 名称不能相同
					cName = cName + fileName + "|";
					File savefile = new File(savedir, fileName);
					FileUtils.copyFile(certificate[i], savefile);

				}

				cName.substring(0, cName.length() - 1);
				String fileurl = "/files/" + datestr;
				bulletin.setFilename(cName);
				bulletin.setFileurl(fileurl);

			}
			bulletin.setBulletinTime(sdf1.parse(sdf1.format(new Date())));
			bulletin.setAccountName(((Account) session
					.get(Constants.ACCOUNT_SESSION)).getAccountName());

			bulletin.setBulletinContent(bulletinCon);
			bulletinService.save(bulletin);
			_logger.info("upload examine success-------");
		} catch (Exception e) {
			throw new AppException("提交图片", e);

		}
		return SUCCESS;
	}

	/**
	 * 修改公告信息
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updataBulletin() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String radiochecked1 = request.getParameter("bulletinLanguage");
		String radiochecked = request.getParameter("bulletinTip");
		String radiochecked2 = request.getParameter("bulletinTips");
		String bulletinCon = request.getParameter("bulletinContent");
		String bulletinCons = bulletinCon + " ";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH-mm");
		Map session = ActionContext.getContext().getSession();
		String realpath = ServletActionContext.getServletContext().getRealPath(
				"/files");

		if (certificate != null) {
			String cName = "";
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String datestr = sdf.format(date);

			File savedir = new File(realpath + "\\/" + datestr);
			if (!savedir.exists())
				savedir.mkdirs();
			for (int i = 0; i < certificate.length; i++) {
				int pos = certificateFileName[i].lastIndexOf(".");
				String last = "." + certificateFileName[i].substring(pos + 1);// 获得后缀名
				String fileName = new Date().getTime() + i + last;// 合成图片的名称
				cName = cName + fileName + "|";
				File savefile = new File(savedir, fileName);
				FileUtils.copyFile(certificate[i], savefile);

			}

			cName.substring(0, cName.length() - 1);
			// String fileurl = realpath + "\\"+datestr ;
			String fileurl = "/files/" + datestr;
			bulletin.setFilename(cName);
			bulletin.setFileurl(fileurl);

			bulletin.setBulletinTime(sdf1.parse(sdf1.format(new Date())));
			bulletin.setAccountName(((Account) session
					.get(Constants.ACCOUNT_SESSION)).getAccountName());

			bulletin.setBulletinLanguage(Long.valueOf(radiochecked1));
			bulletin.setBulletinTip(Long.valueOf(radiochecked));
			bulletin.setBulletinTips(Long.valueOf(radiochecked2));
			bulletin.setBulletinContent(bulletinCon);
			bulletinService.updateBulletins(bulletin);
			return SUCCESS;
		} else {

			bulletin.setBulletinLanguage(Long.valueOf(radiochecked1));
			bulletin.setBulletinTip(Long.valueOf(radiochecked));
			bulletin.setBulletinTips(Long.valueOf(radiochecked2));
			bulletin.setBulletinContent(bulletinCon);
			bulletin.setBulletinTime(sdf1.parse(sdf1.format(new Date())));
			bulletinService.updateBulletin(bulletin);
			// bulletinService.save(bulletin.getBulletinContent());
			Bulletin bulletins = bulletin;
			System.out.print(bulletin);
			return SUCCESS;
		}

		// if(getUploadFileName!=null){
		// filename = getUploadFileName[0];
		// }
		// if(filename ==null){
		// try {
		// bulletin.setBulletinLanguage(Long.valueOf(radiochecked1));
		// bulletin.setBulletinTip(Long.valueOf(radiochecked));
		// bulletinService.updateBulletin(bulletin);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return SUCCESS;
		// } else{
		// try {
		// bulletin.setBulletinLanguage(Long.valueOf(radiochecked1));
		// bulletin.setBulletinTip(Long.valueOf(radiochecked));
		//					
		// bulletinService.updateBulletins(bulletin,getUpload,filename,packName);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return SUCCESS;
		// }
	}

	/**
	 * 获得公告信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getBulletinByIds() throws Exception {
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		bulletin = bulletinService.getBulletinById(Long.parseLong(bulletinId));
		Long bulletinstate = bulletin.getBulletinTip();
		Long bulletinlanguage = bulletin.getBulletinLanguage();
		Long bulletinTips = bulletin.getBulletinTips();
		if (bulletinstate != null) {
			if (bulletinstate == 2) {
				request.setAttribute("radiochecked", "2");
			}
		}
		if (bulletinlanguage != null) {
			if (bulletinlanguage == 2) {
				request.setAttribute("radiochecked1", "2");
			}
		}
		if (bulletinlanguage != null) {
			if (bulletinlanguage == 3) {
				request.setAttribute("radiochecked2", "3");
			}
		}
		if (bulletinTips != null) {
			if (bulletinTips == 1) {
				request.setAttribute("radiochecked3", "1");
			}
		}

		request.setAttribute("BULLETINLANGUAGE", bulletinlanguage);
		request.setAttribute("BULLETINTIP", bulletinstate);
		request.setAttribute("BULLETINTIPS", bulletinTips); 
		
		_logger.info("get all bulletin  by page success-------");
		return SUCCESS; // 详情页面
	}

	/**
	 * 批量删除问题
	 * 
	 * @return
	 * @throws AppException
	 */
	public String delete() throws AppException {
		String idstr = "";
		for (int i = 0; i < idArray.length; i++) {
			idstr = idstr + idArray[i] + ",";
			try {
				bulletinService.deleteBulletin(idArray[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_logger.info("insert op_log success-------");
		}
		_logger.info("update batch success");

		return SUCCESS;
	}

	/**
	 * 批量删除操作
	 * 
	 * @return
	 */

	public String deleteChecked() throws Exception {
		// System.out.print("====================="+bulletinId);
		// HttpServletRequest request = ServletActionContext.getRequest();
		//	
		// String name = bulletinId.replaceAll(" ", "");
		// String[] ids = request.getParameterValues("bulletinId");
		// // String[] ids = name.split(",");
		//		
		// for(int i=0;i<ids.length;i++){
		//			
		// }

		// HttpServletRequest request = ServletActionContext.getRequest();
		// String ch = request.getParameter("delid");

		String idSting = "";
		for (int i = 0; i < this.delid.length; i++) {
			idSting = idSting + this.delid[i] + ",";
		}
		idSting = idSting.substring(0, idSting.length() - 1);// 待删除记录若干id的字符串，逗号分隔
		// testService.delete(idSting);
		return SUCCESS;
	}

	//		
	// String[] ids=name.split(",");
	// for(int i=0;i<deluser.length;i++){
	// System.out.println(user[i]);

	// HttpServletRequest request = ServletActionContext.getRequest();
	//		
	// String[] ids = request.getParameterValues("bulletinId");
	// System.out.print("====================="+ids);
	// for(int i=0;i<ids.length;i++){
	// String str = ids[i]+",";
	//			
	// }
	//		
	//		
	//		

	//		  
	// for(int i=0;i<bulletinId.length();i++){
	// Bulletin bulliten = new Bulletin();
	//			  
	// bulliten.setBulletinId(bullitenId[i]);
	// list.add(bulliten);
	//		      
	// }
	// bulletinService.deleteBulletinListById(list);
	// return SUCCESS;
	// }

	/**
	 * 不同用户登陆显示信息分别
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectRole() throws Exception {
		Map session = ActionContext.getContext().getSession();
		Account account = (Account) session.get(Constants.ACCOUNT_SESSION);
		String accountId = account.getAccountId();
		List<AccountRole> accountRoleList = (List) bulletinService
				.getAccountRole(accountId);
		if (accountRoleList.size() > 0) {
			for (int i = 0; i < accountRoleList.size(); i++) {
				AccountRole accountRole = accountRoleList.get(i);
				String roleId = accountRole.getRoleId();
				if ("1".equals(roleId)) {
					return "adminSuccess";
				} else if ("5".equals(roleId)) {
					return "clientSuccess";
				} else {
					return "personSuccess";
				}
			}
		} else {
			return ERROR;
		}
		return ERROR;
	}

	/**
	 * 已登录的用户(超级管理员有 修改 查询 删除 普通用户查看) (英文) 分页显示所有公告
	 */
	public String getAllDataByPages() throws Exception {
		Map session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();

		// if(
		// ServletActionContext.getContext().getLocale().toString().equals("en_US")){
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);

		try {
			Integer totleCount = bulletinService.getAllDataCounts();
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Bulletin> bulletinlist = bulletinService.getAllDataByPages(
					bulletin, begin, numOfEachPage);
			request.setAttribute("bulletinlist", bulletinlist);
			_logger
					.info("get all data by page  for LOGIN  all  success 英文-------");
		} catch (Exception e) {
			throw new AppException("分页查询全部信息", e);
		}
		this.actionName = "getAllDataByPages";
		return SUCCESS;

		// }else{
		// // 分页配置
		// Pagination p = new Pagination(0,0,0);
		// String currPage=(String)request.getParameter("goPage");
		// if(currPage==null){
		// currPage="1";//” currPage”是当前页数
		// }
		// _logger.info("currPage:"+currPage);
		// Integer currPage1 = Integer.parseInt(currPage);
		//		
		// try {
		// Integer totleCount = bulletinService.getAllDataCountsCH();
		// p.getPagination(request,totleCount, currPage1,null,null,null);
		// int numOfEachPage = Constants.NUMOFEACHPAGE;
		// int begin = p.getRownum_begin(numOfEachPage, currPage1);
		// List<Bulletin> bulletinlist =
		// bulletinService.getAllDataByPagesCH(begin, numOfEachPage);
		// request.setAttribute("bulletinlist", bulletinlist);
		// _logger.info("get all data by page for LOGIN all success 中文-------");
		// } catch (Exception e) {
		// throw new AppException("分页查询全部信息", e);
		// }
		// this.actionName = "getAllDataByPages";
		// System.out.println(ServletActionContext.getContext().getLocale());
		//
		//		
		// return SUCCESS;
		// }
		//	

	}

	public void indexList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (ServletActionContext.getContext().getLocale().toString().equals(
				"en_US")) {
			List<Bulletin> list = new ArrayList<Bulletin>();
			// Pagination p = new Pagination(0,0,0);
			try {
				list = bulletinService.getIndexDataByPage(0, 4);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("list", list);
		} else {
			List<Bulletin> list = new ArrayList<Bulletin>();
			// Pagination p = new Pagination(0,0,0);
			try {
				list = bulletinService.getIndexDataByPageCH(0, 4);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("list", list);
		}
	}

	/**
	 * 未登录 客户查看 首页公告 分页显示所有公告
	 */
	public String getAllDataByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (ServletActionContext.getContext().getLocale().toString().equals(
				"en_US")) {
			// 分页配置
			Pagination p = new Pagination(0, 0, 0);
			String currPage = (String) request.getParameter("goPage");
			if (currPage == null) {
				currPage = "1";// ” currPage”是当前页数
			}
			_logger.info("currPage:" + currPage);
			Integer currPage1 = Integer.parseInt(currPage);

			try {
				Integer totleCount = bulletinService.getAllDataCountOut();
				p.getPagination(request, totleCount, currPage1, null, null,
						null);
				int numOfEachPage = Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				List<Bulletin> bulletinlist = bulletinService.getAllDataByPage(
						begin, numOfEachPage);
				request.setAttribute("bulletinlist", bulletinlist);
				_logger
						.info("get all data by page  for NOLOGIN  success-------");
			} catch (Exception e) {
				throw new AppException("分页查询全部信息", e);
			}
		} else {
			Pagination p = new Pagination(0, 0, 0);
			String currPage = (String) request.getParameter("goPage");
			if (currPage == null) {
				currPage = "1";// ” currPage”是当前页数
			}
			_logger.info("currPage:" + currPage);
			Integer currPage1 = Integer.parseInt(currPage);

			try {
				Integer totleCount = bulletinService.getAllDataCountOutCH();
				p.getPagination(request, totleCount, currPage1, null, null,
						null);
				int numOfEachPage = Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				List<Bulletin> bulletinlist = bulletinService
						.getAllDataByPageCH(begin, numOfEachPage);
				request.setAttribute("bulletinlist", bulletinlist);
				_logger
						.info("get all data by page  for NOLOGIN  success-------");
			} catch (Exception e) {
				throw new AppException("分页查询全部信息", e);
			}
		}
		this.actionName = "getAllDataByPage";
		return SUCCESS;
	}

	/**
	 * 客户 分页显示所有公告
	 */
	public String getAllCDataByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);

		try {
			Integer totleCount = bulletinService.getAllCDataCount();
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Bulletin> bulletinlist = bulletinService.getAllCDataByPage(
					begin, numOfEachPage);
			request.setAttribute("bulletinlist", bulletinlist);
			_logger.info("get all data by page  for LOGIN C success-------");
		} catch (Exception e) {
			throw new AppException("分页查询全部信息", e);
		}
		this.actionName = "getAllCDataByPage";
		return SUCCESS;
	}

	/**
	 * 删除公告
	 */
	public String deleteBulletinById() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		bulletinService.deleteBulletinById(Long.parseLong(bulletinId));

		request.setAttribute("message", "删除成功!");
		return SUCCESS;
	}

	/**
	 * @see 已登录用户（非管理级别）、客户 根据ID查找Entity
	 */
	public String getBulletinById() throws Exception {
		this.bulletin = bulletinService.getBulletinById(Long
				.parseLong(bulletinId));
		HttpServletRequest request = ServletActionContext.getRequest();
		if (bulletin.getFilename() != null) {

			String names = bulletin.getFilename();
			String urls = bulletin.getFileurl();

			String namess = names.replace("|", ",");
			String[] name = namess.split(",");
			List bulletinlist = new ArrayList();

			for (int i = 0; i < name.length; i++) {

				bulletinlist.add(urls + "/" + name[i]);
			}

			request.setAttribute("bulletinlist", bulletinlist);
		}
		// String filepaths = bulletin.getFileurl();
		// String filepath = "d:" + filepaths;
		// bulletin.setFileurl(filepath);
		return SUCCESS;
	}

	/**
	 * @see 已登录用户（管理级别） 根据ID查找Entity
	 */
	public String getABulletinById() throws Exception {
		bulletin = bulletinService.getBulletinById(Long.parseLong(bulletinId));
		HttpServletRequest request = ServletActionContext.getRequest();
		String cont = bulletin.getBulletinContent();

		String contt = cont.replace("<p>", "");
		String contts = contt.replace("</p>", "<br/>");
		
		
		
		
		
		
		
		if (bulletin.getFilename() != null) {
			String names = bulletin.getFilename();
			String urls = bulletin.getFileurl();
			String namess = names.replace("|", ",");
			String[] name = namess.split(",");
			List bulletinlist = new ArrayList();

			for (int i = 0; i < name.length; i++) {
				bulletinlist.add(urls + "/" + name[i]);
			}
			request.setAttribute("bulletinlist", bulletinlist);
		}

		return SUCCESS; // 详情页面 detailBulletin.jsp
	}

	/**
	 * @see 已登录用户（非管理级别）、客户 根据ID查找Entity
	 */
	public String getBulletinByIdPerson() throws Exception {
		this.bulletin = bulletinService.getBulletinById(Long
				.parseLong(bulletinId));
		HttpServletRequest request = ServletActionContext.getRequest();
		if (bulletin.getFilename() != null) {

			String names = bulletin.getFilename();
			String urls = bulletin.getFileurl();

			String namess = names.replace("|", ",");
			String[] name = namess.split(",");
			List bulletinlist = new ArrayList();

			for (int i = 0; i < name.length; i++) {

				bulletinlist.add(urls + "/" + name[i]);
			}

			request.setAttribute("bulletinlist", bulletinlist);
		}
		// String filepaths = bulletin.getFileurl();
		// String filepath = "d:" + filepaths;
		// bulletin.setFileurl(filepath); 单照片上传方法
		return SUCCESS;
	}

	/**
	 * 由AccountId得到客户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLinkusByType() throws Exception {
		// bulletin =
		// bulletinService.getBulletinById(Long.parseLong(bulletinId));
		String linkustype = "1";
		try {
			List list = bulletinService.getLinkusByType(linkustype);
			if (list.size() > 0) {
				Map map = (Map) list.get(0);
				linkusid = map.get("LINKUSID").toString();

			} else {
				linkusid = "0";
			}
		} catch (Exception e) {
			throw new AppException("获得client信息", e);
		}

		if (linkusid != "0") {
			linkus = bulletinService.getLinkusById(Long.valueOf(linkusid));
		}

		_logger.info("get all linkus  by page success-------");
		return SUCCESS; // 详情页面

	}

	/**
	 * 更新联系我们的内容
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updataLinkus() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String linkusid = request.getParameter("linkusid");
		linkus = bulletinService.getLinkusById(Long.valueOf(linkusid));

		_logger.info("updata linkus  by page success-------");
		return SUCCESS; // 详情页面

	}

	/**
	 * 更新联系我们的内容
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updataLink() throws Exception {
		if(!linkusid.equals("") && linkusid != null){
		linkus = bulletinService.getLinkusById(Long.valueOf(linkusid));
		}
		_logger.info("updata linkus  by page success-------");
		return SUCCESS; // 详情页面

	}

	/**
	 * 更新联系我们的内容
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updataLink1() throws Exception {
		if(!linkusid.equals("") && linkusid != null){
			linkus = bulletinService.getLinkusById(Long.valueOf(linkusid));
		}
		_logger.info("updata linkus  by page success-------");
		return SUCCESS; // 详情页面

	}

	/**
	 * 删除联系集团的信息
	 */
	public String deleteLinkusById() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String linkusid = request.getParameter("linkusid");
		if (linkusid != null && !linkusid.equals("")) {
			bulletinService.deleteLinkusById(Long.parseLong(linkusid));
		}

		_logger.info("delete linkus  by page success-------");
		return SUCCESS;
	}

	/**
	 * 删除联系集团分部的信息
	 */
	public String deleteLinkusById1() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String linkusid = request.getParameter("linkusid");
		if (linkusid != null && !linkusid.equals("")) {
			bulletinService.deleteLinkusById(Long.parseLong(linkusid));
		}

		_logger.info("delete linkus  by page success-------");
		return SUCCESS;
	}

	/**
	 * 增加集团联系方式 并删除以前的
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveLinkus() throws AppException {
		try {
			this.deleteLinkusById();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HttpServletRequest request = ServletActionContext.getRequest();
		String linkuscontent = request.getParameter("linkuscontent");// 获得保存CKedit内容

		linkus.setLinkustype("1");
		try {
			linkus.setCreatetime(sdf.parse(sdf.format(new Date())));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		linkus.setLinkuscontent(linkuscontent);
		try {
			bulletinService.savelinkus(linkus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_logger.info("upload examine success-------");

		return SUCCESS;
	}

	/**
	 * 增加集团分部联系方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveLinkus1() throws AppException {
		try {
			this.deleteLinkusById1();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HttpServletRequest request = ServletActionContext.getRequest();
		String linkuscontent = request.getParameter("linkuscontent");// 获得保存CKedit内容

		linkus.setLinkustype("2");
		try {
			linkus.setCreatetime(sdf.parse(sdf.format(new Date())));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		linkus.setLinkuscontent(linkuscontent);
		try {
			bulletinService.savelinkus(linkus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_logger.info("upload examine success-------");

		return SUCCESS;
	}

	/**
	 * 获得集团分部联系方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLinkusByType1() throws Exception {
		// bulletin =
		// bulletinService.getBulletinById(Long.parseLong(bulletinId));
		String linkustype = "2";
		try {
			List list = bulletinService.getLinkusByType(linkustype);
			if (list.size() > 0) {
				Map map = (Map) list.get(0);
				linkusid = map.get("LINKUSID").toString();

			} else {
				linkusid = "0";
			}
		} catch (Exception e) {
			throw new AppException("获得linkus信息", e);
		}

		if (linkusid != "0") {
			linkus = bulletinService.getLinkusById(Long.valueOf(linkusid));
		}

		_logger.info("get all linkus  by page success-------");
		return SUCCESS; // 详情页面

	}

	/**
	 * 修改
	 */
	public String updateBulletin() throws Exception {
		bulletinService.updateBulletin(bulletin);
		return SUCCESS;
	}

	public String[] getIdArray() {
		return idArray;
	}

	public void setIdArray(String[] idArray) {
		this.idArray = idArray;
	}

	public String getBulletinContents() {
		return bulletinContents;
	}

	public void setBulletinContents(String bulletinContents) {
		this.bulletinContents = bulletinContents;
	}

	public File[] getCertificate() {
		return certificate;
	}

	public void setCertificate(File[] certificate) {
		this.certificate = certificate;
	}

	public String[] getCertificateFileName() {
		return certificateFileName;
	}

	public void setCertificateFileName(String[] certificateFileName) {
		this.certificateFileName = certificateFileName;
	}

	public String getLinkusid() {
		return linkusid;
	}

	public void setLinkusid(String linkusid) {
		this.linkusid = linkusid;
	}

	public Linkus getLinkus() {
		return linkus;
	}

	public void setLinkus(Linkus linkus) {
		this.linkus = linkus;
	}

}
