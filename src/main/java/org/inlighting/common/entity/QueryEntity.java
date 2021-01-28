package org.inlighting.common.entity;


import lombok.Data;

/**
 * 数据的实体
 * 
 * @author pugaofei
 *
 */
@Data
public class QueryEntity {

	private int rows = 10;

	private int page = 1;

	private int start = 0;

	private int end = 10;

	String[] sort = null;

	String order = "";
}
