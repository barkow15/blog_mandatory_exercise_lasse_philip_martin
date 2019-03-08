import java.sql.SQLException;

public interface BlogServiceInterface{
    public int    createPost(Post post) throws SQLException{}
    public void   deletePost(int id)    throws SQLException{}
    public void   editPost(Post post)   throws SQLException{}
    public Post   getPost(int id)       throws SQLException{}
    public Post[] getPosts()            throws SQLException{}
}