/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.io.File;

/**
 *
 * @author ASUS
 */
public class crud {
    
    // Ganti namaDB sesuai dengan database SQL Anda
    private String namaDB = "db_barang_bukti"; 
    private String url = "jdbc:mysql://localhost:3306/" + namaDB;
    private String username = "root";
    private String password = "";
    private Connection koneksi;
    
    // Variabel untuk menyimpan hasil pencarian (jika diperlukan)
    public ResultSet dataResult; 

    public crud() {
        try {
            // Pastikan Anda telah menambahkan library JDBC MySQL Connector ke NetBeans
            Driver mysqldriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(mysqldriver);
            koneksi = DriverManager.getConnection(url, username, password);
            System.out.println("Berhasil dikoneksikan ke database: " + namaDB);
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Gagal koneksi ke database: " + error.getMessage());
        }
    }
    
    // --- FUNGSI UTILITY (READ/Cari Data) ---
    public ResultSet getData(String query) {
        try {
            Statement stmt = koneksi.createStatement();
            dataResult = stmt.executeQuery(query);
            return dataResult;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mengambil data: " + e.getMessage());
            return null;
        }
    }


    // =========================================================================
    // --- CRUD UNTUK TABEL PENGGUNA ---
    // =========================================================================

    // C: CREATE (Tambah)
    public void simpanPengguna(String id, String user, String pass, String nama, String jabatan) {
        try {
            String sql = "INSERT INTO PENGGUNA (id_user, username, password, nama_pegawai, jabatan) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, id);
            perintah.setString(2, user);
            perintah.setString(3, pass);
            perintah.setString(4, nama);
            perintah.setString(5, jabatan);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pengguna berhasil disimpan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data Pengguna: " + e.getMessage());
        }
    }

    // U: UPDATE (Ubah)
    public void ubahPengguna(String id, String user, String pass, String nama, String jabatan) {
        try {
            String sql = "UPDATE PENGGUNA SET username=?, password=?, nama_pegawai=?, jabatan=? WHERE id_user=?";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, user);
            perintah.setString(2, pass);
            perintah.setString(3, nama);
            perintah.setString(4, jabatan);
            perintah.setString(5, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pengguna berhasil diubah!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengubah data Pengguna: " + e.getMessage());
        }
    }

    // D: DELETE (Hapus)
    public void hapusPengguna(String id) {
        try {
            String sql = "DELETE FROM PENGGUNA WHERE id_user=?";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pengguna berhasil dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data Pengguna: " + e.getMessage());
        }
    }
    
    // =========================================================================
    // --- CRUD UNTUK TABEL BARANG_BUKTI ---
    // (Anda perlu menyesuaikan tipe data dan jumlah parameter sesuai Frame)
    // =========================================================================

    // C: CREATE (Tambah)
    public void simpanBarangBukti(String idBB, String nama, String tglMasuk, int jumlah, String satuan, String status, String kondisi, String ket, String idUser, String idKat) {
        try {
            String sql = "INSERT INTO BARANG_BUKTI (id_barang_bukti, nama_barang, tanggal_masuk, jumlah_barang, satuan, status_barang, kondisi_barang, keterangan, id_user_input, id_kategori) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, idBB);
            perintah.setString(2, nama);
            perintah.setString(3, tglMasuk);
            perintah.setInt(4, jumlah);
            perintah.setString(5, satuan);
            perintah.setString(6, status);
            perintah.setString(7, kondisi);
            perintah.setString(8, ket);
            perintah.setString(9, idUser);
            perintah.setString(10, idKat);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Barang Bukti berhasil disimpan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data Barang Bukti: " + e.getMessage());
        }
    }

    // U: UPDATE (Ubah)
    public void ubahBarangBukti(String idBB, String nama, String tglMasuk, int jumlah, String satuan, String status, String kondisi, String ket, String idUser, String idKat) {
        try {
            String sql = "UPDATE BARANG_BUKTI SET nama_barang=?, tanggal_masuk=?, jumlah_barang=?, satuan=?, status_barang=?, kondisi_barang=?, keterangan=?, id_user_input=?, id_kategori=? WHERE id_barang_bukti=?";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, nama);
            perintah.setString(2, tglMasuk);
            perintah.setInt(3, jumlah);
            perintah.setString(4, satuan);
            perintah.setString(5, status);
            perintah.setString(6, kondisi);
            perintah.setString(7, ket);
            perintah.setString(8, idUser);
            perintah.setString(9, idKat);
            perintah.setString(10, idBB);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Barang Bukti berhasil diubah!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengubah data Barang Bukti: " + e.getMessage());
        }
    }

    // D: DELETE (Hapus)
    public void hapusBarangBukti(String idBB) {
        try {
            String sql = "DELETE FROM BARANG_BUKTI WHERE id_barang_bukti=?";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, idBB);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Barang Bukti berhasil dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data Barang Bukti: " + e.getMessage());
        }
    }

    // =========================================================================
    // --- CRUD UNTUK TABEL BARANG_TEMUAN ---
    // =========================================================================

    // C: CREATE (Tambah)
    public void simpanBarangTemuan(String idBT, String nama, String tglMasuk, int jumlah, String satuan, String kondisi, String ket, String idUser, String idKat) {
        try {
            String sql = "INSERT INTO BARANG_TEMUAN (id_barang_temuan, nama_barang, tanggal_masuk, jumlah_barang, satuan, kondisi_barang, keterangan, id_user_input, id_kategori) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, idBT);
            perintah.setString(2, nama);
            perintah.setString(3, tglMasuk);
            perintah.setInt(4, jumlah);
            perintah.setString(5, satuan);
            perintah.setString(6, kondisi);
            perintah.setString(7, ket);
            perintah.setString(8, idUser);
            perintah.setString(9, idKat);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Barang Temuan berhasil disimpan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data Barang Temuan: " + e.getMessage());
        }
    }

    // U: UPDATE (Ubah)
    public void ubahBarangTemuan(String idBT, String nama, String tglMasuk, int jumlah, String satuan, String kondisi, String ket, String idUser, String idKat) {
        try {
            String sql = "UPDATE BARANG_TEMUAN SET nama_barang=?, tanggal_masuk=?, jumlah_barang=?, satuan=?, kondisi_barang=?, keterangan=?, id_user_input=?, id_kategori=? WHERE id_barang_temuan=?";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, nama);
            perintah.setString(2, tglMasuk);
            perintah.setInt(3, jumlah);
            perintah.setString(4, satuan);
            perintah.setString(5, kondisi);
            perintah.setString(6, ket);
            perintah.setString(7, idUser);
            perintah.setString(8, idKat);
            perintah.setString(9, idBT);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Barang Temuan berhasil diubah!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengubah data Barang Temuan: " + e.getMessage());
        }
    }

    // D: DELETE (Hapus)
    public void hapusBarangTemuan(String idBT) {
        try {
            String sql = "DELETE FROM BARANG_TEMUAN WHERE id_barang_temuan=?";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, idBT);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Barang Temuan berhasil dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data Barang Temuan: " + e.getMessage());
        }
    }
   
    // C: CREATE (Tambah)
    public boolean simpanKategori(String nama, String deskripsi) {
    try {
        String sql = "INSERT INTO KATEGORI_BARANG (nama_kategori, deskripsi) VALUES (?, ?)";
        PreparedStatement perintah = koneksi.prepareStatement(sql);
        perintah.setString(1, nama);
        perintah.setString(2, deskripsi);
        
        int rowsAffected = perintah.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL saat Simpan Kategori: " + e.getMessage());
        return false;
    }
}

public boolean ubahKategori(int idKategori, String nama, String deskripsi) {
    try {
        String sql = "UPDATE KATEGORI_BARANG SET nama_kategori = ?, deskripsi = ? WHERE id_kategori = ?";
        PreparedStatement perintah = koneksi.prepareStatement(sql);
        perintah.setString(1, nama);
        perintah.setString(2, deskripsi);
        perintah.setInt(3, idKategori);
        
        int rowsAffected = perintah.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL saat Ubah Kategori: " + e.getMessage());
        return false;
    }
}

public boolean hapusKategori(int idKategori) {
    try {
        String sql = "DELETE FROM KATEGORI_BARANG WHERE id_kategori = ?";
        PreparedStatement perintah = koneksi.prepareStatement(sql);
        perintah.setInt(1, idKategori);
        
        int rowsAffected = perintah.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL saat Hapus Kategori: " + e.getMessage());
        return false;
    }
}

    // =========================================================================
    // --- CRUD UNTUK TABEL LOG_AKTIVITAS ---
    // (Tabel ini biasanya hanya butuh CREATE/READ. UPDATE/DELETE jarang digunakan)
    // =========================================================================

    // C: CREATE (Tambah) - Otomatis mencatat waktu
    public void simpanLogAktivitas(String idLog, String idUser, String jenisAksi, String deskripsi) {
        try {
            String sql = "INSERT INTO LOG_AKTIVITAS (id_log, id_user, waktu_aksi, jenis_aksi, deskripsi) VALUES (?, ?, NOW(), ?, ?)";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, idLog);
            perintah.setString(2, idUser);
            perintah.setString(3, jenisAksi);
            perintah.setString(4, deskripsi);
            perintah.executeUpdate();
            // Tidak perlu notifikasi JOptionPane untuk log
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan Log Aktivitas: " + e.getMessage());
        }
    }
    
    // D: DELETE (Hapus) - Untuk membersihkan log lama
    public void hapusLogAktivitas(String idLog) {
        try {
            String sql = "DELETE FROM LOG_AKTIVITAS WHERE id_log=?";
            PreparedStatement perintah = koneksi.prepareStatement(sql);
            perintah.setString(1, idLog);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Log Aktivitas berhasil dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus Log Aktivitas: " + e.getMessage());
        }
    }
    public boolean hapusLogAktivitas(int idLog) {
    try {
        String sql = "DELETE FROM LOG_AKTIVITAS WHERE id_log = ?";
        PreparedStatement perintah = koneksi.prepareStatement(sql);
        perintah.setInt(1, idLog);
        
        int rowsAffected = perintah.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error SQL saat Hapus Log: " + e.getMessage());
        return false;
    }
}
public void tampilLaporan(String laporanFile) {
    try {
        // Mencari lokasi file .jasper atau .jrxml di dalam package laporan
        String path = "src/laporan/" + laporanFile + ".jasper";
        File file = new File(path);
        
        // Mengisi laporan dengan koneksi database yang sudah ada di class crud
        JasperPrint print = JasperFillManager.fillReport(path, null, koneksi);
        
        // Menampilkan laporan
        JasperViewer.viewReport(print, false);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal menampilkan laporan: " + e.getMessage());
    }
}
}
