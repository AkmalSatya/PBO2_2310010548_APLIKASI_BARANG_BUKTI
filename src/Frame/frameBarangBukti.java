/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frame;

import configDB.crud;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author ASUS
 */
public class frameBarangBukti extends javax.swing.JFrame {
    private crud objekKu;
    
    
    /**
     * Creates new form frameBarangBukti
     */
    public frameBarangBukti() {
        initComponents();
        this.setLocationRelativeTo(null);
        objekKu = new crud();
        
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });
        tabelBarangBukti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangBuktiMouseClicked(evt);
            }
        });
        
        isiComboBox();
        tampilkanDataBarangBukti("");
    }
private void tampilkanDataBarangBukti(String keyword) {
        DefaultTableModel model = new DefaultTableModel();
        // Pastikan urutan kolom sesuai dengan yang diambil di query!
        model.addColumn("ID BB"); 
        model.addColumn("Nama Barang");
        model.addColumn("Tgl Masuk");
        model.addColumn("Jumlah");
        model.addColumn("Status"); 
        model.addColumn("Kondisi");
        
        try {
            String query = "SELECT id_barang_bukti, nama_barang, tanggal_masuk, jumlah_barang, status_barang, kondisi_barang " +
                           "FROM BARANG_BUKTI " +
                           "WHERE nama_barang LIKE '%" + keyword + "%' OR " +
                           "id_barang_bukti LIKE '%" + keyword + "%' " +
                           "ORDER BY id_barang_bukti DESC";
            
            ResultSet rs = objekKu.getData(query);
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_barang_bukti"),
                    rs.getString("nama_barang"),
                    rs.getString("tanggal_masuk"),
                    rs.getInt("jumlah_barang"),
                    rs.getString("status_barang"),
                    rs.getString("kondisi_barang")
                });
            }
            // BARIS KRITIS: Mengatur model ke JTable
            tabelBarangBukti.setModel(model); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saat memuat data Barang Bukti: " + e.getMessage());
        }
}
    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {                                    
        String keyword = txtCari.getText();
        tampilkanDataBarangBukti(keyword);
    }
    
    private void isiComboBox() {
        // 1. Mengisi cmbKondisi (Data Statis/ENUM Kondisi Barang)
        cmbKondisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Baik", "Kurang Baik", "Rusak"
        }));
        
        // 1. Mengisi cmbStatus (Data Statis/ENUM Status Barang)
        // KODE BARU: Mengisi status barang
        status_barang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Dikembalikan", "Dimusnahkan", "Dirampas Negara", "Lainnya" // Nilai ENUM dari SQL
        }));
        
        // 2. Mengisi cmbKategori (Data Dinamis dari Database)
        tampilkanDataKategori(); 
    }
    private void tampilkanDataKategori() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        String query = "SELECT nama_kategori FROM KATEGORI_BARANG ORDER BY nama_kategori";
        ResultSet rs = objekKu.getData(query);
        
        try {
            model.addElement("-- Pilih Kategori --");
            while (rs.next()) {
                model.addElement(rs.getString("nama_kategori"));
            }
            cmbKategori.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data Kategori dari DB: " + e.getMessage());
        }
    }
    private void tabelBarangBuktiMouseClicked(java.awt.event.MouseEvent evt) {                                           
        int baris = tabelBarangBukti.getSelectedRow();
        if (baris != -1) {
            // Ambil data dari tabel berdasarkan kolom (INDEX KOLOM SANGAT KRITIS)
            String id = tabelBarangBukti.getValueAt(baris, 0).toString();      // ID BB
            String nama = tabelBarangBukti.getValueAt(baris, 1).toString();    // Nama Barang
            String tglMasuk = tabelBarangBukti.getValueAt(baris, 2).toString(); // Tgl Masuk
            String jumlah = tabelBarangBukti.getValueAt(baris, 3).toString();    // Jumlah
            String status = tabelBarangBukti.getValueAt(baris, 4).toString();    // Status
            String kondisi = tabelBarangBukti.getValueAt(baris, 5).toString();   // Kondisi
            
            // --- Isi Field Input ---
            txtIDBarangBukti.setText(id);
            txtNamaBarang.setText(nama);
            txtTglMasuk.setText(tglMasuk);
            txtJumlah.setText(jumlah);
            // txtSatuan & txtKeterangan TIDAK ADA di kolom tabel (harus diambil via query tambahan jika perlu)
            
            status_barang.setSelectedItem(status);
            cmbKondisi.setSelectedItem(kondisi);
            
            // Atur status tombol
            btnTambah.setEnabled(false);
            btnUbah.setEnabled(true);
            btnHapus.setEnabled(true);
        }
    }
    private String getIDKategori(String namaKategori) {
        if (namaKategori.equals("-- Pilih Kategori --")) return "1"; 
        
        String idKat = "1"; 
        try {
            String query = "SELECT id_kategori FROM KATEGORI_BARANG WHERE nama_kategori = '" + namaKategori + "'";
            ResultSet rs = objekKu.getData(query);
            if (rs.next()) {
                idKat = rs.getString("id_kategori");
            }
        } catch (SQLException e) {
            System.err.println("Error get ID Kategori: " + e.getMessage());
        }
        return idKat;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        txtIDBarangBukti = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtTglMasuk = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        txtSatuan = new javax.swing.JTextField();
        cmbKondisi = new javax.swing.JComboBox<>();
        cmbKategori = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        status_barang = new javax.swing.JComboBox<>();
        txtCari = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelBarangBukti = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("FORM DATA BARANG BUKTI");

        jLabel2.setText("ID BARANG BUKTI");

        jLabel3.setText("NAMA BARANG");

        jLabel4.setText("TANGGAL MASUK");

        jLabel5.setText("JUMLAH BARANG");

        jLabel6.setText("SATUAN");

        jLabel7.setText("KONDISI BARANG");

        jLabel8.setText("KATEGORI");

        jLabel9.setText("KETERANGAN");

        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnUbah.setText("UBAH");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        cmbKondisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baik", "Kurang Baik", "Rusak" }));
        cmbKondisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKondisiActionPerformed(evt);
            }
        });

        cmbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kendaraan Roda Empat", "Kendaraan Roda Dua", "Elektronik / Gadget (Handphone)" }));
        cmbKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKategoriActionPerformed(evt);
            }
        });

        txtKeterangan.setColumns(20);
        txtKeterangan.setRows(5);
        jScrollPane1.setViewportView(txtKeterangan);

        jLabel11.setText("STATUS BARANG");

        status_barang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dikembalikan", "Dimusnahkan", "Dirampas Negara", "Lainnya" }));

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });

        tabelBarangBukti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabelBarangBukti);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCari))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(status_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIDBarangBukti, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbKondisi, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnUbah)
                            .addComponent(btnTambah)
                            .addComponent(btnHapus))
                        .addGap(59, 59, 59))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDBarangBukti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTambah)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnUbah)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnHapus)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel9)
                        .addGap(108, 108, 108)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbKondisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(status_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(cmbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addGap(187, 187, 187))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
     try {
            String idBB = txtIDBarangBukti.getText();
            String nama = txtNamaBarang.getText();
            String tglMasuk = txtTglMasuk.getText(); 
            int jumlah = Integer.parseInt(txtJumlah.getText());
            String satuan = txtSatuan.getText();
            
            // KOREKSI UTAMA: Pisahkan Status dan Kondisi
            String status = status_barang.getSelectedItem().toString(); // NILAI STATUS DARI cmbStatus
            String kondisi = cmbKondisi.getSelectedItem().toString(); // NILAI KONDISI DARI cmbKondisi
            
            String ket = txtKeterangan.getText(); 
            
            // Logika FK
            String idUser = "1"; 
            String namaKategori = cmbKategori.getSelectedItem().toString(); 
            String idKat = getIDKategori(namaKategori); 
            
            if (idBB.isEmpty() || nama.isEmpty() || tglMasuk.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID, Nama, dan Tanggal Masuk tidak boleh kosong!");
                return;
            }
            // Panggilan dengan Status dan Kondisi yang benar
            objekKu.simpanBarangBukti(idBB, nama, tglMasuk, jumlah, satuan, status, kondisi, ket, idUser, idKat);
            tampilkanDataBarangBukti("");
            JOptionPane.showMessageDialog(this, "Data Barang Bukti berhasil disimpan!"); 
     } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input Jumlah tidak valid (harus angka): " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saat menambah data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
       try {
            String idBB = txtIDBarangBukti.getText();
            if (idBB.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID Barang Bukti harus diisi untuk menghapus data!");
                return;
            }
            
            int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin hapus Barang Bukti ID " + idBB + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            
            if (konfirmasi == JOptionPane.YES_OPTION) {
                 objekKu.hapusBarangBukti(idBB);
                 tampilkanDataBarangBukti("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saat menghapus data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        try {
           String idBB = txtIDBarangBukti.getText();
            String nama = txtNamaBarang.getText();
            String tglMasuk = txtTglMasuk.getText();
            int jumlah = Integer.parseInt(txtJumlah.getText());
            String satuan = txtSatuan.getText();
            
            // KOREKSI UTAMA: Pisahkan Status dan Kondisi
            String status = status_barang.getSelectedItem().toString(); // NILAI STATUS DARI cmbStatus
            String kondisi = cmbKondisi.getSelectedItem().toString(); // NILAI KONDISI DARI cmbKondisi
            
            String ket = txtKeterangan.getText();
            
            String idUser = "1"; 
            String namaKategori = cmbKategori.getSelectedItem().toString(); 
            String idKat = getIDKategori(namaKategori); 
            
            if (idBB.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID Barang Bukti harus diisi untuk mengubah data!");
                return;
            }
            // Panggilan dengan Status dan Kondisi yang benar
            objekKu.ubahBarangBukti(idBB, nama, tglMasuk, jumlah, satuan, status, kondisi, ket, idUser, idKat);
            tampilkanDataBarangBukti("");
            JOptionPane.showMessageDialog(this, "Data Barang Bukti berhasil diubah!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input Jumlah tidak valid (harus angka): " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saat mengubah data: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void cmbKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKategoriActionPerformed

    private void cmbKondisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKondisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKondisiActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        String keyword = txtCari.getText();
        tampilkanDataBarangBukti(keyword);
    }//GEN-LAST:event_txtCariActionPerformed

    /*
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frameBarangBukti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frameBarangBukti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frameBarangBukti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frameBarangBukti.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frameBarangBukti().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cmbKategori;
    private javax.swing.JComboBox<String> cmbKondisi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> status_barang;
    private javax.swing.JTable tabelBarangBukti;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIDBarangBukti;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtSatuan;
    private javax.swing.JTextField txtTglMasuk;
    // End of variables declaration//GEN-END:variables
}
