import java.util.ArrayList;
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
    private boolean diDalam;
    private List<String> riwayat;

public Penghuni (String nama, String nomorKartu, String platKendaraan, String nomorKamar){
    this.nama = nama;
    this.nomorKartu = nomorKartu;
    this.platKendaraan = platKendaraan;
    this.diDalam = true;
    this.nomorKamar = nomorKamar;
    this.riwayat = new ArrayList<>();
}

public String getNama() {return nama; }
public String getNomorKartu() {return nomorKartu; }
public String getPlatKendaraan() {return platKendaraan; }
public String getNomorKamar () {return nomorKamar;}
public boolean isDiDalam() {return diDalam; }

public void setDiDalam(boolean diDalam) { this.diDalam = diDalam; }
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

class AksesLog{
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
            this.nomorKamar = nomorKamar;

    }
    public String toLogString(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return aksi + " | " + waktu.format(fmt) + " | Plat: " + platKendaraan;
    }

    public void tampilLog (){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern ("dd-MM-yyyy HH:mm:ss");
        String emoji = aksi.equals ("MASUK") ? "🟢" : "🔴";
        System.out.println(" " + emoji + "[" + waktu.format(fmt) + "]" + aksi + " | " + nama + " | Kamar " + nomorKamar + " | Kartu " + nomorKartu + " | Plat " + platKendaraan);

    }
}

class SmartDoor {
    private String namaKos;
    private Map<String, Penghuni> dataPenghuni;
    private Map<String, Kartu> dataKartu;
   
    private List<AksesLog> logAkses;
     
        public SmartDoor(String namaKos){
            this.namaKos = namaKos;
            this.dataPenghuni = new HashMap<>();
            this.dataKartu = new HashMap<>();
            this.logAkses = new ArrayList<>();
        }

    public void daftarPenghuni (Penghuni p){
        Kartu k = new Kartu (p.getNomorKartu());
        dataPenghuni.put (p.getNomorKartu(), p);
        dataKartu.put(p.getNomorKartu(), k);
        System.out.println ("✅ Penghuni baru terdaftar: ");
        System.out.println (" Nama : " + p.getNama());
        System.out.println (" Kamar : " + p.getNomorKamar());
        System.out.println (" Kartu :" + p.getNomorKartu());
        System.out.println (" Plat : " + p.getPlatKendaraan());
    }

public void hapusPenghuni(String nomorKartu){
    Penghuni p = dataPenghuni.get (nomorKartu);
    if (p == null   ){
        System.out.println ("❌ Penghuni dengan kartu " + nomorKartu + " tidak ditemukan.");
        return;
    }
    dataPenghuni.remove(nomorKartu);
    dataKartu.remove (nomorKartu);
    System.out.println (" 🗒️ Data penghuni " + p.getNama() + "(Kamar " + p.getNomorKamar () + ") berhasil dihapus.");
}

public void hapusSemuaPenghuni(){
    int jumlah = dataPenghuni.size();
    dataPenghuni.clear();
    dataKartu.clear();
    logAkses.clear();
    System.out.println (" 📊 Semua data penghuni (" + jumlah + " orang) berhasil dihapus.");
    System.out.println (" 🗒️ Log akses juga telah dibersihkan.");

}
    public void tap (String nomorKartu) throws InterruptedException {
        Thread.sleep(500);
        System.out.println ("\n Scanning kartu: " + nomorKartu + "...");

        Kartu kartu = dataKartu.get(nomorKartu);
        if (kartu == null) {
            System.out.println(" ❌ AKSES DITOLAK -- Kartu tidak dikenal!");
            return;
        }
        if (!kartu.isAktif()){
            System.out.println(" ❌ AKSES DITOLAK -- Kartu dinonaktifkan!");
            return;
        }

        Penghuni penghuni = dataPenghuni.get(nomorKartu);
        String aksi = penghuni.isDiDalam() ? "KELUAR" : "MASUK" ;
        penghuni.setDiDalam(!penghuni.isDiDalam());

        AksesLog log = new AksesLog(
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

    public void nonaktifkanKartu (String nomorKartu){
        Kartu k = dataKartu.get(nomorKartu);
        if (k != null){
        k.setAktif(false);
        Penghuni p = dataPenghuni.get(nomorKartu);
        System.out.println (nomorKartu + "milik " + (p != null ? p.getNama() : "?") + "dinonaktifkan.");
    
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
             System.out.println(" Kamar " + p.getNomorKamar() + " | " + p.getNama() + " | " + status + " | Plat: " + p.getPlatKendaraan());

        }
    }
     System.out.println("========================================================");
    }

    public void tampilSemuaLog(){
        System.out.println("\n ====================================================");
        System.out.println(" || LOG AKSES -- " + namaKos);
        System.out.println("========================================================");
        if (logAkses.isEmpty()){
            System.out.println( " Belum ada aktivitas.");
        } else {
            for (AksesLog log : logAkses){
                log.tampilLog();
            }
    }

    System.out.println("===============================================================");
    }
public void tampilRiwayatPenghuni(String nomorKartu){
    Penghuni p = dataPenghuni.get(nomorKartu);
    if (p != null){
        p.tampilRiwayat();
    }else {
System.out.println (" ❌ Penghuni tidak ditemukan.");
    }
    }
}

public class SmartDoorKos {
    public static void main (String[] args) throws InterruptedException {
        System.out.println ("======================================================");
        System.out.println ("||            SMART DOOR SYSTEM -- KOS NCT 127       ||");
        System.out.println ("||      Jl. Blok M, Kebayoran Baru, Jakarta Selatan     ");
        System.out.println ("======================================================");

        SmartDoor smartDoor = new SmartDoor ("KOS NCT 127");

        System.out.print ("======= DAFTARKAN PENGHUNI LAMA =======");
        smartDoor.daftarPenghuni (new Penghuni("Lee Jeno",  "128", "B 213 SZA", "B 6"));
        smartDoor.daftarPenghuni(new Penghuni("Lina Mukerji",  "102", "AA 345 WQT", "B 19"));
        smartDoor.daftarPenghuni(new Penghuni("Bruno Mars", "103", "F 567 TYO", "B 4"));
 
        System.out.println("\n======= AKTIVITAS PENGHUNI LAMA =======");
        smartDoor.tap("B 213 SZA"); 
        Thread.sleep(800);
        smartDoor.tap("AA 345 WQT");
        Thread.sleep(800);
        smartDoor.tap("B 213 SZA");
        Thread.sleep(800);
 
        System.out.println("\n  [ Kartu Lina Mukerji hilang — dinonaktifkan ]");
        smartDoor.nonaktifkanKartu("F 567 TYO");
        smartDoor.tap("F 567 TYO"); 
 
        smartDoor.tampilStatusPenghuni();
        smartDoor.tampilSemuaLog();
        smartDoor.tampilRiwayatPenghuni("B 213 SZA");
 
        System.out.println("\n======= PERGANTIAN PENGHUNI =======");
        System.out.println("  [ Semua penghuni lama pindah/keluar kos ]");
        smartDoor.hapusSemuaPenghuni();
        smartDoor.tampilStatusPenghuni();
 
        System.out.println("\n======= DAFTARKAN PENGHUNI BARU =======");
        smartDoor.daftarPenghuni(new Penghuni("Tayo",   "021", "S 123 LK", "C 21"));
        smartDoor.daftarPenghuni(new Penghuni("Luna Maya", "128", "B 3324 EKE", "C 8"));
        smartDoor.daftarPenghuni(new Penghuni("Aldi Taher", "065", "A 897 RK", "C 1"));
 
        System.out.println("\n======= AKTIVITAS PENGHUNI BARU =======");
        smartDoor.tap("S 123 LK");
        Thread.sleep(800);
        smartDoor.tap("B 3324 EKE");
        Thread.sleep(800);
        smartDoor.tap("S 123 LK");
        Thread.sleep(800);
        smartDoor.tap("A 897 RK");
        Thread.sleep(800);
        smartDoor.tap("A 897 RK");
 
        System.out.println("\n  [ Luna Maya pindah kos — hapus data ]");
        smartDoor.hapusPenghuni("B 3324 EKE");
 
        smartDoor.tampilStatusPenghuni();
        smartDoor.tampilSemuaLog();
        smartDoor.tampilRiwayatPenghuni("021");
        smartDoor.tampilRiwayatPenghuni("065");
 
        System.out.println("\n  Terima kasih telah menggunakan Smart Door System!");
         }
    }
