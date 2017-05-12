package com.xfh.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author 次此处写自己的姓名
 * 功能：金额实体类，与数据库中money表对应
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="do_record",schema="donation_platform")
public class Do_Record implements Serializable {
	private Integer id;
	private Integer mon_Number;   //捐献金额数
	private String do_Time;       //捐赠时间
	private User userByUserId;  //对应用户
	private Project proByProId;  //对应活动
	
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
	@Column(name="mon_Number",nullable=false)
	public Integer getMon_Number() {
		return mon_Number;
	}
	public void setMon_Number(Integer mon_Number) {
		this.mon_Number = mon_Number;
	}
	
	@Basic 
	@Column(name="do_Time",nullable=false)
	public String getDo_Time() {
		return do_Time;
	}
	public void setDo_Time(String do_Time) {
		this.do_Time = do_Time;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",referencedColumnName="id")
	public User getUserByUserId() {
		return userByUserId;
	}
	public void setUserByUserId(User userByUserId) {
		this.userByUserId = userByUserId;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pro_id",referencedColumnName="id")
	public Project getProByProId() {
		return proByProId;
	}
	public void setProByProId(Project proByProId) {
		this.proByProId = proByProId;
	}
	
	
}
