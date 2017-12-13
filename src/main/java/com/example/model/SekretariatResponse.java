package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class SekretariatResponse extends BaseResponse<SekretariatResponse.SekretariatResult> {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Getter
	@Setter
	@JsonSerialize
	public static class SekretariatResult {
		@JsonProperty("kurikulum")
		String kode_kurikulum;
		
		@JsonProperty("npm")
		Object npm;
		
		@JsonProperty("angkatan")
		String angkatan;
		
		@JsonProperty("idUniv")
		String id_universitas;
		
		@JsonProperty("idFakultas")
		String id_fakultas;
		
		@JsonProperty("idProdi")
		String id_prodi;
	}
}
