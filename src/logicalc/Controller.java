/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalc;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author NizomSidiq
 */
public class Controller implements Initializable {
    
    private final Stack<String> stOperator = new Stack<>(); 
    private final Stack<String> stack = new Stack<>();
    private final HashMap<String, Integer> opPrior = new HashMap<>();
    private Formula formula;
    private BTreePrinter2 printer;
    
    private void checkOp(String op)
    {
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
    
    private Tree convertToTree(Stack<String> st)
    {
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
    public void initialize(URL url, ResourceBundle rb) 
    {
        /* Operator priority low to high */
	opPrior.put("(", 1);
	opPrior.put("=", 2);
	opPrior.put(">", 3);
	opPrior.put("&", 4);
	opPrior.put("|", 5);
	opPrior.put("~", 6);
        
        btnMainTree.setOnAction((e) -> {
            taResult.setText(printer.getVisual());
        });
        
        lvRules.getSelectionModel().selectedItemProperty().addListener((ob, o, n) -> {
            lvRulesId.getSelectionModel().clearSelection();
            ObservableList ruleIds = FXCollections.observableArrayList(formula.getCollection().get(n));
            lvRulesId.setItems(ruleIds);
            lvRulesId.getSelectionModel().selectFirst();
        });
        
        lvRulesId.getSelectionModel().selectedItemProperty().addListener((ob, o, n) -> {
            if (n != null) {
                int selected = printer.getMap().get(n);
                taResult.selectRange(selected -2, selected + 1);
            }
        });
    }
    
    /* FXML SECTION */
    
    @FXML
    private TextField soal;
    @FXML private TextArea taResult;
    @FXML private ListView<String> lvRules;
    @FXML private ListView<Integer> lvRulesId;
    @FXML private Button btnMainTree;
    private ToggleGroup group = new ToggleGroup();
    
    @FXML
    private void goAction(ActionEvent event) 
    {
        lvRules.getItems().clear();
        lvRulesId.getItems().clear();
        Logicalc.resetGlobalID();
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
        
        // Cek Formula tree thd hukum ekuivalensi
        formula = new Formula(tree);
        printer = new BTreePrinter2(tree);
        
        //tampilkan checkFormula pada Text Area
        taResult.setText(printer.getVisual());
        
        // get Rules yang berlaku pada formula
        List<String> rules = formula.getCollection().entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .map(e -> e.getKey())
                .collect(Collectors.toList());
        // getRulesToListView
        lvRules.setItems(FXCollections.observableArrayList(rules));
    }
    
    @FXML private void showKeyboardAction(ActionEvent e) 
    {
        System.out.println("keyboard belum dibuat");
    }
    @FXML private void ekuivalensiAction(ActionEvent e) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        String rule = lvRules.getSelectionModel().getSelectedItem();
        int id = lvRulesId.getSelectionModel().getSelectedItem();
        System.out.println("exec" + rule);
        java.lang.reflect.Method s = formula.getClass().getMethod("exec" + rule, Tree.class);
        s.invoke(formula, formula.getTree(id));
        taResult.setText(printer.getVisual());
        List<String> rules = formula.getCollection().entrySet().stream()
                .filter(ev -> !ev.getValue().isEmpty())
                .map(ev -> ev.getKey())
                .collect(Collectors.toList());
        if (!lvRules.getItems().equals(FXCollections.observableArrayList(rules)))
        lvRules.setItems(FXCollections.observableArrayList(rules));
    }
}
