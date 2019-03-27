package tech.kea.assignment.model;

import java.lang.reflect.Array;

public class MenuItem {
    private int id = 0;
    private String name = null;
    private String url = null;
    private int parentId = 0;
    private int blogId = 0;
    private int sortorder = 100;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    private int depth = 0;

    public MenuItem(){

    }



    public MenuItem(int id, String name, int sortorder, int parentId, int blogId, String url)
    {
        this(id, name, sortorder, parentId, blogId, url, -1);
    }

    public MenuItem(int id, String name, int sortorder, int parentId, int blogId, String url, int depth)
    {
        this.setId(id);
        this.setName(name);
        this.setSortorder(sortorder);
        this.setParentId(parentId);
        if(url == null || url.equals(""))
        {
            this.setBlogId(blogId);
        }
        else
        {
            this.setUrl(url);
        }
        this.depth = depth;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getBlogId() {
        return blogId;
    }

    /**
     * Only a blogId or a url can be representated at anypoint. Setting a blogId as the main link will remove any url set
     */
    public void setBlogId(int blogId) {
        this.url = null;
        this.blogId = blogId;
    }

    /**
     * Dummy method for thymeleaf. A blogId must always be a int
     */
    public void setBlogId(String blogId) {
        this.blogId = 0;
    }

    public int getSortorder() {
        return sortorder;
    }

    public void setSortorder(int sortorder) {
        this.sortorder = sortorder;
    }

    /**
     * The html link is the HTML formated link, if this is set it is used, else the html link is build by the url or the blogId.
     */
    public String getHTMLLink()
    {
            if(this.url == null)
            {
                return "/posts/post/" + Integer.toString(this.blogId);
            }
            else
            {
                return this.url;
            }
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getFormatedName(){
        if(depth == 0){
            return("<b>" + name + "-" + depth + "</b>");
        }
        else{
            String fname = name;
            for(int i = 0; i < depth; i++){
                fname = "&nbsp;&nbsp;" + fname;
            }
            return(fname + "-" + depth);
        }
    }

    public String getIndent(){
        String indent = "";
        for(int i = 0; i < depth; i++){
            indent = "&nbsp;&nbsp;" + indent;
        }
        return(indent);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    /**
     * Only a blogId or a url can be representated at anypoint. Setting a url as the main link will remove any blogId set
     */
    public void setUrl(String url) {
        if(url != null && !url.equals(""))
        {
            this.blogId = 0;
            this.url = url;
        }
    }





}
