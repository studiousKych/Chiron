package chiron.taylor.chirag.chiron.models;

import java.io.Serializable;

public class SetModel implements Serializable {

    private String Name;
    private int Load;
    private int Reps;
    private int Rest;
    private String Url;
    private int Order;

    public SetModel(String n,
                    int l,
                    int r,
                    int e,
                    String u,
                    int o) {
        this.Name = n;
        this.Load = l;
        this.Reps = r;
        this.Rest = e;
        this.Url = u;
        this.Order = o;
    }

    // Set Methods
    public void setName(String Name) {
        this.Name = Name;
    }

    public void setLoad(int Load) {
        this.Load = Load;
    }

    public void setReps(int Reps) {
        this.Reps = Reps;
    }

    public void setRest(int Rest) { this.Rest = Rest; }

    public void setUrl(String Url) { this.Url = Url; }

    public void setOrder(int Order) { this.Order = Order; }

    // Get Methods
    public String getName() {
        return this.Name;
    }

    public int getLoad() {
        return this.Load;
    }

    public int getReps() {
        return this.Reps;
    }

    public int getRest() { return this.Rest; }

    public String getUrl() { return this.Url; }

    public int getOrder() { return this.Order; }

}

