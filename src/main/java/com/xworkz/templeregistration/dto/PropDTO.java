package com.xworkz.templeregistration.dto;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.xworkz.templeregistration.dao.TempleDAOImpl;

public class PropDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(PropDTO.class);
	
	private String propName;
	private String propValue;
	private String propType;
	
	public PropDTO() {
		logger.info("LOGGER :" + this.getClass().getSimpleName() + " object created");
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public String getPropValue() {
		return propValue;
	}
	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}
	public String getPropType() {
		return propType;
	}
	public void setPropType(String propType) {
		this.propType = propType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PropDTO [propName=" + propName + ", propValue=" + propValue + ", propType=" + propType + "]";
	}
	
}
