package com.taller1.VarelaFanny.model;



import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the FOLLOWUPINTERESTED database table.
 *
 */
@Entity
@NamedQuery(name = "Followupinterested.findAll", query = "SELECT f FROM Followupinterested f")
public class Followupinterested implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FollowupinterestedPK id;

	private String dumy;

	// bi-directional many-to-one association to Followuptype
	@ManyToOne
	@JoinColumn(name = "FOLTYPE_FOLLOWUP_ID", insertable = false, updatable = false)
	private Followuptype followuptype;

	// bi-directional many-to-one association to Rolee
	@ManyToOne
	@JoinColumn(name = "ROLE_ROLE_ID", insertable = false, updatable = false)
	private Rolee rolee;

	public Followupinterested() {
	}

	public String getDumy() {
		return this.dumy;
	}

	public Followuptype getFollowuptype() {
		return this.followuptype;
	}

	public FollowupinterestedPK getId() {
		return this.id;
	}

	public Rolee getRolee() {
		return this.rolee;
	}

	public void setDumy(String dumy) {
		this.dumy = dumy;
	}

	public void setFollowuptype(Followuptype followuptype) {
		this.followuptype = followuptype;
	}

	public void setId(FollowupinterestedPK id) {
		this.id = id;
	}

	public void setRolee(Rolee rolee) {
		this.rolee = rolee;
	}

}