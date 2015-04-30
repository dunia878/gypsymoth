package com.sinosoft.gypsymoth.utils;



import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import oracle.jdbc.OracleResultSet;
import oracle.sql.CLOB;

import com.sinosoft.gypsymoth.pojo.Bulletin;
import com.sinosoft.gypsymoth.pojo.Linkus;

/**
 * clob大字段处理
 * @author lixin
 *
 */
public class LobFactory {

	private static Connection con;
	String clobValue = null; //表的CLOB字段值
	
	/**
	 * 创建数据库连接
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException,ClassNotFoundException {
		Class.forName(Initialiser.Gypsymoth.getPropertie(Constants.PropertyKeys.CON_DRIVER));
		
		String url = Initialiser.Gypsymoth.getPropertie(Constants.PropertyKeys.CON_URL);
		String name = Initialiser.Gypsymoth.getPropertie(Constants.PropertyKeys.CON_NAME);
		String pass = Initialiser.Gypsymoth.getPropertie(Constants.PropertyKeys.CON_PASS);
		
		con = DriverManager.getConnection(url,name,pass);

		return con;

	}

	/**
	 * 读取CLOB字段值(信息资料公告)
	 * @param serialno
	 * @param table_name
	 * @param Clob_value
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static List read(Long bulletinId) throws SQLException,IOException {
		
		String rtn = null;
		String c_name = null;
		
		List rtnlist = new ArrayList();
		try {
			String sql = "SELECT BULLETINCONTENT from BULLETIN where BULLETINID = "+bulletinId;
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			java.sql.Clob clob = null;
			while(rs.next()) {
				HashMap map = new HashMap();
				clob = rs.getClob("BULLETINCONTENT");			
				rtn=clob.getSubString((long)1,(int)clob.length());			
				map.put("BULLETINCONTENT", rtn);
//				System.out.println("serialno :"+serialno+"  c_name:"+c_name);
//				System.out.println("c_value :"+rtn);
				rtnlist.add(map);
			}
			rs.close();
			pstmt.close();

		}catch (SQLException e){
			throw e;
		}catch (Exception ee) {
			ee.printStackTrace();
		}
//		System.out.println("----------"+rtn);
		
		con.close();
		return rtnlist;
	}
	
	
	/**
	 * 读取CLOB字段值(在线答疑)
	 * @param serialno
	 * @param table_name
	 * @param Clob_value
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static List read1(Long linkusid) throws SQLException,IOException {
		
		String rtn = null;
		String c_name = null;
		
		List rtnlist = new ArrayList();
		try {
			String sql = "SELECT LINKUSCONTENT from LINKUS where LINKUSID = "+linkusid;
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			java.sql.Clob clob = null;
			while(rs.next()) {
				HashMap map = new HashMap();
				clob = rs.getClob("LINKUSCONTENT");			
				rtn=clob.getSubString((long)1,(int)clob.length());			
				map.put("LINKUSCONTENT", rtn);
				rtnlist.add(map);
			}
			rs.close();
			pstmt.close();

		}catch (SQLException e){
			throw e;
		}catch (Exception ee) {
			ee.printStackTrace();
		}
//		System.out.println("----------"+rtn);
		
		con.close();
		return rtnlist;
	}
	
	/**
	 * 更新对clob字段值的持久化
	 * @param content
	 * @param lob_table
	 * @param serialno
	 * @param Clob_value
	 * @param table_name
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static boolean update(String content,Long bulletinId) throws SQLException,IOException, 
			ClassNotFoundException {

		String sql = "update BULLETIN set BULLETINCONTENT = empty_clob() "
			+"where BULLETINID =" + bulletinId;

		con = getConnection();
		con.setAutoCommit(false);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
		sql = "select BULLETINCONTENT from BULLETIN where  BULLETINID =" + bulletinId;

		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(sql);

		java.sql.Clob clob ;
		boolean isInsert = false;
		if (rs.next()) {
			isInsert = true;
			clob = ((OracleResultSet)rs).getClob("BULLETINCONTENT");
			oracle.sql.CLOB my_clob = (CLOB)clob;
			
	//		clob = ((org.apache.commons.dbcp.DelegatingResultSet)rs).getClob(fieldName);
	//		OutputStream writer = my_clob.getAsciiOutputStream();
	//		byte[] contentStr = content.getBytes();
	//		writer.write(contentStr);
	
			java.io.Writer writer = my_clob.getCharacterOutputStream();
	        writer.write(content);
	
			
			writer.flush();
	
			writer.close();

		}
		
		con.commit();
		rs.close();
		st.close();
		pstmt.close();
		con.setAutoCommit(true);
		con.close();
		return isInsert;
		}

	/**
	 * 实现对clob字段值的持久化
	 * @param content
	 * @param lob_table
	 * @param serialno
	 * @param Clob_value
	 * @param table_name
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void write(String content,String lob_table,BigDecimal serialno,
			Integer c_no,String c_name,String table_name,Integer multiply,Integer multiply_id) throws SQLException,IOException, 
			ClassNotFoundException {
		
		String sql = "INSERT INTO " + lob_table + "(SERIALNO,TABLE_NAME,C_NO,C_NAME,C_VALUE,MULTIPLY,MULTIPLY_ID)"
			+" VALUES(?,?,?,?,?,?,?)";
		
		con = getConnection();
		con.setAutoCommit(false);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setBigDecimal(1, serialno);
		pstmt.setString(2, table_name);
		pstmt.setInt(3, c_no);
		pstmt.setString(4, c_name);
		pstmt.setString(5, "empty_clob()");
		pstmt.setInt(6, multiply);
		pstmt.setInt(7, multiply_id);
		pstmt.execute();
		
		String sqls = "update " + lob_table + " set c_value = empty_clob() "
		+"where serialno =" + serialno +" and c_name='"+c_name+"'";

		PreparedStatement pstmts = con.prepareStatement(sqls);
		pstmts.executeUpdate();
		
		sql = "select c_value from " + lob_table + " where  serialno =" + serialno +" and c_name='"+c_name+"'";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		java.sql.Clob clob ;

		if (rs.next()) {

			clob = ((OracleResultSet)rs).getClob("C_VALUE");
			oracle.sql.CLOB my_clob = (CLOB)clob;
			
	//		clob = ((org.apache.commons.dbcp.DelegatingResultSet)rs).getClob(fieldName);
	//		OutputStream writer = my_clob.getAsciiOutputStream();
	//		byte[] contentStr = content.getBytes();
	//		writer.write(contentStr);
	
			java.io.Writer writer = my_clob.getCharacterOutputStream();
	        writer.write(content);
			
			writer.flush();
			writer.close();
		}
		con.commit();
		rs.close();
		st.close();
		pstmt.close();
		pstmts.close();
		con.setAutoCommit(true);
		con.close();
		}
	
	
	/**
	 * 实现对clob字段值的持久化 (公告表)
	 * @param content
	 * @param lob_table
	 * @param serialno
	 * @param Clob_value
	 * @param table_name
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void write(Bulletin bulletin) throws SQLException,IOException, 
			ClassNotFoundException {
		
		String sql = "INSERT INTO  BULLETIN(BULLETINID,BULLETINNAME,BULLETINCONTENT,ACCOUNTNAME,BULLETINTIP,BULLETINTIME,BULLETINTIPS,BULLETINLANGUAGE,FILEURL,FILENAME) "
			+" VALUES(BULLETIN_SEQU.Nextval,?,?,?,?,?,?,?,?,?)";
		String accountName = bulletin.getAccountName();
		String bulletinContent =  bulletin.getBulletinContent();
		
//		String cons = bulletinContent.replace("<p>", "");
//		String conss1 = cons.replace("&nbsp;", "");
//		String conss = conss1.replace("</p>", "<br/>");
		
		Long bulletinLanguage = bulletin.getBulletinLanguage();
		String bulletinName = bulletin.getBulletinName();
//		Date bulletinTime = bulletin.getBulletinTime();
		Long bulletinTip = bulletin.getBulletinTip();
		Long bulletinTips = bulletin.getBulletinTips();
		String filename = bulletin.getFilename();
		String fileurl = bulletin.getFileurl();
		
//		java.sql.Date date = new java.sql.Date(bulletinTime.getTime());
		
		con = getConnection();
		con.setAutoCommit(false);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bulletinName);
		pstmt.setString(2, "empty_clob()");
		pstmt.setString(3,accountName);
		pstmt.setLong(4, bulletinTip);
//		pstmt.setDate(5, date);
		pstmt.setTimestamp(5,new Timestamp((new java.util.Date()).getTime()));   

		pstmt.setLong(6, bulletinTips);
		pstmt.setLong(7, bulletinLanguage);
		pstmt.setString(8, fileurl);
		pstmt.setString(9, filename);
		
		pstmt.execute();
		
		String sqls = "update BULLETIN set bulletinContent = empty_clob()  where bulletinId in (select max(bulletinId) from BULLETIN)";

		PreparedStatement pstmts = con.prepareStatement(sqls);
		pstmts.executeUpdate();
		
		sql = "select bulletinContent from  BULLETIN   where bulletinId in (select max(bulletinId) from BULLETIN)";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		java.sql.Clob clob ;

		if (rs.next()) {

			clob = ((OracleResultSet)rs).getClob("BULLETINCONTENT");
			oracle.sql.CLOB my_clob = (CLOB)clob;
			
	//		clob = ((org.apache.commons.dbcp.DelegatingResultSet)rs).getClob(fieldName);
	//		OutputStream writer = my_clob.getAsciiOutputStream();
	//		byte[] contentStr = content.getBytes();
	//		writer.write(contentStr);
	
			java.io.Writer writer = my_clob.getCharacterOutputStream();
	        writer.write(bulletinContent);
			
			writer.flush();
			writer.close();
		}
		con.commit();
		rs.close();
		st.close();
		pstmt.close();
		pstmts.close();
		con.setAutoCommit(true);
		con.close();
		}
	
	
	
	/**
	 * 实现对clob字段值的持久化 (联系我们)
	 * @param content
	 * @param lob_table
	 * @param serialno
	 * @param Clob_value
	 * @param table_name
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void write1(Linkus linkus) throws SQLException,IOException, 
			ClassNotFoundException {
		
		String sql = "INSERT INTO  LINKUS(LINKUSID,LINKUSNAME,LINKUSCONTENT,CREATETIME,LINKUSTYPE) "
			+" VALUES(LINKUS_SEQU.Nextval,?,?,?,?)";
		String linkusname = linkus.getLinkusname();
		String linkuscontent =  linkus.getLinkuscontent();
		String conss1 = linkuscontent.replace("&nbsp;", "");
		String linkustype = linkus.getLinkustype();
		Date createtime = linkus.getCreatetime();
		java.sql.Date date = new java.sql.Date(createtime.getTime());
		con = getConnection();
		con.setAutoCommit(false);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, linkusname);
		pstmt.setString(2, "empty_clob()");
		pstmt.setDate(3,date);
		pstmt.setString(4, linkustype);
		pstmt.execute();
		
		String sqls = "update LINKUS set linkusContent = empty_clob()  where linkusId in (select max(linkusId) from LINKUS)";

		PreparedStatement pstmts = con.prepareStatement(sqls);
		pstmts.executeUpdate();
		
		sql = "select linkusContent from  LINKUS   where linkusId in (select max(linkusId) from LINKUS)";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		java.sql.Clob clob ;

		if (rs.next()) {

			clob = ((OracleResultSet)rs).getClob("LINKUSCONTENT");
			oracle.sql.CLOB my_clob = (CLOB)clob;
			java.io.Writer writer = my_clob.getCharacterOutputStream();
	        writer.write(conss1);
			
			writer.flush();
			writer.close();
		}
		con.commit();
		rs.close();
		st.close();
		pstmt.close();
		pstmts.close();
		con.setAutoCommit(true);
		con.close();
		}
	
	/**
	 * 更新对thesis_clob表中的字段值的进行更新 --add by yangjr
	 * @param content
	 * @param table_thesis  表名
	 * @param serialno   
	 * @param col_name   更新的字段名
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public static void updateThesis(String content, String table_thesis, BigDecimal serialno, String col_name) throws SQLException, IOException, ClassNotFoundException {
		String sql = "update " + table_thesis + " set " + col_name + " = empty_clob() " + "where parentid =" + serialno;
		con = getConnection();
		con.setAutoCommit(false);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
		sql = "select " + col_name + " from " + table_thesis + " where  parentid =" + serialno;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		java.sql.Clob clob;
		if (rs.next()) {
			clob = ((OracleResultSet) rs).getClob(col_name);
			oracle.sql.CLOB my_clob = (CLOB) clob;
			java.io.Writer writer = my_clob.getCharacterOutputStream();
			writer.write(content);
			writer.flush();
			writer.close();
		}
		con.commit();
		rs.close();
		st.close();
		pstmt.close();
		con.setAutoCommit(true);
		con.close();
	}
	
	/**
	 * TEST
	 * @param args
	 */
	public static void main(String[] args) {

		String id="20080728105886";
		BigDecimal serialno = new BigDecimal(id);
		String table_name = "thesisinfo";
		String Clob_name ="Summarycn";
		String c_name = "Summarycn";
		String Clob_value= "中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间。中青在线7月7日报道 近年来，随着中非传统友好关系的不断深化，中国维和部队也频频在非洲大陆上亮相。这自然引起了将非洲视为“势力范围”的西方国家的注意。美国《星条旗报》从6月29日起刊发了有关美军非洲司令部的报道，有关中国的议题屡屡见诸字里行间";

		String lob_table = "resource_clob";
		Integer c_no = 1;
		Integer multiply = 1;
		Integer multiply_id =2;
		try {

		LobFactory jc = new LobFactory();
//		jc.update(Clob_value,lob_table,serialno,Clob_name,table_name);
//		jc.write(Clob_value,lob_table,serialno,
//				c_no,c_name,table_name,multiply,multiply_id);
		//jc.read(serialno,table_name);

		}

		catch (Exception e) {

		System.out.println(e);

		e.printStackTrace();

		}
	}

	
}
