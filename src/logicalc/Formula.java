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
    private Tree root;
    private boolean identity;
    private boolean idempotensi;
    private boolean dNegasi;
    private boolean tautKontra;
    private boolean distributif;
    private boolean DeMo;
    private boolean absorption;
    private boolean komutatif;
    private boolean assosiatif;
    private boolean implikasi;
    private boolean biimplikasi;
    private boolean kontraposisi;
    private boolean eksportasi;
    private HashMap<String, List<Integer>> collection;
    public Formula(Tree root) {
        this.root = root;
        checkFormula();
        this.collection = this.makeCollection();
    }
    public final void checkFormula(){
        this.identity = this.isIdentity(this.root);
        if (identity) System.out.println("identitas = " + String.valueOf(this.identity));
        this.idempotensi = this.isIdempotensi(this.root);
        if (idempotensi) System.out.println("idempotensi = " + String.valueOf(this.idempotensi));
        this.dNegasi = this.isDNegasi(this.root);
        if (dNegasi) System.out.println("Double Negasi = " + String.valueOf(this.dNegasi));
         this.tautKontra = this.isTautKontra(this.root);
        if (tautKontra) System.out.println("Tautology dan Kontraposisi = " + String.valueOf(this.tautKontra));
         this.DeMo = this.isDeMo(this.root);
        if (DeMo) System.out.println("De Morgan \u2192 " + String.valueOf(this.DeMo));
         this.distributif = this.isDistributif(this.root);
        if (distributif) System.out.println("Distributif \u2192 " + String.valueOf(this.distributif));
         this.absorption = this.isAbsorption(this.root);
        if (absorption) System.out.println("Absorption \u2192 " + String.valueOf(this.absorption));
         this.komutatif = this.isKomutatif(this.root);
        if (komutatif) System.out.println("Komutatif \u2192 " + String.valueOf(this.komutatif));
         this.assosiatif = this.isAssosiatif(this.root);
        if (assosiatif) System.out.println("Assosiatif \u2192 " + String.valueOf(this.assosiatif));
         this.implikasi = this.isImplikasi(this.root);
        if (implikasi) System.out.println("Implikasi \u2192 " + String.valueOf(this.implikasi));
         this.biimplikasi = this.isBiimplikasi(this.root);
        if (biimplikasi) System.out.println("Biimplikasi \u2192 " + String.valueOf(this.biimplikasi));
         this.kontraposisi = this.isImplikasi(this.root);
        if (kontraposisi) System.out.println("Kontraposisi \u2192 " + String.valueOf(this.kontraposisi));
         this.eksportasi = this.isEksportasi(this.root);       
        if (eksportasi) System.out.println("Eksportasi \u2192 " + String.valueOf(this.eksportasi));
    }
    public String getCheck(){
        String q = "";
        if (this.identity) q += "identitas \u2192 " + String.valueOf(this.identity) + "\n";
        if (this.idempotensi) q += "idempotensi \u2192 " + String.valueOf(this.idempotensi) + "\n";
        if (this.dNegasi) q += "Double Negasi = " + String.valueOf(this.dNegasi) + "\n";
        if (this.tautKontra) q += "Tautology dan Kontraposisi = " + String.valueOf(this.tautKontra) + "\n";
        if (this.DeMo) q += "De Morgan \u2192 " + String.valueOf(this.DeMo) + "\n";
        if (this.distributif) q += "Distributif \u2192 " + String.valueOf(this.distributif) + "\n";
        if (this.absorption) q += "Absorption \u2192 " + String.valueOf(this.absorption) + "\n";
        if (this.komutatif) q += "Komutatif \u2192 " + String.valueOf(this.komutatif) + "\n";
        if (this.assosiatif) q += "Assosiatif \u2192 " + String.valueOf(this.assosiatif) + "\n";
        if (this.implikasi) q += "Implikasi \u2192 " + String.valueOf(this.implikasi) + "\n";
        if (this.biimplikasi) q += "Biimplikasi \u2192 " + String.valueOf(this.biimplikasi) + "\n";
        if (this.kontraposisi) q += "Kontraposisi \u2192 " + String.valueOf(this.kontraposisi) + "\n";
        if (this.eksportasi) q += "Eksportasi \u2192 " + String.valueOf(this.eksportasi) + "\n";
        return q;
    }
    public HashMap<String, List<Integer>> getColleciton() {
        return this.collection;
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
    private boolean isIdentity(Tree pohon){
        Tree tmp = pohon;
        if (tmp != null){
            if (tmp.getData().equals("&") || tmp.getData().equals("|")) {
                if(tmp.getLeft() != null){
                    if (tmp.getLeft().getData().equals("T")) return true;
                    if (tmp.getLeft().getData().equals("F")) return true;
                }
                if(tmp.getRight() != null){
                    if (tmp.getRight().getData().equals("T")) return true;
                    if (tmp.getRight().getData().equals("F")) return true;
                }
            }
            if (tmp.getLeft() != null)
                if(isIdentity(tmp.getLeft())) return true;
            if (tmp.getRight() != null)
                if(isIdentity(tmp.getRight())) return true;
        }
        return false;
    }
    private boolean isIdempotensi(Tree pohon){
        Tree tmp = pohon;
        if (tmp != null){
            if (tmp.getData().equals("&")|| tmp.getData().equals("|"))
                if(tmp.getLeft() != null && tmp.getRight() != null)
                    if(Logicalc.TreeCaseEquals(tmp.getLeft(), tmp.getRight()))
                        return true;
            if (tmp.getLeft() != null)
                if(isIdempotensi(tmp.getLeft())) return true;
            if (tmp.getRight() != null)
                if(isIdempotensi(tmp.getRight())) return true;
            
        }
        return false;
    }
    private boolean isDNegasi(Tree pohon){
        Tree tmp = pohon;
        if (tmp != null){
            if(tmp.getData().equals("~"))
                if(tmp.getRight().getData().equals("~")) return true;
            if(tmp.getLeft() != null)
                if(isDNegasi(tmp.getLeft())) return true;
            if(tmp.getRight() != null)
                if(isDNegasi(tmp.getRight())) return true;
        }
        return false;
    }
    private boolean isTautKontra(Tree pohon){
        Tree tmp = pohon;
        if (tmp != null){
            if (tmp.getData().equals("&") || tmp.getData().equals("|")){
                if(tmp.getLeft().getData().equals("~"))
                    if(Logicalc.TreeCaseEquals(tmp.getLeft().getRight(), tmp.getRight()))
//                    if(tmp.getLeft().getRight().getData().equals(tmp.getRight().getData()))
                        return true;
                if(tmp.getRight().getData().equals("~"))
                    if(Logicalc.TreeCaseEquals(tmp.getRight().getRight(), tmp.getLeft()))
//                    if(tmp.getRight().getRight().getData().equals(tmp.getLeft().getData()))
                        return true;
            }    
            if(tmp.getLeft() != null)
                if(isTautKontra(tmp.getLeft())) return true;
            if(tmp.getRight() != null)
                if(isTautKontra(tmp.getRight())) return true;
        }
        return false;
    }
    private boolean isDeMo(Tree pohon){
        Tree tmp = pohon;
        if (tmp != null){
            if (tmp.getData().equals("~"))
                if(tmp.getRight().getData().equals("&") || tmp.getRight().getData().equals("|"))
                    return true;
            if(tmp.getLeft() != null)
                if(isDeMo(tmp.getLeft())) return true;
            if(tmp.getRight() != null)
                if(isDeMo(tmp.getRight())) return true;
        }
        return false;
    }
    private boolean isDistributif (Tree pohon){
        Tree tmp = pohon;
        if (tmp != null){
            if (tmp.getData().equals("&"))
                if (tmp.getRight().getData().equals("|") || tmp.getLeft().getData().equals("|"))
                    return true;
            if(tmp.getData().equals("|"))
                if (tmp.getRight().getData().equals("&") || tmp.getLeft().getData().equals("&"))
                    return true;
            if (tmp.getLeft() != null)
                if (isDistributif(tmp.getLeft())) return true;
            if (tmp.getRight() != null)
                if (isDistributif(tmp.getRight())) return true;
        }
        return false;
    }
    private boolean isAbsorption(Tree pohon){
        Tree tmp = pohon;
        if (tmp != null){
            if (tmp.getData().equals("&")){   
                if (tmp.getRight().getData().equals("|")){
                    if(Logicalc.TreeCaseEquals(tmp.getLeft(), tmp.getRight().getLeft())) return true;
                    if(Logicalc.TreeCaseEquals(tmp.getLeft(), tmp.getRight().getRight())) return true;
                }
                if (tmp.getLeft().getData().equals("|")){
                    if(Logicalc.TreeCaseEquals(tmp.getRight(), tmp.getLeft().getLeft())) return true;
                    if(Logicalc.TreeCaseEquals(tmp.getRight(), tmp.getLeft().getRight())) return true;
                }
            }
            if (tmp.getData().equals("|")){   
                if (tmp.getRight().getData().equals("&")){
                    if(Logicalc.TreeCaseEquals(tmp.getLeft(), tmp.getRight().getLeft())) return true;
                    if(Logicalc.TreeCaseEquals(tmp.getLeft(), tmp.getRight().getRight())) return true;
                }
                if (tmp.getLeft().getData().equals("&")){
                    if(Logicalc.TreeCaseEquals(tmp.getRight(), tmp.getLeft().getLeft())) return true;
                    if(Logicalc.TreeCaseEquals(tmp.getRight(), tmp.getLeft().getRight())) return true;
                }
            }
            if (tmp.getLeft() != null)
                if (isAbsorption(tmp.getLeft())) return true;
            if (tmp.getRight() != null)
                if (isAbsorption(tmp.getRight())) return true;
        }
        return false;
    }
    private boolean isKomutatif(Tree pohon){
        Tree tmp = pohon;
        if (tmp != null){
            if (tmp.getData().equals("&") || tmp.getData().equals("|")) return true;
            if (tmp.getLeft() != null) if (isKomutatif(tmp.getLeft())) return true;
            if (tmp.getRight() != null) if (isKomutatif(tmp.getRight())) return true;
        }
        return false;
    }
    private boolean isAssosiatif(Tree pohon){
        Tree tmp = pohon;
        if (tmp != null){
            if (tmp.getData().equals("&") || tmp.getData().equals("|")){   
                if (tmp.getData().equals(tmp.getLeft().getData())) return true;
                if (tmp.getData().equals(tmp.getRight().getData())) return true;
            }
            if (tmp.getLeft() != null) if (isAssosiatif(tmp.getLeft())) return true;
            if (tmp.getRight() != null) if (isAssosiatif(tmp.getRight())) return true;
        }
        return false;
    }
    private boolean isImplikasi(Tree pohon){
        Tree tmp = pohon;
        if(tmp != null){
            if (tmp.getData().equals(">")) return true;
            if (tmp.getLeft() != null) if (isImplikasi(tmp.getLeft())) return true;
            if (tmp.getRight() != null) if (isImplikasi(tmp.getRight())) return true;
        }
        return false;
    }
    private boolean isEksportasi(Tree pohon){
        Tree tmp = pohon;
        if (tmp != null) {
            if (tmp.getData().equals(">"))
                if (tmp.getLeft().getData().equals("&"))
                    return true;
            if (tmp.getLeft() != null) if (isEksportasi(tmp.getLeft())) return true;
            if (tmp.getRight() != null) if (isEksportasi(tmp.getRight())) return true;
        }
        return false;
    }
    private boolean isBiimplikasi(Tree pohon){
        Tree tmp = pohon;
        if (tmp != null){
            if (tmp.getData().equals("=")) return true;
            if (tmp.getLeft() != null) if (isBiimplikasi(tmp.getLeft())) return true;
            if (tmp.getRight() != null) if (isBiimplikasi(tmp.getRight())) return true;
        }
        return false;
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
