package com.github.bordertech.corpdir.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Location of contact.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class LocationEntity implements Serializable {

	private Long id;
	private String alternateKey;
	private String desc;
	private AddressEntity address;
	private List<LocationEntity> subLocations;
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
	 * @return the alternate location key
	 */
	public String getAlternateKey() {
		return alternateKey;
	}

	/**
	 *
	 * @param alternateKey the alternate location key
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
	 * @return the address
	 */
	public AddressEntity getAddress() {
		return address;
	}

	/**
	 *
	 * @param address the address
	 */
	public void setAddress(final AddressEntity address) {
		this.address = address;
	}

	/**
	 *
	 * @return the sub locations
	 */
	public List<LocationEntity> getSubLocations() {
		return subLocations;
	}

	/**
	 *
	 * @param subLocations the sub locations
	 */
	public void setSubLocations(final List<LocationEntity> subLocations) {
		this.subLocations = subLocations;
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
