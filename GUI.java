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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Screen;

public class GUI extends Application {
    private final double width = Screen.getPrimary().getBounds().getMaxX();
    private final double height = Screen.getPrimary().getBounds().getMaxY();
    private String algorithm = "";
    private Vector<TimeStamp> timeStamp = new Vector<>();
    private boolean backFlag = false;
    private Vector<Label> endTimeLabel = new Vector<>();
    private int labelIndex = -1;
    @Override
    public void start(Stage primaryStage) {
        //Defining Variables
        Vector<Process> inputProcesses = new Vector<>();
        //set background image
        Image img = new Image("https://3.bp.blogspot.com/-1SYrbxJsLiI/Uh9yksZGFvI/AAAAAAAAHCQ/i41Qndj_YIs/s1600/Photo-Background-Application.jpg");
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background bGround = new Background(bImg);
        //Set the scenes
        StackPane root1 = new StackPane();
        Scene welcomeScene = new Scene(root1, width*0.9, height*0.7);
        root1.setBackground(bGround);
        FlowPane root2 = new FlowPane(width*0.01,height*0.01);
        Scene processesScene = new Scene(root2, width*0.9, height*0.7);
        
        root2.setPadding(new Insets(height*0.2, width*0.15, height*0.1, width*0.15));
        root2.setBackground(bGround);
        BorderPane border = new BorderPane();
        GridPane root3 = new GridPane();
        border.setBackground(bGround);
        
        
        border.setCenter(root3);
        border.setMaxWidth(width*0.5);
        border.setMinWidth(width*0.5);
        root3.setTranslateX(width*0.25);
        root3.setHgap(width * 0.045);
        root3.setVgap(height * 0.03);
        root3.setTranslateX(width * 0.2);
        Scene inputScene = new Scene(border, width*0.9, height*0.7);
        Group group = new Group();
        Pane root4 = new Pane(group);
        Scene ganttChartScene = new Scene(root4, width*0.9, height*0.7);
        root4.setPrefWidth(width*0.9);
        root4.setPrefHeight(height*0.7);
        root4.setBackground(bGround);
        
        //Set controls
        Label description = new Label("This application considers simulation of dispatcher\n management using different types of schedule\n and show gantt chart and waiting time for the specified process");
        description.setScaleX(width * 0.002);
        description.setScaleY(width * 0.002);
        description.setTextFill(BLACK);
//        description.setBorder(Border.stroke(WHITE));
        description.setAlignment(Pos.TOP_CENTER);
        description.setTranslateY(height * -0.2);
        
        Button startBtn = new Button();
        startBtn.setText("Start");
        startBtn.setMinSize(width*0.2, height*0.1);
        startBtn.setTranslateY(height * 0.15);
        startBtn.setStyle("-fx-background-color: #3c7fb1,linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);-fx-background-insets: 0,1,2;-fx-background-radius: 3,2,1;-fx-padding: 3 30 3 30;-fx-text-fill: black;-fx-font-size: 50px;");
        
        Button exitBtn1 = new Button("Exit");
        exitBtn1.setStyle("-fx-background-color: #3c7fb1,linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);-fx-background-insets: 0,1,2;-fx-background-radius: 3,2,1;-fx-padding: 3 30 3 30;-fx-text-fill: black;-fx-font-size: 25px;");
        exitBtn1.setScaleX(width * 0.001);
        exitBtn1.setScaleY(height * 0.002);
        exitBtn1.setTranslateX(width * 0.4);
        exitBtn1.setTranslateY(height * 0.35);
        
        Button backBtn1 = new Button("←");
        backBtn1.setStyle("-fx-background-color: #3c7fb1,linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);-fx-background-insets: 0,1,2;-fx-background-radius: 3,2,1;-fx-padding: 3 30 3 30;-fx-text-fill: black;-fx-font-size: 25px;");
        backBtn1.setScaleX(width * 0.001);
        backBtn1.setScaleY(height * 0.002);
        backBtn1.setTranslateX(width * -0.1);
        backBtn1.setTranslateY(height * -0.09);
        
        Label chooseAlgorithmLabel = new Label("Choose Algorithm:");
        chooseAlgorithmLabel.setScaleX(width * 0.003);
        chooseAlgorithmLabel.setScaleY(height * 0.006);
        chooseAlgorithmLabel.setTranslateX(width * 0.3);
        chooseAlgorithmLabel.setTranslateY(height * -0.1);
        
        Button FCFSBtn = new Button();
        FCFSBtn.setText("FCFS");
        FCFSBtn.setMinSize(width*0.7, height*0.1);
        FCFSBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 40px;");
        
        Button preemptiveSJFBtn = new Button();
        preemptiveSJFBtn.setText("Preemptive SJF");
        preemptiveSJFBtn.setMinSize(width*0.7, height*0.1);
        preemptiveSJFBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #237DF3 50%, #2685FF 51%, #3DDEE9 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 40px;");
        
        Button non_preemptiveSJFBtn = new Button();
        non_preemptiveSJFBtn.setText("Non-Preemptive SJF");
        non_preemptiveSJFBtn.setMinSize(width*0.7, height*0.1);
        non_preemptiveSJFBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #237DF3 50%, #2685FF 51%, #3DDEE9 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 40px;");

        Button preemptivePriorityBtn = new Button();
        preemptivePriorityBtn.setText("Preemptive Priority");
        preemptivePriorityBtn.setMinSize(width*0.7, height*0.1);
        preemptivePriorityBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #69BEE9 50%, #1AD9DF 51%, #A4F6FC 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 40px;");
        
        Button non_preemptivePriorityBtn = new Button();
        non_preemptivePriorityBtn.setText("Non-Preemptive Priority");
        non_preemptivePriorityBtn.setMinSize(width*0.7, height*0.1);
        non_preemptivePriorityBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #69BEE9 50%, #1AD9DF 51%, #A4F6FC 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 40px;");
        
        Button roundRobinBtn = new Button();
        roundRobinBtn.setText("Round Robin");
        roundRobinBtn.setMinSize(width*0.7, height*0.1);
        roundRobinBtn.setStyle("-fx-background-color: rgba(0,0,0,0.08),linear-gradient(#5a61af, #51536d),linear-gradient(#e4fbff 0%,#cee6fb 10%, #237DF3 50%, #2685FF 51%, #3DDEE9 100%);-fx-background-insets: 0 0 -1 0,0,1;-fx-background-radius: 5,5,4;-fx-padding: 3 30 3 30;-fx-text-fill: #242d35;-fx-font-size: 40px;");
        
        
        Vector<InsertProcess> process = new Vector<>();
        Button okBtn = new Button("Proceed →");
        okBtn.setStyle("-fx-background-color: #3c7fb1,linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);-fx-background-insets: 0,1,2;-fx-background-radius: 3,2,1;-fx-padding: 3 30 3 30;-fx-text-fill: black;-fx-font-size: 25px;");
        okBtn.setTranslateX(width * 0.42);
        okBtn.setTranslateY(height * -0.1);
        okBtn.setMinWidth(width * 0.1);
        okBtn.setScaleX(width * 0.001);
        okBtn.setScaleY(height * 0.002);
        
        process.add(new InsertProcess(width, height));
        Label pNameLabel = new Label("Process Name:");
        pNameLabel.setId("label_test");
        pNameLabel.setScaleX(width * 0.001);
        pNameLabel.setScaleY(width * 0.002);
        root3.add(pNameLabel, 2, 0);
        Label aTimeLabel = new Label("Arrival Time:");
        aTimeLabel.setScaleX(width * 0.001);
        aTimeLabel.setScaleY(width * 0.002);
        root3.add(aTimeLabel, 3, 0);
        Label bTimeLabel = new Label("Burst Time:");
        bTimeLabel.setScaleX(width * 0.001);
        bTimeLabel.setScaleY(width * 0.002);
        root3.add(bTimeLabel, 4, 0);
        root3.add(process.get(0).removeBtn, 0, 1);
        root3.add(process.get(0).pName, 2, 1);
        root3.add(process.get(0).aTime, 3, 1);
        root3.add(process.get(0).bTime, 4, 1);
        Label priorityLabel = new Label("Priority:");
        priorityLabel.setScaleX(width * 0.001);
        priorityLabel.setScaleY(width * 0.002);
        root3.add(priorityLabel, 5, 0);
        priorityLabel.setVisible(false);
        root3.add(process.get(0).prty, 5, 1);
        process.get(0).prty.setVisible(false);
        TextField quantum = new TextField("1");
        quantum.setScaleX(width * 0.0008);
        quantum.setScaleY(height * 0.002);
        Label quantumLabel = new Label("Quantum:");
        quantumLabel.setScaleX(width * 0.001);
        quantumLabel.setScaleY(width * 0.002);
        root3.add(quantumLabel, 5, 0);
        quantumLabel.setVisible(false);
        root3.add(quantum, 5, 1);
        quantum.setVisible(false);
        
        Label processesLabel = new Label("Enter Processes:");
        processesLabel.setScaleX(width * 0.0015);
        processesLabel.setScaleY(height * 0.003);
        processesLabel.setTranslateX(width * 0.1);
        processesLabel.setTranslateY(height * 0.02);
        border.setTop(processesLabel);
        
        Button backBtn2 = new Button("←");
        backBtn2.setStyle("-fx-background-color: #3c7fb1,linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);-fx-background-insets: 0,1,2;-fx-background-radius: 3,2,1;-fx-padding: 3 30 3 30;-fx-text-fill: black;-fx-font-size: 25px;");
        backBtn2.setScaleX(width * 0.001);
        backBtn2.setScaleY(height * 0.002);
        backBtn2.setTranslateX(height * 0.1);
        backBtn2.setTranslateY(height * 0.75);
        border.setLeft(backBtn2);
        
        process.get(0).removeBtn.setVisible(false);
        border.setBottom(okBtn);
        
        Label ganttChartLabel = new Label("Gantt Chart:");
        ganttChartLabel.setScaleX(width * 0.003);
        ganttChartLabel.setScaleY(height * 0.006);
        ganttChartLabel.setTranslateX(width * 0.5);
        ganttChartLabel.setTranslateY(height * 0.1);
        root4.getChildren().add(ganttChartLabel);
        
        Button returnBtn = new Button("Return Home");
        returnBtn.setStyle("-fx-background-color: #3c7fb1,linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);-fx-background-insets: 0,1,2;-fx-background-radius: 3,2,1;-fx-padding: 3 30 3 30;-fx-text-fill: black;-fx-font-size: 25px;");
        returnBtn.setScaleX(width * 0.001);
        returnBtn.setScaleY(height * 0.002);
        returnBtn.setTranslateX(width * 0.1);
        returnBtn.setTranslateY(height * 0.8);
        root4.getChildren().add(returnBtn);
        
        Button exitBtn = new Button("Exit");
        exitBtn.setStyle("-fx-background-color: #3c7fb1,linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);-fx-background-insets: 0,1,2;-fx-background-radius: 3,2,1;-fx-padding: 3 30 3 30;-fx-text-fill: black;-fx-font-size: 25px;");
        exitBtn.setScaleX(width * 0.001);
        exitBtn.setScaleY(height * 0.002);
        exitBtn.setTranslateX(width * 0.85);
        exitBtn.setTranslateY(height * 0.8);
        root4.getChildren().add(exitBtn);
        
        //Event Handlers
        backBtn1.setOnAction(e -> {
            primaryStage.setScene(welcomeScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
        });
        startBtn.setOnAction(e -> {
            primaryStage.setScene(processesScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        exitBtn1.setOnAction(e -> Platform.exit());
        FCFSBtn.setOnAction(e -> {
            algorithm = "FCFS";
            if(!backFlag) root3.add(process.get(0).addBtn, 1, 1);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        preemptiveSJFBtn.setOnAction(e -> {
            algorithm = "Preemptive SJF";
            if(!backFlag) root3.add(process.get(0).addBtn, 1, 1);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        non_preemptiveSJFBtn.setOnAction(e -> {
            algorithm = "Non-Preemptive SJF";
            if(!backFlag) root3.add(process.get(0).addBtn, 1, 1);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        preemptivePriorityBtn.setOnAction(e -> {
            algorithm = "Preemptive Priority";
            if(!backFlag) root3.add(process.get(0).addBtn, 1, 1);
            process.get(0).prty.setVisible(true);
            priorityLabel.setVisible(true);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        non_preemptivePriorityBtn.setOnAction(e -> {
            algorithm = "Non-Preemptive Priority";
            if(!backFlag) root3.add(process.get(0).addBtn, 1, 1);
            process.get(0).prty.setVisible(true);
            priorityLabel.setVisible(true);
            primaryStage.setScene(inputScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
                });
        roundRobinBtn.setOnAction(e -> {
            algorithm = "Round Robin";
            if(!backFlag) root3.add(process.get(0).addBtn, 1, 1);
            quantumLabel.setVisible(true);
            quantum.setVisible(true);
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
        backBtn2.setOnAction(e -> {
            backFlag = true;
            int i = InsertProcess.processCount - 1;
            while(InsertProcess.processCount > 1){
                removeAction(root3, process);
            }
            for(int j = 0; j < endTimeLabel.size(); j++) {
                endTimeLabel.get(j).setVisible(false);
            }
            process.get(0).pName.setText("P0");
            process.get(0).aTime.setText("0");
            process.get(0).bTime.setText("1");
            process.get(0).prty.setText("0");
            process.get(0).q.setText("1");
            priorityLabel.setVisible(false);
            process.get(0).prty.setVisible(false);
            quantum.setVisible(false);
            quantumLabel.setVisible(false);
            primaryStage.setScene(processesScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
        });
        returnBtn.setOnAction(e -> {
            backFlag = true;
            inputProcesses.removeAllElements();
            int i = InsertProcess.processCount - 1;
            while(InsertProcess.processCount > 1){
                removeAction(root3, process);
            }
            for(int j = 0; j < endTimeLabel.size(); j++) {
                endTimeLabel.get(j).setVisible(false);
            }
            process.get(0).pName.setText("P0");
            process.get(0).aTime.setText("0");
            process.get(0).bTime.setText("1");
            process.get(0).prty.setText("0");
            process.get(0).q.setText("1");
            priorityLabel.setVisible(false);
            process.get(0).prty.setVisible(false);
            quantum.setVisible(false);
            quantumLabel.setVisible(false);
            primaryStage.setScene(processesScene);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
        });
        exitBtn.setOnAction(e -> Platform.exit());
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
            Label zeroTimeLabel = new Label("0");
            zeroTimeLabel.setScaleX(width * 0.001);
            zeroTimeLabel.setScaleY(height * 0.002);
            zeroTimeLabel.setTranslateX(width * 0.1);
            zeroTimeLabel.setTranslateY(height * 0.52);
            zeroTimeLabel.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 25));
            group.getChildren().add(zeroTimeLabel);
            float timeWidth = timeStamp.get(timeStamp.size() - 1).getEndTime();
            for(int i = 0; i < timeStamp.size(); i++){
                rect = new Rectangle(width * 0.1 + (width * 0.8 * timeStamp.get(i).getStartTime() / timeWidth), height*0.4, width * 0.8 * (timeStamp.get(i).getEndTime() - timeStamp.get(i).getStartTime()) / timeWidth, height*0.1);
                rect.setArcWidth(20);
                rect.setArcHeight(20);                
                rect.setFill(Color.DARKCYAN);
                rect.setStrokeWidth(8.0);
                rect.setStroke(Color.DARKSLATEGREY); 
                Text text = new Text(timeStamp.get(i).getProcessName());
                text.setFill(Color.BLACK);
                text.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,25));
                text.toFront();
                text.setX(width * 0.1 + (width * 0.8 * timeStamp.get(i).getStartTime() / timeWidth + width * 0.4 * (timeStamp.get(i).getEndTime() - timeStamp.get(i).getStartTime()) / timeWidth));
                text.setY(height*0.45);
                labelIndex++;
                endTimeLabel.add(new Label(Float.toString(timeStamp.get(i).getEndTime())));
                endTimeLabel.get(labelIndex).setScaleX(width * 0.001);
                endTimeLabel.get(labelIndex).setScaleY(height * 0.002);
                endTimeLabel.get(labelIndex).setTranslateX(width * 0.1 + (width * 0.8 * timeStamp.get(i).getStartTime() / timeWidth) + width * 0.8 * (timeStamp.get(i).getEndTime() - timeStamp.get(i).getStartTime()) / timeWidth);
                endTimeLabel.get(labelIndex).setTranslateY(height * 0.52);
                endTimeLabel.get(labelIndex).setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 25));
            
                group.getChildren().addAll(rect, text, endTimeLabel.get(labelIndex));
            }
        });

        //Assign controls
        root1.getChildren().addAll(description, startBtn, exitBtn1);
        root2.getChildren().addAll(chooseAlgorithmLabel, FCFSBtn, preemptiveSJFBtn, non_preemptiveSJFBtn, preemptivePriorityBtn, non_preemptivePriorityBtn, roundRobinBtn, backBtn1);

        
        
        //Starting the application
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Scheduler");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    public void addAction(GridPane pane, Vector<InsertProcess> process){
            process.get(InsertProcess.processCount - 1).addBtn.setVisible(false);
            process.get(InsertProcess.processCount - 1).removeBtn.setVisible(false);
            process.add(new InsertProcess(width, height));
            pane.add(process.get(InsertProcess.processCount - 1).removeBtn, 0, InsertProcess.processCount);
            pane.add(process.get(InsertProcess.processCount - 1).pName, 2, InsertProcess.processCount);
            pane.add(process.get(InsertProcess.processCount - 1).aTime, 3, InsertProcess.processCount);
            pane.add(process.get(InsertProcess.processCount - 1).bTime, 4, InsertProcess.processCount);
            pane.add(process.get(InsertProcess.processCount - 1).addBtn, 1, InsertProcess.processCount);
            if(InsertProcess.processCount == 10) process.get(InsertProcess.processCount - 1).addBtn.setVisible(false);
            if(algorithm == "Preemptive Priority" || algorithm == "Non-Preemptive Priority"){
                pane.add(process.get(InsertProcess.processCount - 1).prty, 5, InsertProcess.processCount);
            }
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