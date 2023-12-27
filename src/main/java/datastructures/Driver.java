package datastructures;

import datastructures.ds.Dictionary;
import datastructures.ds.Pair;
import datastructures.ds.PriorityQueue;
import datastructures.ds.SelfBalancingDictionary;
import datastructures.util.StopWatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * represents the entry point of the project
 */
public class Driver{

    /**
     * main method
     *
     * @param args arguments
     */
    public static void main(String[] args){
        Driver.wordCounterBST(new SelfBalancingDictionary<>());
    }

    /**
     * @param dict dictionary
     *             use Dictionary (BST) to do a word count on bible
     */
    public static void wordCounterBST(Dictionary<String, Integer> dict){
        String p = "src/main/resources/bible.txt";
        ArrayList<String> words = new ArrayList<>();
        PriorityQueue<Pair<Integer, String>> pq = new PriorityQueue<>(true);
        try{
            Scanner scanner = new Scanner(new File(p));
            while(scanner.hasNext()){
                String word = scanner.next();
                if(word.matches("[a-zA-Z]+")){
                    words.add(word.toLowerCase());
                }
            }
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        StopWatch.shared.begin();
        for(String word : words){
            Integer c = dict.get(word);
            if(c == null){
                dict.insert(word, 1);
            }else{
                dict.insert(word, c + 1);
            }
        }
        for(Pair<String, Integer> pair : dict){
            pq.insert(new Pair<>(pair.getSecond(), pair.getFirst()));
        }
        StopWatch.shared.end("Runtime:", 1.0);

        StringBuilder builder = new StringBuilder();
        while(!pq.isEmpty()){
            Pair<Integer, String> pair = pq.pop();
            builder.append(String.format("word: %-20scount:%d\n", pair.getSecond(), pair.getFirst()));
        }
        try(FileWriter writer = new FileWriter(p.replace("bible", "bible-word-count"))){
            writer.write(builder.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}