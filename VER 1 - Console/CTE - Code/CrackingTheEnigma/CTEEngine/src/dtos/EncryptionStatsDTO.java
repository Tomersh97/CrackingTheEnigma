package dtos;

import java.io.Serializable;

public class EncryptionStatsDTO implements Serializable {
    String beforeEncrypt;
    String afterEncrypt;
    long timeToEncrypt;

    public EncryptionStatsDTO(String before, String after, long time){
        beforeEncrypt = before;
        afterEncrypt = after;
        timeToEncrypt = time;
    }

    public EncryptionStatsDTO(String before){
        this.beforeEncrypt = before;
    }

    public String getAfterEncrypt(){
        return afterEncrypt;
    }

    public String getBeforeEncrypt(){
        return beforeEncrypt;
    }

    @Override
    public String toString() {
        return "<" + beforeEncrypt + "> --> <" + afterEncrypt + "> (" + timeToEncrypt + ") nano-seconds";
    }
}
