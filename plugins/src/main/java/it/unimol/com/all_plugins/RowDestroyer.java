package it.unimol.com.all_plugins;

import com.google.auto.service.AutoService;
import it.unimol.com.Plugin;

import java.io.*;

@AutoService(Plugin.class)
public class RowDestroyer implements Plugin {
    private String path;

    @Override
    public void run() {
        try {
            File oldMain = new File("C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\homework_asd\\src\\main\\java\\it\\unimol\\project\\Main.java");
            BufferedReader br = new BufferedReader(new FileReader(oldMain));

            File newMain = new File("C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\homework_asd\\src\\main\\java\\it\\unimol\\project\\Main2.java");
            BufferedWriter bw = new BufferedWriter(new FileWriter(newMain));

            String currentLine = br.readLine();
            String lineToRemove = "}";
            while (currentLine != null) {
                System.out.println(currentLine);
                String trimmedLine = currentLine.trim();
                if (!trimmedLine.equals(lineToRemove))
                    bw.write(currentLine + System.getProperty("line.separator"));
                currentLine = br.readLine();
            }
            br.close();
            bw.close();
            oldMain.delete();
            newMain.renameTo(oldMain);
        } catch (IOException e) {

        }


    }
}
