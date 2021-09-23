package it.unimol.com.app.pipeline_utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Job {
    private String name_type;
    //private List<Step> steps;
    private String runs_on;
    private ArrayList<Map> allSteps = new ArrayList<>();


    public String getRuns_on() {
        return runs_on;
    }

    public void setRuns_on(String runs_on) {
        this.runs_on = runs_on;
    }

    public String getName_type() {
        return name_type;
    }

    public void setName_type(String name_type) {
        this.name_type = name_type;
    }

/*
    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
*/

    public ArrayList<Map> getAllSteps() {
        return allSteps;
    }

    public void setAllSteps(ArrayList<Map> allSteps) {
        this.allSteps = allSteps;
    }

    @Override
    public String toString() {
        return "Job{" +
                "allSteps=" + allSteps +
                '}';
    }
}
