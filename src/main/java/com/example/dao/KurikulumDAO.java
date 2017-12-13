package com.example.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.SekretariatResponse;
import com.example.model.UniversitasResponse;

@Mapper
public interface KurikulumDAO {
	UniversitasResponse selectUniversitas(String id_univ);
	UniversitasResponse selectAllUniversitas();
	Collection<SekretariatResponse> getAllAngkatan();
}
