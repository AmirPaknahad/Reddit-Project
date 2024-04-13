import java.util.ArrayList;
import java.util.List;

public class Post {
    private String title;
    private List<Comment> commentList;
//    List<Account> userWhoCommented;
    private String body;
    private List<String> tags;
    private int vote;
    private Account user;
    private Subreddit subreddit;
    private List<Account> upVoteList;
    private List<Account> downVoteList;
    public static List<Post> postsList = new ArrayList<>();
    public Post(){
        commentList = new ArrayList<>();
        tags = new ArrayList<>();
        upVoteList = new ArrayList<>();
        downVoteList = new ArrayList<>();
    }
    public void reviewPost(){
        System.out.println("in <" + subreddit.getName() + "> by <" + user.getUsername() + ">");
        System.out.println("\t" + title);
        System .out.println("\t" + body);
        System.out.println();
        if (vote > 0)
            System.out.print("\t" + vote + " UpVote");
        else if (vote < 0)
            System.out.print("\t" + vote + " DownVote");
        else
            System.out.print("\t" + vote + " Vote");
        System.out.println(" | " + commentList.size() + " Comments");
    }
    public void showPost(){
        System.out.println("\n\n\n");
        System.out.println("\tin <" + subreddit.getName() + "> by <" + user.getUsername() + ">\n");
        System.out.println("\t" + title);
        System .out.println("\t" + body);
        System.out.println();
        System.out.print("\t");
        for (int i = 0; i < tags.size(); i++){
            System.out.print(tags.get(i) + " ");
        }
        System.out.println("\n");
        if (vote > 0)
            System.out.println("\t" + vote + " UpVote");
        else if (vote < 0)
            System.out.println("\t" + vote + " DownVote");
        else
            System.out.println("\t" + vote + " Vote");
        System.out.println("\tComments:");
        if (commentList.size() == 0){
            System.out.println("\tThere is no comment yet.");
        }
        else{
            for (int i = 0; i < commentList.size(); i++){
                System.out.print("\t\t" + (i + 1) + "- ");
                commentList.get(i).showComment();
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("\tBack");
        System.out.println("\tUpVote - DownVote");
        System.out.println("\tAddComment");


    }
    public void enterTitle(String title){
        this.title = title;
    }
    public void enterBody(String body){
        this.body = body;
    }
    public void enterTags(List<String> tags){
        this.tags = tags;
    }
    public void enterUser(Account user){
        this.user = user;
    }
    public void enterSubreddit(Subreddit subreddit){
        this.subreddit = subreddit;
    }
    public void upVote(Account acc){
        if (!upVoteList.contains(acc)) {
            vote++;
            acc.upVotePostKarma();
            upVoteList.add(acc);
            if(downVoteList.contains(acc)){
                downVoteList.remove(acc);
                vote++;
                acc.upVotePostKarma();
            }
        }
        else{
            vote--;
            acc.downVotePostKarma();
            upVoteList.remove(acc);
        }
    }
    public void downVote(Account acc){
        if (!downVoteList.contains(acc)) {
            vote--;
            acc.downVotePostKarma();
            downVoteList.add(acc);
            if(upVoteList.contains(acc)){
                upVoteList.remove(acc);
                vote--;
                acc.downVotePostKarma();
            }
        }
        else{
            vote++;
            acc.upVotePostKarma();
            downVoteList.remove(acc);
        }
    }
    public void addComment(Comment comment){
        commentList.add(comment);
    }
    public int getCommentListSize(){
        return commentList.size();
    }
    public void showComment(int commentIndex){
        System.out.print("\t\t");
        commentList.get(commentIndex).showComment();
    }
    public void commentUpVote(int commentIndex, Account acc){
        commentList.get(commentIndex).upVote(acc);

    }
    public void commentDownVote(int commentIndex, Account acc){
        commentList.get(commentIndex).downVote(acc);
    }
    public void deletePost(Post post){
        user.deletePost(post);
    }
}
