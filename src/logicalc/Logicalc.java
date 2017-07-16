/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author NizomSidiq
 */
public class Logicalc extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public static boolean isNumber(String x){
        if (x.equals("T") || x.equals("F")) return true;
        try {
            int a = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean TreeEquals(Tree a, Tree b){
        if (a == null && b == null) return true;
        if (a != null && b != null) {
            if (a.isOperand() && b.isOperand())
                return (TreeEquals(a.getLeft(),b.getLeft()) 
                        && TreeEquals(a.getRight(), b.getRight()));
            return (a.getData().equals(b.getData()) 
                    && TreeEquals(a.getLeft(),b.getLeft()) 
                    && TreeEquals(a.getRight(), b.getRight()));
        }    
        return false;
    }
    public static boolean TreeCaseEquals(Tree a, Tree b){
        if (a == null && b == null) return true;
        if (a != null && b != null) {
            return (a.getData().equals(b.getData()) 
                    && TreeCaseEquals(a.getLeft(),b.getLeft()) 
                    && TreeCaseEquals(a.getRight(), b.getRight()));
        }    
        return false;
    }
}
