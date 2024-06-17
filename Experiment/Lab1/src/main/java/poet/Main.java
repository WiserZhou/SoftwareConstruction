/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.*;

/**
 * Example program using GraphPoet.
 *
 * <p>PS2 instructions: you are free to change this example class.
 */
public class Main {

    /**
     * Generate example poetry.
     *
     * @param args unused
     * @throws IOException if a poet corpus file cannot be found or read
     */
    public static void main(String[] args) throws IOException {
//        RemoveDot();
        final GraphPoet nimoy = new GraphPoet(new File("src/main/resources/StrayBirds2.txt"));
        final String input = "It is the tears of the earth that keep her smiles in bloom.";
        System.out.println(input + "\n>>>\n" + nimoy.poem(input));
        System.out.println(nimoy);
    }

    /**
     * 从文本文件中移除句号、问号和引号。
     *
     * @throws IOException 如果在读取或写入文件时发生错误。
     */
    public static void RemoveDot() throws IOException {
        // 输入文件路径和输出文件路径
        String inputFile = "src/main/resources/StrayBirds.txt";
        String outputFile = "src/main/resources/StrayBirds2.txt";

        // 读取输入文件
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        // 创建一个用于写入的缓冲区
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;
        // 逐行读取文件内容
        while ((line = reader.readLine()) != null) {
            // 去掉每行中的英文句号，问号和引号
            String newLine = line.replace(".", "");
            newLine = newLine.replace("\"", "");
            newLine = newLine.replace("?", "");
            // 将处理后的内容写入输出文件
            // 如果去除符号后不为空行，则写入输出文件
            if (!newLine.trim().isEmpty()) {
                writer.write(newLine);
                writer.newLine();
            }
        }

        // 关闭读写流
        reader.close();
        writer.close();

        System.out.println("英文句号、引号、问号已经成功去除并保存到输出文件中。");
    }
}
