/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Vector;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Ahmed Alaa
 */
public class InsertProcess {
    public static int processCount = 0;
    public String processName;
    public String arrivalTime;
    public String burstTime;
    public String priority;
    public Button removeBtn;
    public String quantum;
    public TextField pName;
    public TextField aTime;
    public TextField bTime;
    public TextField prty;
    public static TextField q;
    public Button addBtn;

    public InsertProcess(String processName, String arrivalTime, String burstTime, String priority) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        removeBtn = new Button("-");
        pName = new TextField(processName);
        aTime = new TextField(arrivalTime);
        bTime = new TextField(burstTime);
        prty = new TextField(priority);
        addBtn = new Button("+");
        processCount++;
    }
    public InsertProcess(String processName, String arrivalTime, String burstTime) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        removeBtn = new Button("-");
        pName = new TextField(processName);
        aTime = new TextField(arrivalTime);
        bTime = new TextField(burstTime);
        addBtn = new Button("+");
        processCount++;
    }

    public InsertProcess(double width, double height) {
        processName = "P" + String.valueOf(processCount);
        arrivalTime = "0";
        priority  = "0";
        burstTime = String.valueOf(1);
        removeBtn = new Button("-");
        quantum = "1";
        pName = new TextField(processName);
        aTime = new TextField(arrivalTime);
        bTime = new TextField(burstTime);
        prty = new TextField(priority);
        addBtn = new Button("+");
        q = new TextField(quantum);
        removeBtn.setScaleX(width * 0.001);
        removeBtn.setScaleY(height * 0.002);
        pName.setScaleX(width * 0.001);
        pName.setScaleY(height * 0.002);
        aTime.setScaleX(width * 0.001);
        aTime.setScaleY(height * 0.002);
        bTime.setScaleX(width * 0.001);
        bTime.setScaleY(height * 0.002);
        prty.setScaleX(width * 0.001);
        prty.setScaleY(height * 0.002);
        addBtn.setScaleX(width * 0.001);
        addBtn.setScaleY(height * 0.002);
        q.setScaleX(width * 0.01);
        q.setScaleY(height * 0.02);
        processCount++;
    }
    public void updateProcess() {
        processName = pName.getText();
        arrivalTime = aTime.getText();
        burstTime = bTime.getText();
        priority = prty.getText();
        quantum = q.getText();
    }
    
}
