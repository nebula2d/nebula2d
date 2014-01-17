package com.nebula2d.editor.ui;

import com.nebula2d.editor.framework.components.KeyFrameAnimation;
import com.nebula2d.editor.util.IntKfAnimPropertyDocumentListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class KeyFrameAnimationEditDialog extends JDialog {



    private KeyFrameAnimation animation;
    public KeyFrameAnimationEditDialog(KeyFrameAnimation animation) {
        this.animation = animation;
        create();
    }

    private void create() {
        JLabel frameWidthLbl = new JLabel("Frame Width:");
        JLabel frameHeightLbl = new JLabel("Frame height:");
        JLabel startFrameLbl = new JLabel("Start Frame:");
        JLabel endFrameLbl = new JLabel("End Frame:");
        JLabel speedLbl = new JLabel("Speed:");

        JTextField frameWidthTf = new JTextField(10);

        JTextField frameHeightTf = new JTextField(10);
        JTextField startFrameTf = new JTextField(10);
        JTextField endFrameTf = new JTextField(10);
        final JTextField speedTf = new JTextField(10);



        DocumentListener floatDocumentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                if (!validateFloats(speedTf.getText())) {
                    String text = speedTf.getText();
                    speedTf.setText(text.substring(0, text.length() - 1));
                    return;
                }

                animation.setSpeed(Float.parseFloat(speedTf.getText()));
                animation.init();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        };

        frameWidthTf.getDocument().addDocumentListener(
                new IntKfAnimPropertyDocumentListener(frameWidthTf, animation,
                        IntKfAnimPropertyDocumentListener.AnimationPropertyType.FRAME_WIDTH)
        );
        frameHeightTf.getDocument().addDocumentListener(
                new IntKfAnimPropertyDocumentListener(frameHeightTf, animation,
                        IntKfAnimPropertyDocumentListener.AnimationPropertyType.FRAME_HEIGHT)
        );
        startFrameTf.getDocument().addDocumentListener(
                new IntKfAnimPropertyDocumentListener(startFrameTf, animation,
                        IntKfAnimPropertyDocumentListener.AnimationPropertyType.START_FRAME)
        );
        endFrameTf.getDocument().addDocumentListener(
                new IntKfAnimPropertyDocumentListener(endFrameTf, animation,
                        IntKfAnimPropertyDocumentListener.AnimationPropertyType.END_FRAME)
        );

        speedTf.getDocument().addDocumentListener(floatDocumentListener);

        JPanel inputPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(inputPanel);
        inputPanel.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(frameWidthLbl).
                addComponent(frameHeightLbl).addComponent(startFrameLbl).addComponent(endFrameLbl).
                addComponent(speedLbl));
        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(frameWidthTf).
                addComponent(frameHeightTf).addComponent(startFrameTf).addComponent(endFrameTf).
                addComponent(speedTf));
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(frameWidthLbl).addComponent(frameWidthTf));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(frameHeightLbl).addComponent(frameHeightTf));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(startFrameLbl).addComponent(startFrameTf));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(endFrameLbl).addComponent(endFrameTf));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(speedLbl).addComponent(speedTf));
        groupLayout.setVerticalGroup(vGroup);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        StillKeyFrameAnimationCanvas stillCanvas = new StillKeyFrameAnimationCanvas(animation);
        AnimationRenderCanvas animatedCanvas = new AnimationRenderCanvas(new AnimationRenderAdapter(animation));
        rightPanel.add(stillCanvas);
        rightPanel.add(animatedCanvas.getCanvas());
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        setSize(new Dimension(800, 600));
        validate();
        setVisible(true);
        animatedCanvas.initCamera();
    }

    public boolean validateFloats(String text) {

        try {
            Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}