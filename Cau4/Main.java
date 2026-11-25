import java.util.*;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ChinhSachCon implements MienGiam {
    public long tinhGiam(long hocPhi) {
        return hocPhi * 50 / 100;
    }
}

class HoNgheo implements MienGiam {
    public long tinhGiam(long hocPhi) {
        return hocPhi * 70 / 100;
    }
}

class HocBong implements MienGiam {
    public long tinhGiam(long hocPhi) {
        return Math.min(hocPhi, 3000000L);
    }
}

abstract class SinhVien {
    protected String ma, he;
    protected int tinChi;
    protected MienGiam chinhSach;

    public SinhVien(String ma, String he, int tc) {
        this.ma = ma;
        this.he = he;
        this.tinChi = tc;
        this.chinhSach = null;
    }

    public abstract long hocPhi();

    public long tienGiam() {
        if (chinhSach == null) {
            return 0;
        }
        return chinhSach.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - tienGiam();
    }
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

public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<SinhVien> ds = new ArrayList<>();
        HashMap<String, SinhVien> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String ma = sc.next();
            int tc = sc.nextInt();

            SinhVien sv;

            if (he.equals("CQ")) {
                sv = new ChinhQuy(ma, tc);
            } else if (he.equals("CLC")) {
                sv = new ChatLuongCao(ma, tc);
            } else {
                sv = new LienThong(ma, tc);
            }

            ds.add(sv);
            map.put(ma, sv);
        }

        int m = sc.nextInt();

        ArrayList<String> loi = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String ma = sc.next();
            String cs = sc.next();

            if (!map.containsKey(ma)) {
                loi.add(ma);
                continue;
            }

            SinhVien sv = map.get(ma);

            if (cs.equals("CS")) {
                sv.chinhSach = new ChinhSachCon();
            } else if (cs.equals("HN")) {
                sv.chinhSach = new HoNgheo();
            } else if (cs.equals("HB")) {
                sv.chinhSach = new HocBong();
            }
        }

        for (String ma : loi) {
            System.out.println("NOT FOUND " + ma);
        }

        String[] cacHe = { "CQ", "CLC", "LT" };

        int[] dem = new int[3];
        long[] tong = new long[3];

        for (SinhVien sv : ds) {
            long hp = sv.hocPhi();
            long giam = sv.tienGiam();
            long nop = sv.phaiNop();

            System.out.println(
                    sv.ma + " " + sv.he + " "
                            + hp + " " + giam + " " + nop);

            for (int i = 0; i < 3; i++) {
                if (sv.he.equals(cacHe[i])) {
                    dem[i]++;
                    tong[i] += nop;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (dem[i] > 0) {
                System.out.println(
                        cacHe[i] + " " + dem[i] + " " + tong[i]);
            }
        }
    }
}
