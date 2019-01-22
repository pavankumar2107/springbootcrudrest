package com.uskcorp.springbootcrudrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "appinfo")
@EntityListeners(AuditingEntityListener.class)
public class AppInfo {

	private long id;
	private String text;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "text", nullable = false)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}