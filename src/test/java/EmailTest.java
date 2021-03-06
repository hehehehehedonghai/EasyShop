import com.shop.utils.MD5Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EmailTest
 *
 * @author Yarn
 * @create 2017/11/11/10:08
 */
public class EmailTest {
    public static boolean checkEmaile(String emaile){
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(emaile);
        //进行正则匹配
        return m.matches();
    }

    public static String str(String str) {
        return MD5Util.MD5EncodeUtf8(str);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(str("admin"));

    }
}
