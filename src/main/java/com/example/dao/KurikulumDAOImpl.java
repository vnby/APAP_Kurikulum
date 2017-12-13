package com.example.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.SekretariatResponse;
import com.example.model.UniversitasResponse;

@Service
public class KurikulumDAOImpl implements KurikulumDAO {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public UniversitasResponse selectUniversitas(String id_univ) {
		UniversitasResponse universitas = restTemplate.getForObject("https://apap2017-univ-apps.herokuapp.com/getUniversitas/1/", UniversitasResponse.class);
		return universitas;
	}

	@Override
	public UniversitasResponse selectAllUniversitas() {
		UniversitasResponse listAllUniversitas = restTemplate.getForObject("https://apap2017-univ-apps.herokuapp.com/getUniversitasList/", UniversitasResponse.class);
		return listAllUniversitas;
	}

	@Override
	public Collection<SekretariatResponse> getAllAngkatan() {
		Collection<SekretariatResponse> allAngkatanAktif = restTemplate.getForObject("http://localhost/APAP/index.json", Collection.class);
		return allAngkatanAktif;
	}
}
