package Assignments_JDBC_1_to_6.apps;

import Weeks_With_JAVA.Assignments_JDBC_1_to_6.apps.MonitorUpdater;

class MonitorUpdaterTest {
    MonitorUpdater monitorUpdater = new MonitorUpdater();

    @org.junit.jupiter.api.Test
    void resetVarsChildren() {
        assert monitorUpdater.getChildren()==0;
        assert monitorUpdater.getChildren()!=1;
    }
    @org.junit.jupiter.api.Test
    void resetVarsElves() {
        assert monitorUpdater.getElves()==0;
        assert monitorUpdater.getElves()!=1;
    }
    @org.junit.jupiter.api.Test
    void resetVarsNice() {
        assert monitorUpdater.getNice()==0;
        assert monitorUpdater.getNice()!=1;
    }
}