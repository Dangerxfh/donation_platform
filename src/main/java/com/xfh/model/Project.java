package com.xfh.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 
 * @author Fog
 *功能：活动实体类
 */
@SuppressWarnings("serial")
@Entity
@Table(name="project",schema="donation_platform")
public class Project implements Serializable{
	private Integer id;
	private String pro_Title;   //活动标题
	private String pro_Des;    //活动描述
	private String pro_StartTime;   //活动开始时间
	private String pro_EndTime;    //活动结束时间
	private String pro_Status;   //活动状态
	private String pro_Sponsor;   //活动发起方
	private Integer pro_TargetNumber; //目标数
	private Integer pro_CurNumber;   //当前数
	private Integer pro_CurPeoples;   //当前参与活动总人次
	private Set<User> userByUserId;   //参与活动用户集合
	private Set<Do_Record> recById;  //捐款记录集合
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Basic
	@Column(name="pro_Title",nullable=false)
	public String getPro_Title() {
		return pro_Title;
	}
	public void setPro_Title(String pro_Title) {
		this.pro_Title = pro_Title;
	}
	
	@Basic
	@Column(name="pro_Des",nullable=false)
	public String getPro_Des() {
		return pro_Des;
	}
	public void setPro_Des(String pro_Des) {
		this.pro_Des = pro_Des;
	}
	
	@Basic
	@Column(name="pro_StartTime",nullable=false)
	public String getPro_StartTime() {
		return pro_StartTime;
	}
	public void setPro_StartTime(String pro_StartTime) {
		this.pro_StartTime = pro_StartTime;
	}
	
	@Basic
	@Column(name="pro_EndTime",nullable=true)
	public String getPro_EndTime() {
		return pro_EndTime;
	}
	public void setPro_EndTime(String pro_EndTime) {
		this.pro_EndTime = pro_EndTime;
	}
	
	@Basic
	@Column(name="pro_Status",nullable=false)
	public String getPro_Status() {
		return pro_Status;
	}
	public void setPro_Status(String pro_Status) {
		this.pro_Status = pro_Status;
	}
	
	@Basic
	@Column(name="pro_Sponsor",nullable=false)
	public String getPro_Sponsor() {
		return pro_Sponsor;
	}
	public void setPro_Sponsor(String pro_Sponsor) {
		this.pro_Sponsor = pro_Sponsor;
	}
	
	@Basic
	@Column(name="pro_TargetNumber",nullable=false)
	public Integer getPro_TargetNumber() {
		return pro_TargetNumber;
	}
	public void setPro_TargetNumber(Integer pro_TargetNumber) {
		this.pro_TargetNumber = pro_TargetNumber;
	}
	@Basic
	@Column(name="pro_CurNumber")
	public Integer getPro_CurNumber() {
		return pro_CurNumber;
	}
	public void setPro_CurNumber(Integer pro_CurNumber) {
		this.pro_CurNumber = pro_CurNumber;
	}
	
	
	@Basic
	@Column(name="pro_CurPeoples")
	public Integer getPro_CurPeoples() {
		return pro_CurPeoples;
	}
	public void setPro_CurPeoples(Integer pro_CurPeoples) {
		this.pro_CurPeoples = pro_CurPeoples;
	}
	
	@ManyToMany(mappedBy="proById",cascade=CascadeType.ALL)
	public Set<User> getUserByUserId() {
		return userByUserId;
	}
	public void setUserByUserId(Set<User> userByUserId) {
		this.userByUserId = userByUserId;
	}
	
	@OneToMany(mappedBy="proByProId")
	public Set<Do_Record> getRecById() {
		return recById;
	}
	public void setRecById(Set<Do_Record> recById) {
		this.recById = recById;
	}

	//重写hashcode
	@Override
	public int hashCode() {
		int result=1;
		result=31*result+((id==null?0:id.hashCode()));
		return result;
	}
	
	//重写equals方法
	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(obj==null || getClass()!=obj.getClass())
			return false;
		Project that=(Project) obj;
		if(id!=that.id)
			return false;
		return true;
	}
}
