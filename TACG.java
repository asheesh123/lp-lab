import java.io.*;
import java.util.*;
public class TACG {
	static int index=1;
	public static void main(String[] args) {
		System.out.println("this solution is for single digit arithmetic operational expression");
		TACG ob=new TACG();
		String input="(5 * 3 + (4 + 2 % (2 * 8)))";
		System.out.println("input expresion: "+input);
		input=input.replaceAll(" ", "");
		Node root=ob.algorithm(input);
		ob.dfs(root);
	}
	void dfs(Node root) {
		if(isOperator(root.op)) {
			dfs(root.left);
			dfs(root.right);
            System.out.println("--> "+ root.name +" = " + root.left.name + " "+ root.op  + " " + root.right.name);
		}
	}
	public int getPrecedence(Character c){
        if (c=='+' || c=='-'){
            return 1;
        }
        else if (c=='*'){
            return 2;
        }
        else if (c=='/'){
            return 3;
        }
        else if (c=='%'){
            return 4;
        }
        else { 
            return 0;
		}
    }
	public Node algorithm(String input) {
		Stack<Character> ops=new Stack<>();
		Stack<Node> expns=new Stack<>();
		Character c;
		for(int i=0;i<input.length();i++) {
			c=input.charAt(i);
			if(c=='(') {
				ops.push(c);
			}
			else if(Character.isDigit(c)) {
				expns.push(new Node(c));
			}
			else if(isOperator(c)) {
				while (!ops.empty()&&getPrecedence(ops.peek()) >= getPrecedence(c)) {
                    Character operator = ops.pop();
                    Node right = expns.pop();
                    Node left = expns.pop();
                    expns.push(new Node(operator,left,right,"E"+index++));
                }
				ops.push(c);
			}
			else if(c==')') {
				while (ops.peek()!= '(') {
                    Character operator = ops.pop();
                    Node right = expns.pop();
                    Node left = expns.pop();
                    expns.push(new Node(operator,left,right,"E"+index++));
                }
				ops.pop();
			}
			else {
				System.out.println("Some where error occured!");
			}
		}
		while(!ops.empty()) {
			Character operator = ops.pop();
            Node right = expns.pop();
            Node left = expns.pop();
            expns.push(new Node(operator,left,right,"E"+index++));
		}
		return expns.pop();
	}
	public  boolean isOperator(Character c){
        if (c=='+' || c=='-' || c=='/' || c=='*'|| c=='%'){
            return true;
        }
        else{
            return false;
        }
    }
}
class Node{
	Node left;
	Node right;
	Character op;
	String name;
	public Node(Character c){
		op=c;
        this.name=c+"";
	}
	public Node(Character op,Node left,Node right,String name){
		this.op=op;
		this.left=left;
		this.right=right;
		this.name=name;
	}
}