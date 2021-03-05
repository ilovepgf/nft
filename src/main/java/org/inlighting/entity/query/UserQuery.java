package org.inlighting.entity.query;

import org.inlighting.common.entity.QueryEntity;

import lombok.Data;

@Data
public class UserQuery extends QueryEntity {

	private Integer catalogId;
	
	private Integer labelId;
}
