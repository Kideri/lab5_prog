public class WorkException extends Exception {
    private String exceptionMessage;
    private Human causer;
    private double success;
    private int successRate;

    public WorkException(String exceptionMessage, Human causer, double success, int successRate) {
        this.exceptionMessage = exceptionMessage;
        this.causer = causer;
        this.success = success;
        this.successRate = successRate;
    }

    public String getMessage () {
        return (exceptionMessage + "\n"+
                "Causer is: " + causer + "\n"+
                "Success rate was " + successRate + "%\n"+
                "But causer have " + success);
    }
}
