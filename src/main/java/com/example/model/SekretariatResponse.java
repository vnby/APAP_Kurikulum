package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonSerialize
public class SekretariatResponse {
	@JsonProperty("status")
	private int status;
	@JsonProperty("msg")
	private String msg;
	@JsonProperty("result")
	private List<AngkatanAktif> angkatanAktif;
}
