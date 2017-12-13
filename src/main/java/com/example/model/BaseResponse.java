package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BaseResponse<E> {
	
	@JsonProperty("status")
	private int status;
	@JsonProperty("msg")
	private String msg;
	@JsonProperty("result")
	private E result;
}
