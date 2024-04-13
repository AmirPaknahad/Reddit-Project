import java.util.ArrayList;
import java.util.List;

public class Comment {
    String commentBody;
    Account user;
    int vote;
    List<Account> upVoteList;
    List<Account> downVoteList;

    public Comment(){
        upVoteList = new ArrayList<>();
        downVoteList = new ArrayList<>();
    }
    public void showComment(){
        System.out.println("By <" + user.getUsername() + ">");
        System.out.println("\t\t" + commentBody);
        if (vote > 0)
            System.out.println("\t\t" + vote + " UpVote");
        else if (vote < 0)
            System.out.println("\t\t" + vote + " DownVote");
        else
            System.out.println("\t\t" + vote + " Vote");
    }
    public void upVote(Account acc){
        if (!upVoteList.contains(acc)) {
            vote++;
            acc.upVoteCommentKarma();
            upVoteList.add(acc);
            if(downVoteList.contains(acc)){
                downVoteList.remove(acc);
                vote++;
                acc.upVoteCommentKarma();
            }
        }
        else{
            vote--;
            acc.downVoteCommentKarma();
            upVoteList.remove(acc);
        }
    }
    public void downVote(Account acc){
        if (!downVoteList.contains(acc)) {
            vote--;
            acc.downVoteCommentKarma();
            downVoteList.add(acc);
            if(upVoteList.contains(acc)){
                upVoteList.remove(acc);
                vote--;
                acc.downVoteCommentKarma();
            }
        }
        else{
            vote++;
            acc.upVoteCommentKarma();
            downVoteList.remove(acc);
        }
    }
}
