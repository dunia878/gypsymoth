package com.sinosoft.gypsymoth.pojo;

public abstract class AbstractFile implements java.io.Serializable{
	
	private Long id;
	private Long businessId;
    private String fileName;
    private Integer fileType;
    private String filePath;
    private String desctrption;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDesctrption() {
		return desctrption;
	}
	public void setDesctrption(String desctrption) {
		this.desctrption = desctrption;
	}
	

}
