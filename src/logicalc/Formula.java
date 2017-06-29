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
    
    public Formula(Tree root) {
        this.root = root;
        checkFormula();
    }
    public final void checkFormula(){
        this.identity = this.isIdentity(this.root);
        System.out.println("identitas = " + String.valueOf(this.identity));
        this.idempotensi = this.isIdempotensi(this.root);
        System.out.println("idempotensi = " + String.valueOf(this.idempotensi));
        this.dNegasi = this.isDNegasi(this.root);
        System.out.println("Double Negasi = " + String.valueOf(this.dNegasi));
         this.tautKontra = this.isTautKontra(this.root);
        System.out.println("Tautology dan Kontraposisi = " + String.valueOf(this.tautKontra));
         this.DeMo = this.isDeMo(this.root);
        System.out.println("De Morgan \u2192 " + String.valueOf(this.DeMo));
        // this.distributif = this.isDistributif();
        // this.absorption = this.isAbsorption();
        // this.komutatif = this.isKomutatif();
        // this.assosiatif = this.isAssosiatif();
        // this.implikasi = this.isImplikasi();
        // this.biimplikasi = this.isBiimplikasi();
        // this.kontraposisi = this.isKontraposisi();
        // this.eksportasi = this.isEksportasi();       
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
                    if(tmp.getLeft().getData().equals(tmp.getRight().getData()))
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
                    if(tmp.getLeft().getRight().getData().equals(tmp.getRight().getData()))
                        return true;
                if(tmp.getRight().getData().equals("~"))
                    if(tmp.getRight().getRight().getData().equals(tmp.getLeft().getData()))
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
}
