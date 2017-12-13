package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Universitas
{
	@JsonProperty("id_univ")
	private String id_univ;
	@JsonProperty("nama_univ")
	private String nama_univ;
	
	private List<Fakultas> listOfFakultas;
}

