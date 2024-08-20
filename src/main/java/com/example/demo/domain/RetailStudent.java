package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RetailStudent {
	
	@Id
	@Column(name = "id")
    private String id;
	
	@Column(name = "name")
	private String name;
	
	  @Column(name = "surname")
	  private String surname;
	  
	  @Column(name = "contact")
	  private String contact;
	  
	  @Column(name = "address")
	  private String address;
	  
	  @Column(name = "gender")
	  private String gender;
	  
	  @Column(name = "welfare")
	  private String typeWelfare;
	  
	  @Column(name = "matricStatus")
	  private String matricStatus;
	  
	  @Column(name = "ward")
	  private int ward;
	  
	  @Column(name = "qualification")
	  private String qualification;
	  
	  @Column(name = "program")
	  private String program;
	  
	  @Column(name = "Initials")
	  private String initial;
	  
	  public RetailStudent(String id, String name, String surname, String contact, String address, String gender,
				String typeWelfare, String matricStatus, int ward, String qualification , String program , String initial) {
			super();
			this.id = id;
			this.name = name;
			this.surname = surname;
			this.contact = contact;
			this.address = address;
			this.gender = gender;
			this.typeWelfare = typeWelfare;
			this.matricStatus = matricStatus;
			this.ward = ward;
			this.qualification = qualification;
			this.program = program;
			this.initial = initial;
		}


		public RetailStudent() {
			super();
			// TODO Auto-generated constructor stub
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getSurname() {
			return surname;
		}


		public void setSurname(String surname) {
			this.surname = surname;
		}


		public String getContact() {
			return contact;
		}


		public void setContact(String contact) {
			this.contact = contact;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public String getGender() {
			return gender;
		}


		public void setGender(String gender) {
			this.gender = gender;
		}


		public String getTypeWelfare() {
			return typeWelfare;
		}


		public void setTypeWelfare(String typeWelfare) {
			this.typeWelfare = typeWelfare;
		}


		public String getMatricStatus() {
			return matricStatus;
		}


		public void setMatricStatus(String matricStatus) {
			this.matricStatus = matricStatus;
		}


		public int getWard() {
			return ward;
		}


		public void setWard(int ward) {
			this.ward = ward;
		}


		public String getQualification() {
			return qualification;
		}


		public void setQualification(String qualification) {
			this.qualification = qualification;
		}


		public String getProgram() {
			return program;
		}


		public void setProgram(String program) {
			this.program = program;
		}


		public String getInitial() {
			return initial;
		}


		public void setInitial(String initial) {
			this.initial = initial;
		}
		

}
