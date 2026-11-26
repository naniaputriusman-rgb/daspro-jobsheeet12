import java.util.Scanner;

public class RekapLaundry23 {

    static void inputData(int[][] data, String[] layanan, int[] harga, String[] hari, Scanner sc) {
        for (int i = 0; i < data.length; i++) {
            System.out.print("Masukkan nama layanan ke-" + (i + 1) + ": ");
            layanan[i] = sc.nextLine();
            System.out.print("Masukkan harga/kg untuk " + layanan[i] + ": ");
            harga[i] = sc.nextInt();

            for (int j = 0; j < data[i].length; j++) {
                System.out.print("Berat cucian " + layanan[i] + " hari " + hari[j] + " (kg): ");
                data[i][j] = sc.nextInt();
            }
            sc.nextLine(); // Membersihkan buffer newline
            System.out.println();
        }
    }

    static void tampilTabel(int[][] data, String[] layanan, int[] harga, String[] hari) {
        System.out.println("=== REKAP DATA LAUNDRY ===");
        System.out.printf("%-16s %-10s", "Layanan", "Harga/kg");
        for (String h : hari) {
            System.out.printf("%-9s", h);
        }
        System.out.println();

        for (int i = 0; i < data.length; i++) {
            System.out.printf("%-16s Rp%-8d", layanan[i], harga[i]);
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%-9d", data[i][j]);
            }
            System.out.println();
        }
    }

    static int totalPerLayanan(int[][] data, int idxLayanan) {
        int total = 0;
        for (int j = 0; j < data[idxLayanan].length; j++) {
            total += data[idxLayanan][j];
        }
        return total;
    }

    static void tampilTotalDanTertinggi(int[][] data, String[] layanan) {
        System.out.println("\n=== TOTAL BERAT PER LAYANAN ===");
        int idxMax = 0;
        int maxTotal = 0;

        for (int i = 0; i < data.length; i++) {
            int total = totalPerLayanan(data, i);
            System.out.println(layanan[i] + ": " + total + " kg");
            if (i == 0 || total > maxTotal) {
                maxTotal = total;
                idxMax = i;
            }
        }

        System.out.println("\nLayanan dengan total berat tertinggi: "
                + layanan[idxMax] + " (" + maxTotal + " kg)");
    }

    static int hariTersibuk(int[][] data) {
        int idxHariMax = 0;
        int maxBeratHari = 0;

        for (int j = 0; j < data[0].length; j++) {
            int totalHari = 0;
            for (int i = 0; i < data.length; i++) {
                totalHari += data[i][j];
            }
            if (j == 0 || totalHari > maxBeratHari) {
                maxBeratHari = totalHari;
                idxHariMax = j;
            }
        }
        return idxHariMax;
    }

    static void tampilPendapatan(int[][] data, String[] layanan, int[] harga) {
        System.out.println("\n=== PENDAPATAN PER LAYANAN (6 HARI) ===");
        int totalKeseluruhan = 0;

        for (int i = 0; i < data.length; i++) {
            int totalBerat = totalPerLayanan(data, i);
            int pendapatan = totalBerat * harga[i];
            totalKeseluruhan += pendapatan;
            System.out.println(layanan[i] + ": " + totalBerat + " kg x Rp" + harga[i] + " = Rp" + pendapatan);
        }
        System.out.println("Total pendapatan selama 6 hari: Rp" + totalKeseluruhan);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] hari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
        String[] layanan = new String[4];
        int[] harga = new int[4];
        int[][] data = new int[4][6];

        inputData(data, layanan, harga, hari, sc);
        tampilTabel(data, layanan, harga, hari);
        tampilTotalDanTertinggi(data, layanan);

        int idxSibuk = hariTersibuk(data);
        System.out.println("Hari tersibuk: " + hari[idxSibuk]);

        tampilPendapatan(data, layanan, harga);

        sc.close();
    }
}