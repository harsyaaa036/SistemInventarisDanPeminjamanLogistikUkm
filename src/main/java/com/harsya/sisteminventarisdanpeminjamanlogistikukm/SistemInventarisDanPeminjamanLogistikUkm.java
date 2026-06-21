package com.harsya.sisteminventarisdanpeminjamanlogistikukm;

import com.harsya.ukm.gui.MainFrame;
import javax.swing.SwingUtilities;

public class SistemInventarisDanPeminjamanLogistikUkm {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}