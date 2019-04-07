import java.io.*;
import java.util.*;
public class LexialAnalyzer {
    public static void generateTokens(String str){
        String s="";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '||str.charAt(i)=='\t'||str.charAt(i)=='.'||str.charAt(i)=='*'||str.charAt(i)==';'||str.charAt(i)=='{'||str.charAt(i)=='}'||str.charAt(i)=='('||str.charAt(i)==')'||str.charAt(i)==','||str.charAt(i)=='='||str.charAt(i)=='\''||str.charAt(i)=='\"'||str.charAt(i)=='\\'||str.charAt(i)=='\n') {
                if(s.length()>0&&str.charAt(i)!='\n')
                    System.out.println(s);
                if(str.charAt(i)!='\n'&&str.charAt(i)!='\t'&&str.charAt(i)!=' '){
                    System.out.println(str.charAt(i));
                }
                s="";
            }else{
                s+=str.charAt(i);
            }
        }
        System.out.println(s);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\Asheesh\\eclipse-workspace\\LPLAB\\src\\LexialAnalyzer.java"));
        String s="";
        while((s=br.readLine())!=null){
            generateTokens(s);
        }
        br.close();
        sc.close();
    }
}
