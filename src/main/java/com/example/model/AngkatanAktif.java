package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AngkatanAktif
{
	@JsonProperty("kurikulum")
	private String kode_kurikulum;
	
	@JsonProperty("angkatan")
	private String angkatan;
	
	@JsonProperty("idUniv")
	private String id_universitas;
	
	@JsonProperty("idFakultas")
	private String id_fakultas;
	
	@JsonProperty("idProdi")
	private String id_prodi;
}

