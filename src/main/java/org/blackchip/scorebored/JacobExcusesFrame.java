package org.blackchip.scorebored;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import org.blackchip.scorebored.swing.Swing;
import org.blackchip.scorebored.talker.SwingTalker;

public class JacobExcusesFrame extends javax.swing.JFrame {
    
    private SwingTalker talker;
    private JacobExcuses jacobExcuses;
    private String defaultExcuse = "Select excuse to edit or enter new excuse";    
    private static String LAME_EXCUSE = "Lame excuse";
     
    public JacobExcusesFrame(JMenuBar menu, SwingTalker talker) {
        this.talker = talker;
        this.jacobExcuses = JacobExcuses.getInstance();
        initComponents();
        this.resetModel();
        excuseList.setSelectedIndex(0);
        this.setJMenuBar(menu);
    }
    
    private void resetModel() {
        DefaultListModel newModel = new DefaultListModel();
        for(String s : jacobExcuses.getList()) {
            newModel.addElement(s);
        }
        excuseList.setListData(new String[0]);
        excuseList.setModel(newModel);
    }

    private void editExcuse() {
        String oldExcuse = (String)excuseList.getSelectedValue();
        String newExcuse = editText.getText();
        if ( oldExcuse.equals(newExcuse) ) {
            return;
        }
        
        try {
            if ( ! jacobExcuses.modify(oldExcuse, newExcuse) ) {
                Swing.showNotice("You already have that excuse");
                editText.setText(oldExcuse);
                editText.requestFocusInWindow();
                return;
            }
            jacobExcuses.save();
            this.resetModel();
            excuseList.setSelectedIndex(jacobExcuses.getList()
                    .indexOf(newExcuse));
        } catch ( IOException ie ) {
            Swing.showError("Unable to save Jacob excuses", ie);
        } 
        addButton.requestFocusInWindow();
    }
    
    private void addExcuse() {
        try {
            if ( ! jacobExcuses.add(LAME_EXCUSE) ) {
                Swing.showNotice("We don't accept lame excuses here!\n" + 
                        "Edit your lame excuse before creating another one.");
            } else {
                jacobExcuses.save();
                this.resetModel();
                excuseList.setSelectedIndex(jacobExcuses.getList()
                        .indexOf(LAME_EXCUSE));
                editText.requestFocusInWindow();
            }
        } catch ( IOException ie ) {
            Swing.showError("Unable to save Jacob excuses", ie);
        }
    }
    
    private void deleteExcuse() {
        try {
            String excuse = (String)excuseList.getSelectedValue();
            int index = excuseList.getSelectedIndex();
            if ( excuse != null ) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish "
                        + "to delete this Fantastic Excuse??", "Warning", dialogButton);
                if(result == JOptionPane.YES_OPTION) {
                    if ( jacobExcuses.remove(excuse) ) {
                        jacobExcuses.save();
                    }
                    this.resetModel();
                    int size = excuseList.getModel().getSize();
                    index = ( index < size ) ? index : size - 1;
                    excuseList.setSelectedIndex(index);
                    excuseList.requestFocusInWindow();
                }
            }  
        } catch ( IOException ie ) {
            Swing.showError("Unable to save Jacob excuses", ie);
        }        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        excuseList = new javax.swing.JList();
        editText = new javax.swing.JTextField();
        deleteExcuse = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        speakerButton = new javax.swing.JButton();

        setTitle("Excuses");

        excuseList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        excuseList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        excuseList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                setExcusesText(evt);
            }
        });
        jScrollPane1.setViewportView(excuseList);

        editText.setText("Select excuse to edit or enter new excuse");
        editText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTextActionPerformed(evt);
            }
        });
        editText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                removeDefault(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                editTextFocusLost(evt);
            }
        });

        deleteExcuse.setText("Delete");
        deleteExcuse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteExcuseActionPerformed(evt);
            }
        });
        deleteExcuse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deleteExcuseKeyPressed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        addButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addButtonKeyPressed(evt);
            }
        });

        speakerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/speaker.png"))); // NOI18N
        speakerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speakerButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(deleteExcuse)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(addButton))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1)
                    .add(layout.createSequentialGroup()
                        .add(editText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(speakerButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(16, 16, 16)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(speakerButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(editText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addButton)
                    .add(deleteExcuse))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setExcusesText(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_setExcusesText
        if(excuseList.getSelectedValue() != null) {
            editText.setText(excuseList.getSelectedValue().toString());
        }
        else {
           editText.setText(defaultExcuse); 
        }      
    }//GEN-LAST:event_setExcusesText

    private void editTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTextActionPerformed
        editExcuse();
    }//GEN-LAST:event_editTextActionPerformed

    private void deleteExcuseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteExcuseActionPerformed
        deleteExcuse();
    }//GEN-LAST:event_deleteExcuseActionPerformed

    private void removeDefault(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_removeDefault
        editText.selectAll();  
    }//GEN-LAST:event_removeDefault

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addExcuse();
    }//GEN-LAST:event_addButtonActionPerformed

    private void editTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editTextFocusLost
        editExcuse();
    }//GEN-LAST:event_editTextFocusLost

    private void speakerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speakerButtonActionPerformed
        if ( !editText.getText().isEmpty() ) {
            talker.say(editText.getText());
        }
    }//GEN-LAST:event_speakerButtonActionPerformed

    private void addButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addButtonKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            addExcuse();
        }
    }//GEN-LAST:event_addButtonKeyPressed

    private void deleteExcuseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deleteExcuseKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            deleteExcuse();
        }
    }//GEN-LAST:event_deleteExcuseKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteExcuse;
    private javax.swing.JTextField editText;
    private javax.swing.JList excuseList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton speakerButton;
    // End of variables declaration//GEN-END:variables
}
