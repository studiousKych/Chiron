package chiron.taylor.chirag.chiron.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SetModel {

    public String getName() {
        return Name;
    }

    public int getLoad() {
        return Load;
    }

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

    public int getReps() {
        return this.Reps;
    }

    public int getRest() { return this.Rest; }

    public String getUrl() { return this.Url; }

    public int getOrder() { return this.Order; }

}

