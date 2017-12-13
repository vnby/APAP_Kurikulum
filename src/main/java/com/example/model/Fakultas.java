package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fakultas
{
	@JsonProperty("id_univ")
	private String id_univ;
	@JsonProperty("id_fakultas")
	private String id_fakultas;
	@JsonProperty("nama_fakultas")
	private String nama_fakultas;
	
	private List<Prodi> listOfProdi;
	
	
}

