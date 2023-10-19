package com.wanjunshi.dataanalysishub;

import com.wanjunshi.dataanalysishub.models.PostModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.List;

public class TopNController {
    public TableColumn<PostModel, Integer> idCol;
    public TableColumn<PostModel, String> authorCol;
    public TableColumn<PostModel, String> contentCol;
    public TableColumn<PostModel, Integer> sharesCol;
    public TableColumn<PostModel, Integer> likesCol;
    public TableColumn<PostModel, LocalDateTime> dateCol;
    public TableView<PostModel> table;

    private List<PostModel> data;
    public TopNController(List<PostModel> data){
        this.data = data;
    }
    public void initialize(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        contentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
        sharesCol.setCellValueFactory(new PropertyValueFactory<>("shares"));
        likesCol.setCellValueFactory(new PropertyValueFactory<>("likes"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        table.getItems().setAll(data);
    }


}
