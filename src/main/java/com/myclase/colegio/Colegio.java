package com.myclase.colegio;

import controlador.ControladorRegistro;
import vista.VistaColegio;

public class Colegio {

    public static void main(String[] args) {
        ControladorRegistro controlador = new ControladorRegistro();
        java.awt.EventQueue.invokeLater(() -> {
            new VistaColegio(controlador).setVisible(true);
        });
    }
}
