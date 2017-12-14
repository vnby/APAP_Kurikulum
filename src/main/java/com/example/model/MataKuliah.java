package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MataKuliah
{
	private String id;
	private String kode_matkul;
	private String nama_matkul;
	private int jumlah_sks;
	private String deskripsi;
	private String term;
	private String is_wajib;
	private String id_universitas;
	private String id_fakultas;
}
