import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Subreddit {
    private String subredditName;
    private List<Post> posts;
    private List<Account> members;
    static List<Subreddit> subreddits = new ArrayList<>();
    private Account admin;
    private List<Account> moderators;
    private String type;
    private boolean canBeViewed;

    public Subreddit(Account admin){
        posts = new ArrayList<>();
        members = new ArrayList<>();
        moderators = new ArrayList<>();
        this.admin = admin;
    }
    public static int getIndexOfSubreddit(String Name) {
        for (int i = 0; i < subreddits.size(); i++) {
            if (subreddits.get(i).subredditName.equals(Name))
                return i;
        }
        return -1;
    }
    public static boolean checkSubredditNameValidation(String subredditName){
        String regex = "^[a-zA-Z0-9._-]{4,16}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(subredditName);

        return matcher.matches();
    }
    public static boolean nameRepetition(String name){
        for (int i = 0; i < subreddits.size(); i++) {
            if (subreddits.get(i).subredditName.equals(name))
                return true;
        }
        return false;
    }
    public void enterSubredditName(String subredditName){
        this.subredditName = subredditName;
    }
    public void enterSubredditType(String type){
        this.type = type;
    }
//    public void checkType
    public void joinToSubreddit(Account user){
        members.add(user);
    }
    public void leaveSubreddit(Account user){
        members.remove(user);
    }
    public String getName(){
        return this.subredditName;
    }
    public void deletePost(int postIndex){
        posts.get(postIndex).deletePost(posts.get(postIndex));
        Post.postsList.remove(posts.get(postIndex));
        posts.remove(postIndex);
    }

    public void addModerator(Account acc){
        if (!acc.getUsername().equals(admin.getUsername()))
            moderators.add(acc);
    }
    public int getIndexOfModerator(String name){
        for (int i = 0; i < moderators.size(); i++){
            if (moderators.get(i).getUsername().equals(name)){
                return i;
            }
        }
        return -1;
    }
    public void dismissModerator(int inx){
        moderators.remove(inx);
    }
    public void removeMember(Account acc, Subreddit sub){
        members.remove(acc);
        acc.leaveSubreddit(sub);
    }
    public boolean checkAdmin(Account acc){
        return admin == acc;
    }
    public boolean checkModerator(Account acc){
        return moderators.contains(acc);
    }
    public void showMembers(){
        System.out.println("\t-------------------------");
        for(int i = 0; i < members.size(); i++){
            System.out.println("\t " + members.get(i).getUsername());
        }
        System.out.println("\t " + admin.getUsername());
        System.out.println();
        System.out.println("\t-------------------------");
    }
    public void showModerators(){
        System.out.println("\t-------------------------");
        for(int i = 0; i < moderators.size(); i++){
            System.out.println("\t " + moderators.get(i).getUsername());
        }
        if(moderators.size() == 0){
            System.out.println("\tthere is no moderator!");
        }
        System.out.println();
        System.out.println("\t-------------------------");
    }
    public void viewSubreddit(Account user){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\t" + subredditName + " | " + (members.size() + 1) + " members");

        if (admin == user) {
            System.out.print("\tshowModerators / DeletePost");
            System.out.print(" / showMembers / removeMember");
            System.out.print(" / AddModerator / dismissModerator");
        }
        else if (moderators.contains(user) && findUser(user)){
                System.out.print("\tDeletePost / showMember / removeMember");
        }
        System.out.println();
        if (user != admin) {
            if (findUser(user)) {
                System.out.print("\tJoined");
                System.out.println("\t\t\t\t\t\tLeave");
            } else {
                System.out.print("\tNot Joined");
                System.out.println("\t\t\t\t\t\tjoin");
            }
        }

        System.out.println();
        for (int i = 1; i <= posts.size(); i++){
            System.out.print("\t" + i + "- ");
            posts.get(i - 1).reviewPost();
        }
        if (posts.size() == 0){
            System.out.println("\tThis community has not posted yet.");
        }
        System.out.println();
        System.out.println("\tBack");

    }
    public boolean findUser(Account user){
        for (int i = 0; i < members.size(); i++){
            if (members.get(i) == user)
                return true;
        }
        return false;
    }
    public void addPost(Post post){
        this.posts.add(post);
    }
    public int getPostSize(){
        return  posts.size();
    }
    public void showPost(int postInx){
        posts.get(postInx).showPost();
    }
    public void upVote(int postInx, Account acc){
        posts.get(postInx).upVote(acc);
    }
    public void downVote(int postInx, Account acc){
        posts.get(postInx).downVote(acc);
    }
    public void addComment(Comment comment,int postInx){
        posts.get(postInx).addComment(comment);
    }
    public int getCommentListSize(int postIndex){
        return posts.get(postIndex).getCommentListSize();
    }
    public void showComment(int postIndex, int commentIndex){
        posts.get(postIndex).showComment(commentIndex);
    }
    public void commentUpVote(int postIndex, int commentIndex, Account acc){
        posts.get(postIndex).commentUpVote(commentIndex, acc);

    }
    public void commentDownVote(int postIndex, int commentIndex, Account acc){
        posts.get(postIndex).commentDownVote(commentIndex, acc);
    }
    public Post returnPost(int postIndex){
        return posts.get(postIndex);
    }
    public void savePost(Account acc, int postIndex){
        acc.savePost(posts.get(postIndex));
    }
    public void unSavePost(Account acc, int postIndex){
        acc.unSavePost(posts.get(postIndex));
    }
}
