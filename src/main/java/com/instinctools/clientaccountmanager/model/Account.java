package com.instinctools.clientaccountmanager.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity 
@Table(name = "Account", uniqueConstraints = { 
        @UniqueConstraint(columnNames = "ID")}) 
public class Account {
	
	public Account(){
		
	}
	
	public Account(String name){
		this.name = name;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(name = "ID", unique = true, nullable = false) 
    private Integer Id; 

	@Column(name = "NAME", unique = false, nullable = false) 
    private String name;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinTable(name = "CLIENT_ACCOUNT", joinColumns = { @JoinColumn(name = "ACCOUNT_ID") }, inverseJoinColumns = { @JoinColumn(name = "CLIENT_ID") })
	private Client client;
}
