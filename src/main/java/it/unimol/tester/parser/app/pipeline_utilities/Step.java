package it.unimol.tester.parser.app.pipeline_utilities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Step {
    private String uses;
    private String name;
    private String run;
    private Object with;


    @Override
    public String toString() {
        return "Step{" +
                "uses='" + uses + '\'' +
                ", name='" + name + '\'' +
                ", run='" + run + '\'' +
                ", with='" + with + '\'' +
                '}';
    }
}
