package it.unimol.tester.parser.app.pipeline_utilities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PipeLine {
    private String name;
    private List<Job> jobs;


}
