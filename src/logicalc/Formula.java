/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author NizomSidiq
 */
public class Formula {
    private final Tree root;
    private final HashMap<String, List<Integer>> collection;
    public Formula(Tree root) {
        this.root = root;
        this.collection = this.makeCollection();
    }
    public String getCheck(){
        String q = "";
        q = collection.entrySet()
                .stream()
                .map(
                    (entry) -> (!entry.getValue().isEmpty()) ? entry.getKey() + " \u2192 true \n" : "")
                .reduce(q, String::concat);
        return q;
    }
    private HashMap<String, List<Integer>> makeCollection() {
        HashMap<String, List<Integer>> col = new HashMap<>();
        col.put("Identity", getIdentity(this.root));
        col.put("Idempotensi", getIdempotensi(this.root));
        col.put("Absorption", getAbsorption(this.root));
        col.put("Assosiatif", getAssosiatif(this.root));
        col.put("Biimplikasi", getBiimplikasi(this.root));
        col.put("DNegasi", getDNegasi(this.root));
        col.put("DeMo", getDeMo(this.root));
        col.put("Distributif", getDistributif(this.root));
        col.put("Eksportasi", getEksportasi(this.root));
        col.put("Implikasi", getImplikasi(this.root));
        col.put("Komutatif", getKomutatif(this.root));
        col.put("Kontraposisi", getKontraposisi(this.root));
        col.put("TautKontra", getTautKontra(this.root));
        return col;
    }
    public HashMap<String, List<Integer>> getColleciton() {
        return this.collection;
    }
    public Tree getTree(int id) {
        return this.getTree(id, this.root);
    }
    public Tree getTree(int id, Tree pohon) {
        if (pohon.getId() == id) {
            return pohon;
        } 
        if (pohon.getLeft() != null) {
            return getTree(id, pohon.getLeft());
        } 
        if (pohon.getRight() != null) {
            return getTree(id, pohon.getRight());
        }
        return null;
    }
    public List<Integer> getIdentity(Tree pohon) {
        List<Integer> list = new ArrayList<>();
        if (pohon != null) {
            if (pohon.getData().equals("&") || pohon.getData().equals("|")) {
                if (pohon.getLeft().getData().equals("T") || 
                    pohon.getLeft().getData().equals("F") ||  
                    pohon.getRight().getData().equals("T") ||
                    pohon.getRight().getData().equals("F")
                   ) 
                        list.add(pohon.getId());
            }
            if (pohon.getLeft() != null) list.addAll(getIdentity(pohon.getLeft()));
            if (pohon.getRight() != null) list.addAll(getIdentity(pohon.getRight()));
        }
        return list;
    }  
    public List<Integer> getIdempotensi(Tree pohon) {
        List<Integer> list = new ArrayList<>();
        if (pohon != null) {
            if (pohon.getData().equals("&") || pohon.getData().equals("|")) {
                if (Logicalc.TreeCaseEquals(pohon.getLeft(), pohon.getRight())) {
                    list.add(pohon.getId());
                }
            }
            if (pohon.getLeft() != null) list.addAll(getIdempotensi(pohon.getLeft()));
            if (pohon.getRight() != null) list.addAll(getIdempotensi(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getDNegasi(Tree pohon) {
        List<Integer> list = new ArrayList<>();
        if (pohon != null) {
            if (pohon.getData().equals("~"))
                if (pohon.getRight().getData().equals("~"))
                    list.add(pohon.getId());
            if (pohon.getLeft() != null) list.addAll(getDNegasi(pohon.getLeft()));
            if (pohon.getRight() != null) list.addAll(getDNegasi(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getTautKontra(Tree pohon){
        List<Integer> list = new ArrayList<>();
        if (pohon != null){
            if (pohon.getData().equals("&") || pohon.getData().equals("|")){
                if( pohon.getLeft().getData().equals("~") && 
                    Logicalc.TreeCaseEquals(pohon.getLeft().getRight(), pohon.getRight()) ||
//                    if(tmp.getLeft().getRight().getData().equals(tmp.getRight().getData()))
                    pohon.getRight().getData().equals("~") && 
                    Logicalc.TreeCaseEquals(pohon.getRight().getRight(), pohon.getLeft())
//                    if(tmp.getRight().getRight().getData().equals(tmp.getLeft().getData()))
                )
                        list.add(pohon.getId());
            }    
            if(pohon.getLeft() != null) list.addAll(getTautKontra(pohon.getLeft()));
            if(pohon.getRight() != null) list.addAll(getTautKontra(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getKomutatif(Tree pohon){
        List<Integer> list = new ArrayList<>();
        if (pohon != null){
            if (pohon.getData().equals("&") || pohon.getData().equals("|")) 
                list.add(pohon.getId());
            
            if (pohon.getLeft() != null) list.addAll(getKomutatif(pohon.getLeft()));
            if (pohon.getRight() != null) list.addAll(getKomutatif(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getAssosiatif(Tree pohon){
        List<Integer> list = new ArrayList<>();
        if (pohon != null){
            if (pohon.getData().equals("&") || pohon.getData().equals("|")){   
                if (pohon.getData().equals(pohon.getLeft().getData()) || 
                    pohon.getData().equals(pohon.getRight().getData())) 
                    list.add(pohon.getId());
            }
            if (pohon.getLeft() != null) list.addAll(getAssosiatif(pohon.getLeft()));
            if (pohon.getRight() != null) list.addAll(getAssosiatif(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getImplikasi(Tree pohon){
        List<Integer> list = new ArrayList<>();
        if(pohon != null){
            if (pohon.getData().equals(">")) 
                list.add(pohon.getId());
            
            if (pohon.getLeft() != null) list.addAll(getImplikasi(pohon.getLeft()));
            if (pohon.getRight() != null) list.addAll(getImplikasi(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getDeMo(Tree pohon){
        List<Integer> list = new ArrayList<>();
        if (pohon != null){
            if (pohon.getData().equals("~"))
                if(pohon.getRight().getData().equals("&") || pohon.getRight().getData().equals("|"))
                    list.add(pohon.getId());
            if(pohon.getLeft() != null) list.addAll(getDeMo(pohon.getLeft()));
            if(pohon.getRight() != null) list.addAll(getDeMo(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getDistributif (Tree pohon){
        List<Integer> list = new ArrayList<>();
        if (pohon != null){
            if (pohon.getData().equals("&") && 
                    (pohon.getRight().getData().equals("|") || 
                     pohon.getLeft().getData().equals("|")) 
                || 
                pohon.getData().equals("|") &&
                    (pohon.getRight().getData().equals("&") || 
                     pohon.getLeft().getData().equals("&"))
                )
                    list.add(pohon.getId());
            if (pohon.getLeft() != null)
                list.addAll(getDistributif(pohon.getLeft()));
            if (pohon.getRight() != null)
                list.addAll(getDistributif(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getAbsorption(Tree pohon){
        List<Integer> list = new ArrayList<>();
        if (pohon != null){
            if (pohon.getData().equals("&")){   
                if (pohon.getRight().getData().equals("|")){
                    if (Logicalc.TreeCaseEquals(pohon.getLeft(), pohon.getRight().getLeft()) ||
                        Logicalc.TreeCaseEquals(pohon.getLeft(), pohon.getRight().getRight())) 
                            list.add(pohon.getId());
                } else if (pohon.getLeft().getData().equals("|")){
                    if (Logicalc.TreeCaseEquals(pohon.getRight(), pohon.getLeft().getLeft()) ||
                        Logicalc.TreeCaseEquals(pohon.getRight(), pohon.getLeft().getRight())) 
                            list.add(pohon.getId());
                }
            } else if (pohon.getData().equals("|")){   
                if (pohon.getRight().getData().equals("&")){
                    if (Logicalc.TreeCaseEquals(pohon.getLeft(), pohon.getRight().getLeft()) ||
                        Logicalc.TreeCaseEquals(pohon.getLeft(), pohon.getRight().getRight())
                    ) 
                        list.add(pohon.getId());
                } else if (pohon.getLeft().getData().equals("&")){
                    if (Logicalc.TreeCaseEquals(pohon.getRight(), pohon.getLeft().getLeft()) ||
                        Logicalc.TreeCaseEquals(pohon.getRight(), pohon.getLeft().getRight())
                    ) 
                        list.add(pohon.getId());
                }
            }
            
            if (pohon.getLeft() != null) list.addAll(getAbsorption(pohon.getLeft()));
            if (pohon.getRight() != null) list.addAll(getAbsorption(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getEksportasi(Tree pohon){
        List<Integer> list = new ArrayList<>();
        if (pohon != null) {
            if (pohon.getData().equals(">"))
                if (pohon.getLeft().getData().equals("&"))
                    list.add(pohon.getId());

            if (pohon.getLeft() != null) list.addAll(getEksportasi(pohon.getLeft()));
            if (pohon.getRight() != null) list.addAll(getEksportasi(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getBiimplikasi(Tree pohon){
        List<Integer> list = new ArrayList<>();
        if (pohon != null){
            if (pohon.getData().equals("=")) 
                list.add(pohon.getId());

            if (pohon.getLeft() != null) list.addAll(getBiimplikasi(pohon.getLeft()));
            if (pohon.getRight() != null) list.addAll(getBiimplikasi(pohon.getRight()));
        }
        return list;
    }
    public List<Integer> getKontraposisi(Tree pohon) {
        return getImplikasi(pohon);
    }
}
