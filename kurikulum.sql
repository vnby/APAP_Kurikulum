CREATE TABLE KURIKULUM (
    id SERIAL NOT NULL,
    kode_kurikulum VARCHAR(8) NOT NULL,
    nama_kurikulum VARCHAR(255) NOT NULL,
    id_universitas VARCHAR(20) NOT NULL,
    id_fakultas VARCHAR(20) NOT NULL,
    id_prodi VARCHAR(20) NOT NULL,
    primary key (id),
    unique (kode_kurikulum)
);

CREATE TABLE USERS (
username VARCHAR(45) NOT NULL ,
password VARCHAR(45) NOT NULL ,
enabled TINYINT NOT NULL DEFAULT 1 ,
id_universitas VARCHAR(5) NOT NULL,
id_fakultas VARCHAR(5) NOT NULL,
id_prodi VARCHAR(5) NOT NULL,
PRIMARY KEY (username));

CREATE TABLE USER_ROLES (
user_role_id int(11) NOT NULL AUTO_INCREMENT,
username varchar(45) NOT NULL,
role varchar(45) NOT NULL,
PRIMARY KEY (user_role_id),
UNIQUE KEY uni_username_role (role,username),
KEY fk_username_idx (username),
CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

CREATE TABLE MATA_KULIAH (
    id SERIAL NOT NULL,
    kode_matkul VARCHAR(6) NOT NULL,
    nama_matkul VARCHAR(255) NOT NULL,
    jumlah_sks INT NOT NULL,
    deskripsi TEXT,
  	term TINYINT NOT NULL,
  	id_universitas VARCHAR (5) NOT NULL,
  	id_fakultas VARCHAR (5) NOT NULL,
    primary key (id),
    unique (kode_matkul)
);

CREATE TABLE KURIKULUM_MATAKULIAH (
    id SERIAL NOT NULL,
    kode_kurikulum VARCHAR(8) NOT NULL,
    kode_matkul VARCHAR(6) NOT NULL,
    is_wajib VARCHAR(1) NOT NULL,
    primary key (id),
    FOREIGN KEY (kode_kurikulum) REFERENCES KURIKULUM(kode_kurikulum) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (kode_matkul) REFERENCES MATA_KULIAH(kode_matkul) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PRASYARAT_MATAKULIAH (
    id SERIAL NOT NULL,
    kode_kurikulum VARCHAR(8) NOT NULL,
    kode_matkul VARCHAR(6) NOT NULL,
    prasyarat VARCHAR(6) NOT NULL,
    primary key (id),
    FOREIGN KEY (kode_kurikulum) REFERENCES KURIKULUM(kode_kurikulum) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (kode_matkul) REFERENCES MATA_KULIAH(kode_matkul) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (prasyarat) REFERENCES MATA_KULIAH(kode_matkul) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO KURIKULUM (kode_kurikulum, nama_kurikulum, id_universitas, id_fakultas, id_prodi)
VALUES ("CSUI2011", "Kurikulum Fasilkom 2011", "1", "1", "1"),
("FTUI2012", "Kurikulum FT 2012", "1", "2", "1"),
("FEUI2013", "Kurikulum FE 2013", "1", "3", "1");


INSERT INTO MATA_KULIAH (kode_matkul, nama_matkul, jumlah_sks, deskripsi, term, id_universitas, id_fakultas)
VALUES ("CSC001", "DDP 1", 4, "Mata kuliah ini mengajarkan Dasar-Dasar Pemrograman 1", 1, 1, 1), 
("CSC002", "DDP 2", 4, "Mata kuliah ini mengajarkan Dasar-Dasar Pemrograman 2", 2, 1, 1),
("CSC003", "SDA", 4, "Mata kuliah ini mengajarkan Struktur Dasar Algoritma", 3, 1, 1), 
("ENG001", "GAMTEK", 4, "Mata kuliah ini mengajarkan Penggambaran Teknik", 1, 1, 2), 
("ENG002", "Manajerial Industri", 4, "Mata kuliah ini mengajarkan Manajerial Industri", 2, 1, 2), 
("ENG003", "K3L", 4, "Mata kuliah ini mengajarkan K3L", 3, 1, 2), 
("FEB001", "Akuntansi Dasar", 4, "Mata kuliah ini mengajarkan Akuntansi Dasar", 1, 1, 3), 
("FEB002", "Ekonomi Mikro", 4, "Mata kuliah ini mengajarkan Ekonomi Mikro", 2, 1, 3),
("FEB003", "Akuntansi Lanjut", 4, "Mata kuliah ini mengajarkan Akuntansi Lanjut", 3,1 ,3);

INSERT INTO KURIKULUM_MATAKULIAH (kode_kurikulum, kode_matkul, is_wajib) VALUES
("CSUI2011","CSC001", "1"), ("CSUI2011","CSC002", "1"),("CSUI2011","CSC003", "0"),
("FTUI2012","ENG001", "1"), ("FTUI2012","ENG002", "1"), ("FTUI2012","ENG003", "0"),
("FEUI2013","FEB001", "1"), ("FEUI2013","FEB002", "1"), ("FEUI2013","FEB003", "0");

INSERT INTO PRASYARAT_MATAKULIAH(kode_kurikulum, kode_matkul, prasyarat) VALUES 
("CSUI2011", "CSC003", "CSC001"), 
("CSUI2011", "CSC003", "CSC002"), 
("FTUI2012", "ENG003", "ENG001"), 
("FTUI2012", "ENG003", "ENG002"), 
("FEUI2013", "FEB003", "FEB001"), 
("FEUI2013", "FEB003", "FEB002");

INSERT INTO users(username,password,enabled, id_universitas, id_fakultas, id_prodi)
VALUES ('fasilkom','fasilkom', true, '1', '1', '1'), 
('teknik','teknik', true, '1', '2', '1'),
('ekonomi','ekonomi', true, '1', '3', '1');

INSERT INTO user_roles (username, role)
VALUES ('fasilkom', 'ROLE_KAPRODI'),
('teknik', 'ROLE_KAPRODI'),
('ekonomi', 'ROLE_KAPRODI');