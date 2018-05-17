package app.japaneseOperation;
/*
 * Created by david on 4/15/2018.
 * Copyright ISOTOPE Studio
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileSplit {
    public static void main(String[] args) {

        // The name of the file to open.
        String fileName = "C:\\Users\\david\\Desktop\\j\\Japanese.htm";
        List<List<String>> files = new ArrayList<>();

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            int count = -1;
            files.add(new ArrayList<>());
            while ((line = bufferedReader.readLine()) != null) {
                if (count < 0) {
                    if (!line.contains("<DIV id=\"c1\">")) {
                        continue;
                    } else count++;
                }

                if (line.contains("<DIV id=\"c" + (count + 1) + "\">")) {
                    files.add(new ArrayList<>());
                    count++;
                }
                files.get(count).add(line.replaceAll("<IMG src=\"Japanese_files/", "<IMG src=\"img/"));
            }
            // Always close files.
            bufferedReader.close();
            int c[] = {0};
            files.stream().skip(1).forEach(strings -> {
                strings.remove(0);
                for (int i = strings.size() - 1; i >= 0; i--) {
                    if (strings.get(i).equals("</DIV>")) {
                        strings.remove(i);
                        break;
                    }
                }
            });
            files.forEach(strings ->
                    io.fileOperation.Write.write(new File("C:\\Users\\david\\Desktop\\j\\c" + c[0]++ + ".htm"),
                            strings.stream().collect(Collectors.joining("\r\n"))));
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}
