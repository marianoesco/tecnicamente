/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author USUARIO
 */
public class Reporte {

    public Container crearReporteVenta(String jasper, Map parametros) throws Exception {

        java.net.URL url = Reporte.class.getResource(jasper);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tecnicamente", "root", "");

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer visualizador = new JasperViewer(jasperPrint);
        return visualizador.getContentPane();

    }

    public Container crearReporteCompra(String jasper, Map parametros) throws Exception {

        java.net.URL url = Reporte.class.getResource(jasper);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tecnicamente", "root", "");

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer visualizador = new JasperViewer(jasperPrint);
        return visualizador.getContentPane();

    }

    public Container crearReporteServicioTecnico(String jasper, Map parametros) throws Exception {

        java.net.URL url = Reporte.class.getResource(jasper);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tecnicamente", "root", "");

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer visualizador = new JasperViewer(jasperPrint);
        return visualizador.getContentPane();

    }

    public Container crearReporteInformeCompra(String jasper, Map parametros) throws Exception {
        java.net.URL url = Reporte.class.getResource(jasper);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tecnicamente", "root", "");

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer visualizador = new JasperViewer(jasperPrint);

        return visualizador.getContentPane();

    }

    public Container crearReporteInformeVenta(String jasper, Map parametros) throws Exception {

        java.net.URL url = Reporte.class.getResource(jasper);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tecnicamente", "root", "");

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer visualizador = new JasperViewer(jasperPrint);
        return visualizador.getContentPane();

    }

    public Container crearReporteInformeServicioTecnico(String jasper, Map parametros) throws Exception {

        java.net.URL url = Reporte.class.getResource(jasper);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tecnicamente", "root", "");

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
        JasperViewer visualizador = new JasperViewer(jasperPrint);
        return visualizador.getContentPane();

    }

}
