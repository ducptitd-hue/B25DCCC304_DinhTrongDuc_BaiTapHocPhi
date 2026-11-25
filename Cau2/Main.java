import java.util.Scanner;

abstract class SinhVien {
    protected String ma;
    protected String he;
    protected int soTinChi;

    public SinhVien(String ma, String he, int soTinChi) {
        this.ma = ma;
        this.he = he;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();
}

class ChinhQuy extends SinhVien {
    public ChinhQuy(String ma, int tc) {
        super(ma, "CQ", tc);
    }

    @Override
    public long hocPhi() {
        return soTinChi * 450000L;
    }
}

class ChatLuongCao extends SinhVien {
    public ChatLuongCao(String ma, int tc) {
        super(ma, "CLC", tc);
    }

    @Override
    public long hocPhi() {
        return (long) (soTinChi * 450000L * 1.5);
    }
}

class LienThong extends SinhVien {
    public LienThong(String ma, int tc) {
        super(ma, "LT", tc);
    }

    @Override
    public long hocPhi() {
        return soTinChi * 450000L + 2000000L;
    }
}

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        SinhVien[] ds = new SinhVien[n];

        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String ma = sc.next();
            int tc = sc.nextInt();

            if (he.equals("CQ")) {
                ds[i] = new ChinhQuy(ma, tc);
            } else if (he.equals("CLC")) {
                ds[i] = new ChatLuongCao(ma, tc);
            } else {
                ds[i] = new LienThong(ma, tc);
            }
        }

        for (SinhVien sv : ds) {
            System.out.println(
                    sv.ma + " " + sv.he + " " + sv.hocPhi());
        }
    }
}