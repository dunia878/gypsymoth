<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%
    String filename = "file";
    String[] temp;
    String fileUrl = "";
    if (request.getParameter("fileUrl") != null) {
        fileUrl = request.getParameter("fileUrl");
        temp = fileUrl.split("/");
        if(temp.length > 1){
    		filename = temp[temp.length - 1];
		}
    }
    response.setContentType("application/msexcel");
    response.setHeader("content-disposition","attachment; filename="+filename);
    System.out.println("download file :"+filename);
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
        bis = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/attachment/" + fileUrl)));
        bos = new BufferedOutputStream(response.getOutputStream());

        byte[] buff = new byte[2048];
        int bytesread;

        while(-1 != (bytesread = bis.read(buff, 0, buff.length))) {
            bos.write(buff,0,bytesread);
        }

    } catch(final IOException e) {
        System.out.println ( "出现ioexception." + e );
    } finally {
    	out.clear();
		out = pageContext.pushBody();
        if (bis != null)
            bis.close();
        if (bos != null)
            bos.close();
    }
    return;
%>