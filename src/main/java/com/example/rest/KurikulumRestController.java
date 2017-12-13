package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Kurikulum;
import com.example.model.MataKuliah;
import com.example.model.Response;
import com.example.model.ResponseError;
import com.example.model.SyaratLulus;
import com.example.service.KurikulumService;
import com.example.service.MataKuliahService;

@RestController
@RequestMapping("/api")
public class KurikulumRestController
{
	@Autowired
	KurikulumService kurikulumDAO;
	
	@Autowired
	MataKuliahService matakuliahDAO;
	
	@RequestMapping("/getKurikulum")
	public Object getKurikulum(@RequestParam(value = "kodeKurikulum", required = true) String kodeKurikulum) {
		Kurikulum kurikulum = kurikulumDAO.selectKurikulum(kodeKurikulum);
		
		
		if (kurikulum != null) {
			int jumlahSksWajib = kurikulumDAO.hitungSksWajib(kodeKurikulum);
			int jumlahSksPilihan = kurikulumDAO.hitungSksPilihan(kodeKurikulum);
			kurikulum.setJumlahSksWajib(jumlahSksWajib);
			kurikulum.setJumlahSksPilihan(jumlahSksPilihan);
			kurikulum.setJumlahSks(jumlahSksPilihan+jumlahSksWajib);
			Response response = new Response("200", "success", kurikulum);
			return response;
		} else {
			ResponseError error = new ResponseError("404", "Kurikulum tidak ditemukan");
			return error;
		}
	}
	
	@RequestMapping("/getAllKurikulum")
	public Object getAllKurikulum() {
		List<Kurikulum> daftarKurikulum = kurikulumDAO.selectAllKurikulumWithMatkul();
		
		if(daftarKurikulum.size() != 0) {
			Response response = new Response("200", "success", daftarKurikulum);
			return response;
		} else {
			ResponseError error = new ResponseError("404", "Tidak ada kurikulum yang dapat ditampilkan");
			return error;
		}
	}
	
	@RequestMapping("/getMataKuliah")
	public Object getMataKuliah(@RequestParam(value = "kodeMataKuliah", required = true) String kodeMataKuliah,
									@RequestParam(value = "kodeKurikulum", required = true) String kodeKurikulum) {
		List<MataKuliah> matkul = matakuliahDAO.apiGetMataKuliah(kodeMataKuliah, kodeKurikulum);
		if(matkul.size() != 0) {
			Response response = new Response("200", "success", matkul);
			return response;
		} else {
			ResponseError error = new ResponseError("404", "Mata Kuliah tidak ditemukan");
			return error;
		}
	}
	
	@RequestMapping("/getPrasyarat")
	public Object getPrasyarat(@RequestParam(value = "kodeMataKuliah", required = true) String kodeMataKuliah,
									@RequestParam(value = "kodeKurikulum", required = true) String kodeKurikulum) {
		List<MataKuliah> prasyarat = matakuliahDAO.apiGetPrasyarat(kodeMataKuliah, kodeKurikulum);
		
		if(prasyarat.size() != 0) {
			Response response = new Response("200", "success", prasyarat);
			return response;
		} else {
			ResponseError error = new ResponseError("404", "Mata Kuliah tidak ditemukan");
			return error;
		}
	}
	
	@RequestMapping("/getSyaratLulus")
	public Object getSyaratLulus(@RequestParam(value = "kodeKurikulum", required = true) String kodeKurikulum) {
		Kurikulum kurikulum = kurikulumDAO.selectKurikulum(kodeKurikulum);
		
		if(kurikulum != null) {
			int jumlahSksWajib = kurikulumDAO.hitungSksWajib(kodeKurikulum);
			int jumlahSksPilihan = kurikulumDAO.hitungSksPilihan(kodeKurikulum);
			SyaratLulus syaratLulus = new SyaratLulus(kodeKurikulum, jumlahSksWajib+jumlahSksPilihan, jumlahSksWajib, jumlahSksPilihan);
			Response response = new Response("200", "success", syaratLulus);
			
			return response;
		} else {
			ResponseError error = new ResponseError("404", "Kurikulum tidak ditemukan");
			return error;
		}
	}
}
