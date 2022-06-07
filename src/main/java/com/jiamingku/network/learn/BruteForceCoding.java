package com.jiamingku.network.learn;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BruteForceCoding {
    private static byte byteVal = 101; // one hundred and one
    private static short shortVal = 10001; // ten thousand and one
    private static int intVal = 100000001; // one hundred million and one
    private static long longVal = 1000000000001L;// one trillion and one

    private final static int BSIZE = Byte.SIZE / Byte.SIZE;
    private final static int SSIZE = Short.SIZE / Byte.SIZE;
    private final static int ISIZE = Integer.SIZE / Byte.SIZE;
    private final static int LSIZE = Long.SIZE / Byte.SIZE;

    private final static int BYTEMASK = 0xFF; // 8 bits

    public static String byteArrayToDecimalString(byte[] bArray) {
        StringBuilder rtn = new StringBuilder();
        for (byte b : bArray) {
            rtn.append(b & BYTEMASK).append(" ");
        }
        return rtn.toString();
    }

    //    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(null);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


    // Warning:  Untested preconditions (e.g., 0 <= size <= 8)
    public static int encodeIntBigEndian(byte[] dst, long val, int offset, int size) {
        for (int i = 0; i < size; i++) {
            dst[offset++] = (byte) (val >> ((size - i - 1) * Byte.SIZE));
        }
        return offset;
    }

    // Encoded message: 101 39 17 5 245 225 1 0 0 0 232 212 165 16 1
    @Test
    public void test1() {


        try {
            /**
             *  private static byte byteVal = 101; // one hundred and one
             *     private static short shortVal = 10001; // ten thousand and one
             *     private static int intVal = 100000001; // one hundred million and one
             *     private static long longVal = 1000000000001L;// one trillion and one
             */
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(101);
            dataOutputStream.writeShort(10001);
            dataOutputStream.writeInt(100000001);
            dataOutputStream.writeLong(1000000000001L);
            byte[] bbb = byteArrayOutputStream.toByteArray();
            System.out.println("bbb = " + bbb);

            String s = byteArrayToDecimalString(bbb);
            System.out.println("s = " + s);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Warning:  Untested preconditions (e.g., 0 <= size <= 8)
    public static long decodeIntBigEndian(byte[] val, int offset, int size) {
        long rtn = 0;
        for (int i = 0; i < size; i++) {
            rtn = (rtn << Byte.SIZE) | ((long) val[offset + i] & BYTEMASK);
        }
        return rtn;
    }

    public static void main(String[] args) {
        byte[] message = new byte[BSIZE + SSIZE + ISIZE + LSIZE];
        // Encode the fields in the target byte array
        int offset = encodeIntBigEndian(message, byteVal, 0, BSIZE);
        offset = encodeIntBigEndian(message, shortVal, offset, SSIZE);
        offset = encodeIntBigEndian(message, intVal, offset, ISIZE);
        encodeIntBigEndian(message, longVal, offset, LSIZE);
        System.out.println("Encoded message: " + byteArrayToDecimalString(message));

        // Decode several fields
        long value = decodeIntBigEndian(message, BSIZE, SSIZE);
        System.out.println("Decoded short = " + value);
        value = decodeIntBigEndian(message, BSIZE + SSIZE + ISIZE, LSIZE);
        System.out.println("Decoded long = " + value);

        // Demonstrate dangers of conversion
        offset = 4;
        value = decodeIntBigEndian(message, offset, BSIZE);
        System.out.println("Decoded value (offset " + offset + ", size " + BSIZE + ") = " + value);
        byte bVal = (byte) decodeIntBigEndian(message, offset, BSIZE);
        System.out.println("Same value as byte = " + bVal);
    }

}
