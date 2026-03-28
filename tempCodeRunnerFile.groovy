import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Kartu {
    private String nomorKartu;
    private boolean aktif;

    public Kartu(String nomorKartu) {
        this.nomorKartu = nomorKartu;
        this.aktif = true;
    }

    public String getNomorKartu()       { return nomorKartu; }
    public boolean isAktif()            { return aktif; }
    public void setAktif(boolean aktif) { this.aktif = aktif; }

    public void tampilInfo() {
        System.out.println("  No. Kartu : " + nomorKartu);
        System.out.println("  Status    : " + (aktif ? "✅ Aktif" : "❌ Nonaktif"));
    }
}

class Penghuni {
    private String nama;
    private String nomorKamar;
    private String nomorKartu;
    private String platKendaraan;
    private boolean diDalam;
    private List<String> riwayat;

    public Penghuni(String nama, String nomorKamar, String nomorKartu, String platKendaraan) {
        this.nama          = nama;
        this.nomorKamar    = nomorKamar;   // FIX line 78: ditambahkan assign nomorKamar
        this.nomorKartu    = nomorKartu;
        this.platKendaraan = platKendaraan;
        this.diDalam       = true;
        this.riwayat       = new ArrayList<>();
    }

    public String getNama()          { return nama; }
    public String getNomorKamar()    { return nomorKamar; }  // FIX line 222: getter ditambahkan
    public String getNomorKartu()    { return nomorKartu; }
    public String getPlatKendaraan() { return platKendaraan; }
    public boolean isDiDalam()       { return diDalam; }

    public void setDiDalam(boolean diDalam) { this.diDalam = diDalam; }
    public void tambahRiwayat(String log)   { riwayat.add(log); }

    public void tampilRiwayat() {
        System.out.println("\n  🗒️ Riwayat Akses - " + nama + " (Kamar " + nomorKamar + ")");
        System.out.println("  ─────────────────────────────────────────");
        if (riwayat.isEmpty()) {
            System.out.println("  Belum ada riwayat.");
        } else {
            for (String log : riwayat) {
                System.out.println("  • " + log);
            }
        }
    }
}

class AksesLog {
    private String nama;
    private String nomorKamar;
    private String nomorKartu;
    private String platKendaraan;
    private String aksi;
    private LocalDateTime waktu;

    public AksesLog(String nama, String nomorKamar, String nomorKartu,
                    String platKendaraan, String aksi) {
        this.nama          = nama;
        this.nomorKamar    = nomorKamar;   // FIX line 78: ditambahkan assign nomorKamar
        this.nomorKartu    = nomorKartu;
        this.platKendaraan = platKendaraan;
        this.aksi          = aksi;
        this.waktu         = LocalDateTime.now();
    }

    public String toLogString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return aksi + " | " + waktu.format(fmt) + " | Plat: " + platKendaraan;
    }

    public void tampilLog() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String emoji = aksi.equals("MASUK") ? "🟢" : "🔴";
        // FIX line 85: waktu,format → waktu.format
        System.out.println("  " + emoji + " [" + waktu.format(fmt) + "] " +
            aksi + " | " + nama +
            " | Kamar " + nomorKamar +
            " | Kartu: " + nomorKartu +
            " | Plat: " + platKendaraan);
    }
}

class SmartDoor {
    private String namaKos;
    private Map<String, Penghuni> dataPenghuni;  // FIX line 91: dataPengguna → dataPenghuni
    private Map<String, Kartu> dataKartu;
    private List<AksesLog> logAkses;
    // FIX line 93: hapus } yang menutup class terlalu cepat
    // FIX line 95: hapus deklarasi logAkses duplikat

    public SmartDoor(String namaKos) {            // FIX line 97: namakos → namaKos
        this.namaKos      = namaKos;
        this.dataPenghuni = new HashMap<>();       // FIX line 98-99: inisialisasi benar
        this.dataKartu    = new HashMap<>();
        this.logAkses     = new ArrayList<>();
    }

    public void daftarPenghuni(Penghuni p) {
        Kartu k = new Kartu(p.getNomorKartu());
        dataPenghuni.put(p.getNomorKartu(), p);
        dataKartu.put(p.getNomorKartu(), k);
        System.out.println("  ✅ Penghuni baru terdaftar:");
        System.out.println("     Nama  : " + p.getNama());    // FIX line 107: : → ;
        System.out.println("     Kamar : " + p.getNomorKamar());
        System.out.println("     Kartu : " + p.getNomorKartu());
        System.out.println("     Plat  : " + p.getPlatKendaraan());
    }

    public void hapusPenghuni(String nomorKartu) {
        Penghuni p = dataPenghuni.get(nomorKartu);
        if (p == null) {                           // FIX line 117: nul → null
            System.out.println("  ❌ Penghuni dengan kartu " + nomorKartu + " tidak ditemukan.");
            return;
        }
        dataPenghuni.remove(nomorKartu);
        dataKartu.remove(nomorKartu);
        System.out.println("  🗑️  Data penghuni " + p.getNama() +
            " (Kamar " + p.getNomorKamar() + ") berhasil dihapus."); // FIX line 122: gwtNomorKamar → getNomorKamar
    }

    public void hapusSemuaPenghuni() {
        int jumlah = dataPenghuni.size();
        dataPenghuni.clear();
        dataKartu.clear();
        logAkses.clear();
        System.out.println("  🗑️  Semua data penghuni (" + jumlah + " orang) berhasil dihapus.");
        System.out.println("  📋 Log akses juga telah dibersihkan.");
    }

    public void tap(String nomorKartu) throws InterruptedException { // FIX line 128: thrws → throws
        Thread.sleep(500);                                            // FIX line 129: Theard → Thread
        System.out.println("\n  🔍 Scanning kartu: " + nomorKartu + "...");

        Kartu kartu = dataKartu.get(nomorKartu);
        if (kartu == null) {
            System.out.println("  ❌ AKSES DITOLAK — Kartu tidak dikenali!");
            return;
        }
        if (!kartu.isAktif()) {                   // FIX line 139: !kartu == null → !kartu.isAktif()
            System.out.println("  ❌ AKSES DITOLAK — Kartu dinonaktifkan!");
            return;
        }

        Penghuni penghuni = dataPenghuni.get(nomorKartu);  // FIX line 143: nomotKartu → nomorKartu
        String aksi = penghuni.isDiDalam() ? "KELUAR" : "MASUK";
        penghuni.setDiDalam(!penghuni.isDiDalam());         // FIX line 145: isiDiDalam → isDiDalam

        AksesLog log = new AksesLog(                        // FIX line 147: AksesLOg → AksesLog
            penghuni.getNama(),
            penghuni.getNomorKamar(),
            nomorKartu,
            penghuni.getPlatKendaraan(),
            aksi
        );
        logAkses.add(log);
        penghuni.tambahRiwayat(log.toLogString());

        System.out.println("  ✅ AKSES DITERIMA");
        log.tampilLog();
        if (aksi.equals("MASUK")) {
            System.out.println("  🚪 Pintu terbuka — Selamat datang, " + penghuni.getNama() + "!");
        } else {
            System.out.println("  🚪 Pintu terbuka — Selamat jalan, " + penghuni.getNama() + "!");
        }
    }

    // FIX line 166-171: perbaikan total method nonaktifkanKartu
    public void nonaktifkanKartu(String nomorKartu) {       // FIX line 166: Strig → String
        Kartu k = dataKartu.get(nomorKartu);
        if (k != null) {                                    // FIX line 167-170: syntax salah diperbaiki
            k.setAktif(false);
            Penghuni p = dataPenghuni.get(nomorKartu);
            System.out.println("  🔒 Kartu " + nomorKartu +
                " milik " + (p != null ? p.getNama() : "?") + " dinonaktifkan.");
        } else {
            System.out.println("  ❌ Kartu tidak ditemukan.");
        }
    }                                                       // FIX line 171: tambah } penutup

    public void aktifkanKartu(String nomorKartu) {
        Kartu k = dataKartu.get(nomorKartu);
        if (k != null) {
            k.setAktif(true);
            Penghuni p = dataPenghuni.get(nomorKartu);
            System.out.println("  🔓 Kartu " + nomorKartu +
                " milik " + (p != null ? p.getNama() : "?") + " diaktifkan kembali.");
        }
    }

    public void tampilStatusPenghuni() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║      STATUS PENGHUNI — " + namaKos);
        System.out.println("╠══════════════════════════════════════════════╣");
        if (dataPenghuni.isEmpty()) {
            System.out.println("  Tidak ada penghuni terdaftar.");
        } else {
            for (Penghuni p : dataPenghuni.values()) {
                String status = p.isDiDalam() ? "🏠 Di Dalam" : "🌙 Di Luar";
                System.out.println("  Kamar " + p.getNomorKamar() +
                    " | " + p.getNama() +
                    " | " + status +
                    " | Plat: " + p.getPlatKendaraan()); // FIX line 193: getPlatKnedaraan → getPlatKendaraan
            }
        }
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    public void tampilSemuaLog() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║         LOG AKSES — " + namaKos);
        System.out.println("╠══════════════════════════════════════════════╣");
        if (logAkses.isEmpty()) {
            System.out.println("  Belum ada aktivitas.");
        } else {
            for (AksesLog log : logAkses) {
                log.tampilLog();  // FIX line 206: tampilanLog → tampilLog
            }
        }
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    // FIX line 211: tambah if sebelum else
    public void tampilRiwayatPenghuni(String nomorKartu) {
        Penghuni p = dataPenghuni.get(nomorKartu);
        if (p != null) p.tampilRiwayat();
        else System.out.println("  ❌ Penghuni tidak ditemukan.");
    }
}

public class SmartDoorKos {
    public static void main(String[] args) throws InterruptedException {

        // FIX line 216-218: println + tutup kurung + tambah ;
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     SMART DOOR SYSTEM — KOS NCT 127         ║");
        System.out.println("║  Jl. Blok M, Kebayoran Baru, Jakarta Selatan║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        SmartDoor smartDoor = new SmartDoor("KOS NCT 127");

        System.out.println("======= DAFTARKAN PENGHUNI LAMA ======="); // FIX line 222
        smartDoor.daftarPenghuni(new Penghuni("Budi Santoso",  "101", "KRT-001", "W 3421 DT"));
        smartDoor.daftarPenghuni(new Penghuni("Rina Marlina",  "102", "KRT-002", "L 8823 MR"));
        smartDoor.daftarPenghuni(new Penghuni("Dani Prasetyo", "103", "KRT-003", "W 5566 DP"));

        System.out.println("\n======= AKTIVITAS PENGHUNI LAMA =======");
        smartDoor.tap("KRT-001");
        Thread.sleep(800);
        smartDoor.tap("KRT-002");
        Thread.sleep(800);
        smartDoor.tap("KRT-001");
        Thread.sleep(800);

        System.out.println("\n  [ Kartu Dani hilang — dinonaktifkan ]");
        smartDoor.nonaktifkanKartu("KRT-003");
        smartDoor.tap("KRT-003");

        smartDoor.tampilStatusPenghuni();
        smartDoor.tampilSemuaLog();
        smartDoor.tampilRiwayatPenghuni("KRT-001");

        System.out.println("\n======= PERGANTIAN PENGHUNI =======");
        System.out.println("  [ Semua penghuni lama pindah/keluar kos ]");
        smartDoor.hapusSemuaPenghuni();
        smartDoor.tampilStatusPenghuni();

        System.out.println("\n======= DAFTARKAN PENGHUNI BARU =======");
        smartDoor.daftarPenghuni(new Penghuni("Siti Aminah",   "101", "KRT-004", "W 9988 SA"));
        smartDoor.daftarPenghuni(new Penghuni("Fajar Nugroho", "102", "KRT-005", "L 4567 FN"));
        smartDoor.daftarPenghuni(new Penghuni("Maya Dewi",     "103", "KRT-006", "-"));

        System.out.println("\n======= AKTIVITAS PENGHUNI BARU =======");
        smartDoor.tap("KRT-004");
        Thread.sleep(800);
        smartDoor.tap("KRT-005");
        Thread.sleep(800);
        smartDoor.tap("KRT-004");
        Thread.sleep(800);
        smartDoor.tap("KRT-006");
        Thread.sleep(800);
        smartDoor.tap("KRT-006");

        System.out.println("\n  [ Fajar pindah kos — hapus data ]");
        smartDoor.hapusPenghuni("KRT-005");

        smartDoor.tampilStatusPenghuni();
        smartDoor.tampilSemuaLog();
        smartDoor.tampilRiwayatPenghuni("KRT-004");
        smartDoor.tampilRiwayatPenghuni("KRT-006");

        System.out.println("\n  Terima kasih telah menggunakan Smart Door System!");
    }
}