import java.util.*;
/*
 * 1  S->L=R
 * 2  S->R
 * 3  L->*R
 * 4  L->a
 * 5  R->L
 * */
public class CLRParser {
    String table[][]= {{null,"s4","s5",null,"1","2","3"},
            {null,null,null,"accepted",null,null,null},
            {"s6",null,null,"r5",null,null,null},
            {null,null,null,"r2",null,null,null},
            {null,"s4","s5",null,null,"8","7"},
            {"r4",null,null,"r4",null,null,null},
            {null,"s11","s12",null,null,"10","9"},
            {"r3",null,null,"r3",null,null,null},
            {"r5",null,null,"r5",null,null,null},
            {null,null,null,"r1",null,null,null},
            {null,null,null,"r5",null,null,null},
            {null,"s11","s12",null,null,"10","13"},
            {null,null,null,"r4",null,null,null},
            {null,null,null,"r3",null,null,null}};
    Stack<String> stack;
    String input;
    List<String> cols;
    List<String> rows;
    List<String> prods;
    List<String> vars;
    String colls[]={"=","*","a","$","S","L","R"};
    String rowws[]={"0","1","2","3","4","5","6","7","8","9","10","11","12","13"};
    String prodds[]={"L=R","R","*R","a","L"};
    String varrs[]= {"S","S","L","L","R"};
    public CLRParser(String input) {
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
        int num=Integer.parseInt(str.substring(1,str.length()));
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
                int row=Integer.parseInt(stack.peek());
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
    	String input="***a=a";
    	System.out.println("Input string is: "+input);
        CLRParser lParser=new CLRParser(input+"$");//*a=a
        lParser.algorithm();

    }
}

