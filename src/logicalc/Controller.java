/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalc;

import java.net.URL;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author NizomSidiq
 */
public class Controller implements Initializable {
    
    private Stack<String> stOperator = new Stack<>(); 
    private Stack<String> stack = new Stack<>();
    private HashMap<String, Integer> opPrior = new HashMap<>();
    
    private void checkOp(String op){
        if (stOperator.empty()){
            stOperator.push(op);
            return;
        }
        if (op.equals(")")){
            while(true){
                stack.push(stOperator.pop());
                if (stack.peek().equals("(")){
                    return;
                }
            }
        }
        int a = opPrior.get(op);
        int b = opPrior.get(stOperator.peek());
        while (!stOperator.empty() && a < b ){
            stack.push(stOperator.pop());
            b = opPrior.get(stOperator.peek());
        }
        stOperator.push(op);
    }
    private boolean isNumber(String x){
        try {
            int a = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    private Tree convertToTree(Stack<String> st){
        Tree tmp = new Tree(st.pop());
        if (st.empty()){
            return tmp;
        } 
        if (tmp.getData().equals("~")){
            // satu anak
            Tree right = new Tree(stack.pop());
        } else if (!isNumber(tmp.getData())){
            // dua anak
        } else {
            // tanpa anak
        }
        return tmp;
    }

// Contoh : ( 1 > 2 ) & ( ~ 3 )
// Stack  : 1 2 > (
// StackOP: & 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Operator priority low to high */
	opPrior.put("(", 1);
	opPrior.put("<>", 2);
	opPrior.put(">", 3);
	opPrior.put("&", 4);
	opPrior.put("|", 5);
	opPrior.put("~", 6);
        
        String soal = "( 1 > 2 ) & ( ~ 3 )";
        for (String x : soal.split(" ")){
            if (isNumber(x)){
                stack.push(x);
            } else {
                checkOp(x);
            }
//            System.out.print(stack.peek() + " ");
//            try{
//                System.out.println(stOperator.peek());
//            } catch (EmptyStackException e){
//                System.out.println("0");
//            }
        }
        
        while (!stOperator.empty()){
            stack.push(stOperator.pop());
        }
        while (!stack.empty()){
            System.out.print(stack.pop() + " ");
        }
    }
    
    /* FXML SECTION */
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    
    
}
