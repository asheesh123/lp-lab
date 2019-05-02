import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
/*Grammar
(1) S -> AA
(2) A -> aA
(3) A -> b
*/
/*LR(0) Parsing Table
  | a   b      $    | S  A
++++++++++++++++++++++++++
 0|s36 s47          | 1  2
 1|         accept  |
 2|s36 s47          |    5
36|s36 s47          |   89
47|r3  r3     r3    |
 5|           r1    |
89|r2  r2     r2    |
++++++++++++++++++++++++++
*/
public class LALRParser {
    String table[][] = {
            {"s36","s47",null,"1","2"},
            {null,null,"accepted",null,null},
            {"s36","s47",null,null,"5"},
            {"s36","s47",null,null,"89"},
            {"r3","r3","r3",null,null},
            {null,null,"r1",null,null},
            {"r2","r2","r2",null,null},
    };
    Stack<String> stack;
    String input;
    List<String> cols;
    List<String> rows;
    List<String> prods;
    List<String> vars;
    String colls[]={"a","b","$","S","A"};
    String rowws[]={"0","1","2","36","47","5","89"};
    String prodds[]={"AA","aA","b"};
    String varrs[]= {"S","A","A"};
    public LALRParser(String input) {
        stack=new Stack<>();
        stack.push("$");
        stack.push("0");
        this.input=input;
        cols=new ArrayList<>();
        cols.addAll(Arrays.asList(colls));
        rows=new ArrayList<>();
        rows.addAll(Arrays.asList(rowws));
        prods=new ArrayList<>();
        prods.addAll(Arrays.asList(prodds));
        vars=new ArrayList<>();
        vars.addAll(Arrays.asList(varrs));
    }
    void addToStack(String str,String inp){
        char ch=str.charAt(0);
        int num=Integer.parseInt(str.substring(1,str.length()));//4
        switch (ch){
            case 's':
                stack.push(inp);
                stack.push(num+"");
                input=input.substring(1,input.length());
                break;
            case 'r':
                int len=prods.get(num-1).length();
                for(int i=0;i<2*len;i++){
                    stack.pop();
                }
                
                int row=rows.indexOf(stack.peek());
                int col=cols.indexOf(vars.get(num-1));
                stack.push(vars.get(num-1));
                stack.push(table[row][col]);
                break;
        }
    }
    void algorithm() {
        while(input.length()!=0){
            try{
                String str=input.charAt(0)+"";
                int c=cols.indexOf(str);
                int r=rows.indexOf(stack.peek());
                String ele=table[r][c];
                if(ele.equals("accepted")){
                    System.out.println("Input String is accepted");
                    break;
                }
                addToStack(ele,input.charAt(0)+"");
            }
            catch(Exception e){
                System.out.println("Input String is not accepted");
                break;
            }
        }
    }
    public static void main(String[] args) {
    	String input="abb$";
    	System.out.println("input string is: "+input);
        LALRParser lParser=new LALRParser(input);
        lParser.algorithm();

    }
}

