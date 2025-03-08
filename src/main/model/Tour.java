package main.model;

import java.util.Date;

public class Tour {
	private Integer id;
	private String name;
	private String code;
	private int continent;
	private Date date;
	private int duration;
	private boolean allInclusive = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getContinent() {
		return continent;
	}

	public void setContinent(int continent) {
		this.continent = continent;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isAllInclusive() {
		return allInclusive;
	}

	public void setAllInclusive(boolean allInclusive) {
		this.allInclusive = allInclusive;
	}

	@Override
	public String toString() {
		return "Tour{" +
				"id=" + id +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", continent=" + continent +
				", date=" + date +
				", duration=" + duration +
				", allInclusive=" + allInclusive +
				'}';
	}
}
