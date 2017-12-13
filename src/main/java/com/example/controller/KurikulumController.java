package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.model.Kurikulum;
import com.example.model.KurikulumMatkul;
import com.example.model.MataKuliah;
import com.example.model.PrasyaratMatkul;
import com.example.model.User;
import com.example.service.KurikulumService;
import com.example.service.MataKuliahService;

@Controller
public class KurikulumController {
	@Autowired
	KurikulumService kurikulumDAO;

	@Autowired
	MataKuliahService matakuliahDAO;

	@RequestMapping("/kurikulum/add")
	public String addKurikulum(Model model) {
		Kurikulum kurikulum = new Kurikulum();
		
		//System.out.println(kurikulumDAO.getAllAngkatan());

		model.addAttribute("kurikulum", kurikulum);

		return "add-kurikulum";
	}

	@RequestMapping(value = "/kurikulum/add", method = RequestMethod.POST)
	public RedirectView addSubmitKurikulum(Model model, @ModelAttribute Kurikulum kurikulum) {
		// get user info
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = kurikulumDAO.getUserInfo(username);
		String id_universitas = user.getId_universitas();
		String id_fakultas = user.getId_fakultas();
		String id_prodi = user.getId_prodi();

		Kurikulum kurikulumTemp = kurikulum;
		kurikulumTemp.setId_universitas(id_universitas);
		kurikulumTemp.setId_fakultas(id_fakultas);
		kurikulumTemp.setId_prodi(id_prodi);

		kurikulumDAO.addKurikulum(kurikulumTemp);

		List<Kurikulum> allKurikulum = kurikulumDAO.selectAllKurikulum();
		model.addAttribute("allKurikulum", allKurikulum);

		return new RedirectView("/kurikulum/viewall");
	}

	@RequestMapping("/kurikulum/addmatkul")
	public String addMatkulAtKurikulum(Model model) {
		KurikulumMatkul kurikulummatkul = new KurikulumMatkul();

		// get user info
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = kurikulumDAO.getUserInfo(username);
		String id_universitas = user.getId_universitas();
		String id_fakultas = user.getId_fakultas();
		String id_prodi = user.getId_prodi();

		List<Kurikulum> allKurikulum = kurikulumDAO.selectAllKurikulum();
		List<Kurikulum> filteredKurikulum = new ArrayList<Kurikulum>();
		List<MataKuliah> allMatkul = matakuliahDAO.selectAllMataKuliah();

		for (int i = 0; i < allKurikulum.size(); i++)
			if (allKurikulum.get(i).getId_universitas().equals(id_universitas)
					&& allKurikulum.get(i).getId_fakultas().equals(id_fakultas)
					&& allKurikulum.get(i).getId_prodi().equals(id_prodi))
				filteredKurikulum.add(allKurikulum.get(i));

		model.addAttribute("kurikulummatkul", kurikulummatkul);
		model.addAttribute("allkurikulum", filteredKurikulum);
		model.addAttribute("allmatkul", allMatkul);

		return "add-matkul-at-kurikulum";
	}

	@RequestMapping(value = "/kurikulum/addmatkul", method = RequestMethod.POST)
	public RedirectView addMatkulAtKurikulumSubmit(Model model, @ModelAttribute KurikulumMatkul kurikulummatkul) {
		kurikulumDAO.addMatkulAtKurikulum(kurikulummatkul);

		List<Kurikulum> allKurikulum = kurikulumDAO.selectAllKurikulum();
		model.addAttribute("allKurikulum", allKurikulum);

		return new RedirectView("/kurikulum/viewall");
	}

	@RequestMapping("/kurikulum/update/{kode_kurikulum}")
	public String updateKurikulum(Model model, @PathVariable(value = "kode_kurikulum") String kode_kurikulum) {
		Kurikulum kurikulum = kurikulumDAO.selectKurikulum(kode_kurikulum);

		if (kurikulum != null) {
			model.addAttribute("kurikulum", kurikulum);
			return "update-kurikulum";
		} else {
			model.addAttribute("kode_kurikulum", kode_kurikulum);
			return "not-found";
		}

	}

	@RequestMapping(value = "/kurikulum/update/{kode_kurikulum}", method = RequestMethod.POST)
	public RedirectView updateKurikulumSubmit(Model model,
			@PathVariable(value = "kode_kurikulum") String kode_kurikulum, @ModelAttribute Kurikulum kurikulum) {

		model.addAttribute("kurikulum", kurikulum);
		model.addAttribute("kode_kurikulum", kode_kurikulum);

		kurikulumDAO.updateKurikulum(kurikulum);

		return new RedirectView("/kurikulum/view?kodeKurikulum=" + kode_kurikulum);
	}

	@RequestMapping("/kurikulum/viewall")
	public String viewAllKurikulum(Model model) {
		// get user info
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = kurikulumDAO.getUserInfo(username);
		String id_universitas = user.getId_universitas();
		String id_fakultas = user.getId_fakultas();
		String id_prodi = user.getId_prodi();

		List<Kurikulum> kurikulumBerMatkul = kurikulumDAO.selectAllKurikulumWithMatkul();
		List<Kurikulum> filteredKurikulum = new ArrayList<Kurikulum>();

		// isi sks
		for (int i = 0; i < kurikulumBerMatkul.size(); i++) {
			if (!kurikulumBerMatkul.get(i).getMatakuliah().isEmpty()) {
				Integer jumlahSksPilihan = kurikulumDAO.hitungSksPilihan(kurikulumBerMatkul.get(i).getKode_kurikulum());
				Integer jumlahSksWajib = kurikulumDAO.hitungSksWajib(kurikulumBerMatkul.get(i).getKode_kurikulum());
				int jumlahSks = 0;

				if (jumlahSksPilihan == null || jumlahSksWajib == null)
					if (jumlahSksPilihan == null) {
						jumlahSksPilihan = 0;
						jumlahSks = jumlahSksWajib;
					} else {
						jumlahSksWajib = 0;
						jumlahSks = jumlahSksPilihan;
					}
				else
					jumlahSks = jumlahSksWajib + jumlahSksPilihan;

				kurikulumBerMatkul.get(i).setJumlahSksPilihan(jumlahSksPilihan);
				kurikulumBerMatkul.get(i).setJumlahSksWajib(jumlahSksWajib);
				kurikulumBerMatkul.get(i).setJumlahSks(jumlahSks);
			}

			if (kurikulumBerMatkul.get(i).getId_universitas().equals(id_universitas)
					&& kurikulumBerMatkul.get(i).getId_fakultas().equals(id_fakultas)
					&& kurikulumBerMatkul.get(i).getId_prodi().equals(id_prodi))
				filteredKurikulum.add(kurikulumBerMatkul.get(i));
		}

		model.addAttribute("allKurikulum", filteredKurikulum);
		return "viewall-kurikulum";
	}

	@RequestMapping("/kurikulum/view")
	public String viewKurikulum(@RequestParam(value = "kodeKurikulum", required = false) String kodeKurikulum,
			Model model) {
		Kurikulum kurikulum = kurikulumDAO.selectKurikulum(kodeKurikulum);

		if (kurikulum != null) {
			MataKuliah matkul;
			String kodeMatkul;
			String isWajib;

			for (int i = 0; i < kurikulum.getMatakuliah().size(); i++) {
				matkul = kurikulum.getMatakuliah().get(i);
				kodeMatkul = matkul.getKode_matkul();
				isWajib = matakuliahDAO.cekIsWajib(kodeMatkul);

				matkul.setIs_wajib(isWajib);
			}

			model.addAttribute("kurikulum", kurikulum);
			return "viewdetail-kurikulum";
		} else {
			model.addAttribute("kode_kurikulum", kodeKurikulum);
			return "not-found";
		}
	}

	@RequestMapping("/kurikulum/delete")
	public RedirectView deleteKurikulum(@RequestParam(value = "kodeKurikulum", required = false) String kodeKurikulum) {
		kurikulumDAO.deleteKurikulum(kodeKurikulum);
		return new RedirectView("/kurikulum/viewall");
	}

	@RequestMapping("/matakuliah/add")
	public String addMatKul(Model model) {
		MataKuliah mataKuliah = new MataKuliah();
		model.addAttribute("mataKuliah", mataKuliah);
		return "add-matkul";
	}

	@RequestMapping(value = "/matakuliah/add", method = RequestMethod.POST)
	public RedirectView addSubmit(Model model, @ModelAttribute MataKuliah mataKuliah) {
		matakuliahDAO.addMataKuliah(mataKuliah);

		List<MataKuliah> daftarMatkul = matakuliahDAO.selectAllMataKuliah();
		model.addAttribute("daftarMatkul", daftarMatkul);

		return new RedirectView("/matakuliah/viewall");
	}

	@RequestMapping("/matakuliah/update/{kodeMataKuliah}")
	public String updateMatKul(Model model,
			@PathVariable(value = "kodeMataKuliah", required = false) String kodeMataKuliah) {

		MataKuliah mataKuliah = matakuliahDAO.selectMataKuliah(kodeMataKuliah);
		if (mataKuliah != null) {
			model.addAttribute("mataKuliah", mataKuliah);
			return "update-matkul";
		} else {
			model.addAttribute("kodeMataKuliah", kodeMataKuliah);
			return "not-found";
		}
	}

	@RequestMapping(value = "/matakuliah/update/{kodeMataKuliah}", method = RequestMethod.POST)
	public RedirectView updateSubmit(Model model,
			@PathVariable(value = "kodeMataKuliah", required = false) String kodeMataKuliah,
			@ModelAttribute MataKuliah mataKuliah) {

		matakuliahDAO.updateMataKuliah(mataKuliah);

		model.addAttribute("mataKuliah", mataKuliah);

		return new RedirectView("/matakuliah/view?kodeMataKuliah=" + kodeMataKuliah);
	}

	@RequestMapping("/matakuliah/viewall")
	public String viewAllMatKul(Model model) {
		List<MataKuliah> daftarMatkul = matakuliahDAO.selectAllMataKuliah();

		model.addAttribute("daftarMatkul", daftarMatkul);

		return "viewall-matkul";
	}

	@RequestMapping("/matakuliah/view")
	public String viewMatKul(@RequestParam(value = "kodeMataKuliah", required = false) String kodeMataKuliah,
			Model model) {
		MataKuliah mataKuliah = matakuliahDAO.selectMataKuliah(kodeMataKuliah);
		if (mataKuliah != null) {
			String prasyarat = "";
			List<String> listprasyarat = matakuliahDAO.getPrasyarat(kodeMataKuliah);

			if (listprasyarat.size() == 0)
				prasyarat += "Tidak ada prasyarat";
			else {
				for (int i = 0; i < listprasyarat.size(); i++) {
					prasyarat += matakuliahDAO.selectMataKuliah(listprasyarat.get(i)).getNama_matkul();
					if (i + 1 < listprasyarat.size())
						prasyarat += ", ";
				}
			}

			String isWajib;
			isWajib = matakuliahDAO.cekIsWajib(kodeMataKuliah);
			mataKuliah.setIs_wajib(isWajib);

			model.addAttribute("mataKuliah", mataKuliah);
			model.addAttribute("prasyarat", prasyarat);
			return "viewdetail-matkul";
		} else {
			model.addAttribute("kodeMataKuliah", kodeMataKuliah);
			return "not-found";
		}
	}

	@RequestMapping("/matakuliah/delete")
	public RedirectView deleteMatkul(@RequestParam(value = "kodeMataKuliah", required = false) String kodeMataKuliah) {
		matakuliahDAO.deleteMataKuliah(kodeMataKuliah);
		return new RedirectView("/matakuliah/viewall");
	}

	@RequestMapping("/angkatanaktif")
	public String viewAngAktif() {
		return "view-angkatanaktif";
	}

	@RequestMapping("/matakuliah/addprasyarat")
	public String cariPenduduk(@RequestParam(value = "kode_kurikulum") Optional<String> kode_kurikulum,
			@RequestParam(value = "kode_matkul") Optional<String> kode_matkul,
			@RequestParam(value = "prasyarat") Optional<String> prasyarat, Model model) {

		// get user info
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = kurikulumDAO.getUserInfo(username);
		String id_universitas = user.getId_universitas();
		String id_fakultas = user.getId_fakultas();
		String id_prodi = user.getId_prodi();

		List<Kurikulum> allKurikulum = kurikulumDAO.selectAllKurikulum();
		List<Kurikulum> filteredKurikulum = new ArrayList<Kurikulum>();

		for (int i = 0; i < allKurikulum.size(); i++)
			if (allKurikulum.get(i).getId_universitas().equals(id_universitas)
					&& allKurikulum.get(i).getId_fakultas().equals(id_fakultas)
					&& allKurikulum.get(i).getId_prodi().equals(id_prodi))
				filteredKurikulum.add(allKurikulum.get(i));

		model.addAttribute("allkurikulum", filteredKurikulum);

		if (kode_kurikulum.isPresent()) {
			List<MataKuliah> matkulAtKurikulum = kurikulumDAO.selectKurikulum(kode_kurikulum.get()).getMatakuliah();
			model.addAttribute("matkulAtKurikulum", matkulAtKurikulum);
			model.addAttribute("kode_kurikulum", kode_kurikulum.get());
		}

		if (kode_kurikulum.isPresent() && kode_matkul.isPresent() && prasyarat.isPresent()) {
			PrasyaratMatkul prasyaratmatkul = new PrasyaratMatkul("0", kode_kurikulum.get(), kode_matkul.get(),
					prasyarat.get());
			matakuliahDAO.addPrasyarat(prasyaratmatkul);
			return "index";
		}

		return "add-prasyarat-at-matkul";
	}
}