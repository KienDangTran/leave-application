package com.giong.model;

import java.io.Serializable;

/**
 * The persistent abtract class for database table.
 * 
 */
public abstract class BaseEntity implements Serializable {
	
	public abstract Object getId();
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "; ID: [" + this.getId().toString() + "] ";
	}
}
