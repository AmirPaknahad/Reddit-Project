import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        runMenu();
        in.close();

    }
    public static void mainMenu(){
        clearScreen();
        System.out.println();
        System.out.println("\t\t1- Sign Up");
        System.out.println("\t\t2- Log In");
        System.out.println("\t\t3- Exit");
        System.out.println();
        System.out.println();
        System.out.print("\t Please enter the number of section: ");
    }
    public static int runMenu(){
        clearScreen();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\t Welcome to Reddit!");
        String str = "";
        while (!str.equals("3")) {
            mainMenu();
            str = in.nextLine();
            while (!str.equals("1") && !str.equals("2") && !str.equals("3")) {
                clearScreen();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("\t please enter the number correctly!");
                mainMenu();
                str = in.nextLine();
            }
            if (str.equals("1")) {
                clearScreen();
                Singup();
            } else if (str.equals("2")) {
                clearScreen();
                login();
            }
        }
        return 0;

    }
    public static int Singup(){
        clearScreen();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\tyou should create an Account! any time you give up, enter \"Exit\".");
        System.out.println();
        System.out.print("\tPlease enter your Email: ");
        String email = in.nextLine();
        if (email.equals("Exit"))
            return 0;

        while (!Account.checkEmailValidation(email) || Account.emailRepetition(email)){
            if (Account.emailRepetition(email)){
                System.out.println();
                System.out.println();
                System.out.println("\tYou already have a Account with this email,\n\tplease use another email or log in(type \"Exit\" in input case)");
                System.out.print("\tPlease enter your Email: ");
                email = in.nextLine();
                if (email.equals("Exit"))
                    return 0;
            }
            if (!Account.checkEmailValidation(email)) {
                System.out.println();
                System.out.println();
                System.out.println("\tinvalid email,");
                System.out.print("\tPlease enter your Email: ");
                email = in.nextLine();
                if (email.equals("Exit"))
                    return 0;
            }
        }

        System.out.println();
        System.out.print("\tPlease enter your Password: ");
        String pass = in.nextLine();
        if (pass.equals("Exit"))
            return 0;

        System.out.println();
        System.out.print("\tPlease enter your username (username must have at least 4 and maximum 16 characters): ");
        String us = in.nextLine();
        if (us.equals("Exit"))
            return 0;

        while (!Account.checkUsernameValidation(us) || Account.usernameRepetition(us)){
            if (Account.usernameRepetition(us)){
                System.out.println();
                System.out.println();
                System.out.println("\tThis username has been already taken!");
                System.out.print("\tPlease enter your username (username must have at least 4 and maximum 16 characters): ");
                us = in.nextLine();
                if (us.equals("Exit"))
                    return 0;
            }
            if (!Account.checkUsernameValidation(us)) {
                System.out.println();
                System.out.println();
                System.out.println("\tinvalid username,");
                System.out.print("\tPlease enter your username (username must have at least 4 and maximum 16 characters): ");
                us = in.nextLine();
                if (us.equals("Exit"))
                    return 0;
            }
        }
        Account user = new Account();
        user.enterEmail(email);
        user.enterPassword(pass);
        user.doHash();
        user.enterUsername(us);
        Account.addUserToList(user);
        System.out.println();
        System.out.println();
        System.out.println("\tYour Account successfully created! you can Log in anytime you want!");
        System.out.print("\tenter anything to return to menu...");
        String str = in.nextLine();
        return 0;
    }
    public static int login(){
        clearScreen();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\tYou have to log in into your Account, any time you give up, enter \"Exit\".");
        System.out.println();
        System.out.print("\tPlease enter your username: ");
        String username = in.nextLine();
        if (username.equals("Exit"))
            return 0;
        int inx = Account.getIndexOfUser(username);

        while (inx < 0){
            System.out.println();
            System.out.println("\tuser not found! try again.");
            System.out.print("\tPlease enter your username: ");
            username = in.nextLine();
            if (username.equals("Exit"))
                return 0;
            inx = Account.getIndexOfUser(username);
        }
        System.out.println();
        System.out.print("\tPlease enter your password: ");
        String pass = in.nextLine();
        if (pass.equals("Exit"))
            return 0;
        while (!Account.users.get(inx).validatePassword(pass)){
            System.out.println();
            System.out.println("\tincorrect password!");
            System.out.print("\tPlease enter your password: ");
            pass = in.nextLine();
            if (pass.equals("Exit"))
                return 0;
        }
        System.out.println();
        System.out.println();
        System.out.print("\tyou log in successfully. please enter anything to go into your account...");
        String str = in.nextLine();
        if (str.equals("Exit"))
            return 0;
        redditMenu(inx);



        return 0;
    }
    public static int redditMenu(int userIndx){
        System.out.println();
        System.out.println();
        System.out.print("\tSearch");
        System.out.print("\t\t\t\t");
        System.out.println("Profile");
        postReviewInExplore();
        for (int i = 0; i < 12 - Post.postsList.size() * 5; i++ )
            System.out.println();
        System.out.println();
        System.out.print("\tCommunity-");
        System.out.print("CreatePost-");
        System.out.println("Saved");
        System.out.println();
        System.out.print("\tenter what you want: ");
        String str = in.nextLine();
        if (str.equals("Community"))
            communityPage(userIndx);
        else if(str.equals("CreatePost"))
            createPostPage(userIndx);
        else if(str.equals("Saved")){
            savedPostPage(userIndx);
        }
        else if(str.equals("Search")){
            SearchPage(userIndx);
        }
        else if(str.equals("Profile")){
            profile(userIndx, userIndx);
        }
        else if(str.equals("Back") || str.equals("Exit") || str.equals("LogOut")){
            return 0;
        }
        else{
            redditMenu(userIndx);
        }

        return 0;
    }
    public static int profile(int userIndex, int user){
        System.out.println();
        System.out.println();
        System.out.println();
        if (user == userIndex){
            System.out.println("\tusername: " + Account.users.get(userIndex).getUsername());
            System.out.println("\temail: " + Account.users.get(userIndex).getEmail());
            System.out.println("\tkarma: " + Account.users.get(userIndex).getKarma());
            System.out.println("\tfollowers: 0 | following : 0");
            System.out.println("\tcommunities: ");
            for(int i = 0; i < Account.users.get(userIndex).subredditSize(); i++){
                System.out.println("\t" + Account.users.get(userIndex).getSubName(i));
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("\tKarma / posts / changeEmail / changeUsername / changePassword / Back");
            System.out.println();
            System.out.print("\tenter what you want: ");
            String str = in.nextLine();
            if(str.equals("Back")){
                redditMenu(userIndex);
                return 0;
            }
            else if(str.equals("Karma")){
                System.out.println("\tPostKarma: " + Account.users.get(userIndex).getPostKarma());
                System.out.println("\tCommentKarma: " + Account.users.get(userIndex).getCommentKarma());
                System.out.print("\t...");
                String pass = in.nextLine();
                profile(userIndex, user);
            }
            else if(str.equals("posts")){
                profilePostPage(userIndex, user);
            }
            else if(str.equals("changeEmail")){
                System.out.println();
                System.out.println("\tanytime you give up, enter \"Exit\".");
                System.out.print("\tenter new eamil: ");
                String email = in.nextLine();
                if (email.equals("Exit"))
                    profile(userIndex, user);
                while (!Account.checkEmailValidation(email) || Account.emailRepetition(email)){
                    if (Account.emailRepetition(email)){
                        System.out.println();
                        System.out.println();
                        System.out.println("\tYou cant use this email");
                        System.out.print("\tPlease enter your Email: ");
                        email = in.nextLine();
                        if (email.equals("Exit"))
                            profile(userIndex, user);
                    }
                    if (!Account.checkEmailValidation(email)) {
                        System.out.println();
                        System.out.println();
                        System.out.println("\tinvalid email,");
                        System.out.print("\tPlease enter your Email: ");
                        email = in.nextLine();
                        if (email.equals("Exit"))
                            profile(userIndex, user);
                    }
                }
                System.out.println();
                System.out.println("\tyour email successfully changed!");
                Account.users.get(userIndex).enterEmail(email);
                System.out.print("\t...");
                String pass = in.nextLine();
                profile(userIndex, user);
            }
            else if(str.equals("changeUsername")){
                System.out.println();
                System.out.println("\tanytime you give up, enter \"Exit\".");
                System.out.print("\tenter new username: ");
                String username = in.nextLine();
                if (username.equals("Exit"))
                    profile(userIndex, user);
                while (!Account.checkUsernameValidation(username) || Account.usernameRepetition(username)){
                    if (Account.usernameRepetition(username)){
                        System.out.println();
                        System.out.println();
                        System.out.println("\tThis username has been already taken!");
                        System.out.print("\tPlease enter your username (username must have at least 4 and maximum 16 characters): ");
                        username = in.nextLine();
                        if (username.equals("Exit"))
                            profile(userIndex, user);
                    }
                    if (!Account.checkUsernameValidation(username)) {
                        System.out.println();
                        System.out.println();
                        System.out.println("\tinvalid username,");
                        System.out.print("\tPlease enter your username (username must have at least 4 and maximum 16 characters): ");
                        username = in.nextLine();
                        if (username.equals("Exit"))
                            profile(userIndex, user);
                    }
                }
                System.out.println();
                System.out.println("\tyour username successfully changed!");
                Account.users.get(userIndex).enterUsername(username);
                System.out.print("\t...");
                String pass = in.nextLine();
                profile(userIndex, user);
            }
            else if(str.equals("changePassword")){
                System.out.println();
                System.out.println("\tanytime you give up, enter \"Exit\".");
                System.out.print("\tenter new password: ");
                String username = in.nextLine();
                if (username.equals("Exit"))
                    profile(userIndex, user);
                System.out.println();
                System.out.println("\tyour password successfully changed!");
                Account.users.get(userIndex).enterPassword(username);
                Account.users.get(userIndex).doHash();
                System.out.print("\t...");
                String pass = in.nextLine();
                profile(userIndex, user);
            }
            else{
                profile(userIndex, user);
            }
        }
        else{
            System.out.println("\tusername: " + Account.users.get(user).getUsername());
            System.out.println("\tkarma: " + Account.users.get(user).getKarma());
            System.out.println("\tfollowers: 0 | following : 0");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("\tKarma / posts / Back");
            System.out.println();
            System.out.print("\tenter what you want: ");
            String str = in.nextLine();
            if(str.equals("Back")){
                redditMenu(userIndex);
                return 0;
            }
            else if(str.equals("Karma")){
                System.out.println("\tPostKarma: " + Account.users.get(user).getPostKarma());
                System.out.println("\tCommentKarma: " + Account.users.get(user).getPostKarma());
                System.out.print("\t...");
                String pass = in.nextLine();
                profile(userIndex, user);
            }
            else if(str.equals("posts")){
                profilePostPage(user, userIndex);
            }
        }

        return 0;
    }
    public static int profilePostPage(int userIndex, int user){
        System.out.println("\t-------------------------");
        Account.users.get(userIndex).showSavedPost();
        System.out.println();
        System.out.println("\tBack");
        System.out.println();
        System.out.print("Enter what you want: ");
        String str = in.nextLine();
        if (str.equals("Back")){
            profile(userIndex, user);
            return 0;
        }
        try {
            int profilePostIndex = Integer.parseInt(str);
            profilePostIndex--;
            if(profilePostIndex < Account.users.get(userIndex).getProfilePostSize()){
                Account.users.get(userIndex).showProfilePost(profilePostIndex);
                postReviewForProfilePost(userIndex, user, profilePostIndex);
            }

            else{
                System.out.println("\tinvalid input");
                profilePostPage(userIndex, user);
            }
        }
        catch (NumberFormatException e) {
            System.out.println("\tinvalid input");
            profilePostPage(userIndex, user);
        }

        System.out.println("\t-------------------------");
        return 0;
    }
    public static int postReviewForProfilePost(int userIndex,int user ,int profilePostIndex){
        System.out.print("\tEnter what you want: ");
        String str = in.nextLine();
        if (str.equals("Back")){
            profilePostPage(userIndex, user);
            return 0;
        }
        else if(str.equals("UpVote")){
            Account.users.get(userIndex).upVoteProfilePost(profilePostIndex, Account.users.get(userIndex));
            postReviewForProfilePost(userIndex, user, profilePostIndex);
        }
        else if(str.equals("DownVote")){
            Account.users.get(userIndex).downVoteProfilePost(profilePostIndex, Account.users.get(userIndex));
            postReviewForProfilePost(userIndex, user, profilePostIndex);
        }
        else if(str.equals("AddComment")){
            System.out.println();
            System.out.print("\tEnter the comment: ");
            String cmnt = in.nextLine();
            Comment comment = new Comment();
            comment.commentBody = cmnt;
            comment.user = Account.users.get(userIndex);
            Account.users.get(userIndex).addCommentToProfilePost(profilePostIndex ,comment);
            postReviewForProfilePost(userIndex, user, profilePostIndex);

        }
        else{
            try {
                int commentIndex = Integer.parseInt(str);
                commentIndex--;
                if (commentIndex < Account.users.get(userIndex).getProfilePostCommentSize(profilePostIndex)){
                    commentForPostReviewForProfilePost(userIndex, user, profilePostIndex, commentIndex);
                }
                else{
                    System.out.println("\tinvalid input.");
                    postReviewForProfilePost(userIndex, user, profilePostIndex);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("\tinvalid input.");
                postReviewForProfilePost(userIndex, user, profilePostIndex);
            }
        }
        return 0;
    }
    public static int commentForPostReviewForProfilePost(int userIndex, int user ,int profilePostIndex, int commentIndex){
        System.out.println();
        Account.users.get(userIndex).showComment(profilePostIndex, commentIndex);
        System.out.println();
        System.out.print("\tEnter what you want(UpVote / DownVote / Back): ");
        String str = in.nextLine();
        if (str.equals("Back")){
            postReviewForProfilePost(userIndex, user, profilePostIndex);
            return 0;
        }
        else if(str.equals("UpVote")){
            Account.users.get(userIndex).commentUpVoteProfile(profilePostIndex, commentIndex, Account.users.get(userIndex));
            postReviewForProfilePost(userIndex, user, profilePostIndex);
        }
        else if(str.equals("DownVote")){
            Account.users.get(userIndex).commentDownVoteProfile(profilePostIndex, commentIndex, Account.users.get(userIndex));
            postReviewForProfilePost(userIndex, user, profilePostIndex);
        }
        else{
            System.out.println();
            System.out.print("\tinvalid input");
            postReviewForProfilePost(userIndex, user, profilePostIndex);
            return 0;
        }
        return 0;
    }
    public static int SearchPage(int userIndex) {
        System.out.println("\tfor searching communities, you should use a phrase like \"r/communityName\"");
        System.out.println("\tand for searching users, you should use \"u/userName\"");
        System.out.println("\tyou can enter \"Exit\" anytime you want.");
        System.out.print("\tEnter what you want to search: ");
        String str = in.nextLine();
        if (str.equals("Exit")){
            redditMenu(userIndex);
        }
        String type = str.substring(0,2);
        str = str.substring(2);
        if (type.equals("u/")){
            int user = Account.getIndexOfUser(str);
            if (user < 0){
                System.out.println("\tnothing found!");
                profile(userIndex, user);
            }
        }
        else if (type.equals("r/")){
            int sub = Subreddit.getIndexOfSubreddit(str);
            if (sub < 0){
                System.out.println("\tnothing found!");
            }
            else{
                communityReview(userIndex, sub);
            }
        }
        else{
            System.out.println("\tnothing found!");
        }
        return 0;
    }
    public static int savedPostPage(int userIndex){
        System.out.println("\t-------------------------");
        Account.users.get(userIndex).showSavedPost();
        System.out.println();
        System.out.println("\tBack");
        System.out.println();
        System.out.print("Enter what you want: ");
        String str = in.nextLine();
        if (str.equals("Back")){
            redditMenu(userIndex);
            return 0;
        }
        try {
            int savedPostIndex = Integer.parseInt(str);
            savedPostIndex--;
            if(savedPostIndex < Account.users.get(userIndex).getSavedPostSize()){
                Account.users.get(userIndex).showSavedPost(savedPostIndex);
                postReviewForSavedPost(userIndex, savedPostIndex);
            }

            else{
                System.out.println("\tinvalid input");
                savedPostPage(userIndex);
            }
        }
        catch (NumberFormatException e) {
            System.out.println("\tinvalid input");
            savedPostPage(userIndex);
        }

        System.out.println("\t-------------------------");
        return 0;
    }
    public static int postReviewForSavedPost(int userIndex, int savedPostIndex){
        System.out.println("\tUnSave");
        System.out.print("\tEnter what you want: ");
        String str = in.nextLine();
        if (str.equals("Back")){
            savedPostPage(userIndex);
            return 0;
        }
        else if(str.equals("UnSave")){
            Account.users.get(userIndex).unSavePost(savedPostIndex);
            savedPostPage(userIndex);
        }
        else if(str.equals("UpVote")){
            Account.users.get(userIndex).upVoteSavedPost(savedPostIndex, Account.users.get(userIndex));
            postReviewForSavedPost(userIndex, savedPostIndex);
        }
        else if(str.equals("DownVote")){
            Account.users.get(userIndex).downVoteSavedPost(savedPostIndex, Account.users.get(userIndex));
            postReviewForSavedPost(userIndex, savedPostIndex);
        }
        else if(str.equals("AddComment")){
            System.out.println();
            System.out.print("\tEnter the comment: ");
            String cmnt = in.nextLine();
            Comment comment = new Comment();
            comment.commentBody = cmnt;
            comment.user = Account.users.get(userIndex);
            Account.users.get(userIndex).addCommentToSavedPost(savedPostIndex ,comment);
            postReviewForSavedPost(userIndex, savedPostIndex);

        }
        else{
            try {
                int commentIndex = Integer.parseInt(str);
                commentIndex--;
                if (commentIndex < Account.users.get(userIndex).getSavedPostCommentSize(savedPostIndex)){
                    commentForPostReviewForSavedPost(userIndex, savedPostIndex, commentIndex);
                }
                else{
                    System.out.println("\tinvalid input.");
                    postReviewForSavedPost(userIndex, savedPostIndex);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("\tinvalid input.");
                postReviewForSavedPost(userIndex, savedPostIndex);
            }
        }
        return 0;
    }
    public static int commentForPostReviewForSavedPost(int userIndex, int savedPostIndex, int commentIndex){
        System.out.println();
        Account.users.get(userIndex).showComment(savedPostIndex, commentIndex);
        System.out.println();
        System.out.print("\tEnter what you want(UpVote / DownVote / Back): ");
        String str = in.nextLine();
        if (str.equals("Back")){
            postReviewForSavedPost(userIndex, savedPostIndex);
            return 0;
        }
        else if(str.equals("UpVote")){
            Account.users.get(userIndex).commentUpVote(savedPostIndex, commentIndex, Account.users.get(userIndex));
            postReviewForSavedPost(userIndex, savedPostIndex);
        }
        else if(str.equals("DownVote")){
            Account.users.get(userIndex).commentDownVote(savedPostIndex, commentIndex, Account.users.get(userIndex));
            postReviewForSavedPost(userIndex, savedPostIndex);
        }
        else{
            System.out.println();
            System.out.print("\tinvalid input");
            postReviewForSavedPost(userIndex, savedPostIndex);
            return 0;
        }
        return 0;
    }
    public static void postReviewInExplore(){
        System.out.println();
        for (int i = Post.postsList.size() - 1; i >= 0; i--){
            System.out.print("\t" + (Post.postsList.size() - i) + "- ");
            Post.postsList.get(i).reviewPost();
            System.out.println("\t---------------------");
        }
        if (Post.postsList.size() == 0){
            System.out.println("\tNothing to show.");
        }
    }
    public static int communityPage(int userIndx) {
        System.out.println();
        System.out.println("\tCommunity you joined: ");
        int subSize = Account.users.get(userIndx).subredditSize();
        if (subSize == 0) {
            System.out.println("\tyou have not joined any community yet!");
            for (int i = 0; i < 11; i++) {
                System.out.println();
            }
        } else {
            Account.users.get(userIndx).subredditName();
            for (int i = 0; i < 11 - subSize; i++) {
                System.out.println();
            }
        }
        System.out.println("\tCreate");
        System.out.println("\tSearch");
        System.out.println("\tBack");
        System.out.println();
        System.out.print("\tenter what you want: ");
        String str = in.nextLine();
        if (str.equals("Exit"))
            return 0;



        else if (str.equals("Create")) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("\tyou are creating a Community! any time you give up, enter \"Exit\".");
            System.out.println();
            System.out.print("\tPlease enter name (Name must have at least 3 and maximum 24 characters): ");
            String name = in.nextLine();
            if (name.equals("Exit")) {
                communityPage(userIndx);
                return 0;
            }
            while (!Subreddit.checkSubredditNameValidation(name) || Subreddit.nameRepetition(name)) {
                if (Subreddit.nameRepetition(name)) {
                    System.out.println();
                    System.out.println();
                    System.out.println("\tThis name has been already taken!");
                    System.out.print("\tPlease enter name (Name must have at least 3 and maximum 24 characters): ");
                    name = in.nextLine();
                    if (name.equals("Exit")) {
                        communityPage(userIndx);
                        return 0;
                    }
                }
                if (!Subreddit.checkSubredditNameValidation(name)) {
                    System.out.println();
                    System.out.println();
                    System.out.println("\tinvalid name,");
                    System.out.print("\tPlease enter name (Name must have at least 3 and maximum 24 characters): ");
                    name = in.nextLine();
                    if (name.equals("Exit")) {
                        communityPage(userIndx);
                        return 0;
                    }
                }
            }
            System.out.println();
            System.out.print("\tPlease enter type (Public / Restricted / Private): ");
            String type = in.nextLine();
            if (name.equals("Exit")) {
                communityPage(userIndx);
                return 0;
            }
            while (!type.equals("Public") && !type.equals("Restricted") && !type.equals("Private")) {
                System.out.println("\tinvalid type,");
                System.out.print("\tPlease enter type (Public / Restricted / Private): ");
                type = in.nextLine();
                if (name.equals("Exit")) {
                    communityPage(userIndx);
                    return 0;
                }
            }
            Subreddit sub = new Subreddit(Account.users.get(userIndx));
            sub.enterSubredditName(name);
            sub.enterSubredditType(type);
            Account.users.get(userIndx).joinToSubreddit(sub);
            Subreddit.subreddits.add(sub);
            System.out.println();
            System.out.println("\tYour community successfully created!");
            System.out.print("\tenter anything to Back to community page...");
            str = in.nextLine();
            communityPage(userIndx);
        }
        else if (str.equals("Back")) {
            redditMenu(userIndx);
            return 0;
        }
        else if (str.equals("Search")){
            System.out.println();
            System.out.println("\tif you want to quit, enter \"Back\".");
            System.out.print("\tenter the name of the community: ");
            String comname = in.nextLine();
            if (comname.equals("Back"))
                communityPage(userIndx);
            while (!Subreddit.nameRepetition(comname)){
                System.out.println();
                System.out.println("\tcommunity not found");
                System.out.print("\tenter the name of the community: ");
                comname = in.nextLine();
                if (comname.equals("Back"))
                    communityPage(userIndx);
            }
            communityReview(userIndx,Subreddit.getIndexOfSubreddit(comname));


        }
        else{
            if (subSize > 0 && str.length() == 1) {
                try {
                    int number = Integer.parseInt(str);
                    number--;
                    if (number < subSize) {
                        number = Subreddit.getIndexOfSubreddit(Account.users.get(userIndx).getSubName(number));
                        communityReview(userIndx, number);
                    }
                    else{
                        System.out.println("\tinvalid input.");
                        communityPage(userIndx);
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("\tinvalid input.");
                    communityPage(userIndx);
                }
            }
            else{
                System.out.println("\tinvalid input.");
                communityPage(userIndx);
            }
        }
        redditMenu(userIndx);
        return 0;
    }
    public static int communityReview(int userIndx, int subIndx){
        Subreddit.subreddits.get(subIndx).viewSubreddit(Account.users.get(userIndx));
        System.out.println();
        System.out.print("\tenter what you want: ");
        String s = in.nextLine();

        if (Subreddit.subreddits.get(subIndx).checkAdmin(Account.users.get(userIndx))){
            if (s.equals("showModerators")){
               Subreddit.subreddits.get(subIndx).showModerators();
               System.out.println();
               System.out.print("\tEnter anything... ");
               String chr = in.nextLine();
               communityReview(userIndx,subIndx);
            }
            else if(s.equals("DeletePost")){
                System.out.println();
                System.out.print("\tEnter the number of the post: ");
                String postinx = in.nextLine();
                try {
                    int inx = Integer.parseInt(postinx);
                    inx--;
                    if (inx < Subreddit.subreddits.get(subIndx).getPostSize()) {
                        Subreddit.subreddits.get(subIndx).deletePost(inx);
                        communityReview(userIndx,subIndx);
                    }
                    else{
                        System.out.println("\tinvalid input.");
                        communityReview(userIndx,subIndx);
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("\tinvalid input.");
                    communityReview(userIndx,subIndx);
                }
            }
            else if(s.equals("showMembers")){
                Subreddit.subreddits.get(subIndx).showMembers();
                System.out.println();
                System.out.print("\tEnter anything... ");
                String chr = in.nextLine();
                communityReview(userIndx,subIndx);
            }
            else if(s.equals("removeMember")){
                System.out.println();
                Subreddit.subreddits.get(subIndx).showMembers();
                System.out.println();
                System.out.print("\tEnter member's name: ");
                String name = in.nextLine();
                int index = Account.getIndexOfUser(name);
                if (index < 0){
                    System.out.println("\tinvalid input.");
                    communityReview(userIndx,subIndx);
                }
                else{
                    Subreddit.subreddits.get(subIndx).removeMember(Account.users.get(index), Subreddit.subreddits.get(subIndx));
                    communityReview(userIndx,subIndx);
                }
            }
            else if(s.equals("AddModerator")){
                System.out.println();
                Subreddit.subreddits.get(subIndx).showMembers();
                System.out.println();
                System.out.print("\tEnter member's name to be moderator: ");
                String name = in.nextLine();
                int index = Account.getIndexOfUser(name);
                if (index < 0){
                    System.out.println("\tinvalid input.");
                    communityReview(userIndx,subIndx);
                }
                else{
                    Subreddit.subreddits.get(subIndx).addModerator(Account.users.get(index));
                    communityReview(userIndx,subIndx);
                }
            }
            else if(s.equals("dismissModerator") && Subreddit.subreddits.get(subIndx).checkModerator(Account.users.get(userIndx))){
                System.out.println();
                Subreddit.subreddits.get(subIndx).showModerators();
                System.out.println();
                System.out.print("\tEnter member's name to dismiss: ");
                String name = in.nextLine();
                int index = Subreddit.subreddits.get(subIndx).getIndexOfModerator(name);
                if (index < 0){
                    System.out.println("\tinvalid input.");
                    communityReview(userIndx,subIndx);
                }
                else{
                    Subreddit.subreddits.get(subIndx).dismissModerator(index);
                    communityReview(userIndx,subIndx);
                }
            }
        }
        else if (Subreddit.subreddits.get(subIndx).checkModerator(Account.users.get(userIndx))){
            if(s.equals("DeletePost")){
                System.out.println();
                System.out.print("\tEnter the number of the post: ");
                String postinx = in.nextLine();
                try {
                    int inx = Integer.parseInt(postinx);
                    inx--;
                    if (inx < Subreddit.subreddits.get(subIndx).getPostSize()) {
                        Subreddit.subreddits.get(subIndx).deletePost(inx);
                        communityReview(userIndx,subIndx);
                    }
                    else{
                        System.out.println("\tinvalid input.");
                        communityReview(userIndx,subIndx);
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("\tinvalid input.");
                    communityReview(userIndx,subIndx);
                }
            }
            else if(s.equals("showMembers")){
                Subreddit.subreddits.get(subIndx).showMembers();
                System.out.println();
                System.out.print("\tEnter anything... ");
                String chr = in.nextLine();
                communityReview(userIndx,subIndx);
            }
            else if(s.equals("removeMember")){
                System.out.println();
                Subreddit.subreddits.get(subIndx).showMembers();
                System.out.println();
                System.out.print("\tEnter member's name: ");
                String name = in.nextLine();
                int index = Account.getIndexOfUser(name);
                if (index < 0){
                    System.out.println("\tinvalid input.");
                    communityReview(userIndx,subIndx);
                }
                else{
                    Subreddit.subreddits.get(subIndx).removeMember(Account.users.get(index), Subreddit.subreddits.get(subIndx));
                    communityReview(userIndx,subIndx);
                }
            }
        }
        if (s.equals("Exit"))
            return 0;
        else if (s.equals("Back"))
            communityPage(userIndx);
        else if (s.equals("join")){
            if(Subreddit.subreddits.get(subIndx).findUser(Account.users.get(userIndx))){
                System.out.println("\tyou already joined!");
                communityReview(userIndx,subIndx);
            }
            else{
                Account.users.get(userIndx).joinToSubreddit(Subreddit.subreddits.get(subIndx));
                Subreddit.subreddits.get(subIndx).joinToSubreddit(Account.users.get(userIndx));
                communityReview(userIndx,subIndx);
            }
        }
        else if (s.equals("Leave")){
            if(Subreddit.subreddits.get(subIndx).findUser(Account.users.get(userIndx))){
                Account.users.get(userIndx).leaveSubreddit(Subreddit.subreddits.get(subIndx));
                Subreddit.subreddits.get(subIndx).leaveSubreddit(Account.users.get(userIndx));
                communityReview(userIndx,subIndx);
            }
        }
        else{
            try {
                int inx = Integer.parseInt(s);
                inx--;
                if (inx < Subreddit.subreddits.get(subIndx).getPostSize()) {
                    postReviewForCommunity(userIndx, subIndx, inx);

                }
                else{
                    System.out.println("\tinvalid input.");
                    communityReview(userIndx,subIndx);

                }
            }
            catch (NumberFormatException e) {
                System.out.println("\tinvalid input.");
                communityReview(userIndx,subIndx);
            }
        }
        return 0;
    }
    public static int createPostPage(int userIndx){
        System.out.println("\n");
        System.out.println("\tyou are creating a Post! any time you give up, enter \"Exit\" or \"Back\".");
        System.out.println();
        System.out.println("\tYour communities: ");
        if (Account.users.get(userIndx).subredditSize() == 0) {
            System.out.println("\tYou have not joined any community yet!");
            System.out.println();
        }
        else{
            Account.users.get(userIndx).subredditName();
            System.out.println();
        }
        System.out.print("\tWhich community do you want to post in?(if you do not have any, type \"Create\")");
        String str = in.nextLine();
        if (str.equals("Exit") || str.equals("Back")){
            redditMenu(userIndx);
            return 0;
        }
        else if (str.equals("Create")){
            communityPage(userIndx);
            return 0;
        }
        else{
            if(Account.users.get(userIndx).subredditSize() == 0){
                System.out.println("\tYou have not joined any community yet! please create one first");
                createPostPage(userIndx);
                return 0;
            }
            else{
                try {
                    int inx = Integer.parseInt(str);
                    inx--;
                    if (inx < Account.users.get(userIndx).subredditSize()) {
                        inx = Subreddit.getIndexOfSubreddit(Account.users.get(userIndx).getSubName(inx));
                        System.out.println();
                        System.out.print("\tEnter title of post: ");
                        String title = in.nextLine();
                        System.out.println();
                        System.out.print("\tEnter body of post: ");
                        String body = in.nextLine();
                        System.out.println();
                        System.out.print("\tIf do you want to add some tags, type their names in order.\n\twhen you done, type \"Finish\" to end.");
                        List<String> tags = new ArrayList<>();
                        String tag = in.nextLine();
                        while(!tag.equals("Finish")){
                            tags.add(tag);
                            tag = in.nextLine();
                        }
                        Post post = new Post();
                        post.enterTitle(title);
                        post.enterBody(body);
                        post.enterTags(tags);
                        post.enterUser(Account.users.get(userIndx));
                        post.enterSubreddit(Subreddit.subreddits.get(inx));
                        Account.users.get(userIndx).addPost(post);
                        Subreddit.subreddits.get(inx).addPost(post);
                        Post.postsList.add(post);
                    }
                    else{
                        System.out.println("\tinvalid input.");
                        createPostPage(userIndx);
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("\tinvalid input.");
                    createPostPage(userIndx);
                }
            }
        }
        return 0;
    }
    public static int postReviewForCommunity(int userIndx, int subIndx, int postIndx){
        Subreddit.subreddits.get(subIndx).showPost(postIndx);
        if (Account.users.get(userIndx).savedListContains(Subreddit.subreddits.get(subIndx).returnPost(postIndx))){
            System.out.println("\tUnSave");
        }
        else{
            System.out.println("\tSave");
        }
        System.out.print("\tEnter what you want: ");
        String str = in.nextLine();
        if (str.equals("Back")){
            communityPage(userIndx);
            return 0;
        }
        else if(str.equals("Save") && !Account.users.get(userIndx).savedListContains(Subreddit.subreddits.get(subIndx).returnPost(postIndx))){
            Subreddit.subreddits.get(subIndx).savePost(Account.users.get(userIndx), postIndx);
            postReviewForCommunity(userIndx, subIndx, postIndx);
        }
        else if(str.equals("UnSave") && Account.users.get(userIndx).savedListContains(Subreddit.subreddits.get(subIndx).returnPost(postIndx))){
            Subreddit.subreddits.get(subIndx).unSavePost(Account.users.get(userIndx), postIndx);
            postReviewForCommunity(userIndx, subIndx, postIndx);
        }
        else if(str.equals("UpVote")){
            Subreddit.subreddits.get(subIndx).upVote(postIndx, Account.users.get(userIndx));
            postReviewForCommunity(userIndx, subIndx, postIndx);
        }
        else if(str.equals("DownVote")){
            Subreddit.subreddits.get(subIndx).downVote(postIndx, Account.users.get(userIndx));
            postReviewForCommunity(userIndx, subIndx, postIndx);
        }
        else if(str.equals("AddComment")){
            System.out.println();
            System.out.print("\tEnter the comment: ");
            String cmnt = in.nextLine();
            Comment comment = new Comment();
            comment.commentBody = cmnt;
            comment.user = Account.users.get(userIndx);
            Subreddit.subreddits.get(subIndx).addComment(comment, postIndx);
            postReviewForCommunity(userIndx, subIndx, postIndx);

        }
        else{
            try {
                int commentInx = Integer.parseInt(str);
                commentInx--;
                if (commentInx < Subreddit.subreddits.get(subIndx).getCommentListSize(postIndx)){
                    commentForpostReviewForCommunity(userIndx, subIndx, postIndx, commentInx);
                }
                else{
                    System.out.println("\tinvalid input.");
                    postReviewForCommunity(userIndx, subIndx, postIndx);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("\tinvalid input.");
                postReviewForCommunity(userIndx, subIndx, postIndx);
            }
        }
        return 0;
    }
    public static int commentForpostReviewForCommunity(int userIndex, int subIndex, int postIndex, int commentIndex){
        System.out.println();
        Subreddit.subreddits.get(subIndex).showComment(postIndex, commentIndex);
        System.out.println();
        System.out.print("\tEnter what you want(UpVote / DownVote / Back): ");
        String str = in.nextLine();
        if (str.equals("Back")){
            postReviewForCommunity(userIndex, subIndex, postIndex);
            return 0;
        }
        else if(str.equals("UpVote")){
            Subreddit.subreddits.get(subIndex).commentUpVote(postIndex, commentIndex, Account.users.get(userIndex));
            commentForpostReviewForCommunity(userIndex, subIndex, postIndex, commentIndex);
        }
        else if(str.equals("DownVote")){
            Subreddit.subreddits.get(subIndex).commentDownVote(postIndex, commentIndex, Account.users.get(userIndex));
            commentForpostReviewForCommunity(userIndex, subIndex, postIndex, commentIndex);
        }
        else{
            System.out.println();
            System.out.print("\tinvalid input");
            commentForpostReviewForCommunity(userIndex, subIndex, postIndex, commentIndex);
            return 0;
        }
        return 0;
    }





}