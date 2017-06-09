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
    
    /* Constructor */
    public Tree(String x){
	this.data = x;
	this.left = null;
	this.right = null;
    }
    
    /* Methods */
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
    public void print(){
	if(this != null){
            left.print();
            System.out.print("" + this.data + " ");
            right.print();
	}
    }
}