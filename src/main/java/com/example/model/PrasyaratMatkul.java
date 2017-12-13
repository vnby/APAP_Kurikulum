package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrasyaratMatkul
{
	private String id;
	private String kode_kurikulum;
	private String kode_matkul;
	private String prasyarat;
}
