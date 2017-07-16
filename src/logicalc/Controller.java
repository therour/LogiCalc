/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalc;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author NizomSidiq
 */
public class Controller implements Initializable {
    
    private final Stack<String> stOperator = new Stack<>(); 
    private final Stack<String> stack = new Stack<>();
    private final HashMap<String, Integer> opPrior = new HashMap<>();
    
    private void checkOp(String op){
        if (stOperator.empty() || op.equals("(")){
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
        while (true) {
            if (stOperator.empty()) {
                stOperator.push(op);
                return;
            }
            if (a < opPrior.get(stOperator.peek())){
                stack.push(stOperator.pop());
            } else {
                stOperator.push(op);
                return;
            }
        }
    }
    
    private Tree convertToTree(Stack<String> st){
        Tree tmp = new Tree(st.pop());
        if ("~".equals(tmp.getData())){
            if (Logicalc.isNumber(st.peek())){
                tmp.setRight(new Tree(st.pop()));
            } else {
                tmp.setRight(convertToTree(st));
            }
            return tmp;
        }
        if (Logicalc.isNumber(st.peek())){
            tmp.setRight(new Tree(st.pop()));
        } else {
            tmp.setRight(convertToTree(st));
        }
        if (Logicalc.isNumber(st.peek())){
            tmp.setLeft(new Tree(st.pop()));
        } else {
            tmp.setLeft(convertToTree(st));
        } 
        return tmp;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Operator priority low to high */
	opPrior.put("(", 1);
	opPrior.put("=", 2);
	opPrior.put(">", 3);
	opPrior.put("&", 4);
	opPrior.put("|", 5);
	opPrior.put("~", 6);
        
        lbHint.setText(
                "&\t \u2192 Konjungsi" + "\n"
                + "|\t \u2192 Disjungsi" + "\n"
                + ">\t \u2192 Implikasi" + "\n"
                + "=\t \u2192 Biimplikasi" + "\n"
                + "~\t \u2192 Negasi" + "\n"
                + "T\t \u2192 True" + "\n"
                + "F\t \u2192 False" + "\n" + "\n"
                + "1,2,3 \u2192 Operand" + "\n"
                + "Contoh : (1 > 2) & ~(2 | 3) = T | F"
        );
    }
    
    /* FXML SECTION */
    
    @FXML
    private TextField soal;
    @FXML private Label lbHint;
    @FXML private TextArea taResult;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        // print intro
        System.out.println("===== Formula Processing =====");
        System.out.println("");
        // get input
        String q = soal.getText();
        
        // convert string to stack
        for (String x : q.split("")){
            if (" ".equals(x)) continue;
            if (Logicalc.isNumber(x)) stack.push(x);
            else checkOp(x);
        }
        while (!stOperator.empty()){
            stack.push(stOperator.pop());
        }
        
        // lihat bentuk stack
        Stack tmp = (Stack) stack.clone();
        String cekstack = "";
        while (!tmp.empty())
            cekstack += tmp.pop() + " ";
        System.out.println("input :" + q);
        System.out.println("Stack :");
        System.out.println(cekstack);
        System.out.println("top ---> bottom");
        
        // convert stack to Tree : tree
        Tree tree = convertToTree(stack);
        
        // lihat bentuk Tree : tree
        BTreePrinter.printNode(tree);
        
        // Cek Formula tree thd hukum ekuivalensi
        System.out.println("Hukum Ekuivalensi yang dapat dilakukan");
        Formula formula = new Formula(tree);
        
        //tampilkan checkFormula pada Text Area
        taResult.setText(formula.getCheck());
    }   
}
