module com.wanjunshi.dataanalysishub {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.wanjunshi.dataanalysishub to javafx.fxml;
    opens com.wanjunshi.dataanalysishub.models to javafx.fxml;
    exports com.wanjunshi.dataanalysishub;
    exports com.wanjunshi.dataanalysishub.models;
}