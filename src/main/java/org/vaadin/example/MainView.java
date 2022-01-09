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

    HorizontalLayout toolBar = new HorizontalLayout();
    add(toolBar);
    Button launchFreeformButton = new Button("Launch Freeform Editor");
    launchFreeformButton.addClickListener(clk->{
      workSpace.removeAll();
      launchFreeformEditor();
    });
    toolBar.add(launchFreeformButton);

    Button finishEditingButton = new Button("Finish Editing");
    toolBar.add(finishEditingButton);
    finishEditingButton.addClickListener(clk->{
      finishEditing();
    });

    workSpace = new VerticalLayout();
    workSpace.setSizeFull();
    add(workSpace);

    workSpace.add(new Span("......................................................"));
    workSpace.add(new Span("..... Image editor will go here ....."));
    workSpace.add(new Span("......................................................"));

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

}
