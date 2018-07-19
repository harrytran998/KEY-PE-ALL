/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadEx;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Timer;

/** THIS PROJECT CONTAINS
 *
 * @author MTC
 */
public class CharacterRun extends javax.swing.JFrame {

    Thread t, time;
    Timer timer;
    boolean flag = false;

    //<editor-fold defaultstate="collapsed" desc=" Init 2 thread ">
    private void initThread() {
        t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (flag) {
                        if (radMoveLeft.isSelected()) {
                            changeToLeft();
                            if (cbxAutoChangeColor.isSelected()) {
                                changeColor();
                            }
                        } else if (radMoveRight.isSelected()) {
                            changeToRight();
                            if (cbxAutoChangeColor.isSelected()) {
                                changeColor();
                            }
                        }
                    }
                    try {
//                        sleep(Integer.parseInt(txtSpeed.getText()));
                        sleep(sldSpeed.getValue());
                    } catch (InterruptedException ex) {

                    }
                }
            }

        };
        time = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (cbxTimeLocal.isSelected()) {
                        lblTimeLocal.setVisible(true);
                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("YYYY - MMMM - EEE hh:mm:ss");
                        sdf.setTimeZone(cal.getTimeZone());
                        lblTimeLocal.setText(sdf.format(cal.getTime()));
                    } else {
                        lblTimeLocal.setVisible(false);
                    }
                    try {
                        t.sleep(1000);
                    } catch (InterruptedException ex) {

                    }
                }
            }
        };
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Init Timer ">
    boolean onOffThread = true;
    private void intitTimer() {
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbxUseTimer.isSelected()) {
                    if (onOffThread) {
                        t.suspend();
                        onOffThread = !onOffThread;
                    }
                    if (radMoveLeft.isSelected()) {
                        changeToLeft();
                        if (cbxAutoChangeColor.isSelected()) {
                            changeColor();
                        }
                    } else if (radMoveRight.isSelected()) {
                        changeToRight();
                        if (cbxAutoChangeColor.isSelected()) {
                            changeColor();
                        }
                    }
                } else {
                    if (!onOffThread) {
                        t.resume();
                        onOffThread = !onOffThread;
                    }
                }
            }
        });
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" ChangeToRight & Left Function ">
    private void changeToRight() {
        String s = lblWord.getText();
//        int number = Integer.parseInt(txtNumChange.getText());
        int number = sldChangeNumber.getValue();
        String printer = s.substring(s.length() - number) + s.substring(0, s.length() - number);
        lblWord.setText(printer);
    }

    private void changeToLeft() {
        String s = lblWord.getText();
//        int number = Integer.parseInt(txtNumChange.getText());
        int number = sldChangeNumber.getValue();
        String printer = s.substring(number) + s.substring(0, number);
        lblWord.setText(printer);
    }

    private void changeColor() {

        Color c = lblWord.getForeground();
        if (c == Color.BLACK) {
            lblWord.setForeground(Color.RED);
        } else if (c == Color.RED) {
            lblWord.setForeground(Color.BLUE);
        } else if (c == Color.BLUE) {
            lblWord.setForeground(Color.GREEN);
        } else if (c == Color.GREEN) {
            lblWord.setForeground(Color.YELLOW);
        } else {
            lblWord.setForeground(Color.BLACK);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" initFont & Size ; Update Font ">
    private void initFonts() {
        String fontName[]
                = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        cbbFontName.setModel(new DefaultComboBoxModel(fontName));
        String currentFont = lblWord.getFont().getFamily();
        cbbFontName.setSelectedItem(currentFont);
    }

    private void initSize() {
        ArrayList<Integer> listSize = new ArrayList<>();
        for (int i = 8; i < 73; i += 4) {
            listSize.add(i);
        }
        cbbSize.setModel(new DefaultComboBoxModel(listSize.toArray()));
        int currentSize = lblWord.getFont().getSize();
        cbbSize.setSelectedItem(currentSize);
    }

    private void actionUpdateFont() {
        cbbFontName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFont();
            }
        });
        cbbSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFont();
            }
        });
        cbbStyle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFont();
            }
        });
    }

    private void updateFont() {
        String fontName = cbbFontName.getSelectedItem().toString();
        int fontStyle = getFontStyle();
        int fontSize = Integer.parseInt(cbbSize.getSelectedItem().toString());
        lblWord.setFont(new Font(fontName, fontStyle, fontSize));
    }

    private int getFontStyle() {
        switch (cbbStyle.getSelectedItem().toString()) {
            case "Bold":
                return 1;
            case "Italic":
                return 2;
            case "Bold and Italic":
                return 3;
            default:
                return 0;
        }
    }
    //</editor-fold>
    
    /**
     * Creates new form ControlForm
     */
    public CharacterRun() {
        initComponents();
        initFonts();
        initSize();
        initThread();
        intitTimer();
        t.start();
        time.start();
        timer.start();
        actionUpdateFont();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnlButton = new javax.swing.JPanel();
        btnRun = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();
        pnlFont = new javax.swing.JPanel();
        cbbFontName = new javax.swing.JComboBox<>();
        cbbStyle = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        pnlRunChar = new javax.swing.JPanel();
        radMoveLeft = new javax.swing.JRadioButton();
        radMoveRight = new javax.swing.JRadioButton();
        cbxAutoChangeColor = new javax.swing.JCheckBox();
        cbxUseTimer = new javax.swing.JCheckBox();
        pnlControl = new javax.swing.JPanel();
        lblChangeNumber = new javax.swing.JLabel();
        sldChangeNumber = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        sldSpeed = new javax.swing.JSlider();
        lblWord = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cbxTimeLocal = new javax.swing.JCheckBox();
        lblTimeLocal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnRun.setText("Run");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnContinue.setText("Continue");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnStop)
                .addGap(38, 38, 38)
                .addComponent(btnContinue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addGap(33, 33, 33))
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRun)
                    .addComponent(btnCancel)
                    .addComponent(btnStop)
                    .addComponent(btnContinue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbbStyle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular", "Bold", "Italic", "Bold and Italic" }));

        javax.swing.GroupLayout pnlFontLayout = new javax.swing.GroupLayout(pnlFont);
        pnlFont.setLayout(pnlFontLayout);
        pnlFontLayout.setHorizontalGroup(
            pnlFontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFontLayout.createSequentialGroup()
                .addComponent(cbbFontName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(cbbStyle, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(cbbSize, 0, 76, Short.MAX_VALUE))
        );
        pnlFontLayout.setVerticalGroup(
            pnlFontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFontLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbFontName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonGroup1.add(radMoveLeft);
        radMoveLeft.setText("Move Left");

        buttonGroup1.add(radMoveRight);
        radMoveRight.setText("Move Right");

        cbxAutoChangeColor.setText("Auto change color");

        cbxUseTimer.setText("Use Timer");

        javax.swing.GroupLayout pnlRunCharLayout = new javax.swing.GroupLayout(pnlRunChar);
        pnlRunChar.setLayout(pnlRunCharLayout);
        pnlRunCharLayout.setHorizontalGroup(
            pnlRunCharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRunCharLayout.createSequentialGroup()
                .addGroup(pnlRunCharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radMoveLeft)
                    .addComponent(radMoveRight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(pnlRunCharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxAutoChangeColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxUseTimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlRunCharLayout.setVerticalGroup(
            pnlRunCharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRunCharLayout.createSequentialGroup()
                .addGroup(pnlRunCharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radMoveLeft)
                    .addComponent(cbxAutoChangeColor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(pnlRunCharLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radMoveRight)
                    .addComponent(cbxUseTimer))
                .addContainerGap())
        );

        lblChangeNumber.setText("Number of change number");

        sldChangeNumber.setMajorTickSpacing(1);
        sldChangeNumber.setMaximum(10);
        sldChangeNumber.setMinimum(1);
        sldChangeNumber.setMinorTickSpacing(1);
        sldChangeNumber.setPaintLabels(true);
        sldChangeNumber.setPaintTicks(true);
        sldChangeNumber.setValue(1);

        jLabel1.setText("Speed");

        sldSpeed.setMajorTickSpacing(400);
        sldSpeed.setMaximum(2200);
        sldSpeed.setMinimum(200);
        sldSpeed.setMinorTickSpacing(100);
        sldSpeed.setPaintLabels(true);
        sldSpeed.setPaintTicks(true);
        sldSpeed.setValue(400);

        javax.swing.GroupLayout pnlControlLayout = new javax.swing.GroupLayout(pnlControl);
        pnlControl.setLayout(pnlControlLayout);
        pnlControlLayout.setHorizontalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlLayout.createSequentialGroup()
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlControlLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(sldSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlControlLayout.createSequentialGroup()
                        .addComponent(lblChangeNumber)
                        .addGap(35, 35, 35)
                        .addComponent(sldChangeNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlControlLayout.setVerticalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(sldSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblChangeNumber)
                    .addComponent(sldChangeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        lblWord.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblWord.setText("HELLLOWOORRLDAHIHI");

        cbxTimeLocal.setText("Time Local");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxTimeLocal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lblTimeLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxTimeLocal)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblTimeLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(lblWord, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlRunChar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblWord, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRunChar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        flag = true;
    }//GEN-LAST:event_btnRunActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        flag = false;
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        flag = true;
    }//GEN-LAST:event_btnContinueActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CharacterRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CharacterRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CharacterRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CharacterRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CharacterRun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnRun;
    private javax.swing.JButton btnStop;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbFontName;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbStyle;
    private javax.swing.JCheckBox cbxAutoChangeColor;
    private javax.swing.JCheckBox cbxTimeLocal;
    private javax.swing.JCheckBox cbxUseTimer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblChangeNumber;
    private javax.swing.JLabel lblTimeLocal;
    private javax.swing.JLabel lblWord;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlControl;
    private javax.swing.JPanel pnlFont;
    private javax.swing.JPanel pnlRunChar;
    private javax.swing.JRadioButton radMoveLeft;
    private javax.swing.JRadioButton radMoveRight;
    private javax.swing.JSlider sldChangeNumber;
    private javax.swing.JSlider sldSpeed;
    // End of variables declaration//GEN-END:variables
}
