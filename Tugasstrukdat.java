import java.until.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Kartu{
    private String nomorKartu;
    private boolean aktif;

    public Kartu (String nomorKartu){
        this.nomorKartu = nomorKartu;
        this.aktif = true;
    }

    public String getNomorKartu() { return nomorKartu; }
    public boolean isAktif() { return aktif; }
    public void setAktif (boolean aktif) { this.aktif = aktif; }


    public void tampilanInfo (){
        System.out.println(" No. Kartu : " + nomorKartu);
        System.out.println(" Status : " + (aktif ? "✅ Aktif" : "❌ Nonaktif"));
    

    }
}

 class Penghuni {
    private String nama;
    private String nomorKamar;
    private String nomorKartu;
    private String platKendaraan;
    private String boolean diDalam;
    private String List<String> riwayat;
}

public Penghuni (String nama, String nomorKartu, String platKendaraan){
    this.nama = nama;
    this.nomorKartu = nomorKartu;
    this.platKendaraan = platKendaraan;
    this.diDalam = true;
    this.riwayat = new ArrayList;
}

public String getnama() {return nama; }
public String getnomorKartu() {return nomorKartu; }
public String getplatKendaraan() {return platKendaraan; }
public boolean isdiDalam() {return diDalam; }

public void setdiDalam(boolean diDalam) { this.diDalam = diDalam; }
public void tambahRiwayat(String log) { riwayat.add(log); }

public void tampilRiwayat(){
    System.out.println ("\n 🗒️ Riwayat Akses -" + nama + "(Kamar" + nomorKamar +")");
    System.out.println ("-----------------------------------------------------------");
    if  (riwayat.isEmpty()){
        System.out.println(" Belum ada riwayat. ");
    } else {
        for (String log : riwayat){
            System.out.println (" . " + log);

               }
         }
    }
}

class Akseslog{
 private String nama;
    private String nomorKamar;
    private String nomorKartu;
    private String platKendaraan;
    private String aksi;
    private LocalDateTime waktu;

    public AksesLog (String nama, String nomorKamar, String nomorKartu, String platKendaraan, String aksi){
            this.nama = nama;
            this.nomorKartu = nomorKartu;
            this.platKendaraan = platKendaraan;
            this.aksi = aksi;
            this.waktu = LocalDateTime.now();

    }
    public String toLogString(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return aksi + " | " + waktu.format(fmt) + " | Plat: " + platKendaraan;
    }

    public void tampilLog (){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern ("dd-MM-yyyy HH:mm:ss");
        String emoji = aksi.equals ("MASUK") ? "🟢" : "🔴";
        System.out.println(" " + emoji + "[" + waktu,format(fmt) + "]" + aksi + " | " + nama + " | Kamar " + nomorKamar + " | Kartu " + nomorKartu + " | Plat " + platKendaraan);
    }
}

class SmartDoor {
    private String namaKos;
    private Map<String, Penghuni> dataPengguna;
    private Map<String, Kartu> dataKartu;
    private List<Akseslog> logAkses;
}

    private List<AksesLog> logAkses;
     
        public SmartDoor(String namakos){
            this.namaKos = namaKos;
            this.dataPenghuni = dataPenghuni;
            this.dataKartu = dataKartu;
            this.logAkses = new ArrayList<>();
        }

    public void daftarPenghuni (Penghuni p){
        Kartu k = new Kartu (p.getNomorKartu());
        dataPenghuni.put (p.getNomorKartu(), p);
        dataKartu.put(p.getNomorKartu(), k);
        System.out.println ("✅ Penghuni baru terdaftar: ");
        System.out.println (" Nama : " + p.getNama()):
        System.out.println (" Kamar : " + p.getNomorKamar());
        System.out.println (" Kartu :" + p.getNomorKartu());
        System.out.println (" Plat : " + p.getPlatKendaraan());
    }

public void hapusPenghuni(String nomorKartu){
    Penghuni p = dataPenghuni.get (nomorKartu);
    if (p == nul){
        System.out.println ("❌ Penghuni dengan kartu " + nomorKartu + " tidak ditemukan.");
        return;
    }
    dataPenghuni.remove(nomorKartu);
    dataKartu.remove (nomorKartu);
    System.out.println (" 🗒️ Data penghuni " + p.getNama() + "(Kamar " + p.gwtNomorKamar () + ") berhasil dihapus.");
}

public void hapusSemuaPenghuni(){
    int jumlah = dataPenghuni.size();
    dataPenghuni.clear();
    dataKartu.clear();
    logAkses.clear();
    System.out.println (" 📊 Semua data penghuni (" + jumlah + " orang) berhasil dihapus.");
    System.out.println (" 🗒️ Log akses juga telah dibersihkan.");

}
    public void tap (String nomorKartu) thrws InterruptedException {
        Theard.sleep(500);
        System.out.println ("\n Scanning kartu: " + nomorKartu + "...");

        Kartu kartu = dataKartu.get(nomorKartu);
        if (kartu == null) {
            System.out.println(" ❌ AKSES DITOLAK -- Kartu tidak dikenal!");
            return;
        }
        if (!kartu == null){
            System.out.println(" ❌ AKSES DITOLAK -- Kartu dinonaktifkan!");
            return;
        }

        Penghuni penghuni = dataPenghuni.get(nomotKartu);
        String aksi = penghuni.isDiDalam() ? "KELUAR" : "MASUK" ;
        penghuni.setDiDalam(!penghuni.isiDiDalam());

        AksesLOg log = new AksesLOg(
            penghuni.getNama(),
            penghuni.getNomorKamar(),
            nomorKartu,
            penghuni.getPlatKendaraan(),
            aksi

        );
        logAkses.add(log);
        penghuni.tambahRiwayat(log.toLogString());

        System.out.println(" ✅ AKSES DITERIMA");
        log.tampilLog();
        if (aksi.equals("MASUK")) {
            System.out.println(" PINTU TERBUKA -- Selamat datang, " + penghuni.getNama()+ "!");

        } else {
            System.out.println(" PINTU TERBUKA -- Selamat Jalan, " + penghuni.getNama() + "!");

         }
    }

    public void nonaktifkanKartu (Strig nomorKartu){
        Kartu k = dataKartu.get(nomorKartu);
        k.setAktif(false);
        Penghuni p [ dataPenghuni.get(nomorKartu + "milik " + (p != null ? p.getNama() : "?") + "dinonaktifkan.");
    } else {
        System.out.println(" ❌ Kartu tidak ditemukan.");
    }
}

public void aktifkanKartu(String nomorKartu){
    Kartu k = dataKartu.get(nomorKartu);
    if (k != null){
        k.setAktif (true);
        Penghuni p = dataPenghuni.get(nomorKartu);
        System.out.println(" 🔑 Kartu " + nomorKartu + " milik " + (p != null ? p.getNama() : "?") + "diaktifkan kmebali.");
    }
}

public void tampilStatusPenghuni(){
    System.out.println("\n ==================================================");
    System.out.println(" || STATUS PENGHUNI - " + namaKos);
    System.out.println("======================================================");
    if (dataPenghuni.isEmpty()){
         System.out.println(" Tidak ada penghuni terdaftar.");

    } else {
        for (Penghuni p : dataPenghuni.values()){
            String status = p.isDiDalam()? " 🏠 Di Dalam" : "🌙 Di Luar";
             System.out.println(" Kamar " + p.getNomorKamar() + " | " + p.getNama() + " | " + status + " | Plat: " + p.getPlatKnedaraan());

        }
    }
     System.out.println("========================================================");
    }

    public void tampilSemuaLog(){
        System.out.println("\n ===================================================");
        System.out.println (" ||     LOG AKSES  -   " + namaKos);
        System.out.println("=======================================================");

        if (logAkses.isEmpty())
            System.out.println ("Belum ada aktivitas");
    } else {
        for 
    }
    }
