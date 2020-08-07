package work.chinadream.util;

/**
 * 随机生成手机号
 */
public class RandomPhone {
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    /**
     * 获取手机号
     * @return
     */
    public static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }
    private static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
}
