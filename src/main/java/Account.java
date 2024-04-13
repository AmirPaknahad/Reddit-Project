import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private String email;
    private String password;
    private String username;
    private int karma;
    private int commentKarma;
    private int postKarma;
    private List<Post> posts;
    private List<Subreddit> subreddits;
    private List<Post> explorePosts;
    static List<Account> users = new ArrayList<>();
    private List<Post> savedPosts;


    public Account(){
        posts = new ArrayList<>();
        subreddits = new ArrayList<>();
        explorePosts = new ArrayList<>();
        savedPosts = new ArrayList<>();
    }
    public int getKarma(){
        karma = commentKarma + postKarma;
        return karma;
    }
    public int getCommentKarma(){
        return commentKarma;
    }
    public int getPostKarma(){
        return postKarma;
    }
    public void upVotePostKarma(){
        postKarma++;
    }
    public void downVotePostKarma(){
        postKarma--;
    }
    public void upVoteCommentKarma(){
        commentKarma++;
    }
    public void downVoteCommentKarma(){
        commentKarma--;
    }
    public static void addUserToList(Account user){
        users.add(user);
    }
    public static boolean emailRepetition(String email){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).email.equals(email))
                return true;
        }
        return false;
    }
    public static boolean usernameRepetition(String username){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username))
                return true;
        }
        return false;
    }
    public void enterEmail(String email){
        this.email = email;
    }
    public void enterPassword(String password){
        this.password = password;
    }
    public void enterUsername(String username){
        this.username = username;
    }
    public static boolean checkEmailValidation(String email){
        String regex = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    public static boolean checkUsernameValidation(String username){
        String regex = "^[a-zA-Z]+[a-zA-Z0-9._-]{3,15}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }
    public String hashingPassword(String str){
        long mod1 = 1000000007, mod2 = 1000000009, hash1 = 0, hash2 = 0, pow1 = 1, pow2 = 1;
        int mab1 = 631, mab2 = 929;
        for (int i = 0; i < str.length(); i++){
            int chr = str.charAt(i) - ' ';
            hash1 = (hash1 + (pow1 * chr)) % mod1;
            pow1 = (pow1 * mab1) % mod1;
            hash2 = (hash2 + (pow2 * chr)) % mod2;
            pow2 = (pow2 * mab2) % mod2;
        }
        String hash = String.valueOf(hash1) + String.valueOf(hash2);
        return hash;
    }
    public void doHash(){
        this.password = hashingPassword(this.password);
    }


    public static int getIndexOfUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username))
                return i;
        }
        return -1;
    }
    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public static boolean checkPasswordValidation(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
        /*
        At least one lowercase letter
        At least one uppercase letter
        At least one digit
        Minimum length of 8 characters
        */
    }
    public boolean validatePassword(String password){
        return hashingPassword(password).equals(this.password);
    }
    public boolean validateUsername(String username){
        return username.equals(this.username);
    }
    public boolean validateEmail(String email){
        return email.equals(this.email);
    }
    public void joinToSubreddit(Subreddit sub){
        subreddits.add(sub);
    }
    public void leaveSubreddit(Subreddit sub){
        subreddits.remove(sub);
    }
    public void explorePosts(){

    }
    public int subredditSize(){
        return subreddits.size();
    }
    public void subredditName(){
        for (int i = 0; i < subreddits.size(); i++) {
            System.out.println("\t" + (i+1) + "- " + subreddits.get(i).getName());
        }
    }

    public String getSubName(int inx){
        return subreddits.get(inx).getName();
    }
    public void addPost(Post post){
        this.posts.add(post);
    }
    public boolean savedListContains(Post post){
        return savedPosts.contains(post);
    }
    public void savePost(Post post){
        savedPosts.add(post);
    }
    public void unSavePost(Post post){
        savedPosts.remove(post);
    }
    public void unSavePost(int index){
        savedPosts.remove(index);
    }
    public void deletePost(Post post){
        posts.remove(post);
    }
    public void showSavedPost(){
        for (int i = 0; i < savedPosts.size(); i++){
            System.out.print("\t" + (i + 1) + "- ");
            savedPosts.get(i).reviewPost();
        }
    }
    public int getSavedPostSize(){
        return savedPosts.size();
    }
    public void showSavedPost(int postIndex){
        savedPosts.get(postIndex).showPost();
    }
    public void upVoteSavedPost(int postIndex, Account acc){
        savedPosts.get(postIndex).upVote(acc);
    }
    public void downVoteSavedPost(int postIndex, Account acc){
        savedPosts.get(postIndex).downVote(acc);
    }
    public void addCommentToSavedPost(int postIndex ,Comment comment){
        savedPosts.get(postIndex).addComment(comment);
    }
    public int getSavedPostCommentSize(int postIndex){
        return savedPosts.get(postIndex).getCommentListSize();
    }
    public void showComment(int postIndex, int commentIndex){
        savedPosts.get(postIndex).showComment(commentIndex);
    }
    public void commentUpVote(int postIndex, int commentIndex, Account acc){
        savedPosts.get(postIndex).commentUpVote(commentIndex, acc);

    }
    public void commentDownVote(int postIndex, int commentIndex, Account acc){
        savedPosts.get(postIndex).commentDownVote(commentIndex, acc);
    }








    public int getProfilePostSize(){
        return posts.size();
    }
    public void showProfilePost(int postIndex){
        posts.get(postIndex).reviewPost();
    }
    public void upVoteProfilePost(int postIndex, Account acc){
        posts.get(postIndex).upVote(acc);
    }
    public void downVoteProfilePost(int postIndex, Account acc){
        posts.get(postIndex).downVote(acc);
    }
    public void addCommentToProfilePost(int postIndex ,Comment comment){
        posts.get(postIndex).addComment(comment);
    }
    public int getProfilePostCommentSize(int postIndex){
        return posts.get(postIndex).getCommentListSize();
    }
    public void showCommentProfile(int postIndex, int commentIndex){
        posts.get(postIndex).showComment(commentIndex);
    }
    public void commentUpVoteProfile(int postIndex, int commentIndex, Account acc){
        posts.get(postIndex).commentUpVote(commentIndex, acc);

    }
    public void commentDownVoteProfile(int postIndex, int commentIndex, Account acc){
        posts.get(postIndex).commentDownVote(commentIndex, acc);
    }
}
