package com.example.dao;

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
		UniversitasResponse universitas = restTemplate.getForObject("https://apap2017-univ-apps.herokuapp.com/getUniversitas/"+id_univ, UniversitasResponse.class);
		return universitas;
	}

	@Override
	public UniversitasResponse selectAllUniversitas() {
		UniversitasResponse listAllUniversitas = restTemplate.getForObject("https://apap2017-univ-apps.herokuapp.com/getUniversitasList/", UniversitasResponse.class);
		return listAllUniversitas;
	}

	@Override
	public SekretariatResponse getAllAngkatan() {
		SekretariatResponse allAngkatanAktif = restTemplate.getForObject("http://localhost/APAP/index.json", SekretariatResponse.class);
		return allAngkatanAktif;
	}

	@Override
	public UniversitasResponse selectFakultas(String id_univ, String id_fakultas) {
		UniversitasResponse fakultas = restTemplate.getForObject("https://apap2017-univ-apps.herokuapp.com/getFakultas/"+id_univ+"/"+id_fakultas, UniversitasResponse.class);
		return fakultas;
	}

	@Override
	public UniversitasResponse selectProdi(String id_univ, String id_fakultas, String id_prodi) {
		UniversitasResponse prodi = restTemplate.getForObject("https://apap2017-univ-apps.herokuapp.com/getProdi/"+id_univ+"/"+id_fakultas+"/"+id_prodi, UniversitasResponse.class);
		return prodi;
	}
}
