module com.wanjunshi.dataanalysishub {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.wanjunshi.dataanalysishub to javafx.fxml;
    exports com.wanjunshi.dataanalysishub;
}