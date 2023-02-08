package Weeks_With_JAVA.Examination.Model;

public class TopList {
    private int skoId;
    private int count;

    public TopList(int skoId, int count) {
        this.skoId = skoId;
        this.count = count;
    }

    public int getSkoId() {
        return skoId;
    }

    public int getCount() {
        return count;
    }
}