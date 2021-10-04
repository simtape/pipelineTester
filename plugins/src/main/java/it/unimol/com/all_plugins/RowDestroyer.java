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
            File file = new File("C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\homework_asd\\src\\main\\java\\it\\unimol\\project\\Main.java");
            BufferedReader reader = new BufferedReader(new FileReader(file));


            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("{"))
                    bw.write(" ");

            }
            bw.close();
            reader.close();


        } catch (
                IOException e) {

        }


    }
}
