package com.example.service;

import java.util.List;

import com.example.model.MataKuliah;
import com.example.model.PrasyaratMatkul;

public interface MataKuliahService
{
	MataKuliah selectMataKuliah(String kodeMataKuliah);
	
	List<MataKuliah> selectAllMataKuliah();
	
	void addMataKuliah(MataKuliah mataKuliah);
	
	void updateMataKuliah(MataKuliah mataKuliah);
	
	List<MataKuliah> apiGetMataKuliah(String kodeMataKuliah, String kodeKurikulum);
	
	List<MataKuliah> apiGetPrasyarat(String kodeMataKuliah, String kodeKurikulum);
	
	void addPrasyarat(PrasyaratMatkul prasyaratmatkul);
	
	void deleteMataKuliah(String kodeMataKuliah);
	
	String cekIsWajib(String kodeMataKuliah);
	
	List<String> getPrasyarat(String kodeMataKuliah);
}
