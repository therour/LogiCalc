/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicalc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author NizomSidiq
 * source https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
 */
class BTreePrinter2 {
    
    public static String getVisual(Tree root){
        int maxLevel = BTreePrinter2.maxLevel(root);
        return printNodeInternal(Collections.singletonList(root), 1, maxLevel);

    }

    private static <T extends Comparable<?>> String printNodeInternal(List<Tree> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter2.isAllElementsNull(nodes))
            return "";
        String result = "";
        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        result += BTreePrinter2.printWhitespaces(firstSpaces);

        List<Tree> newNodes = new ArrayList<Tree>();
        for (Tree node : nodes) {
            if (node != null) {
                System.out.print(node.getData());
                result += node.getData();
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
                result += " ";
            }

            result += BTreePrinter2.printWhitespaces(betweenSpaces);
        }
        System.out.println("");
        result += "\n";

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                result += BTreePrinter2.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    result += BTreePrinter2.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null){
                    System.out.print("/");
                    result += "/";
                }
                else
                    result += BTreePrinter2.printWhitespaces(1);

                result += BTreePrinter2.printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null){
                    System.out.print("\\");
                    result += "\\";
                }
                else
                    result += BTreePrinter2.printWhitespaces(1);

                result += BTreePrinter2.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
            result += "\n";
        }

        result += printNodeInternal(newNodes, level + 1, maxLevel);
        return result;
    }

    private static String printWhitespaces(int count) {
        String whitespace = "";
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
            whitespace += " ";
        }
        return whitespace;

    }

    private static <T extends Comparable<?>> int maxLevel(Tree node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter2.maxLevel(node.getLeft()), BTreePrinter2.maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}

