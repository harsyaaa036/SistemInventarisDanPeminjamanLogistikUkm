package com.harsya.sisteminventarisdanpeminjamanlogistikukm;

import GUI.MainFrame;
import javax.swing.SwingUtilities;

public class SistemInventarisDanPeminjamanLogistikUkm {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
