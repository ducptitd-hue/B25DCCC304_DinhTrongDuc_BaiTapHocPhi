import java.util.*;

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String s) {
        super(s);
    }
}

abstract class SinhVien {
    protected String ma, he;
    protected int tinChi;

    public SinhVien(String ma, String he, int tinChi) {
        this.ma = ma;
        this.he = he;
        this.tinChi = tinChi;
    }

    public abstract long hocPhi();
}

class ChinhQuy extends SinhVien {
    public ChinhQuy(String ma, int tc) {
        super(ma, "CQ", tc);
    }

    public long hocPhi() {
        return tinChi * 450000L;
    }
}

class ChatLuongCao extends SinhVien {
    public ChatLuongCao(String ma, int tc) {
        super(ma, "CLC", tc);
    }

    public long hocPhi() {
        return tinChi * 675000L;
    }
}

class LienThong extends SinhVien {
    public LienThong(String ma, int tc) {
        super(ma, "LT", tc);
    }

    public long hocPhi() {
        return tinChi * 450000L + 2000000L;
    }
}

class QuanLyHocPhi {
    private ArrayList<SinhVien> ds = new ArrayList<>();
    private HashSet<String> danhSachMa = new HashSet<>();
    private ArrayList<String> loi = new ArrayList<>();

    public void them(String he, String ma, String tc)
            throws DuLieuKhongHopLeException {

        if (!he.equals("CQ") && !he.equals("CLC")
                && !he.equals("LT")) {
            throw new DuLieuKhongHopLeException(ma);
        }

        int tinChi;

        try {
            tinChi = Integer.parseInt(tc);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException(ma);
        }

        if (tinChi < 1 || tinChi > 30) {
            throw new DuLieuKhongHopLeException(ma);
        }

        if (danhSachMa.contains(ma)) {
            throw new DuLieuKhongHopLeException(ma);
        }

        SinhVien sv;

        if (he.equals("CQ")) {
            sv = new ChinhQuy(ma, tinChi);
        } else if (he.equals("CLC")) {
            sv = new ChatLuongCao(ma, tinChi);
        } else {
            sv = new LienThong(ma, tinChi);
        }

        ds.add(sv);
        danhSachMa.add(ma);
    }

    public void hienThi() {
        for (String s : loi) {
            System.out.println("INVALID " + s);
        }

        ds.sort((a, b) -> {
            if (a.hocPhi() != b.hocPhi()) {
                return Long.compare(b.hocPhi(), a.hocPhi());
            }
            return a.ma.compareTo(b.ma);
        });

        long tong = 0;

        for (SinhVien sv : ds) {
            System.out.println(
                    sv.ma + " " + sv.he + " " + sv.hocPhi());
            tong += sv.hocPhi();
        }

        System.out.println("TONG " + tong);
    }

    public void ghiLoi(String ma) {
        loi.add(ma);
    }
}

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        QuanLyHocPhi ql = new QuanLyHocPhi();

        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String ma = sc.next();
            String tc = sc.next();

            try {
                ql.them(he, ma, tc);
            } catch (DuLieuKhongHopLeException e) {
                ql.ghiLoi(ma);
            }
        }

        ql.hienThi();
    }
}