package it.unimol.tester.plugins;


import it.unimol.tester.plugins.all_plugins.row_destroyer.NotAJavaProject;
import it.unimol.tester.plugins.all_plugins.row_destroyer.Project;

import java.util.List;

public interface Plugin {
    void run(List<Project>projects) throws NotAJavaProject;
}
