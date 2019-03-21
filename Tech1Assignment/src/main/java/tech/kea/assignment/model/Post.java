package tech.kea.assignment.model;

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
    public int getPostId() { return id; }
    public void setPostId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }

    /**
     *
     * @param  maxCharacters
     * @return this.content formarteret efter hvor mange karakterer som content max må være.
     */
    public String getContentPreview(int maxCharacters){
        String  contentPreview = "";
        int     contentCharLength = this.content.length();


        if(contentCharLength >= maxCharacters){
            // Forkort vores contentPreview til at være max (maxCharacters) karakterer hvis det er over (maxCharacters) karakterer
            contentPreview = this.content.substring(0, maxCharacters) + "...";
        }else{
            contentPreview = this.content;
        }
        return contentPreview;
    }
    public void setContent(String content) { this.content = content; }
    public boolean isHidden() { return hidden; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }
    // Getters & setters <-- /slut -->
}