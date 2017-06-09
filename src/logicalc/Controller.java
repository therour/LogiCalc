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
import javafx.scene.control.TextField;

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
                String tmp = stOperator.pop();
                if (tmp.equals("(")) return;
                stack.push(tmp);
            }
        }
        int a = opPrior.get(op);
        int b = opPrior.get(stOperator.peek());
        while (true) {
            if (stOperator.empty()) {
                stOperator.push(op);
                return;
            }
            if (a < b){
                stack.push(stOperator.pop());
            } else {
                stOperator.push(op);
                return;
            }
        }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Operator priority low to high */
	opPrior.put("(", 1);
	opPrior.put("<>", 2);
	opPrior.put(">", 3);
	opPrior.put("&", 4);
	opPrior.put("|", 5);
	opPrior.put("~", 6);
        
        
    }
    
    /* FXML SECTION */
    @FXML
    private Label label;
    
    @FXML
    private TextField soal;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String q = soal.getText();
        for (String x : q.split(" ")){
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
        String jawaban = "";
        while (!stack.empty()){
            jawaban += " " +  stack.pop();
        }
        label.setText(jawaban);
    }   
}
