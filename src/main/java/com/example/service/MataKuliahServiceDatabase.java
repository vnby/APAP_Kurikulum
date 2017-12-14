package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MataKuliahMapper;
import com.example.model.MataKuliah;
import com.example.model.PrasyaratMatkul;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MataKuliahServiceDatabase implements MataKuliahService
{
	@Autowired
	private MataKuliahMapper mataKuliahMapper;
	
	@Override
	public MataKuliah selectMataKuliah(String kodeMataKuliah) {
		log.info("SELECT MATA KULIAH WITH KODE {}", kodeMataKuliah);
		return mataKuliahMapper.selectMataKuliah(kodeMataKuliah);
	}
	
	@Override
	public List<MataKuliah> selectAllMataKuliah() {
		log.info("SELECT ALL MATA KULIAH");
		return mataKuliahMapper.selectAllMataKuliah();
	}
	
	@Override
    public void addMataKuliah(MataKuliah mataKuliah)
	{
    	log.info ("mata kuliah " + mataKuliah + " add");
    	mataKuliahMapper.addMataKuliah(mataKuliah);
    }
	
	@Override
    public void updateMataKuliah(MataKuliah mataKuliah)
	{
    	log.info ("update mata kuliah with kode {}", mataKuliah.getKode_matkul());
    	mataKuliahMapper.updateMataKuliah(mataKuliah);
    }

	@Override
	public List<MataKuliah> apiGetMataKuliah(String kodeMataKuliah, String kodeKurikulum) {
		log.info("get mata kuliah dengan kode mata kuliah {} dan kode kurikulum {}", kodeMataKuliah, kodeKurikulum);
		return mataKuliahMapper.apiGetMataKuliah(kodeMataKuliah, kodeKurikulum);
	}

	@Override
	public List<MataKuliah> apiGetPrasyarat(String kodeMataKuliah, String kodeKurikulum) {
		log.info("get prasyarat mata kuliah dengan kode mata kuliah {} dan kode kurikulum {}", kodeMataKuliah, kodeKurikulum);
		return mataKuliahMapper.apiGetPrasyarat(kodeMataKuliah, kodeKurikulum);
	}

	@Override
	public void addPrasyarat(PrasyaratMatkul prasyaratmatkul) {
		log.info("add prasyarat {} matkul {} kurikulum {}", prasyaratmatkul.getPrasyarat(), prasyaratmatkul.getKode_matkul(), prasyaratmatkul.getKode_kurikulum());
		mataKuliahMapper.addPrasyarat(prasyaratmatkul);
	}

	@Override
	public void deleteMataKuliah(String kodeMataKuliah) {
		log.info("DELETE MATA KULIAH WITH KODE {}", kodeMataKuliah);
		mataKuliahMapper.deleteMataKuliah(kodeMataKuliah);
	}

	@Override
	public String cekIsWajib(String kodeMataKuliah) {
		log.info("cek is wajib matkul with kode {}", kodeMataKuliah);
		return mataKuliahMapper.cekIsWajib(kodeMataKuliah);
	}

	@Override
	public List<String> getPrasyarat(String kodeMataKuliah) {
		log.info("Select prasyarat FROM prasyarat_matakuliah WHERE kode_matkul = {}", kodeMataKuliah);
		return mataKuliahMapper.getPrasyarat(kodeMataKuliah);
	}

	@Override
	public List<String> getKurikulumYangMenggunakan(String kodeMataKuliah) {
		log.info("SELECT nama_kurikulum FROM kurikulum k join kurikulum_matakuliah km ON k.kode_kurikulum = km.kode_kurikulum WHERE km.kode_matkul = {}", kodeMataKuliah);
		return mataKuliahMapper.getKurikulumYangMenggunakan(kodeMataKuliah);
	}

}
