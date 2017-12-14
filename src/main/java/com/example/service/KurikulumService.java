package com.example.service;

import java.util.List;

import com.example.model.Kurikulum;
import com.example.model.KurikulumMatkul;
import com.example.model.SekretariatResponse;
import com.example.model.UniversitasResponse;
import com.example.model.User;

public interface KurikulumService
{
	Kurikulum selectKurikulum(String kodeKurikulum);
	
	List<Kurikulum> selectAllKurikulum();
	
	void addKurikulum(Kurikulum kurikulum);
	
	void updateKurikulum(Kurikulum kurikulum);
	
	void addMatkulAtKurikulum(KurikulumMatkul kurikulummatkul);

	UniversitasResponse selectUniversitas (String id_univ);
	
	UniversitasResponse selectAllUniversitas ();
	
	List<Kurikulum> selectAllKurikulumWithMatkul();

	void deleteKurikulum(String kodeKurikulum);
	
	Integer hitungSksPilihan(String kodeKurikulum);
	
	Integer hitungSksWajib(String kodeKurikulum);
	
	User getUserInfo(String username);
	
	SekretariatResponse getAllAngkatan();
	
	UniversitasResponse selectFakultas (String id_univ, String id_fakultas);
	
	UniversitasResponse selectProdi (String id_univ, String id_fakultas, String id_prodi);
}
