import java.util.Scanner;

class SinhVien {
    private String ma, hoTen, ngaySinh;

    public SinhVien(String ma, String hoTen, String ngaySinh) {
        this.ma = ma;
        this.hoTen = chuanHoaTen(hoTen);
        this.ngaySinh = chuanHoaNgay(ngaySinh);
    }

    private String chuanHoaTen(String s) {
        String[] a = s.trim().toLowerCase().split("\\s+");
        StringBuilder kq = new StringBuilder();

        for (String x : a) {
            kq.append(Character.toUpperCase(x.charAt(0)));
            kq.append(x.substring(1));
            kq.append(" ");
        }

        return kq.toString().trim();
    }

    private String chuanHoaNgay(String s) {
        String[] a = s.split("/");
        int d = Integer.parseInt(a[0]);
        int m = Integer.parseInt(a[1]);
        int y = Integer.parseInt(a[2]);

        return String.format("%02d/%02d/%04d", d, m, y);
    }

    public void hienThi() {
        System.out.println(ma + " " + hoTen + " " + ngaySinh);
    }
}

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        SinhVien[] ds = new SinhVien[n];

        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine();
            String ten = sc.nextLine();
            String ngay = sc.nextLine();

            ds[i] = new SinhVien(ma, ten, ngay);
        }

        for (SinhVien sv : ds) {
            sv.hienThi();
        }
    }
}