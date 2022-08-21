package machine;

import java.io.Serializable;

public class Reflect implements Serializable {
    private final int input;
    private final int output;

    public Reflect(int in, int out){
        input = in - 1;
        output = out - 1;
    }
    public int getInput(){
        return input;
    }

    public int getOutput(){
        return output;
    }
    public int reflect (int in){
        if(in == input)
            return output;
        else
            return input;
    }
}
