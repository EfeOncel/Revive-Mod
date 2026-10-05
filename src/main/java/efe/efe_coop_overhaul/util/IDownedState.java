package efe.efe_coop_overhaul.util;

public interface IDownedState {
    boolean isDowned();
    void setDowned(boolean downed);

    int getReviveTimer();
    void setReviveTimer(int timer);

}
