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

    public static void main(String[] args) throws InterruptedException {
        //System.out.println( checkEmaile("123456@qq.com123@123@.com"));
        String str = "/*\n" +
                "*      \n" +
                "*          ┌─┐       ┌─┐\n" +
                "*       ┌──┘ ┴───────┘ ┴──┐\n" +
                "*       │                 │\n" +
                "*       │       ───       │\n" +
                "*       │  ─┬┘       └┬─  │\n" +
                "*       │                 │\n" +
                "*       │       ─┴─       │\n" +
                "*       │                 │\n" +
                "*       └───┐         ┌───┘\n" +
                "*           │         │\n" +
                "*           │         │\n" +
                "*           │         │\n" +
                "*           │         └──────────────┐\n" +
                "*           │                        │\n" +
                "*           │                        ├─┐\n" +
                "*           │                        ┌─┘    \n" +
                "*           │                        │\n" +
                "*           └─┐  ┐  ┌───────┬──┐  ┌──┘         \n" +
                "*             │ ─┤ ─┤       │ ─┤ ─┤         \n" +
                "*             └──┴──┘       └──┴──┘ \n" +
                "*                    神兽保佑\n" +
                "*                  \n" +
                "*/";

        for (int i = 0; i < str.length(); i++)
        {
            System.out.print(str.charAt(i));
            try {
                //输一个停一秒
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
