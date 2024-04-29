package com.telus.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class SuccessResponseModel {

	private Date timestamp;
	private String message;
	private int statusCode;
	private List<?> data;

	public SuccessResponseModel(String message, int statusCode) {
		super();
		this.timestamp = new Date();
		this.message = message;
		this.statusCode = statusCode;
	}

	public SuccessResponseModel(String message, int statusCode, Object data) {
		super();
		this.timestamp = new Date();
		this.message = message;
		this.statusCode = statusCode;
		this.setData(data);
	}

	public void setData(Object data) {
		List<Object> list = new ArrayList<Object>();
		if (data != null) {
			if (!(data instanceof List)) {
				list.add(data);
				this.data = list;
			} else {
				this.data = (List<?>) data;
			}
		}
	}

}
