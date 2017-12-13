package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prodi
{
	@JsonProperty("id_univ")
	private String id_univ;
	@JsonProperty("id_fakultas")
	private String id_fakultas;
	@JsonProperty("id_prodi")
	private String id_prodi;
	@JsonProperty("nama_prodi")
	private String nama_prodi;
}

