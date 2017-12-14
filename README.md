# TA_C9
Repository untuk Tugas Akhir Kelompok C9 ADPAP Kelas A

API List:
* [getKurikulum](#get-kurikulum)
* [getMataKuliah](#get-mata-kuliah)
* [getPrasyarat](#get-prasyarat)
* [getAllKurikulum](#get-all-kurikulum)
* [getSyaratLulus](#get-syarat-lulus)

## Get Kurikulum

Method ini digunakan untuk mengembalikan data kurikulum termasuk seluruh mata kuliah di kurikulum tersebut

**URL** : `/api/getKurikulum`

**Method** : `GET`

### Success Response

**Contoh Request**: `/localhost:8080/api/getKurikulum?kodeKurikulum=CSUI2011`

```json
{
	"status":"200",
	"msg":"success",
	"result":{
			"id":"1",
			"kode_kurikulum":"CSUI2011",
			"nama_kurikulum":"Kurikulum Fasilkom 2011",
			"id_universitas":"1",
			"id_fakultas":"1",
			"id_prodi":"1",
			"jumlahSks":12,
			"jumlahSksWajib":8,
			"jumlahSksPilihan":4,
				"matakuliah":[
				{
					"id":"1",
					"kode_matkul":"CSC001",
					"nama_matkul":"DDP 1",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Dasar-Dasar Pemrograman 1",
					"term":"1",
					"is_wajib":"1"
				},
				{
					"id":"2",
					"kode_matkul":"CSC002",
					"nama_matkul":"DDP 2",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Dasar-Dasar Pemrograman 2",
					"term":"2",
					"is_wajib":"1"
				},
				{
					"id":"3",
					"kode_matkul":"CSC003",
					"nama_matkul":"SDA",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Struktur Dasar Algoritma",
					"term":"3",
					"is_wajib":"0"
				}
			]
	}
}


```

### Error Response

```json

{
  "status":404,
  "msg":"Kurikulum tidak ditemukan"
}

```


## Get Mata Kuliah

Method ini digunakan untuk mengembalikan data semua mata kuliah

**URL** : `/api/getMataKuliah`

**Method** : `GET`

### Success Response

**Contoh Request**: `localhost:8080/api/getMataKuliah?kodeMataKuliah=CSC001&kodeKurikulum=CSUI2011`

```json
{
  "status":"200",
	"msg":"success",
	"result":[
		{
			"id":"1",
			"kode_matkul":"CSC001",
			"nama_matkul":"DDP 1",
			"jumlah_sks":4,
			"deskripsi":"Mata kuliah ini mengajarkan Dasar-Dasar Pemrograman 1",
			"term":"1",
			"is_wajib":"1"
		}
	]
}


```

### Error Response

```json
{
  "status":404,
  "msg":"Mata Kuliah tidak ditemukan"
}

```


## Get Prasyarat

Method ini digunakan untuk mengembalikan prasyarat dari suatu mata kuliah

**URL** : `/api/getPrasyarat`

**Method** : `GET`

### Success Response

**Contoh Request**: `localhost:8080/api/getPrasyarat?kodeMataKuliah=CSC003&kodeKurikulum=CSUI2011`

```json
{
	"status":"200",
	"msg":"success",
	"result":[
		{
			"id":"1",
			"kode_matkul":"CSC001",
			"nama_matkul":"DDP 1",
			"jumlah_sks":4,
			"deskripsi":"Mata kuliah ini mengajarkan Dasar-Dasar Pemrograman 1",
			"term":"1",
			"is_wajib":"1"
		},
		{
			"id":"2",
			"kode_matkul":"CSC002",
			"nama_matkul":"DDP 2",
			"jumlah_sks":4,
			"deskripsi":"Mata kuliah ini mengajarkan Dasar-Dasar Pemrograman 2",
			"term":"2",
			"is_wajib":"1"
			}
		]
}

```

### Error Response

```json
{
  "status":404,
  "msg":"Mata Kuliah tidak ditemukan"
}

```


## Get All Kurikulum

Method ini digunakan untuk mengembalikan semua kurikulum yang ada

**URL** : `/api/getAllKurikulum`

**Method** : `GET`

### Success Response

**Contoh Request**: `localhost:8080/api/getAllKurikulum`

```json

{
	"status":"200",
	"msg":"success",
	"result":[
		{
			"id":"1",
			"kode_kurikulum":"CSUI2011",
			"nama_kurikulum":"Kurikulum Fasilkom 2011",
			"id_universitas":"1",
			"id_fakultas":"1",
			"id_prodi":"1",
			"jumlahSks":0,
			"jumlahSksWajib":0,
			"jumlahSksPilihan":0,
			"matakuliah":[
				{
					"id":"1",
					"kode_matkul":"CSC001",
					"nama_matkul":"DDP 1",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Dasar-Dasar Pemrograman 1",
					"term":"1",
					"is_wajib":"1"
				},
				{
					"id":"2",
					"kode_matkul":"CSC002",
					"nama_matkul":"DDP 2",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Dasar-Dasar Pemrograman 2",
					"term":"2",
					"is_wajib":"1"
				},
				{
					"id":"3",
					"kode_matkul":"CSC003",
					"nama_matkul":"SDA",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Struktur Dasar Algoritma",
					"term":"3",
					"is_wajib":"0"
				}
			]
			},
			{
				"id":"2",
				"kode_kurikulum":"FTUI2012",
				"nama_kurikulum":"Kurikulum FT 2012",
				"id_universitas":"1",
				"id_fakultas":"2",
				"id_prodi":"1",
				"jumlahSks":0,
				"jumlahSksWajib":0,
				"jumlahSksPilihan":0,
				"matakuliah":[
				{
					"id":"4",
					"kode_matkul":"ENG001",
					"nama_matkul":"GAMTEK",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Penggambaran Teknik",
					"term":"1",
					"is_wajib":"1"
				},
				{
					"id":"5",
					"kode_matkul":"ENG002",
					"nama_matkul":"Manajerial Industri",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Manajerial Industri",
					"term":"2",
					"is_wajib":"1"
				},
				{
					"id":"6",
					"kode_matkul":"ENG003",
					"nama_matkul":"K3L",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan K3L",
					"term":"3",
					"is_wajib":"0"
				}
				]
			},
			{
				"id":"3",
				"kode_kurikulum":"FEUI2013",
				"nama_kurikulum":"Kurikulum FE 2013",
				"id_universitas":"1",
				"id_fakultas":"3",
				"id_prodi":"1",
				"jumlahSks":0,
				"jumlahSksWajib":0,
				"jumlahSksPilihan":0,
				"matakuliah":[
				{
					"id":"7",
					"kode_matkul":"FEB001",
					"nama_matkul":"Akuntansi Dasar",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Akuntansi Dasar",
					"term":"1",
					"is_wajib":"1"
				},
				{
					"id":"8",
					"kode_matkul":"FEB002",
					"nama_matkul":"Ekonomi Mikro",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Ekonomi Mikro",
					"term":"2",
					"is_wajib":"1"
				},
				{
					"id":"9",
					"kode_matkul":"FEB003",
					"nama_matkul":"Akuntansi Lanjut",
					"jumlah_sks":4,
					"deskripsi":"Mata kuliah ini mengajarkan Akuntansi Lanjut",
					"term":"3",
					"is_wajib":"0"
				}
			]
		}
	]
} 


```

### Error Response

```json
{
  "status":404,
  "msg":"Tidak ada kurikulum yang dapat ditampilkan"
}

```


## Get Syarat Lulus

Method ini digunakan untuk mengembalikan data kurikulum untuk memberikan informasi mengenai jumlah sks wajib dan pilihan dalam suatu kurikulum sebagai syarat lulus yang digunakan bagian akademik mahasiswa

**URL** : `/api/getSyaratLulus`

**Method** : `GET`

### Success Response

**Contoh Request**: `localhost:8080/api/getSyaratLulus?kodeKurikulum=CSUI2011`

```json
{
  "status": "200",
  "msg": "success",
  "result": {
    "kode_kurikulum": "CSUI2011",
    "jumlah_sks": 12,
    "jumlah_sks_wajib": 8,
    "jumlah_sks_pilihan": 4
  }
}

```

### Error Response

```json

{
  "status":404,
  "msg":"Kurikulum tidak ditemukan"
}

```
