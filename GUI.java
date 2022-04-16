/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javafx.scene.shape.Rectangle;
//import java.awt.Rectangle;
import java.util.Vector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Screen;

public class GUI extends Application {
    private final double width = Screen.getPrimary().getBounds().getMaxX();
    private final double height = Screen.getPrimary().getBounds().getMaxY();
    private String algorithm;
    private Vector<TimeStamp> timeStamp = new Vector<>();

    
    @Override
    public void start(Stage primaryStage) {
        //Defining Variables
        Vector<Process> inputProcesses = new Vector<>();
        //Set the scenes
        StackPane root1 = new StackPane();
        Scene welcomeScene = new Scene(root1, width*0.9, height*0.7);
        FlowPane root2 = new FlowPane();
        Scene processesScene = new Scene(root2, width*0.9, height*0.7);
        root2.setPadding(new Insets(height*0.2, width*0.15, height*0.2, width*0.15));
        BorderPane border = new BorderPane();
        GridPane root3 = new GridPane();
        border.setCenter(root3);
        border.setMaxWidth(width*0.5);
        border.setMinWidth(width*0.5);
        root3.setTranslateX(width*0.25);
        root3.setHgap(10);
        root3.setVgap(10);
        Scene inputScene = new Scene(border, width*0.9, height*0.7);
        Group group = new Group();
        Pane root4 = new Pane(group);
        Scene ganttChartScene = new Scene(root4, width*0.9, height*0.7);
        root4.setPrefWidth(width*0.9);
        root4.setPrefHeight(height*0.7);
//        root3.getStylesheets().add( getClass().getResource( "style.css" ).toExternalForm() );
//        root3.getStylesheets().add("G:\\JAVA\\Testing\\GUI\\src\\gui\\style.css");
        //Set controls
        Button startBtn = new Button();
        startBtn.setText("Start");
        startBtn.setMinSize(width*0.3, height*0.1);
        startBtn.setStyle("-fx-background-color: #3c7fb1,linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);-fx-background-insets: 0,1,2;-fx-background-radius: 3,2,1;-fx-padding: 3 30 3 30;-fx-text-fill: black;-fx-font-size: 14px;");
        
        Button FCFSBtn = new Button();
        FCFSBtn.setText("FCFS");
        FCFSBtn.setMinSize(width*0.7, height*0.1);
        FCFSBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 14px;");
        
        Button preemptiveSJFBtn = new Button();
        preemptiveSJFBtn.setText("Preemptive SJF");
        preemptiveSJFBtn.setMinSize(width*0.7, height*0.1);
        preemptiveSJFBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #237DF3 50%, #2685FF 51%, #3DDEE9 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 14px;");
        
        Button non_preemptiveSJFBtn = new Button();
        non_preemptiveSJFBtn.setText("Non-Preemptive SJF");
        non_preemptiveSJFBtn.setMinSize(width*0.7, height*0.1);
        non_preemptiveSJFBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #237DF3 50%, #2685FF 51%, #3DDEE9 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 14px;");

        Button preemptivePriorityBtn = new Button();
        preemptivePriorityBtn.setText("Preemptive Priority");
        preemptivePriorityBtn.setMinSize(width*0.7, height*0.1);
        preemptivePriorityBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #69BEE9 50%, #1AD9DF 51%, #A4F6FC 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 14px;");
        
        Button non_preemptivePriorityBtn = new Button();
        non_preemptivePriorityBtn.setText("Non-Preemptive Priority");
        non_preemptivePriorityBtn.setMinSize(width*0.7, height*0.1);
        non_preemptivePriorityBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #69BEE9 50%, #1AD9DF 51%, #A4F6FC 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 14px;");
        
        Button roundRobinBtn = new Button();
        roundRobinBtn.setText("Round Robin");
        roundRobinBtn.setMinSize(width*0.7, height*0.1);
        roundRobinBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #237DF3 50%, #2685FF 51%, #3DDEE9 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 14px;");
        Vector<InsertProcess> process = new Vector<>();
        Button okBtn = new Button("Proceed →");
        okBtn.setStyle("-fx-background-color: #3c7fb1,linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);-fx-background-insets: 0,1,2;-fx-background-radius: 3,2,1;-fx-padding: 3 30 3 30;-fx-text-fill: black;-fx-font-size: 14px;");
        okBtn.setTranslateX(width * 0.4);
        okBtn.setTranslateY(height * -0.1);
        okBtn.setMinWidth(width * 0.1);
        
        process.add(new InsertProcess());
        Label pNameLabel = new Label("Process Name:");
        pNameLabel.setId("label_test");
        root3.add(pNameLabel, 1, 0);
        Label aTimeLabel = new Label("Arrival Time:");
        root3.add(aTimeLabel, 2, 0);
        Label bTimeLabel = new Label("Burst Time:");
        root3.add(bTimeLabel, 3, 0);
        root3.add(process.get(0).removeBtn, 0, 1);
        root3.add(process.get(0).pName, 1, 1);
        root3.add(process.get(0).aTime, 2, 1);
        root3.add(process.get(0).bTime, 3, 1);
        TextField quantum = new TextField("10");

        
        process.get(0).removeBtn.setVisible(false);
        border.setBottom(okBtn);
        //Event Handlers
        startBtn.setOnAction(e -> {
            primaryStage.setScene(processesScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        FCFSBtn.setOnAction(e -> {
            algorithm = "FCFS";
            root3.add(process.get(0).addBtn, 4, 1);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        preemptiveSJFBtn.setOnAction(e -> {
            algorithm = "Preemptive SJF";
            root3.add(process.get(0).addBtn, 4, 1);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        non_preemptiveSJFBtn.setOnAction(e -> {
            algorithm = "Non-Preemptive SJF";
            root3.add(process.get(0).addBtn, 4, 1);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        preemptivePriorityBtn.setOnAction(e -> {
            algorithm = "Preemptive Priority";
            root3.add(new Label("Priority:"), 4, 0);
            root3.add(process.get(0).prty, 4, 1);
            root3.add(process.get(0).addBtn, 5, 1);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        non_preemptivePriorityBtn.setOnAction(e -> {
            algorithm = "Non-Preemptive Priority";
            root3.add(new Label("Priority:"), 4, 0);
            root3.add(process.get(0).prty, 4, 1);
            root3.add(process.get(0).addBtn, 5, 1);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        roundRobinBtn.setOnAction(e -> {
            algorithm = "Round Robin";
            root3.add(new Label("Quantum:"), 7, 0);
            root3.add(quantum, 8, 0);
            root3.add(process.get(0).addBtn, 4, 1);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        (process.get(InsertProcess.processCount - 1).addBtn).setOnAction(e -> {
            addAction(root3, process);
        });
        (process.get(InsertProcess.processCount - 1).removeBtn).setOnAction(e -> {
            removeAction(root3, process);
        });
        okBtn.setOnAction(e -> {
            for(int i = 0; i < InsertProcess.processCount; i++){
                process.get(i).updateProcess();
                inputProcesses.add(new Process(process.get(i).processName, Float.valueOf(process.get(i).arrivalTime).floatValue(), Float.valueOf(process.get(i).burstTime).floatValue(), Float.valueOf(process.get(i).priority).floatValue()));
            }
            RRProcess my_proc[];
            if(null == algorithm) System.out.println("Error");
            else switch (algorithm) {
                case "FCFS":
                    my_proc = RRProcess.vecToArr(inputProcesses);
                    timeStamp = RRProcess.RRSchedule(my_proc, Process.maximum(my_proc));
                    break;
                case "Preemptive SJF":
                    timeStamp = SJF.sjf_p(Priority.vecToArr(inputProcesses));
                    break;
                case "Non-Preemptive SJF":
                    timeStamp = SJF.sjf_np(Priority.vecToArr(inputProcesses));
                    break;
                case "Preemptive Priority":
                    timeStamp = Priority.priority(Priority.vecToArr(inputProcesses), true);
                    break;
                case "Non-Preemptive Priority":
                    timeStamp = Priority.priority(Priority.vecToArr(inputProcesses), false);
                    break;
                case "Round Robin":
                    my_proc = RRProcess.vecToArr(inputProcesses);
                    timeStamp = RRProcess.RRSchedule(my_proc, Float.valueOf(process.get(0).quantum).floatValue());
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
            primaryStage.setScene(ganttChartScene);
            System.out.println(timeStamp.get(timeStamp.size() - 1));
            Rectangle rect;
            float timeWidth = timeStamp.get(timeStamp.size() - 1).getEndTime();
            for(int i = 0; i < timeStamp.size(); i++){
                rect = new Rectangle(width * 0.1 + width * 0.8 * timeStamp.get(i).getStartTime() / timeWidth, height*0.4, width * 0.1 + width * 0.8 * (timeStamp.get(i).getEndTime() - timeStamp.get(i).getStartTime()) / timeWidth, height*0.1);
                rect.setArcWidth(60);
                rect.setArcHeight(60);                
                rect.setFill(Color.ANTIQUEWHITE.deriveColor(0, 1.2, 1, 0.6));
                rect.setStroke(Color.ANTIQUEWHITE);
                Text text = new Text(timeStamp.get(i).getProcessName());
                text.setFill(Color.BLACK);
                text.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,25));
                text.toFront();
                text.setX(width * 0.1 + width * 0.8 * timeStamp.get(i).getStartTime() / timeWidth + width * 0.1 + width * 0.4 * (timeStamp.get(i).getEndTime() - timeStamp.get(i).getStartTime()) / timeWidth);
                text.setY(height*0.45);
                group.getChildren().addAll(rect, text);
            }
        });

        //Assign controls
        root1.getChildren().add(startBtn);
        root2.getChildren().addAll(FCFSBtn, preemptiveSJFBtn, non_preemptiveSJFBtn, preemptivePriorityBtn, non_preemptivePriorityBtn, roundRobinBtn);

        
        
        //Starting the application
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Scheduler");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    public void addAction(GridPane pane, Vector<InsertProcess> process){
            process.get(InsertProcess.processCount - 1).addBtn.setVisible(false);
            process.get(InsertProcess.processCount - 1).removeBtn.setVisible(false);
            process.add(new InsertProcess());
            pane.add(process.get(InsertProcess.processCount - 1).removeBtn, 0, InsertProcess.processCount);
            pane.add(process.get(InsertProcess.processCount - 1).pName, 1, InsertProcess.processCount);
            pane.add(process.get(InsertProcess.processCount - 1).aTime, 2, InsertProcess.processCount);
            pane.add(process.get(InsertProcess.processCount - 1).bTime, 3, InsertProcess.processCount);
            if(algorithm == "Preemptive Priority" || algorithm == "Non-Preemptive Priority"){
                pane.add(process.get(InsertProcess.processCount - 1).prty, 4, InsertProcess.processCount);
                pane.add(process.get(InsertProcess.processCount - 1).addBtn, 5, InsertProcess.processCount);
            }
            else pane.add(process.get(InsertProcess.processCount - 1).addBtn, 4, InsertProcess.processCount);
            refreshAction(pane,process);
    }
    public void removeAction(GridPane pane, Vector<InsertProcess> process){
            if(InsertProcess.processCount > 2) process.get(InsertProcess.processCount - 2).removeBtn.setVisible(true);
            process.get(InsertProcess.processCount - 2).addBtn.setVisible(true);
            process.get(InsertProcess.processCount - 1).removeBtn.setVisible(false);
            process.get(InsertProcess.processCount - 1).pName.setVisible(false);
            process.get(InsertProcess.processCount - 1).aTime.setVisible(false);
            process.get(InsertProcess.processCount - 1).bTime.setVisible(false);
            if(algorithm == "Preemptive Priority" || algorithm == "Non-Preemptive Priority") process.get(InsertProcess.processCount - 1).prty.setVisible(false);
            process.get(InsertProcess.processCount - 1).addBtn.setVisible(false);
            process.remove(--InsertProcess.processCount);
            refreshAction(pane,process);
    }
    public void refreshAction(GridPane pane, Vector<InsertProcess> process){
        (process.get(InsertProcess.processCount - 1).addBtn).setOnAction(e -> addAction(pane, process));
        (process.get(InsertProcess.processCount - 1).removeBtn).setOnAction(e -> removeAction(pane, process));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}