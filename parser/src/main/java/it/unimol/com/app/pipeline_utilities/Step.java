package it.unimol.com.app.pipeline_utilities;

public class Step {
    private String uses;
    private String name;
    private String run;
    private Object with;

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public Object getWith() {
        return with;
    }

    public void setWith(Object with) {
        this.with = with;
    }

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
