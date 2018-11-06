package club.ihere.wechat.common.util;

import java.security.MessageDigest;

/**
 * @author: fengshibo
 * @date: 2018/11/6 17:07
 * @description:
 */
public class MD5Utils {

    private static final String hexDigIts[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * MD5加密
     *
     * @param origin      字符
     * @param charSetName 编码
     * @return
     */
    public static String MD5Encode(String origin, String charSetName) {
        String resultString = null;
        resultString = getString(origin, charSetName, resultString);
        return resultString;
    }

    /**
     * MD5加密 默认字符集
     *
     * @param origin 字符
     * @return
     */
    public static String MD5EncodeUTF8(String origin) {
        String charSetName = "utf8";
        String resultString = null;
        resultString = getString(origin, charSetName, resultString);
        return resultString;
    }

    private static String getString(String origin, String charSetName, String resultString) {
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == charSetName || "".equals(charSetName)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charSetName)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

}
