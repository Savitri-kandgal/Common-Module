package com.xworkz.templeregistration.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="visiting_info")


@NamedQueries({@NamedQuery(name="fetchDetailsByemail", query="select vEntity from VisitingEntity vEntity where pEntity.pId IN (select pId from PersonalEntity where emailAddress=:EMAIL_ADDRESS)")})
public class VisitingEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger=Logger.getLogger(PersonalEntity.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="V_ID")
	private int vId;
	@Column(name="DATE")
	private String date;
	@Column(name = "NO_OF_PEOPLE")
	private String noOfPeople;
	@Column(name="SPECIAL_ENTRY")
	private String seLt;
	@Column(name="PRASADHA")
	private String prLt;
	@Column(name="POOJA_TYPE")
	private String ptLt;
	@Column(name="ID_CARD")
	private String idLt;
	@Column(name="ID_NUMBER")
	private String idNumber;

	//@JoinColumn(name="P_ID")
	//@OneToOne(cascade = CascadeType.ALL)
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="P_ID")
	private PersonalEntity pEntity;
	
	public VisitingEntity() {
		logger.info("LOGGER: "+this.getClass().getSimpleName()+"obj created");
	}

	public int getvId() {
		return vId;
	}

	public void setvId(int vId) {
		this.vId = vId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getNoOfPeople() {
		return noOfPeople;
	}

	public void setNoOfPeople(String noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	public String getSeLt() {
		return seLt;
	}
	public void setSeLt(String seLt) {
		this.seLt = seLt;
	}
	public String getPrLt() {
		return prLt;
	}
	public void setPrLt(String prLt) {
		this.prLt = prLt;
	}
	public String getPtLt() {
		return ptLt;
	}
	public void setPtLt(String ptLt) {
		this.ptLt = ptLt;
	}
	public String getIdLt() {
		return idLt;
	}
	public void setIdLt(String idLt) {
		this.idLt = idLt;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	

	public PersonalEntity getpEntity() {
		return pEntity;
	}
	public void setpEntity(PersonalEntity pEntity) {
		this.pEntity = pEntity;
	}

	@Override
	public String toString() {
		return "VisitingEntity [vId=" + vId + ", date=" + date + ", noOfPeople=" + noOfPeople + ", seLt=" + seLt
				+ ", prLt=" + prLt + ", ptLt=" + ptLt + ", idLt=" + idLt + ", idNumber=" + idNumber + "]";
	}


}
