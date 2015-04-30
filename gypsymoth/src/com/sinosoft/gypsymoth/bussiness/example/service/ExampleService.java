package com.sinosoft.gypsymoth.bussiness.example.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.gypsymoth.pojo.Example;

public interface ExampleService {

	/**
	 * 保存
	 * @param example
	 * @throws Exception
	 */
	public abstract void save(Example example) throws Exception;

	/**
	 * 查询
	 * @return
	 * @throws Exception
	 */
	public abstract List<Example> getAllData() throws Exception;
	
	public abstract List<Example> getAllDataByPage(int begin,int numOfEachPage ) throws Exception;
	
	public abstract int getAllDataCount() throws Exception;
	
	public void select()  throws Exception;
	
	public abstract List getStatistics(Map statMap) throws Exception;

}