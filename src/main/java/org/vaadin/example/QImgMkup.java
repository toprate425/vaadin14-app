package org.vaadin.example;


import com.vaadin.flow.component.Component;


/**
 * This is just an idea to get the image editing minimum internal in QWeb.
 * Have the javascript internally and just edit on (perhaps) HTML5 canvas.
 *
 * Possible ideas are in ToastUi image editor project (MIT License) at
 * https://github.com/nhn/tui.image-editor/tree/master/apps/image-editor/src/js/drawingMode
 * - freeDrawing = Editor.FREEFORM
 * - lineDrawing = Editor.LINE
 * - shape = Editor.CIRCLE
 * - text = Editor.TEXT
 *
 * Usage ...
 * File jpg = somewhere.getMyJpg();
 * String jpgStr = QUtil.jpg2Base46Str(jpg);
 * QImgMkup qimgMkup = new QImgMkup(jpgStr);
 * EditCfg cfg = new EditCfg(); // set it up
 * qimgMkup.setEditCfg(cfg);
 * Component c = qimgMkup.getImgMkupEditor();
 *
 * Vaadin side UI has all the controls ...
 * [Line][Circle][FreeLine][Color=red][Width=4][Undo] ....[Save][Cancel]
 * [                   ]
 * [   put "c" here    ]
 * [                   ]
 * [                   ]
 *
 * .. after pressing [Save] ...
 * File newJpg = QUtil.base64Str2File(qimgMkup.getEditedJpg());
 *
 */
public class QImgMkup {

    private String base64Jpg = null;

    public enum Editor {FREEFORM, CIRCLE, LINE} // TEXT???

    public static class EditCfg {
        public String color = "red";
        public int thicknessPx = 4; // px
        public Editor editor = Editor.FREEFORM; // FREEFORM = default editor
        // If (add text enabled): String font, int fontSize, String txt

    }

    public QImgMkup(String base64Jpg) {
        this.base64Jpg=base64Jpg;
    }

    public String getEditedJpg() {
        // get updated image from the script
        return "Some-Updated-Bbase64-Jpg-String";
    }

    /** User clicked a button to choose an editor or line color/size.  */
    public void setEditCfg(EditCfg cfg) {}

    /** User clicked UNDO button once. */
    public void undo() {}

    public Component getImgMkupEditor() {

        /**
         *
         *
         * "Magic" in here to get / launch / use JS libraries and place them into a
         *  JavaScript canvas (?) to Vaadin Component that is usable on the Vaadin side.
         *
         *
         */

        return null;
    }

}
