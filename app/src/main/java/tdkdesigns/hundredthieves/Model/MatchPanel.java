package tdkdesigns.hundredthieves.Model;

public class MatchPanel {
    private String Opponent, Date, Outcome;

    public MatchPanel() {
    }

    public MatchPanel(String opponent, String date, String outcome) {
        Opponent = opponent;
        Date = date;
        Outcome = outcome;
    }

    public String getOpponent() {
        return Opponent;
    }

    public void setOpponent(String opponent) {
        Opponent = opponent;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOutcome() {
        return Outcome;
    }

    public void setOutcome(String outcome) {
        Outcome = outcome;
    }
}
