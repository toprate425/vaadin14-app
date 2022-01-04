package org.vaadin.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
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
    add( new H2("Trialing Image Markup"));

    HorizontalLayout toolBar = new HorizontalLayout();
    toolBar.setAlignItems(Alignment.BASELINE);
    add(toolBar);
    toolBar.add(new Label("Choose editor:"));

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

    workSpace = new VerticalLayout();
    workSpace.setSizeFull();
    add(workSpace);

    // starts with just showing the image ...
    launchViewOnlyNoEditor();
  }

  private void launchViewOnlyNoEditor() {
    workSpace.removeAll();
    String sampleJpgBase64Jpg = ""; // From txt file

    QImgMkup.EditCfg cfg = new QImgMkup.EditCfg();
    cfg.color = "N/A";
    cfg.thicknessPx = 0;
    cfg.editor = QImgMkup.Editor.VIEW_ONLY;

    qimgMkup = new QImgMkup(sampleJpgBase64Jpg);
    qimgMkup.setEditCfg(cfg);

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
    String sampleJpgBase64Jpg = ""; // From txt file

    QImgMkup.EditCfg cfg = new QImgMkup.EditCfg();
    cfg.color = "green";
    cfg.thicknessPx = 8;
    cfg.editor = QImgMkup.Editor.CIRCLE;

    qimgMkup = new QImgMkup(sampleJpgBase64Jpg);
    qimgMkup.setEditCfg(cfg);

    Component c = qimgMkup.getImgMkupEditor();
    workSpace.add(c);
  }

  private void launchLineEditor() {
    workSpace.removeAll();
    String sampleJpgBase64Jpg = ""; // From txt file

    QImgMkup.EditCfg cfg = new QImgMkup.EditCfg();
    cfg.color = "green";
    cfg.thicknessPx = 8;
    cfg.editor = QImgMkup.Editor.LINE;

    qimgMkup = new QImgMkup(sampleJpgBase64Jpg);
    qimgMkup.setEditCfg(cfg);

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

}
