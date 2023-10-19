package com.wanjunshi.dataanalysishub;

import com.wanjunshi.dataanalysishub.models.PostModel;
import com.wanjunshi.dataanalysishub.models.UserModel;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Database {
    private HashMap<String, UserModel> users;
    private UserModel currentUser;

    public HashMap<String, UserModel> getUsers() {
        return users;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    public Database(){
        readFromFile();
        if (users == null){
            users = new HashMap<>();
        }
    }

    public boolean addPost(PostModel post){
        if(currentUser.getPosts().containsKey(post.getId())){
            return false;
        }
        currentUser.getPosts().put(post.getId(), post);
        saveToFile();
        return true;
    }

    public PostModel getPost(int id){
        return currentUser.getPosts().get(id);
    }

    public boolean deletePost(int id){
        if (currentUser.getPosts().containsKey(id)){
            currentUser.getPosts().remove(id);
            saveToFile();
            return true;
        }
        return false;
    }


    private void readFromFile(){
        try (FileInputStream fileIn = new FileInputStream("analytics.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
             users = (HashMap<String, UserModel>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            users = new HashMap<>();
        }
    }

    private void saveToFile(){
        // Serialization
        try (FileOutputStream fileOut = new FileOutputStream("analytics.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password){
        if (users.containsKey(username)){
            if(users.get(username).getPassword().equals(password)){
                this.currentUser = users.get(username);
                return true;
            }
        }
        return false;
    }

    public boolean register(UserModel user){
        if (users.containsKey(user.getUsername())){
            return false;
        }

        users.put(user.getUsername(), user);
        saveToFile();
        this.currentUser = user;
        return true;
    }

    public void logout(){
        this.currentUser = null;
    }
    public List<PostModel> getTopN(int number, Comparator<PostModel> c) {
        var data = new ArrayList<>(currentUser.getPosts().values());
        data.sort(c);
        return data.size() < number ? data : data.subList(0, number);
    }

    public List<PostModel> getTopNShares(int number){
        return getTopN(number, new PostModel.ShareComparator().reversed());
    }

    public List<PostModel> getTopNLikes(int number){
        return getTopN(number, new PostModel.LikeComparator().reversed());
    }


    public boolean editProfile(UserModel user){
        if(!user.getUsername().equals(currentUser.getLname())){
            if (users.containsKey(user.getUsername())){
                return false;
            }
        }
        if(user.getUsername().equals(currentUser.getUsername())){
            currentUser.setFname(user.getFname());
            currentUser.setLname(user.getLname());
            currentUser.setPassword(user.getPassword());
            saveToFile();
            return true;
        }
        users.put(user.getUsername(), currentUser);
        users.remove(currentUser.getUsername());
        currentUser.setUsername(user.getUsername());
        currentUser.setFname(user.getFname());
        currentUser.setLname(user.getLname());
        currentUser.setPassword(user.getPassword());
        saveToFile();
        return true;
    }

    public boolean exportPost(PostModel post, Stage stage) {
        String csv = "id,content,share,like,author,datetime\n";
        csv += post.getId() + "," + post.getContent() + "," + post.getShares()+ "," + post.getLikes()+","+ post.getAuthor() + ","+post.getTime();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(csv);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void upgradeToVIP(){
        currentUser.setVip(true);
        saveToFile();
    }

    public List<Integer> getChartData() {
        var data = new ArrayList<Integer>();
        data.add(0);
        data.add(0);
        data.add(0);
        for (var p : currentUser.getPosts().values()){
            if (p.getShares() < 100){
                data.set(0, data.get(0) + 1);
                continue;
            }
            if (p.getShares() < 1000){
                data.set(1, data.get(1) + 1);
                continue;
            }
            data.set(2, data.get(2) + 1);
        }
        return data;
    }
}
