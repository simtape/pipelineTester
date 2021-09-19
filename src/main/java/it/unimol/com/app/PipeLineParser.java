package it.unimol.com.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.auto.service.AutoService;
import it.unimol.com.app.pipeline_utilities.Job;
import it.unimol.com.app.pipeline_utilities.PipeLine;
import it.unimol.com.app.pipeline_utilities.Step;
import it.unimol.com.engine.Parser;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@AutoService(Parser.class)
public class PipeLineParser implements Parser {
    private Order order;
    private InputStream inputStream;
    private PipeLine pipeLine = new PipeLine();
    private List<Job> jobs = new ArrayList<>();
    private List<Step> steps = new ArrayList<>();

    @Override
    public void parse() {

        try {
            inputStream = new FileInputStream(new File("src/main/resources/maven_2.yml"));

        } catch (IOException e) {
        }
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);

        //iteration on the jobs hash maps
        if (data.containsKey("jobs")) {
            Map jobsMap = (Map) data.get("jobs");
            for (Object obj : jobsMap.keySet()) {
                Job job = new Job();
                steps = new ArrayList<>();
                job.setName_type(obj.toString());
                Map subJobMap = (Map) jobsMap.get(obj.toString());

                if (subJobMap.containsKey("runs_on")) {
                    job.setRuns_on(subJobMap.get("runs_on").toString());

                }
                if (subJobMap.containsKey("steps")) {

                    ArrayList<Map> stepsArray = (ArrayList) subJobMap.get("steps");
                    for (Map object : stepsArray) {
                        Step step = new Step();

                        if (object.containsKey("uses")) {
                            step.setUses(object.get("uses").toString());

                        }
                        if (object.containsKey("run")) {
                            step.setRun(object.get("run").toString());

                        }
                        if (object.containsKey("name")) {
                            step.setName(object.get("name").toString());

                        }
                        steps.add(step);
                    }
                    job.setSteps(steps);

                }
                System.out.println("JOB: " + job.getName_type());
                System.out.println("RUNS ON: " + job.getRuns_on());
                System.out.println("STEPS");
                for (Step step : job.getSteps()) {
                    System.out.println(step.toString());

                }
                jobs.add(job);
            }

            pipeLine.setJobs(jobs);
        }


    }

    public Order getOrder() {
        return order;
    }
}