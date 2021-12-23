package org.vaadin.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("") 
public class MainView extends VerticalLayout { 

  private VerticalLayout workSpace = null;
  private QImgMkup qimgMkup = null;

  public MainView() {
    this.setSizeFull();
    add( new H1("Hello world - Trialing Image Markup"));
    Div space = new Div();
    space.setMinHeight("1em");
    add(space);

    HorizontalLayout toolBar = new HorizontalLayout();
    add(toolBar);
    Button launchFreeformButton = new Button("Launch Freeform");
    launchFreeformButton.addClickListener(clk->{
      workSpace.removeAll();
      launchFreeformEditor();
    });
    toolBar.add(launchFreeformButton);

    Button getImgStrButton = new Button("Get Edited Content");
    toolBar.add(getImgStrButton);
    getImgStrButton.addClickListener(clk->{
      if (qimgMkup==null) {
        System.out.println("qimgMkup==null");
      } else {
        String editedImg = qimgMkup.getEditedJpg();
        if (editedImg==null) {
          System.out.println(editedImg==null);
        } else {
          System.out.println("Got edited image. Length="+editedImg.length());
        }
      }
    });

    workSpace = new VerticalLayout();
    workSpace.setSizeFull();
    add(workSpace);

    workSpace.add(new Span("..... Image editor will go here ....."));


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


}
