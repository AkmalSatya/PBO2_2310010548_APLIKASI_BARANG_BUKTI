-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 13, 2026 at 08:42 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_barang_bukti`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang_bukti`
--

CREATE TABLE `barang_bukti` (
  `id_barang_bukti` int(11) NOT NULL,
  `nama_barang` varchar(150) NOT NULL,
  `tanggal_masuk` date NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `satuan` varchar(50) DEFAULT NULL,
  `status_barang` enum('Dikembalikan','Dimusnahkan','Dirampas Negara','Lainnya') NOT NULL,
  `kondisi_barang` enum('Baik','Kurang Baik','Rusak') NOT NULL,
  `keterangan` text DEFAULT NULL,
  `id_user_input` int(11) DEFAULT NULL,
  `id_kategori` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang_bukti`
--

INSERT INTO `barang_bukti` (`id_barang_bukti`, `nama_barang`, `tanggal_masuk`, `jumlah_barang`, `satuan`, `status_barang`, `kondisi_barang`, `keterangan`, `id_user_input`, `id_kategori`) VALUES
(1, 'Handphone', '2025-12-12', 1, 'Satu', 'Dirampas Negara', 'Kurang Baik', 'Bukti Kuat', 1, 3),
(234, 'Motor', '2025-12-12', 1, 'Satu', 'Dirampas Negara', 'Kurang Baik', 'Lainnya', 1, 2),
(341, 'Tas Wanita', '2026-01-01', 1, 'Satu', 'Dirampas Negara', 'Kurang Baik', 'Kecelakaan Roda Dua', 1, 2),
(374, 'Hp', '2025-01-01', 1, 'Satu', 'Dikembalikan', 'Baik', '-', 1, 3),
(9282, 'Mobil', '2025-11-23', 2, 'Dua', 'Dikembalikan', 'Baik', 'Laka Lantas', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `barang_temuan`
--

CREATE TABLE `barang_temuan` (
  `id_barang_temuan` int(11) NOT NULL,
  `nama_barang` varchar(150) NOT NULL,
  `tanggal_masuk` date NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `satuan` varchar(50) DEFAULT NULL,
  `kondisi_barang` enum('Baik','Kurang Baik','Rusak') NOT NULL,
  `keterangan` text DEFAULT NULL,
  `id_user_input` int(11) DEFAULT NULL,
  `id_kategori` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang_temuan`
--

INSERT INTO `barang_temuan` (`id_barang_temuan`, `nama_barang`, `tanggal_masuk`, `jumlah_barang`, `satuan`, `kondisi_barang`, `keterangan`, `id_user_input`, `id_kategori`) VALUES
(222, 'Mobil', '2025-11-11', 2, 'Dua', 'Kurang Baik', 'Lainnya', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `kategori_barang`
--

CREATE TABLE `kategori_barang` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(100) NOT NULL,
  `deskripsi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori_barang`
--

INSERT INTO `kategori_barang` (`id_kategori`, `nama_kategori`, `deskripsi`) VALUES
(1, 'Kendaraan Roda Empat', 'Kendaraan roda 4 adalah jenis kendaraan bermotor yang memiliki empat roda dan biasanya digunakan untuk transportasi darat. Contohnya meliputi mobil penumpang, SUV, truk, dan van.'),
(2, 'Kendaraan Roda Dua', 'Kendaraan roda 2 adalah kendaraan bermotor atau non-motor yang memiliki dua roda sejajar dan digunakan untuk transportasi pribadi. Contohnya adalah sepeda dan sepeda motor.'),
(3, 'Telephone', 'Telepon adalah alat komunikasi yang digunakan untuk mengirim dan menerima suara secara jarak jauh. Awalnya, telepon bekerja dengan mengubah gelombang suara menjadi sinyal listrik yang dikirim melalui kabel.'),
(4, 'Sepeda', 'Roda 2'),
(5, 'Tas', 'ransel');

-- --------------------------------------------------------

--
-- Table structure for table `log_aktivitas`
--

CREATE TABLE `log_aktivitas` (
  `id_log` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `waktu_aksi` datetime NOT NULL,
  `jenis_aksi` varchar(50) NOT NULL,
  `deskripsi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `log_aktivitas`
--

INSERT INTO `log_aktivitas` (`id_log`, `id_user`, `waktu_aksi`, `jenis_aksi`, `deskripsi`) VALUES
(14, 1, '2026-01-11 09:00:23', 'LOGIN', 'Admin Budi berhasil masuk ke sistem'),
(15, 1, '2026-01-11 09:00:23', 'TAMBAH', 'Berhasil input data Barang Temuan iPhone 13'),
(16, 2, '2026-01-11 09:00:23', 'CETAK', 'Petugas Siti mencetak laporan Barang Bukti'),
(17, 1, '2026-01-11 09:00:23', 'HAPUS', 'Menghapus log aktivitas yang sudah lama');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nama_pegawai` varchar(100) DEFAULT NULL,
  `jabatan` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_user`, `username`, `password`, `nama_pegawai`, `jabatan`) VALUES
(1, 'akmaal', '01', 'akam', 'presiden'),
(2, 'admin', 'admin123', 'Budi Santoso', 'Administrator'),
(3, 'petugas1', 'user123', 'Siti Aminah', 'Petugas Lapangan'),
(4, 'Agus', 'agus123', 'Agus A', 'Satpam'),
(5, 'agustina', '12345', 'Agustin', 'manager'),
(6, 'irfan', '12345', 'irfan a', 'karyawan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang_bukti`
--
ALTER TABLE `barang_bukti`
  ADD PRIMARY KEY (`id_barang_bukti`),
  ADD KEY `id_user_input` (`id_user_input`),
  ADD KEY `id_kategori` (`id_kategori`);

--
-- Indexes for table `barang_temuan`
--
ALTER TABLE `barang_temuan`
  ADD PRIMARY KEY (`id_barang_temuan`),
  ADD KEY `id_user_input` (`id_user_input`),
  ADD KEY `id_kategori` (`id_kategori`);

--
-- Indexes for table `kategori_barang`
--
ALTER TABLE `kategori_barang`
  ADD PRIMARY KEY (`id_kategori`),
  ADD UNIQUE KEY `nama_kategori` (`nama_kategori`);

--
-- Indexes for table `log_aktivitas`
--
ALTER TABLE `log_aktivitas`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang_bukti`
--
ALTER TABLE `barang_bukti`
  MODIFY `id_barang_bukti` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9283;

--
-- AUTO_INCREMENT for table `barang_temuan`
--
ALTER TABLE `barang_temuan`
  MODIFY `id_barang_temuan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=223;

--
-- AUTO_INCREMENT for table `kategori_barang`
--
ALTER TABLE `kategori_barang`
  MODIFY `id_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `log_aktivitas`
--
ALTER TABLE `log_aktivitas`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang_bukti`
--
ALTER TABLE `barang_bukti`
  ADD CONSTRAINT `barang_bukti_ibfk_1` FOREIGN KEY (`id_user_input`) REFERENCES `pengguna` (`id_user`),
  ADD CONSTRAINT `barang_bukti_ibfk_2` FOREIGN KEY (`id_kategori`) REFERENCES `kategori_barang` (`id_kategori`);

--
-- Constraints for table `barang_temuan`
--
ALTER TABLE `barang_temuan`
  ADD CONSTRAINT `barang_temuan_ibfk_1` FOREIGN KEY (`id_user_input`) REFERENCES `pengguna` (`id_user`),
  ADD CONSTRAINT `barang_temuan_ibfk_2` FOREIGN KEY (`id_kategori`) REFERENCES `kategori_barang` (`id_kategori`);

--
-- Constraints for table `log_aktivitas`
--
ALTER TABLE `log_aktivitas`
  ADD CONSTRAINT `log_aktivitas_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `pengguna` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
