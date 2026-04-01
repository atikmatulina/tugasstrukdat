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
![WhatsApp Image 2026-04-01 at 2 04 55 PM](https://github.com/user-attachments/assets/a34d2943-c14b-4dbe-b190-5eccf390520f)

![WhatsApp Image 2026-04-01 at 2 04 55 PM (1)](https://github.com/user-attachments/assets/c967f455-08e2-4167-bdda-3804278880b0)

![WhatsApp Image 2026-04-01 at 2 04 55 PM (2)](https://github.com/user-attachments/assets/1c8d5ba9-86a1-4c00-8021-01b40a964186)

![WhatsApp Image 2026-04-01 at 2 04 56 PM](https://github.com/user-attachments/assets/b4025b7e-928b-459a-82a0-3b3082bd8263)






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
