import java.math.BigInteger;

/**
 * 分库分表的
 */
public class HashUtil {
    private static final BigInteger INIT32 = new BigInteger("811c9dc5", 16);
    private static final BigInteger PRIME32 = new BigInteger("01000193", 16);
    private static final BigInteger MOD32 = (new BigInteger("2")).pow(32);

    public HashUtil() {
    }

    public static int fnv1_31(String str) {
        BigInteger bi32 = fnv1_32(str.getBytes());
        return bi32.intValue() & 2147483647;
    }

    public static int fnv1_31(long n) {
        return fnv1_31(String.valueOf(n));
    }

    private static BigInteger fnv1_32(byte[] data) {
        BigInteger hash = INIT32;
        byte[] var2 = data;
        int var3 = data.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            hash = hash.multiply(PRIME32).mod(MOD32);
            hash = hash.xor(BigInteger.valueOf((long) (b & 255)));
        }

        return hash;
    }

    public static void main(String[] args) {
//        String orderId = "3723352" + 10;
//        int hash = HashUtil.fnv1_31(orderId) % 50;
//        System.out.println(hash);
//
//        // -2021070515396707
//        orderId = "2021070515396707";
//        hash = HashUtil.fnv1_31(orderId) % 50;
//        System.out.println(hash);
        String a = "123${";

        int i = a.indexOf("${");
        int ab = a.indexOf("$");
        System.out.println(ab);
        System.out.println(i);


    }
}
