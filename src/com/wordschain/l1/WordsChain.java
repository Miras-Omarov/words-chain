package com.wordschain.l1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordsChain {
    public static void main(String[] args) throws IOException {
        System.out.println("Input directory to a file");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        StringBuilder fullLine = new StringBuilder();
        while(bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            fullLine.append(line).append(" ");
        }
        bufferedReader.close();
        scanner.close();
        String[] resultString = fullLine.toString().split(" ");
        StringBuilder result = getLine(resultString);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        List<StringBuilder> list = new ArrayList<>();
        if (words.length > 1) {
            for (int x = 0; x < words.length; x++) {
                StringBuilder result = new StringBuilder();
                result.append(words[x]).append(" ");
                for (int i = x; i < words.length; i++) {
                    for (int j = 0; j < words.length; j++) {
                        boolean end = false;
                        if (words[i].toLowerCase().charAt(words[i].length() - 1) == words[j].toLowerCase().charAt(0)) {
                            if (!result.toString().contains(words[j])) {
                                result.append(words[j]).append(" ");
                                i = j - 1;
                                end = true;
                            }
                        }
                        if (end)
                            break;
                    }
                }
                result.deleteCharAt(result.length() - 1);
                list.add(result);
            }
        } else {
            return new StringBuilder();
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder stringBuilder : list) {
            if (result.length() < stringBuilder.length()) {
                result = stringBuilder;
            }
        }
        return result;
    }
}
