package org.vaadin.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.router.Route;

import java.io.*;
// import java.nio.file.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import org.apache.commons.codec.binary.Base64;

@Route("") 
public class MainView extends VerticalLayout { 

  private VerticalLayout workSpace = null;
  private QImgMkup qimgMkup = null;

  private static final int CANVAS_WIDTH = 800;
  private static final int CANVAS_HEIGHT = 400;

  private CanvasRenderingContext2D ctx;

  public MainView() {

    this.setSizeFull();
    add( new H2("Trialing Image Markup"));

    HorizontalLayout toolBar = new HorizontalLayout();
    toolBar.setAlignItems(Alignment.BASELINE);
    add(toolBar);
    // toolBar.add(new Label("Choose editor:"));

    Button launchLineButton = new Button("Line");
    launchLineButton.setIcon(VaadinIcon.LINE_V.create());
    launchLineButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SMALL);
    launchLineButton.addClickListener(clk->{
      workSpace.removeAll();
      launchLineEditor();
    });
    toolBar.add(launchLineButton);

    Button launchCircleButton = new Button("Circle");
    launchCircleButton.setIcon(VaadinIcon.CIRCLE_THIN.create());
    launchCircleButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SMALL);
    launchCircleButton.addClickListener(clk->{
      workSpace.removeAll();
      launchCircleEditor();
    });
    toolBar.add(launchCircleButton);

    Button launchFreeformButton = new Button("Freeform");
    launchFreeformButton.setIcon(VaadinIcon.PENCIL.create());
    launchFreeformButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SMALL);
    launchFreeformButton.addClickListener(clk->{
      workSpace.removeAll();
      launchFreeformEditor();
    });
    toolBar.add(launchFreeformButton);

    Button launchTextButton = new Button("Text");
    launchTextButton.setIcon(VaadinIcon.TEXT_LABEL.create());
    launchTextButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SMALL);
    launchTextButton.addClickListener(clk->{
      workSpace.removeAll();
      launchTextEditor();
    });
    toolBar.add(launchTextButton);

    Button finishEditingButton = new Button("Save");
    finishEditingButton.setIcon(VaadinIcon.CLOSE.create());
    finishEditingButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS,ButtonVariant.LUMO_SMALL);
    toolBar.add(finishEditingButton);
    finishEditingButton.addClickListener(clk->{
      finishEditing();
    });

    Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    canvas.getStyle().set("border", "1px solid");
    add(canvas);

    ctx = canvas.getContext();

    workSpace = new VerticalLayout();
    workSpace.setSizeFull();
    add(workSpace);

    // starts with just showing the image ...
    launchViewOnlyNoEditor();
  }

  private void launchViewOnlyNoEditor() {
    // workSpace.removeAll();
    try {
        String file ="src/main/webApp/resources/SampleJpgBase64Str.txt";
     
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String sampleJpgBase64Jpg = reader.readLine();
        reader.close();

        String path = System.getProperty("user.dir") + "/src/main/webApp/resources/sample.jpg";
        FileOutputStream img = new FileOutputStream(path);
        byte[] byteArray = Base64.decodeBase64(sampleJpgBase64Jpg);
        img.write(byteArray);
        
        ctx.drawImage("resources/sample.jpg", 0, 0);

        QImgMkup.EditCfg cfg = new QImgMkup.EditCfg();
        cfg.color = "N/A";
        cfg.thicknessPx = 0;
        cfg.editor = QImgMkup.Editor.VIEW_ONLY;

        qimgMkup = new QImgMkup(sampleJpgBase64Jpg);
        qimgMkup.setEditCfg(cfg);

        Component c = qimgMkup.getImgMkupEditor();
        workSpace.add(c);

    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error");
    }
    
  }

  private void launchLineEditor() {
    workSpace.removeAll();
    drawLine();
    String sampleJpgBase64Jpg = ""; // From txt file

    QImgMkup.EditCfg cfg = new QImgMkup.EditCfg();
    cfg.color = "green";
    cfg.thicknessPx = 8;
    cfg.editor = QImgMkup.Editor.LINE;

    qimgMkup = new QImgMkup(sampleJpgBase64Jpg);
    qimgMkup.setEditCfg(cfg);

    qimgMkup.getImgMkupEditor();
    Component c = qimgMkup.getImgMkupEditor();
    workSpace.add(c);
  }

  private void launchFreeformEditor() {
    workSpace.removeAll();
    String sampleJpgBase64Jpg = ""; // From txt file

    QImgMkup.EditCfg cfg = new QImgMkup.EditCfg();
    cfg.color = "green";
    cfg.thicknessPx = 8;
    cfg.editor = QImgMkup.Editor.FREEFORM;

    qimgMkup = new QImgMkup(sampleJpgBase64Jpg);
    qimgMkup.setEditCfg(cfg);

    Component c = qimgMkup.getImgMkupEditor();
    workSpace.add(c);

  }

  private void launchCircleEditor() {
    workSpace.removeAll();
    drawRandomCircle();
    String sampleJpgBase64Jpg = ""; // From txt file

    QImgMkup.EditCfg cfg = new QImgMkup.EditCfg();
    cfg.color = "green";
    cfg.thicknessPx = 8;
    cfg.editor = QImgMkup.Editor.CIRCLE;

    qimgMkup = new QImgMkup(sampleJpgBase64Jpg);
    qimgMkup.setEditCfg(cfg);

    qimgMkup.getImgMkupEditor();
    Component c = qimgMkup.getImgMkupEditor();
    workSpace.add(c);
  }

  private void launchTextEditor() {
    workSpace.removeAll();
    String sampleJpgBase64Jpg = ""; // From txt file

    QImgMkup.EditCfg cfg = new QImgMkup.EditCfg();
    cfg.color = "green";
    cfg.thicknessPx = 8;
    cfg.editor = QImgMkup.Editor.TEXT;
    // cfg.font ?

    qimgMkup = new QImgMkup(sampleJpgBase64Jpg);
    qimgMkup.setEditCfg(cfg);

    Component c = qimgMkup.getImgMkupEditor();
    workSpace.add(c);
  }

  public void finishEditing() {
    String info = "Finished Editing: ";
    if (qimgMkup==null) {
      info += "qimgMkup==null !";
    } else {
      info += " ";
      String editedImg = qimgMkup.getEditedJpg();
      if (editedImg==null) {
        info += "editedImg==null";
      } else {
        info += " Got edited image. Base64 Length="+editedImg.length();
      }
    }

    workSpace.removeAll();
    workSpace.add(new Span(info));
    qimgMkup = null;
  }

  public void drawLine() {
    ctx.save();
    ctx.moveTo(Math.random() * CANVAS_WIDTH, Math.random() * CANVAS_HEIGHT);
    ctx.lineTo(Math.random() * CANVAS_WIDTH, Math.random() * CANVAS_HEIGHT);
    ctx.stroke();
    ctx.fill();

    // addMouseListener(new MouseAdapter() {
    //   void mousePressed(MouseEvent e) {
    //       startX = e.getX();
    //       startY = e.getY();
    //   }

    //   void mouseReleased(MouseEvent e) {

    //       int endX = e.getX();
    //       int endY = e.getY();

    //       ctx.lineTo(startX, startY);
    //       ctx.moveTo(endX, endY);
    //       ctx.stroke();
    //   }
    // });
  }

  private void drawRandomCircle() {
    ctx.save();
    ctx.setLineWidth(2);
    ctx.setFillStyle(getRandomColor());
    ctx.beginPath();
    ctx.arc(Math.random() * CANVAS_WIDTH, Math.random() * CANVAS_HEIGHT,
            10 + Math.random() * 90, 0, 2 * Math.PI, false);
    ctx.closePath();
    ctx.stroke();
    ctx.fill();
    
  }

  private String getRandomColor() {
      return String.format("rgb(%s, %s, %s)", (int) (Math.random() * 256),
              (int) (Math.random() * 256), (int) (Math.random() * 256));
  }

  
}
