package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.MataKuliah;
import com.example.model.PrasyaratMatkul;

@Mapper
public interface MataKuliahMapper
{

	@Select("SELECT * FROM MATA_KULIAH WHERE kode_matkul= #{kodeMataKuliah}")
	MataKuliah selectMataKuliah(@Param("kodeMataKuliah") String kodeMataKuliah);
	
	@Select("SELECT * FROM MATA_KULIAH")
	List<MataKuliah> selectAllMataKuliah();
	
	@Insert("INSERT INTO MATA_KULIAH (kode_matkul, nama_matkul, jumlah_sks, deskripsi, term)"
			+ "VALUES ('${kode_matkul}', '${nama_matkul}', '${jumlah_sks}', '${deskripsi}', '${term}')")
	void addMataKuliah(MataKuliah mataKuliah);
	
	@Update("update mata_kuliah set nama_matkul = '${nama_matkul}', jumlah_sks = '${jumlah_sks}', "
			+ "term = '${term}', deskripsi = '${deskripsi}' where kode_matkul = '${kode_matkul}'")
	void updateMataKuliah(MataKuliah mataKuliah);
	
	@Select("SELECT MK.id, MK.kode_matkul, MK.nama_matkul, MK.jumlah_sks, MK.deskripsi, MK.term, KM.is_wajib"
			+ " FROM KURIKULUM_MATAKULIAH KM JOIN MATA_KULIAH MK ON KM.kode_matkul = MK.kode_matkul WHERE "
			+ " KM.kode_matkul=#{kodeMataKuliah} AND KM.kode_kurikulum = #{kodeKurikulum}")
	List<MataKuliah> apiGetMataKuliah(@Param("kodeMataKuliah") String kodeMataKuliah, @Param("kodeKurikulum") String kodeKurikulum);
	
	@Select("SELECT MK.id, MK.kode_matkul, MK.nama_matkul, MK.jumlah_sks, MK.deskripsi, MK.term FROM "
			+ "PRASYARAT_MATAKULIAH PM JOIN MATA_KULIAH MK ON PM.prasyarat = MK.kode_matkul WHERE "
			+ "PM.kode_matkul=#{kodeMataKuliah} AND PM.kode_kurikulum=#{kodeKurikulum}")
	List<MataKuliah> apiGetPrasyarat(@Param("kodeMataKuliah") String kodeMataKuliah, @Param("kodeKurikulum") String kodeKurikulum);
	
	@Insert("INSERT INTO PRASYARAT_MATAKULIAH (kode_kurikulum, kode_matkul, prasyarat)"
			+ "VALUES ('${kode_kurikulum}', '${kode_matkul}', '${prasyarat}')")
	void addPrasyarat(PrasyaratMatkul prasyaratmatkul);
	
	@Delete("DELETE FROM MATA_KULIAH WHERE kode_matkul = #{kodeMataKuliah}")
	void deleteMataKuliah(@Param("kodeMataKuliah") String kodeMataKuliah);
	
	@Select("SELECT is_wajib FROM kurikulum_matakuliah WHERE kode_matkul = #{kodeMataKuliah} LIMIT 1")
	String cekIsWajib(String kodeMataKuliah);
	
	@Select("SELECT prasyarat FROM prasyarat_matakuliah WHERE kode_matkul = #{kodeMataKuliah}")
	List<String> getPrasyarat(String kodeMataKuliah);
}
