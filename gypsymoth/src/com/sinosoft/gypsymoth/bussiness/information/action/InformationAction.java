package com.sinosoft.gypsymoth.bussiness.information.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sinosoft.gypsymoth.bussiness.bulletin.action.BulletinAction;
import com.sinosoft.gypsymoth.bussiness.information.service.InformationService;
import com.sinosoft.gypsymoth.exception.AppException;
import com.sinosoft.gypsymoth.pojo.Account;
import com.sinosoft.gypsymoth.pojo.AccountRole;
import com.sinosoft.gypsymoth.pojo.Information;
import com.sinosoft.gypsymoth.utils.Constants;
import com.sinosoft.gypsymoth.utils.Pagination;

public class InformationAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger _logger = Logger.getLogger(BulletinAction.class);

	private InformationService informationService;

	public InformationService getInformationService() {
		return informationService;
	}

	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}

	public Logger get_logger() {
		return _logger;
	}

	private String actionName;
	private Information information;
	private String informationid;
	private Long accountid;
	private String accountname;
	private String filename;
	private String filediscripe;
	private String fileurl;
	private Date fileuptime;
	private Long filetip;
	private Long informationstate;
	private Long informationtip;
	private Long informationtips;

	private String packName;
	private String[] idArray;
	private File[] upload;
	private String[] uploadFileName;


	/**
	 * 修改资料信息
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updataInformation() throws AppException { 
		HttpServletRequest request = ServletActionContext.getRequest();
		String filenames = information.getFilename();
		String radiochecked1 = request.getParameter("filetip");
		String radiochecked = request.getParameter("informationtip");
		String radiochecked2 = request.getParameter("informationtips");
		String[] oldnames = request.getParameterValues("oldtext");
		try {
			information.setInformationtips(Long.valueOf(radiochecked2));
			information.setFiletip(Long.valueOf(radiochecked1));
			information.setInformationtip(Long.valueOf(radiochecked));
			informationService.updateInformations(information, upload, uploadFileName, packName,oldnames);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} 
	
		return SUCCESS;
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
				informationService.deleteInformation(idArray[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_logger.info("delete  information success-------");
		}
		return SUCCESS;
	}

	/**
	 * 选择客户 用户 管理员
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectRole() throws Exception {
		Map session = ActionContext.getContext().getSession();
		Account account = (Account) session.get(Constants.ACCOUNT_SESSION);
		String accountId = account.getAccountId();
		List<AccountRole> accountRoleList = (List) informationService
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
	 * 文件上传
	 */
	public String save() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		 
		if(upload != null){
			informationService.save(information, upload, uploadFileName, packName);
		}else{
//			informationService.save1(information);
		} 
		return SUCCESS;

	}

	public String downloadlist()
	{
		System.out.println("downloadlist");
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		if (id!=null) {
			try {
				information = informationService.getInformationById(Long.parseLong(id));
				getFilelist();
			} catch (Exception e) {
				e.printStackTrace();
 				return ERROR;
			}
		}
		return SUCCESS; 
	}
	
	
	/**
	 * 未登录 首页文件下载
	 * 
	 * @param informationid
	 * @throws Exception
	 */
	public void downloadFileindex() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String informationid = request.getParameter("informationid");
		String rootPath = ServletActionContext.getServletContext().getRealPath("/");	//服务所在目录
		information = informationService.getInformationById(Long.valueOf(informationid));
		String filepaths = information.getFileurl();
		String filenames = information.getFilename();
		
		String filepath = rootPath + filepaths;

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setCharacterEncoding("GBK");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(filenames.getBytes("gb2312"), "ISO8859-1"));

		try {
			FileInputStream fileInputStream = new FileInputStream(new File(
					filepath));
			int len = 1024;
			byte[] bytes = new byte[1024];
			while ((len = fileInputStream.read(bytes)) != -1) {
				response.getOutputStream().write(bytes, 0, len);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		_logger.info("download  information success-------");
	}
	
	/**
	 * 下载文件
	 * @throws Exception
	 */
	public void downloadFiles() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String informationid = request.getParameter("informationid");
		information = informationService.getInformationById(Long
				.valueOf(informationid));
		String filepaths = information.getFileurl();
		String filenames = information.getFilename();
		String filepath = "d:" + filepaths;

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setCharacterEncoding("GBK");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(filenames.getBytes("gb2312"), "ISO8859-1"));

		try {
			FileInputStream fileInputStream = new FileInputStream(new File(
					filepath));
			int len = 1024;
			byte[] bytes = new byte[1024];
			while ((len = fileInputStream.read(bytes)) != -1) {
				response.getOutputStream().write(bytes, 0, len);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		_logger.info("download  information success-------");
	}

	/**
	 * 登陆后文件下载
	 */
	public void downloadFile() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String id = request.getParameter("id");
		String index = request.getParameter("index");
		String filepaths = "";
		String filenames = "";
		
		information = informationService.getInformationById(Long.parseLong(id));
		
		String information_filename = information.getFilename();
		String information_fileurl = information.getFileurl();
		if (information_filename!=null) {
			String[] names = information_filename.split("\\|\\|");
			String[] urls = information_fileurl.split("\\|\\|");
			filepaths = urls[Integer.valueOf(index)];
			filenames = names[Integer.valueOf(index)];
		} 
		
		String rootPath = ServletActionContext.getServletContext().getRealPath("/");
		String filepath = rootPath + filepaths;
		 
		File tempfile = new File(filepath);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setContentLength(Integer.valueOf(String.valueOf(tempfile.length())));   
		   
		response.setCharacterEncoding("GBK");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(filenames.getBytes("gb2312"), "ISO8859-1"));

		try {
			FileInputStream fileInputStream = new FileInputStream(new File(filepath));
			int len = 1024;
			byte[] bytes = new byte[1024*10];
			while ((len = fileInputStream.read(bytes)) != -1) {
				response.getOutputStream().write(bytes, 0, len);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		_logger.info("download  information success-------");
	}

	/**
	 * 首页显示资料列表
	 */
	public void indexList1() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if( ServletActionContext.getContext().getLocale().toString().equals("en_US")){
		List<Information> list1 = new ArrayList<Information>();
		try {
			list1 = informationService.getIndexDataByPage(0, 4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("list1", list1);
		_logger.info("get all   information index success-------");
		}else{
			List<Information> list1 = new ArrayList<Information>();
			try {
				list1 = informationService.getIndexDataByPageCH(0, 4);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("list1", list1);
			_logger.info("get all   information index success-------");
		}
	}

	/**
	 * @see 登录用户（管理级别） 分页查询资料信息
	 */
	public String getAllDataByPages() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
//		if( ServletActionContext.getContext().getLocale().toString().equals("en_US")){
			
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);

		try {
			Integer totleCount = informationService.getAllDataCounts();
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Information> informationlist = informationService
					.getAllDataByPages(begin, numOfEachPage);
			request.setAttribute("informationlist", informationlist);
			_logger.info("get all data by pageFOR all success-------");
		} catch (Exception e) {
			throw new AppException("分页查询全部信息", e);
		}
//		}else{
//			// 分页配置
//			Pagination p = new Pagination(0, 0, 0);
//			String currPage = (String) request.getParameter("goPage");
//			if (currPage == null) {
//				currPage = "1";// ” currPage”是当前页数
//			}
//			_logger.info("currPage:" + currPage);
//			Integer currPage1 = Integer.parseInt(currPage);
//
//			try {
//				Integer totleCount = informationService.getAllDataCountsCH();
//				p.getPagination(request, totleCount, currPage1, null, null, null);
//				int numOfEachPage = Constants.NUMOFEACHPAGE;
//				int begin = p.getRownum_begin(numOfEachPage, currPage1);
//				List<Information> informationlist = informationService
//						.getAllDataByPagesCH(begin, numOfEachPage);
//				request.setAttribute("informationlist", informationlist);
//				_logger.info("get all data by pageFOR all success-------");
//			} catch (Exception e) {
//				throw new AppException("分页查询全部信息", e);
//			}
//		}
//		
		this.actionName = "getAllDataByPages";
		_logger.info("get all   information  log in  success-------");
		return SUCCESS;
	}

	/**
	 * @see 未登录 分页查询资料信息
	 */
	public String getAllDataByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if( ServletActionContext.getContext().getLocale().toString().equals("en_US")){
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);

		try {
			Integer totleCount = informationService.getAllDataCount();
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Information> informationlist = informationService
					.getAllDataByPage(begin, numOfEachPage);
			request.setAttribute("informationlist", informationlist);
			_logger.info("get all data by page  FOR NOLOGIN success-------");
		} catch (Exception e) {
			throw new AppException("分页查询全部信息", e);
		}
		}else{
			Pagination p = new Pagination(0, 0, 0);
			String currPage = (String) request.getParameter("goPage");
			if (currPage == null) {
				currPage = "1";// ” currPage”是当前页数
			}
			_logger.info("currPage:" + currPage);
			Integer currPage1 = Integer.parseInt(currPage);

			try {
				Integer totleCount = informationService.getAllDataCountCH();
				p.getPagination(request, totleCount, currPage1, null, null, null);
				int numOfEachPage = Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				List<Information> informationlist = informationService
						.getAllDataByPageCH(begin, numOfEachPage);
				request.setAttribute("informationlist", informationlist);
				_logger.info("get all data by page  FOR NOLOGIN success-------");
			} catch (Exception e) {
				throw new AppException("分页查询全部信息", e);
			}
		}
		this.actionName = "getAllDataByPage";
		_logger.info("get all   information not log in   success-------");
		return SUCCESS;
	}

	/**
	 * @see 用户登录（非管理级别） 分页查询资料信息
	 */
	public String getAllADataByPage() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if( ServletActionContext.getContext().getLocale().toString().equals("en_US")){
		// 分页配置
		Pagination p = new Pagination(0, 0, 0);
		String currPage = (String) request.getParameter("goPage");
		if (currPage == null) {
			currPage = "1";// ” currPage”是当前页数
		}
		_logger.info("currPage:" + currPage);
		Integer currPage1 = Integer.parseInt(currPage);
		try {
			Integer totleCount = informationService.getAllADataCount();
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Information> informationlist = informationService
					.getAllADataByPage(begin, numOfEachPage);
			request.setAttribute("informationlist", informationlist);
			_logger.info("get all data by page FOR A success-------");
		} catch (Exception e) {
			throw new AppException("分页查询全部信息", e);
		}
		}else{
			Pagination p = new Pagination(0, 0, 0);
			String currPage = (String) request.getParameter("goPage");
			if (currPage == null) {
				currPage = "1";// ” currPage”是当前页数
			}
			_logger.info("currPage:" + currPage);
			Integer currPage1 = Integer.parseInt(currPage);
			try {
				Integer totleCount = informationService.getAllADataCountCH();
				p.getPagination(request, totleCount, currPage1, null, null, null);
				int numOfEachPage = Constants.NUMOFEACHPAGE;
				int begin = p.getRownum_begin(numOfEachPage, currPage1);
				List<Information> informationlist = informationService
						.getAllADataByPageCH(begin, numOfEachPage);
				request.setAttribute("informationlist", informationlist);
				_logger.info("get all data by page FOR A success-------");
			} catch (Exception e) {
				throw new AppException("分页查询全部信息", e);
			}
		}
		this.actionName = "getAllADataByPage";
		_logger.info("get all   information person  log in   success-------");
		return SUCCESS;
	}

	/**
	 * @see 客户登录 分页查询资料信息
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
			Integer totleCount = informationService.getAllCDataCount();
			p.getPagination(request, totleCount, currPage1, null, null, null);
			int numOfEachPage = Constants.NUMOFEACHPAGE;
			int begin = p.getRownum_begin(numOfEachPage, currPage1);
			List<Information> informationlist = informationService
					.getAllCDataByPage(begin, numOfEachPage);
			request.setAttribute("informationlist", informationlist);
			_logger.info("get all data by page FOR C success-------");
		} catch (Exception e) {
			throw new AppException("分页查询全部信息", e);
		}
		this.actionName = "getAllDataByPage";
		_logger.info("get all   information client  log in   success-------");
		return SUCCESS;
	}

	/**
	 * 根据ID查找Entity
	 */
	public String getInformationById() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		information = informationService.getInformationById(Long.parseLong(informationid));
		getFilelist();
		return SUCCESS;
	}
	
	/**
	 * 根据ID查找Entity
	 */
	public String getInformationByIds() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		information = informationService.getInformationById(Long.parseLong(informationid));
		getFilelist();  
		 
		Long bulletinstate = information.getInformationtip();
		Long bulletinlanguage = information.getFiletip();
		Long infotips = information.getInformationtips();
		if (bulletinstate != null ){if(bulletinstate == 2) {
			request.setAttribute("radiochecked", "2");}
		}
		if (bulletinlanguage !=null ){if(bulletinlanguage == 2) {
			request.setAttribute("radiochecked1", "2");}
		}
		if (bulletinlanguage !=null ){if(bulletinlanguage == 3) {
			request.setAttribute("radiochecked2", "3");}
		}
		if (infotips != null ) {
			if( infotips == 1){
			request.setAttribute("radiochecked3", "1");}
		}
		_logger.info("get all information  by page success-------");
		return SUCCESS;
	}

	/**
	 * 根据ID查找Entity
	 */
	public String getInformationByIdPerson() throws Exception {
		information = informationService.getInformationById(Long
				.parseLong(informationid));
		_logger.info("get all information  by page success-------");
		return SUCCESS;
	}

	/**
	 * 删除更新
	 */
	public String deleteInformationById() throws Exception {
		informationService.deleteInformationById(Long.parseLong(informationid));
		_logger.info("delete & update information   success-------");
		return SUCCESS;
	}

	/**
	 * 文件下载 下载文件原始存放路径
	 */
	//	
	// private final static String DOWNLOADFILEPATH="d:/filesLocation/";
	// //初始存放路径
	// //文件名
	//	
	//	
	//	
	// //从下载文件原始存放路径读取得到文件输出流
	// public InputStream getDownloadFile() {
	// return
	// ServletActionContext.getServletContext().getResourceAsStream(DOWNLOADFILEPATH+informationname);
	// }
	// //如果下载文件名为中文，进行字符编码转换
	// public String getDownloadChineseFileName() {
	// String downloadChineseFileName = informationname;
	//  
	// try {
	// downloadChineseFileName = new String(downloadChineseFileName.getBytes(),
	// "ISO8859-1");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	//  
	// return downloadChineseFileName;
	// }
	//
	// public static String getDOWNLOADFILEPATH() {
	// return DOWNLOADFILEPATH;
	// }
	// 文件标题
	private String informationname;

	// 上传文件域对象
	// private File upload;
	// 上传文件名
	// private String filename;
	// 上传文件类型
	// private String filediscripe;
	// 保存文件的目录路径(通过依赖注入)
	// private String fileurl;
	// 以下省略getter和setter......
	// 自己封装的一个把源文件对象复制成目标文件对象
	// private static void copy(File src, File dst) {
	// InputStream in = null;
	// OutputStream out = null;
	// try {
	// in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
	// out = new BufferedOutputStream(new FileOutputStream(dst),
	// BUFFER_SIZE);
	// byte[] buffer = new byte[BUFFER_SIZE];
	// int len = 0;
	// while ((len = in.read(buffer)) > 0) {
	// out.write(buffer, 0, len);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (null != in) {
	// try {
	// in.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// if (null != out) {
	// try {
	// out.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// }
	// @Override
	// public String execute() throws Exception {
	// //根据服务器的文件保存地址和原文件名创建目录文件全路径
	// String dstPath = ServletActionContext.getServletContext()
	// .getRealPath(this.getFileurl())
	// + "\\" + this.getFilename();
	// System.out.println("上传的文件的类型："+ this.getFilediscripe());
	// File dstFile = new File(dstPath);
	// // copy(this.upload, dstFile);
	// return SUCCESS;
	// }

	
	/**
	 * 得到文件信息list
	 */
	public void getFilelist()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		List filelist = new ArrayList();
		String information_filename = information.getFilename();
		String information_fileurl = information.getFileurl();
		if (information_filename!=null) {
			String[] names = information_filename.split("\\|\\|");
			String[] urls = information_fileurl.split("\\|\\|");
			for (int i = 0; i < names.length; i++) {
				HashMap map = new HashMap();
				map.put("name", names[i]); 
				map.put("url", urls[i]);
				map.put("fileindex", i);
				map.put("id", information.getInformationid());
				filelist.add(map); 
			}
		}
		request.setAttribute("filelist", filelist);
	}
	
	public String save1() throws Exception {
		// 根据服务器的文件保存地址和原文件名创建目录文件全路径
		System.out.println("上传的文件的类型：" + this.getFilediscripe());
		// copy(this.upload, dstFile);
		return SUCCESS;
	}
	public String[] getIdArray() {
		return idArray;
	}

	public void setIdArray(String[] idArray) {
		this.idArray = idArray;
	}

	public Information getInformation() {
		return information;
	}

	public void setInformation(Information information) {
		this.information = information;
	}

	public String getInformationid() {
		return informationid;
	}

	public void setInformationid(String informationid) {
		this.informationid = informationid;
	}

	public String getInformationname() {
		return informationname;
	}

	public void setInformationname(String informationname) {
		this.informationname = informationname;
	}

	public Long getAccountid() {
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilediscripe() {
		return filediscripe;
	}

	public void setFilediscripe(String filediscripe) {
		this.filediscripe = filediscripe;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public Date getFileuptime() {
		return fileuptime;
	}

	public void setFileuptime(Date fileuptime) {
		this.fileuptime = fileuptime;
	}

	public Long getFiletip() {
		return filetip;
	}

	public void setFiletip(Long filetip) {
		this.filetip = filetip;
	}

	public Long getInformationtips() {
		return informationtips;
	}

	public void setInformationtips(Long informationtips) {
		this.informationtips = informationtips;
	}

	public Long getInformationstate() {
		return informationstate;
	}

	public void setInformationstate(Long informationstate) {
		this.informationstate = informationstate;
	}

	public Long getInformationtip() {
		return informationtip;
	}

	public void setInformationtip(Long informationtip) {
		this.informationtip = informationtip;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}


	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

}
