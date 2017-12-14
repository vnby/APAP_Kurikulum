package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KurikulumDAOImpl;
import com.example.dao.KurikulumMapper;
import com.example.model.Kurikulum;
import com.example.model.KurikulumMatkul;
import com.example.model.SekretariatResponse;
import com.example.model.UniversitasResponse;
import com.example.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KurikulumServiceDatabase implements KurikulumService {
	@Autowired
	private KurikulumMapper kurikulumMapper;
	@Autowired
	private KurikulumDAOImpl kurikulumDAO;

	@Override
	public Kurikulum selectKurikulum(String kodeKurikulum) {
		log.info("SELECT KURIKULUM WITH KODE {}", kodeKurikulum);
		return kurikulumMapper.selectKurikulum(kodeKurikulum);
	}

	@Override
	public List<Kurikulum> selectAllKurikulum() {
		log.info("SELECT ALL KURIKULUM");
		return kurikulumMapper.selectAllKurikulum();
	}

	@Override
	public void addKurikulum(Kurikulum kurikulum) {
		log.info("add kurikulum with kode {}", kurikulum.getKode_kurikulum());
		kurikulumMapper.addKurikulum(kurikulum);
	}

	@Override
	public void updateKurikulum(Kurikulum kurikulum) {
		log.info("update kurikulum with kode {}", kurikulum.getKode_kurikulum());
		kurikulumMapper.updateKurikulum(kurikulum);
	}

	@Override
	public void addMatkulAtKurikulum(KurikulumMatkul kurikulummatkul) {
		log.info("add matkul at kurikulum with kode matkul {} and kode kurikulum {}", kurikulummatkul.getKode_matkul(),
				kurikulummatkul.getKode_kurikulum());
		kurikulumMapper.addMatkulAtKurikulum(kurikulummatkul);
	}
	
	@Override
	public UniversitasResponse selectUniversitas(String id_univ) {
		log.info("REST - select univ with id_univ {}", id_univ);
		return kurikulumDAO.selectUniversitas(id_univ);
	}

	@Override
	public UniversitasResponse selectAllUniversitas() {
		log.info("REST - select all univ");
		return kurikulumDAO.selectAllUniversitas();
	}
	
	@Override
	public List<Kurikulum> selectAllKurikulumWithMatkul(){
		log.info("select all kurikulum and their matkul");
		return kurikulumMapper.selectAllKurikulumWithMatkul();
	}

	@Override
	public void deleteKurikulum(String kodeKurikulum) {
		log.info("DELETE KURIKULUM WITH KODE {}", kodeKurikulum);
		kurikulumMapper.deleteKurikulum(kodeKurikulum);
	}

	@Override
	public Integer hitungSksPilihan(String kodeKurikulum) {
		log.info("SELECT SUM(jumlah_sks) FROM Kurikulum where kode_kurikulum = {} and is_wajib = 0", kodeKurikulum);
		return kurikulumMapper.hitungSksPilihan(kodeKurikulum);
	}

	@Override
	public Integer hitungSksWajib(String kodeKurikulum) {
		log.info("SELECT SUM(jumlah_sks) FROM Kurikulum where kode_kurikulum = {} and is_wajib = 1", kodeKurikulum);
		return kurikulumMapper.hitungSksWajib(kodeKurikulum);
	}

	@Override
	public User getUserInfo(String username) {
		log.info("SELECT id_universitas, id_fakultas, id_prodi FROM users WHERE username = {}", username);
		return kurikulumMapper.getUserInfo(username);
	}

	@Override
	public SekretariatResponse getAllAngkatan() {
		log.info("GET All Angkatan via REST");
		return kurikulumDAO.getAllAngkatan();
	}

	@Override
	public UniversitasResponse selectFakultas(String id_univ, String id_fakultas) {
		log.info("REST - select fakultas with id_fakultas {}", id_fakultas);
		return kurikulumDAO.selectFakultas(id_univ, id_fakultas);
	}

	@Override
	public UniversitasResponse selectProdi(String id_univ, String id_fakultas, String id_prodi) {
		log.info("REST - select prodi with id_prodi {}", id_prodi);
		return kurikulumDAO.selectProdi(id_univ, id_fakultas, id_prodi);
	}

}