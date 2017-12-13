package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.Kurikulum;
import com.example.model.KurikulumMatkul;
import com.example.model.MataKuliah;
import com.example.model.User;

@Mapper
public interface KurikulumMapper
{
	@Select("SELECT * FROM KURIKULUM WHERE kode_kurikulum = #{kodeKurikulum}")
	@Results(value = {
			@Result(property="kode_kurikulum", column="kode_kurikulum"),
			@Result(property="matakuliah", column="kode_kurikulum", javaType=List.class, many=@Many(select="selectAllMatkulByKurikulum"))})
	Kurikulum selectKurikulum(@Param("kodeKurikulum") String kodeKurikulum);
	
	@Select("SELECT MK.id, MK.kode_matkul, MK.nama_matkul, MK.jumlah_sks, MK.deskripsi, MK.term, KM.is_wajib"
			+ " FROM KURIKULUM_MATAKULIAH KM JOIN MATA_KULIAH MK ON "
			+ " KM.kode_matkul = MK.kode_matkul WHERE kode_kurikulum = #{kodeKurikulum}")
	List<MataKuliah> selectAllMatkulByKurikulum(@Param("kodeKurikulum") String kodeKurikulum);
	
	@Select("SELECT * FROM KURIKULUM")
	List<Kurikulum> selectAllKurikulum();
	
	@Insert("INSERT INTO KURIKULUM (kode_kurikulum, nama_kurikulum, id_universitas, id_fakultas, id_prodi)"
			+ " VALUES ('${kode_kurikulum}', '${nama_kurikulum}', '${id_universitas}', '${id_fakultas}', '${id_prodi}')")
	void addKurikulum(Kurikulum kurikulum);
	
	@Update("UPDATE KURIKULUM SET nama_kurikulum='${nama_kurikulum}' "
			+ "WHERE kode_kurikulum='${kode_kurikulum}'")
	void updateKurikulum(Kurikulum kurikulum);
	
	@Insert("INSERT INTO KURIKULUM_MATAKULIAH (kode_kurikulum, kode_matkul, is_wajib)"
			+ " VALUES ('${kode_kurikulum}', '${kode_matkul}', '${is_wajib}')")
	void addMatkulAtKurikulum(KurikulumMatkul kurikulummatkul);
	
	@Select("select * from kurikulum")
	@Results(value = {
			@Result(property="kode_kurikulum", column="kode_kurikulum"),
			@Result(property="matakuliah", column="kode_kurikulum", javaType=List.class, many=@Many(select="selectAllMatkulByKurikulum"))})
	List<Kurikulum> selectAllKurikulumWithMatkul();
	
	@Delete("DELETE FROM KURIKULUM WHERE kode_kurikulum = #{kodeKurikulum}")
	void deleteKurikulum(@Param("kodeKurikulum") String kodeKurikulum);
	
	@Select("SELECT sum(jumlah_sks) FROM mata_kuliah mk JOIN kurikulum_matakuliah km ON mk.kode_matkul = km.kode_matkul WHERE kode_kurikulum = #{kodeKurikulum} AND is_wajib = 0")
	Integer hitungSksPilihan(@Param("kodeKurikulum") String kodeKurikulum);
	
	@Select("SELECT sum(jumlah_sks) FROM mata_kuliah mk JOIN kurikulum_matakuliah km ON mk.kode_matkul = km.kode_matkul WHERE kode_kurikulum = #{kodeKurikulum} AND is_wajib = 1")
	Integer hitungSksWajib(@Param("kodeKurikulum") String kodeKurikulum);
	
	@Select("SELECT id_universitas, id_fakultas, id_prodi FROM users WHERE username = #{username}")
	User getUserInfo(@Param("username") String username);
}
