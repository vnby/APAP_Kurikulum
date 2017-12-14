package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class UniversitasResponse extends BaseResponse<UniversitasResponse.UniversitasResult> {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Getter
	@Setter
	@JsonSerialize
	public static class UniversitasResult {
		@JsonProperty("univList")
		List<Universitas> listUniversitas;
		
		@JsonProperty("universitas")
		Universitas universitas;
		
		@JsonProperty("fakultasList")
		List<Fakultas> fakultasList;
		
		@JsonProperty("fakultas")
		Fakultas fakultas;
		
		@JsonProperty("prodiList")
		List<Prodi> prodiList;
		
		@JsonProperty("prodi")
		Prodi prodi;
	}
}
