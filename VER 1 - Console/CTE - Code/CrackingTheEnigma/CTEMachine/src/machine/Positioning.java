package machine;

import java.io.Serializable;

public class Positioning implements Serializable {
    private final String left;
    private final String right;

    public Positioning(String left, String right){
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }
    public String getRight(){
        return right;
    }
}

