package com.example.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kurikulum
{
	private String id;
	private String kode_kurikulum;
	private String nama_kurikulum;
	private String id_universitas;
	private String id_fakultas;
	private String id_prodi;
	private int jumlahSks;
	private int jumlahSksWajib;
	private int jumlahSksPilihan;
	private List<MataKuliah> matakuliah;
}
