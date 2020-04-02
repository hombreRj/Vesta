package gg.scenarios.vesta.managers;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class IPManager {

    private String ip;
    private Date date;

    public IPManager(String ip) {
        this.ip = ip;
        date = Date.from(Instant.now());
    }

    @Override
    public String toString() {
        return ip + " " + date.toString();
    }
}
