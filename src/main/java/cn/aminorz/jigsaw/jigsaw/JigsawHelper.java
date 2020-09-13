package cn.aminorz.jigsaw.jigsaw;

import java.util.LinkedList;
import java.util.Scanner;

public class JigsawHelper {
    public static void main(String[] args) {
        OccupiedSectionPoolCodeHelper("");
    }
    public static void OccupiedSectionPoolCodeHelper(String OccupiedSectionPoolName)
    {
        LinkedList<String> linkedList=new LinkedList<>();
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext())
        {
            String now=scanner.next();
            System.out.println(now);
        }
    }
}