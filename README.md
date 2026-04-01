# STRUKTUR DATA MINGGU 4
NAMA : ATIK PUTRI MATULINA

NRP : 5027251128

KELAS: STRUKTUR DATA DAN PEMROGRAMAN BERORIENTASI A

# DESKRIPSI
Pemilik kos yang menggunakan sistem smart door yang dapat mendeteksi dan mengenali nama penghuni, nomor kamar, dan plat kendaraan untuk mencegah kemalingan atau pencurian benda dan barang yang ada di lingkungan KOS NCT 127.
# CLASS DIAGRAM 
https://github.com/atikmatulina/tugasstrukdat/blob/520777b594d9e7fcae80b9566a415847183baabd/CLASS%20DIAGRAM

# KODE PROGRAM 
https://github.com/atikmatulina/tugasstrukdat/blob/f420dc260fc4c6c1db23763a01da90d91c0e7d7a/KODE%20PROGRAM

# SCREENSHOT OUTPUT
<img width="1470" height="956" alt="Screenshot 2026-03-28 at 12 42 23 PM" src="https://github.com/user-attachments/assets/6998ef38-c4db-4b61-8651-11fe6e1a71a7" />



# PENJELASAN OOP YANG DIGUNAKAN 
Class = membuat class kartu, penghuni, smart door, dan AksesLog

Object = membuat intance dari class yang menggunakan new. contoh : SmartDoor smartdooor = new SmartDoor ("KOS NCT 127")

Encapsulation = untuk menyembunyikan data internal dari luar class (private)

private String nama;
private boolean aktif;

public String getNama() { return nama; }      // getter
public void setAktif(boolean aktif) { ... }   // setter

Assosiation = untuk menyimpan dan mengelola objek 

# PENJELASAN KEUNIKAN PROGRAM 
1. untuk menedeteksi siapa penghuni kos (baru atau lama) yang sedang menempati kos tsb
2. meminimalisir pencurian kendaraan dan barang penghuni kos
