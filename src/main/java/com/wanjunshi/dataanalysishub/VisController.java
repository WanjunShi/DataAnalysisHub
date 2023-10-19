package com.wanjunshi.dataanalysishub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.List;

public class VisController {
    public PieChart chart;

    public void initialize(){
        List<Integer> data = Application.db.getChartData();
        chart.setData(FXCollections.observableArrayList(
                new PieChart.Data("0 - 99", data.get(0)),
                new PieChart.Data("100 - 999", data.get(1)),
                new PieChart.Data("> 1000", data.get(2))
        ));
    }
}
