public class Post{
    // Post egenskaber
    private int     id;
    private String  title;
    private String  content;
    private boolean hidden;

    // Constructor som er tom
    public Post(){}

    // Constructor som sætter egenskaberne for Post objektet
    public Post(int id, String title, String content, boolean hidden){
        this.id      = id;
        this.title   = title;
        this.content = content;
        this.hidden  = hidden;
    }

    // Getters & setters <-- start -->
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    // Getters & setters <-- /slut -->
}