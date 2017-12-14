package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.SekretariatResponse;
import com.example.model.UniversitasResponse;

@Mapper
public interface KurikulumDAO {
	UniversitasResponse selectUniversitas(String id_univ);
	UniversitasResponse selectAllUniversitas();
	SekretariatResponse getAllAngkatan();
	UniversitasResponse selectFakultas(String id_univ, String id_fakultas);
	UniversitasResponse selectProdi(String id_univ, String id_fakultas, String id_prodi);
}
