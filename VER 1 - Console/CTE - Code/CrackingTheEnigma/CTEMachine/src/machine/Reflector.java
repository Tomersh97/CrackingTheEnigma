package machine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Reflector implements Serializable {
    private final String reflectorId;
    private final Map<Integer,Reflect> reflections = new HashMap<>();

    public Reflector(String id, List<Reflect> reflectList){
        reflectorId = id;
        for(Reflect reflect: reflectList){
            reflections.put(reflect.getInput(), reflect);
        }
        for(Reflect reflect: reflectList){
            reflections.put(reflect.getOutput(),reflect);
        }
    }

    public String getReflectorId(){
        return reflectorId;
    }
    public int getReflection(Integer input) {
        return reflections.get(input).reflect(input);
    }


}
