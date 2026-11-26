import java.util.Scanner;

public class Kafe23 {
    public static void Menu(String namaPelanggan, boolean isMember) {
        System.out.println("Selamat datang, " + namaPelanggan + "!");

        if (isMember) {
            System.out.println("Anda adalah member, dapatkan diskon 10% untuk setiap pembelian!");
        }

        System.out.println("===== MENU RESTO KAFE =====");
        System.out.println("1. Kopi Hitam - Rp 15,000");
        System.out.println("2. Cappuccino - Rp 20,000");
        System.out.println("3. Latte - Rp 22,000");
        System.out.println("4. Teh Tarik - Rp 12,000");
        System.out.println("5. Roti Bakar - Rp 10,000");
        System.out.println("6. Mie Goreng - Rp 18,000");
        System.out.println("===========================");
        System.out.println("Silakan pilih menu yang Anda inginkan.");
    }

    public static int hitungTotalHarga23(int pilihanMenu, int banyakItem) {
        int[] hargaItems = {15000, 20000, 22000, 12000, 10000, 18000};

        int hargaTotal = hargaItems[pilihanMenu - 1] * banyakItem;
        return hargaTotal;
    }

    public static int hitungDiskon(int total, String kodePromo, boolean isMember) {
        int persen = 0;

        if (kodePromo.equals("DISKON50")) {
            persen = 50;
        } else if (kodePromo.equals("DISKON30")) {
            persen = 30;
        } else if (!kodePromo.isEmpty()) {
            System.out.println("Kode promo invalid");
        }

        // 4b: member mendapat tambahan diskon 10%
        if (isMember) {
            persen += 10;
        }

        // kalikan dulu baru bagi, supaya hasil int tidak terpotong
        return total * persen / 100;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan nama pelanggan: ");
        String nama = sc.nextLine();
        System.out.print("Apakah member? (y/n): ");
        boolean isMember = sc.nextLine().equalsIgnoreCase("y");

        Menu(nama, isMember);

        int totalHarga = 0;

        while (true) {
            System.out.print("\nMasukkan nomor menu yang ingin Anda pesan: ");
            int pilihanMenu = sc.nextInt();

            if (pilihanMenu < 1 || pilihanMenu > 6) {
                System.out.println("Menu tidak tersedia");
                continue;
            }

            System.out.print("Masukkan jumlah item yang ingin dipesan: ");
            int banyakItem = sc.nextInt();

            totalHarga += hitungTotalHarga23(pilihanMenu, banyakItem);

            System.out.print("Pesan menu lain? (y/n): ");
            String lagi = sc.next();
            if (lagi.equalsIgnoreCase("n")) {
                break;
            }
        }
        sc.nextLine();

        System.out.print("Masukkan kode promo (kosongkan jika tidak ada): ");
        String kodePromo = sc.nextLine();

        int diskon = hitungDiskon(totalHarga, kodePromo, isMember);

        System.out.println("\nBesar diskon: Rp" + diskon);
    }
}
