package it.unimol.tester.parser.app.pipeline_utilities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Job {
    private String name_type;
    private List<Step> steps;
    private String runs_on;
    private ArrayList<Map> allSteps = new ArrayList<>();


    public String getRuns_on() {
        return runs_on;
    }

    public void setRuns_on(String runs_on) {
        this.runs_on = runs_on;
    }



    @Override
    public String toString() {
        return "Job{" +
                "allSteps=" + allSteps +
                '}';
    }
}
