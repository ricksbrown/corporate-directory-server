package com.github.bordertech.corpdir.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Position in organization unit.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class PositionEntity implements Serializable {

	private Long id;
	private String alternateKey;
	private String desc;
	private List<PositionEntity> subPositions;
	private List<ContactEntity> contacts;
	private boolean active;
	private boolean custom;

	/**
	 *
	 * @return the unique id
	 */
	public Long getId() {
		return id;
	}

	/**
	 *
	 * @param id the unique id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 *
	 * @return the alternate position key
	 */
	public String getAlternateKey() {
		return alternateKey;
	}

	/**
	 *
	 * @param alternateKey the alternate position key
	 */
	public void setAlternateKey(final String alternateKey) {
		this.alternateKey = alternateKey;
	}

	/**
	 *
	 * @return the description
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 *
	 * @param desc the description
	 */
	public void setDesc(final String desc) {
		this.desc = desc;
	}

	/**
	 *
	 * @return the positions managed by this position
	 */
	public List<PositionEntity> getSubPositions() {
		return subPositions;
	}

	/**
	 *
	 * @param subPositions the positions managed by this position
	 */
	public void setSubPositions(final List<PositionEntity> subPositions) {
		this.subPositions = subPositions;
	}

	/**
	 *
	 * @return the contacts in this position
	 */
	public List<ContactEntity> getContacts() {
		return contacts;
	}

	/**
	 *
	 * @param contacts the contacts in this position
	 */
	public void setContacts(final List<ContactEntity> contacts) {
		this.contacts = contacts;
	}

	/**
	 *
	 * @return true if active record
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 *
	 * @param active true if active record
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 *
	 * @return true if custom record
	 */
	public boolean isCustom() {
		return custom;
	}

	/**
	 *
	 * @param custom true if custom record
	 */
	public void setCustom(final boolean custom) {
		this.custom = custom;
	}

}
