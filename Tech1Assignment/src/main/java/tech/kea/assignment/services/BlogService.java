package tech.kea.assignment.services;


import org.springframework.stereotype.Service;
import tech.kea.assignment.model.Post;
import tech.kea.assignment.repository.BlogRepoInterface;
import tech.kea.assignment.repository.BlogRepoMySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class BlogService implements BlogServiceInterface{

    private BlogRepoInterface blogRepo;

    // Default constructor
    public BlogService() throws SQLException, ClassNotFoundException {
        this(new BlogRepoMySQL());
    }

    // Overloaded constructor med BlogRepoInterface som parameter
    public BlogService(BlogRepoInterface blogRepoInterface){

        // Kalder default constructor med BlogRepointerface som paramater
        this.blogRepo = blogRepoInterface;
    }

    @Override
    public int createPost(Post post) throws SQLException {
        // Sætter vores post objekt til variablen 'p'
        Post p = post;

        // Vi "pakker" vores post objekt ud og gemmer dettes attributter i variabler
        String  postContent  = p.getContent();
        String  postTitle    = p.getTitle();
        boolean postHidden   = p.isHidden();

        /*
        Vi bruger vores repository lag via blogRepo og laver en ny post post
        ved at køre createPost metoden med de udpakkede attributter som parametre
        og returner int
        */
        return blogRepo.createPost(postContent, postTitle, postHidden);
    }

    @Override
    public void deletePost(int id) throws SQLException {
        // Kør deletePost metoden med en int som parameter for at slette en post post
        blogRepo.deletePost(id);
    }

    @Override
    public void editPost(Post post) throws SQLException {
        // Sætter vores post objekt til variablen 'p'
        Post p = post;

        // Vi "pakker" vores post objekt ud og gemmer dettes attributter i variabler
        int     postID       = p.getPostId();
        String  postContent  = p.getContent();
        String  postTitle    = p.getTitle();
        boolean postHidden   = p.isHidden();

        /*
        Vi bruger vores repository lag via blogRepo og laver en ny post post
        ved at køre editPost metoden med de udpakkede attributter som parametre
        */
        blogRepo.editPost(postID, postTitle, postContent, postHidden);
    }

    @Override
    public Post getPost(int id) throws SQLException {
        // Kør getPost metoden med en int som parameter for at hente en enkelt post post
        ResultSet postResult = blogRepo.getPost(id);

        // Initialiserer vores post variabel af typen Post objekt og sætter det til null
        Post post = null;

        /*
        Hvis vores getPost metode i vores repository
        lag returnere et resultat set arbejder vi videre med det
        */
        if(postResult.next()){
            int     postID       = postResult.getInt("id");
            String  postTitle    = postResult.getString("title");
            String  postContent  = postResult.getString("content");
            boolean postHidden   = postResult.getBoolean("hidden");

            post = new Post(postID, postTitle, postContent, postHidden);
        }

        // Returner post objektet
        return post;
    }

    @Override
    public ArrayList<Post> getPosts() throws SQLException {
        // Initialiserer vores posts variabel af typen arrayList og sætter den til null som standard
        ArrayList<Post> posts = new ArrayList<>();

        ResultSet rs = blogRepo.getPosts();

        // Så længe vi kan hente posts henter vi den enkelte post via en while løkke
        while(rs.next()) {
            // Tilføjer hver post til vores ArrayList<Post> som et Post objekt
            posts.add(
                    new Post(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getBoolean("hidden")
                    )
            );
        }

        // Returnerer afslutningsvis vores ArrayList<Post>
        return posts;
    }

    @Override
    public ArrayList<Post> getAllPosts() throws SQLException {
        // Initialiserer vores posts variabel af typen arrayList og sætter den til null som standard
        ArrayList<Post> posts = new ArrayList<>();

        ResultSet rs = blogRepo.getAllPosts();

        // Så længe vi kan hente posts henter vi den enkelte post via en while løkke
        while(rs.next()) {
            // Tilføjer hver post til vores ArrayList<Post> som et Post objekt
            posts.add(
                new Post(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getBoolean("hidden")
                )
            );
        }

        // Returnerer afslutningsvis vores ArrayList<Post>
        return posts;
    }
}