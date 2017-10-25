/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author NizomSidiq
 * source https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
 */
class BTreePrinter2 {
    private final HashMap<Integer, Integer> titik = new HashMap<>();
    private final Tree root;
    private String printed;
    public HashMap<Integer, Integer> getMap() {
        return this.titik;
    }
    public BTreePrinter2(Tree root) {
        this.root = root;
        int maxLevel = maxLevel(root);
        this.printed = printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        this.tmp = 0;
    }
    public String getVisual() {
        return this.printed; 
    }
    public String getVisual(Tree root){
        int maxLevel = maxLevel(root);
        return printNodeInternal(Collections.singletonList(root), 1, maxLevel);

    }
    private int tmp;
    private <T extends Comparable<?>> String printNodeInternal(List<Tree> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return "";
        String result = "";
        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        result += printWhitespaces(firstSpaces);
        List<Tree> newNodes = new ArrayList<>();
        for (Tree node : nodes) {
            if (node != null) {
                System.out.print(node.getData());
                result += node.getData();
                titik.put(node.getId(), this.tmp + result.length());
                System.out.println("" + titik.get(node.getId()));
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
                result += " ";
            }

            result += printWhitespaces(betweenSpaces);
        }
        System.out.println("");
        result += "\n";

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                result += printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    result += printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null){
                    System.out.print("/");
                    result += "/";
                }
                else
                    result += printWhitespaces(1);

                result += printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null){
                    System.out.print("\\");
                    result += "\\";
                }
                else
                    result += printWhitespaces(1);

                result += printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
            result += "\n";
        }
        this.tmp += result.length();

        result += printNodeInternal(newNodes, level + 1, maxLevel);
        return result;
    }

    private String printWhitespaces(int count) {
        String whitespace = "";
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
            whitespace += " ";
        }
        return whitespace;

    }

    private <T extends Comparable<?>> int maxLevel(Tree node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.getLeft()), maxLevel(node.getRight())) + 1;
    }

    private <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}

