/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalc;

/**
 *
 * @author NizomSidiq
 */

public class Tree
{
    private String data;
    private Tree left;
    private Tree right;
    private boolean isOperand;
    private int id;
    
    /* Constructor */
    public Tree(String x){
	this.data = x;
        this.isOperand = Logicalc.isNumber(x);
	this.left = null;
	this.right = null;
    }
    
    /* Methods */
    public int getId(){
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getData(){
	return this.data;
    }
    public Tree getLeft(){
	return this.left;
    }
    public Tree getRight(){
	return this.right;
    }
    public void setLeft(Tree n){
	this.left = n;
    }
    public void setRight(Tree n){
	this.right = n;
    }
    public static void print(Tree t){
	if(t != null){
            print(t.getLeft());
            System.out.print("" + t.getData() + " ");
            print(t.getRight());
	}
    }
    protected void changeTree(Tree pohon) {
        this.data = pohon.getData();
        this.setLeft(pohon.getLeft());
        this.setRight(pohon.getRight());
        this.id = pohon.getId();
    }
    
    public boolean isOperand(){
        return this.isOperand;
    }
    public String treeToString() {
        return this.treeToString(this);
    }
    public String treeToString(Tree pohon){
        String str = "";
        if(pohon != null){
            str = pohon.getData();
            if (pohon.getLeft() != null) {
                str = "(" + treeToString(pohon.getLeft()) + str; 
            }
            if (pohon.getRight() != null) {
                if (pohon.getRight().getData().equals("~")) {
                    str += "(" + treeToString(pohon.getRight()) + ")";
                } else {
                    str += treeToString(pohon.getRight()) + ")";
                }
            }
        }
        return str;
    }
}
