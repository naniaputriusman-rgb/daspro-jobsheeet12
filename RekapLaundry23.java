import java.util.Scanner;

public class RekapLaundry23 {

    static void inputData(int[][] data, String[] layanan, int[] harga, Scanner sc) {
        for (int i = 0; i < data.length; i++) {
            System.out.print("Masukkan nama layanan ke-" + (i + 1) + ": ");
            layanan[i] = sc.nextLine();
            System.out.print("Masukkan harga/kg untuk " + layanan[i] + ": ");
            harga[i] = sc.nextInt();

            for (int j = 0; j < data[i].length; j++) {
                System.out.print("Berat cucian " + layanan[i] + " pada Hari ke-" + (j + 1) + " (kg): ");
                data[i][j] = sc.nextInt();
            }
            sc.nextLine(); // Membersihkan buffer newline
            System.out.println();
        }
    }

    static void tampilTabel(int[][] data, String[] layanan, int[] harga) {
        System.out.println("=== REKAP DATA LAUNDRY ===");
        System.out.printf("%-16s %-10s", "Layanan", "Harga/kg");
        for (int j = 0; j < data[0].length; j++) {
            System.out.printf("%-11s", "Hari ke-" + (j + 1));
        }
        System.out.println();

        for (int i = 0; i < data.length; i++) {
            System.out.printf("%-16s Rp%-8d", layanan[i], harga[i]);
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%-11d", data[i][j]);
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

    static void tampilPendapatan(int[][] data, String[] layanan, int[] harga, int jmlHari) {
        System.out.println("\n=== PENDAPATAN PER LAYANAN (" + jmlHari + " HARI) ===");
        int totalKeseluruhan = 0;

        for (int i = 0; i < data.length; i++) {
            int totalBerat = totalPerLayanan(data, i);
            int pendapatan = totalBerat * harga[i];
            totalKeseluruhan += pendapatan;
            System.out.println(layanan[i] + ": " + totalBerat + " kg x Rp" + harga[i] + " = Rp" + pendapatan);
        }
        System.out.println("Total pendapatan selama " + jmlHari + " hari: Rp" + totalKeseluruhan);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah layanan: ");
        int jmlLayanan = sc.nextInt();
        System.out.print("Masukkan jumlah hari: ");
        int jmlHari = sc.nextInt();
        sc.nextLine(); // Membersihkan buffer newline

        String[] layanan = new String[jmlLayanan];
        int[] harga = new int[jmlLayanan];
        int[][] data = new int[jmlLayanan][jmlHari];

        inputData(data, layanan, harga, sc);
        tampilTabel(data, layanan, harga);
        tampilTotalDanTertinggi(data, layanan);

        int idxSibuk = hariTersibuk(data);
        System.out.println("Hari tersibuk: Hari ke-" + (idxSibuk + 1));

        tampilPendapatan(data, layanan, harga, jmlHari);

        sc.close();
    }
}