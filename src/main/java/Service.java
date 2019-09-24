import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service {

    private String host;
    private int port;
    private List<PlanedOutage> planedOutages;

    Service(String host, int port) {
        this.host = host;
        this.port = port;
        planedOutages = new ArrayList<>();
    }

    String getHost() {
        return host;
    }

    int getPort() {
        return port;
    }

    void addPlanedOutage(DateTime start, DateTime end) {
        this.planedOutages.add(new PlanedOutage(start, end));
    }

    void addPlanedOutage(PlanedOutage planedOutage) {
        this.planedOutages.add(planedOutage);
    }

    boolean isInAPlanedOutage() {
        for (PlanedOutage planedOutage : this.planedOutages) {
            if (planedOutage.getStart().isBeforeNow() && planedOutage.getEnd().isAfterNow())
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return port == service.port &&
                host.equals(service.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
