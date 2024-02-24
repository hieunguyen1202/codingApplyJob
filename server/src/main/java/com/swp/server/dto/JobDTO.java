package com.swp.server.dto;

import java.sql.Date;

public class JobDTO {
	private int Category_Id;
	private String Career_Level;
	private Integer Experience;
	private String Offer_Salary;
	private String Qualification;
	private String Job_Type;
	private int Branch_Id;
	private String Description;
	private Date Apply_Before;
	private String Address;

	public JobDTO() {
	}

	public JobDTO(int category_Id, String career_Level, Integer experience, String offer_Salary, String qualification,
			String job_Type, int branch_Id, String description, Date apply_Before, String address) {
		super();
		Category_Id = category_Id;
		Career_Level = career_Level;
		Experience = experience;
		Offer_Salary = offer_Salary;
		Qualification = qualification;
		Job_Type = job_Type;
		Branch_Id = branch_Id;
		Description = description;
		Apply_Before = apply_Before;
		Address = address;
	}

	public int getBranch_Id() {
		return Branch_Id;
	}

	public void setBranch_Id(int branch_Id) {
		Branch_Id = branch_Id;
	}

	public int getCategory_Id() {
		return Category_Id;
	}

	public void setCategory_Id(int category_Id) {
		Category_Id = category_Id;
	}

	public String getCareer_Level() {
		return Career_Level;
	}

	public void setCareer_Level(String career_Level) {
		Career_Level = career_Level;
	}

	public Integer getExperience() {
		return Experience;
	}

	public void setExperience(Integer experience) {
		Experience = experience;
	}

	public String getOffer_Salary() {
		return Offer_Salary;
	}

	public void setOffer_Salary(String offer_Salary) {
		Offer_Salary = offer_Salary;
	}

	public String getQualification() {
		return Qualification;
	}

	public void setQualification(String qualification) {
		Qualification = qualification;
	}

	public String getJob_Type() {
		return Job_Type;
	}

	public void setJob_Type(String job_Type) {
		Job_Type = job_Type;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getApply_Before() {
		return Apply_Before;
	}

	public void setApply_Before(Date apply_Before) {
		Apply_Before = apply_Before;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
}
