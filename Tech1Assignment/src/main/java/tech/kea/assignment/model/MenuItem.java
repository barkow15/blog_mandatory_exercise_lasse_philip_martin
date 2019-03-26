package tech.kea.assignment.model;

import java.lang.reflect.Array;

public class MenuItem {
    private int id = 0;
    private String name = null;
    private String url = null;
    private int parentId = 0;
    private int blogId = 0;
    private String htmlLink = null;
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

    public MenuItem(int id, String name, String url, int depth){
        this.setId(id);
        this.setName(name);
        this.setHTMLLink(url);
        this.setDepth(depth);
    }

    public MenuItem(int id, String name, int sortorder, int parentId, int blogId, String url)
    {
        this.setId(id);
        this.setName(name);
        this.setSortorder(sortorder);
        this.setParentId(parentId);
        if(url == null)
        {
            this.setBlogId(blogId);
        }
        else
        {
            this.setUrl(url);
        }
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
    public String getHTMLLink() {
        if(this.htmlLink == null)
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
        else
        {
            return this.htmlLink;
        }
    }

    /**
     * The html link is the HTML formated link, if this is set it is used, else the html link is build by the url or the blogId. To remove a preset HTML link, set it to null.
     */
    public void setHTMLLink(String htmlLink) {
        this.htmlLink = htmlLink;
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
        this.blogId = 0;
        this.url = url;
    }





}
