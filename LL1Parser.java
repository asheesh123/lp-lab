/*
 * G->E$
 * E->TK
 * K->+TK|null
 * T->FH
 * H->*FH|null
 * F->(E)|a
 * */
import java.util.*;
public class LL1Parser {
    String input="";
    int index=-1;
    ArrayList<String> nonTers=new ArrayList<>();
    ArrayList<String> terms=new ArrayList<>();
    Stack<String> stack=new Stack<>();
    String table[][]={{"E$",null,null,"E$",null,null},
            {"TK",null,null,"TK",null,""},
            {null,"+TK",null,null,"",""},
            {"FH",null,null,"FH",null,null},
            {null,"","*FH",null,"",""},
            {"a",null,null,"(E)",null,null}};
    public LL1Parser(String str){
        nonTers.add("G");nonTers.add("E");nonTers.add("K");nonTers.add("T");nonTers.add("H");nonTers.add("F");
        terms.add("a");terms.add("+");terms.add("*");terms.add("(");terms.add(")");terms.add("$");
        this.input=str;
    }
    public void pushRule(String rule){
        for(int i=rule.length()-1;i>=0;i--){
            stack.push(rule.charAt(i)+"");
        }
    }
    public void algorithm(){
        stack.push(input.charAt(0)+"");
        stack.push("G");
        String token=read();
        String top=null;
        do{
            top=stack.pop();
            try{
                if(this.nonTers.contains(top)){
                    String rule=this.table[this.nonTers.indexOf(top)][this.terms.indexOf(token)];
                    pushRule(rule);
                }
                else if(this.terms.contains(top)){
                    if(!top.equals(token)){
                        System.out.println("Error1");
                        System.exit(0);
                    }
                    else{
                        System.out.println("matching:"+token);
                        token=read();
                    }
                }
                else{
                    System.out.println("Error2");
                    System.exit(0);
                }
            }
            catch (Exception e){
                System.out.println("Error token:"+token);
                System.exit(0);
            }
            if(token.equals("$")){
                break;
            }
        }while (true);
        if(token.equals("$")){
            System.out.println("Accepted");
        }
        else{
            System.out.println("Not Accepted");
        }
    }
    String read(){
        index++;
        char ch=input.charAt(index);
        String str=String.valueOf(ch);
        return str;
    }
    public static void main(String args[]){
        LL1Parser l=new LL1Parser("a+a$");
        l.algorithm();
    }
}
