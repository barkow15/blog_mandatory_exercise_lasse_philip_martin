package tech.kea.assignment.services;

public class Logging {
    String callerclass = "";

    public Logging()
    {
        this.callerclass = "";
    }

    public Logging(String callerclass)
    {
        this.callerclass = callerclass + " > ";
    }
    public void log(String str)
    {
        log(str, 0);
    }

    public void log(String str, int indent)
    {
        String s = "";
        for(int i = 0; i < indent;i++)
        {
            s = s + "\t";
        }
        s = s + str;
        System.out.println(callerclass + s);
    }
}
